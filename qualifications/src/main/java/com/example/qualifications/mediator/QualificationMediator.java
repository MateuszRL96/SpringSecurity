package com.example.qualifications.mediator;

import com.example.qualifications.entity.*;
import com.example.qualifications.exception.BadSignatureException;
import com.example.qualifications.exception.QualificationDontExistException;
import com.example.qualifications.exception.UserDontLoginException;
import com.example.qualifications.service.AuthService;
import com.example.qualifications.service.ItemService;
import com.example.qualifications.service.ProductService;
import com.example.qualifications.service.SignatureValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.cookie.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class QualificationMediator {

    private final QualificationDTOToQualification QualificationDTOToQualification;
    private final QualificationService QualificationService;
    private ItemService itemService;
    private SignatureValidator signatureValidator;
    private final QualificationItemsToItems QualificationItemsToItems;
    private final QualificationToQualificationDTO QualificationToQualificationDTO;
    private ProductService productService;
    private AuthService authService;

    public ResponseEntity<?> createQualification(QualificationDTO QualificationDTO, HttpServletRequest request, HttpServletResponse response) {
        Qualification qualification = QualificationDTOToQualification.toQualification(QualificationDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
        return ResponseEntity.status(200).headers(httpHeaders).body(QualificationService.createQualification(Qualification,request,response));
    }

    public ResponseEntity<Response> handleNotify(com.example.Qualification.entity.notify.Notify notify, HttpServletRequest request) {
        String header = request.getHeader("OpenPayu-Signature");
        try {
            signatureValidator.validate(header,notify);
            QualificationService.completeQualification(notify);
        } catch (NoSuchAlgorithmException | JsonProcessingException | BadSignatureException e) {
            return ResponseEntity.badRequest().body(new Response("Bad signature"));
        }catch (QualificationDontExistException e1){
            return ResponseEntity.badRequest().body(new Response("Qualification don't exist"));
        };
        return ResponseEntity.ok(new Response("Notification handle success"));
    }

    public ResponseEntity<?> getQualification(String uuid, HttpServletRequest request) {
        if (uuid == null || uuid.isEmpty()){
            try{
                List<Cookie> cookies = Arrays.stream(request.getCookies()).filter(value->
                                value.getName().equals("Authorization") || value.getName().equals("refresh"))
                        .toList();
                UserRegisterDTO user = authService.getUserDetails(cookies);
                if (user!=null){
                    List<QualificationDTO> QualificationDTOList = new ArrayList<>();
                    QualificationService.getQualificationsByClient(user.getLogin()).forEach(value->{
                        QualificationDTOList.add(QualificationToQualificationDTO.toQualificationDTO(value));
                    });
                    return ResponseEntity.ok(QualificationDTOList);
                }
                throw new QualificationDontExistException();
            }catch (NullPointerException e){
                throw new UserDontLoginException();
            }
        }
        Qualification Qualification = QualificationService.getQualificationByUuid(uuid);
        List<QualificationItems> itemsList = itemService.getByQualification(Qualification);
        if (itemsList.isEmpty()) throw new QualificationDontExistException();
        List<Items> itemsDTO = new ArrayList<>();
        AtomicReference<Double> summary = new AtomicReference<>(0d);
        itemsList.forEach(value->{
            Items items = QualificationItemsToItems.toItems(value);
            items.setImageUrl(productService.getProduct(value.getProduct()).getImageUrls()[0]);
            itemsDTO.add(items);
            summary.set(summary.get()+value.getPriceSummary());

        });
        QualificationDTO QualificationDTO = QualificationToQualificationDTO.toQualificationDTO(Qualification);
        summary.set(summary.get() + QualificationDTO.getDeliver().getPrice());
        QualificationDTO.setSummaryPrice(summary.get());
        QualificationDTO.setItems(itemsDTO);
        return ResponseEntity.ok(QualificationDTO);
    }
}

