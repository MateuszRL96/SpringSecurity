package com.example.qualifications.mediator;

import com.example.qualifications.entity.*;
import com.example.qualifications.exception.BadSignatureException;
import com.example.qualifications.exception.QualificationDontExistException;
import com.example.qualifications.exception.UserDontLoginException;
import com.example.qualifications.service.*;
import com.example.qualifications.translators.QualificationDTOToQualification;
import com.example.qualifications.translators.QualificationItemsToItems;
import com.example.qualifications.translators.QualificationToQualificationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

    private QualificationDTOToQualification qualificationDTOToQualification;
    private QualificationService qualificationService;
    private ItemService itemService;
    private QualificationItemsToItems qualificationItemsToItems;
    private QualificationToQualificationDTO qualificationToQualificationDTO;
    private ProductService productService;
    private AuthService authService;

    public ResponseEntity<?> createQualification(QualificationDTO qualificationDTO, HttpServletRequest request, HttpServletResponse response) {
        Qualification qualification = qualificationDTOToQualification.toQualification(qualificationDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/json");
        return ResponseEntity.status(200).headers(httpHeaders).body(qualificationService.createQualification(qualification, request, response));    }

    public ResponseEntity<Response> handleNotify(com.example.qualifications.entity.notify.Notify notify, HttpServletRequest request) {
        String header = request.getHeader("OpenPayu-Signature");
        try {
            qualificationService.completeQualification(notify);
        } catch (BadSignatureException e) {
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
                    List<QualificationDTO> qualificationDTOList = new ArrayList<>();
                    qualificationService.getQualificationsByClient(user.getLogin()).forEach(value->{
                        qualificationDTOList.add(qualificationToQualificationDTO.toQualificationDTO(value));
                    });
                    return ResponseEntity.ok(qualificationDTOList);
                }
                throw new QualificationDontExistException();
            }catch (NullPointerException e){
                throw new UserDontLoginException();
            }
        }
        Qualification qualification = qualificationService.getQualificationByUuid(uuid);
        List<QualificationItems> itemsList = itemService.getByQualification(qualification);
        if (itemsList.isEmpty()) throw new QualificationDontExistException();
        List<Items> itemsDTO = new ArrayList<>();
        AtomicReference<Double> summary = new AtomicReference<>(0d);
        itemsList.forEach(value->{
            Items items = qualificationItemsToItems.toItems(value.getQualification());
            items.setImageUrls(productService.getProduct(value.getProduct()).getImageUrls()[0]);
            itemsDTO.add(items);
            summary.set(summary.get()+value.getPriceSummary());

        });
        QualificationDTO qualificationDTO = qualificationToQualificationDTO.toQualificationDTO(qualification);
        summary.set(summary.get() + QualificationDTO.getDeliver().getPrice());
        qualificationDTO.setSummaryPrice(summary.get());
        qualificationDTO.setItems(itemsDTO);
        return ResponseEntity.ok(qualificationDTO);
    }
}

