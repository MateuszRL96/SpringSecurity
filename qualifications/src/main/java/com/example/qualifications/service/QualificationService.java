package com.example.qualifications.service;

import com.example.qualifications.entity.*;
import com.example.qualifications.exception.BasketDontExistException;
import com.example.qualifications.exception.EmptyBasketException;
import com.example.qualifications.exception.QualificationDontExistException;
import com.example.qualifications.repo.DeliverRepository;
import com.example.qualifications.repo.QualificationRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class QualificationService {
    private QualificationRepository qualificationRepository;
    private DeliverRepository deliverRepository;
    private KoszykService koszykService;
    private ItemService itemService;
    private final KoszykItemDTOToQualificationItems koszykItemDTOToItems;
    private EmailService emailService;
    private AuthService authService;



    private Qualification save(Qualification qualification) {
        Deliver deliver = deliverRepository.findByUuid(qualification.getDeliver().getUuid()).orElseThrow(UknowDeliverTypException::new);
        StringBuilder stringBuilder = new StringBuilder("Qualification/")
                .append(QualificationRepository.count())
                .append("/")
                .append(LocalDate.now().getMonthValue())
                .append("/")
                .append(LocalDate.now().getYear());
        Qualification.setUuid(UUID.randomUUID().toString());
        Qualification.setStatus(Status.PENDING);
        Qualification.setQualification(stringBuilder.toString());
        Qualification.setDeliver(deliver);
        return QualificationRepository.saveAndFlush(Qualification);
    }
    @Transactional
    public String createQualification(Qualification Qualification, HttpServletRequest request, HttpServletResponse response) {
        List<Cookie> cookies = Arrays.stream(request.getCookies()).filter(value->
                        value.getName().equals("Authorization") || value.getName().equals("refresh"))
                .toList();
        UserRegisterDTO userRegisterDTO = authService.getUserDetails(cookies);
        if (userRegisterDTO != null) {
            Qualification.setClient(userRegisterDTO.getLogin());
        }
        Qualification finalQualification = save(Qualification);
        AtomicReference<String> result = new AtomicReference<>();
        Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("koszyk")).findFirst().ifPresentOrElse(value -> {
            ListKoszykItemDTO koszyk = koszykService.getkoszyk(value);
            if (koszyk.getKoszykProducts().isEmpty()) throw new EmptyBasketException();
            List<QualificationItems> items = new ArrayList<>();
            koszyk.getKoszykProducts().forEach(item -> {
                QualificationItems QualificationItems = koszykItemDTOToItems.toQualificationItems(item);
                QualificationItems.setQualification(finalQualification);
                QualificationItems.setUuid(UUID.randomUUID().toString());
                items.add(itemService.save(QualificationItems));
                koszykService.removekoszyk(value,item.getUuid());
            });
                        value.setMaxAge(0);
            response.addCookie(value);
            emailService.sendActivation(Qualification.getEmail(),Qualification.getUuid());
        }, () -> {
            throw new BasketDontExistException();
        });
        return result.get();
    }
    public void completeQualification(com.example.Qualification.entity.notify.Notify notify)throws QualificationDontExistException{
        QualificationRepository.findQualificationByQualifications(notify.getQualification().getExtQualificationId()).ifPresentOrElse(value->{
            value.setStatus(notify.getQualification().getStatus());
            QualificationRepository.save(value);
        },()->{
            throw new QualificationDontExistException();
        });
    }
    public Qualification getQualificationByUuid(String uuid) {
        return QualificationRepository.findQualificationByUuid(uuid).orElseThrow(QualificationDontExistException::new);
    }
    public List<Qualification> getQualificationsByClient(String login) {
        return QualificationRepository.findQualificationByClient(login);
    }
}