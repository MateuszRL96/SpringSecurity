package com.example.qualifications.service;

import com.example.koszyk.entity.KoszykItems;
import com.example.qualifications.entity.*;
import com.example.qualifications.exception.*;
import com.example.qualifications.repo.DeliverRepository;
import com.example.qualifications.repo.QualificationRepository;
import com.example.qualifications.translators.KoszykItemDTOToQualificationItems;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private static QualificationRepository qualificationRepository;
    private static DeliverRepository deliverRepository;
    private static KoszykService koszykService;
    private static ItemService itemService;
    private static KoszykItemDTOToQualificationItems koszykItemDTOToItems;
    private static EmailService emailService;
    private static AuthService authService;



    private Qualification save(Qualification qualification) {
        Deliver deliver = deliverRepository.findByUuid(qualification.getDeliver().getUuid()).orElseThrow(UknowDeliverTypException::new);
        StringBuilder stringBuilder = new StringBuilder("Qualification/")
                .append(qualificationRepository.count())
                .append("/")
                .append(LocalDate.now().getMonthValue())
                .append("/")
                .append(LocalDate.now().getYear());
        qualification.setUuid(UUID.randomUUID().toString());
        qualification.setStatus(Status.PENDING);
        qualification.setQualification(stringBuilder.toString());
        qualification.setDeliver(deliver);
        return qualificationRepository.saveAndFlush(qualification);
    }
    @Transactional
    public String createQualification(Qualification qualification, HttpServletRequest request, HttpServletResponse response) {
        List<Cookie> cookies = Arrays.stream(request.getCookies()).filter(value->
                        value.getName().equals("Authorization") || value.getName().equals("refresh"))
                .toList();
        UserRegisterDTO userRegisterDTO = authService.getUserDetails(cookies);
        if (userRegisterDTO != null) {
            qualification.setClient(userRegisterDTO.getLogin());
        }
        Qualification finalQualification = save(qualification);
        AtomicReference<String> result = new AtomicReference<>();
        Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("koszyk")).findFirst().ifPresentOrElse(value -> {
            ListKoszykItemDTO koszyk = koszykService.getKoszyk(value);
            if (koszyk.getKoszykProducts().isEmpty()) throw new EmptyBasketException();
            List<QualificationItems> items = new ArrayList<>();
            koszyk.getKoszykProducts().forEach(item -> {
                QualificationItems qualificationItems = koszykItemDTOToItems.toQualificationItems(item);
                qualificationItems.setQualification(finalQualification);
                qualificationItems.setUuid(UUID.randomUUID().toString());
                items.add(itemService.save(qualificationItems));
                koszykService.removeKoszyk(value,item.getUuid());
            });
                        value.setMaxAge(0);
            response.addCookie(value);
            emailService.sendActivation(qualification.getEmail(),qualification.getUuid());
        }, () -> {
            throw new BasketDontExistException();
        });
        return result.get();
    }
    public void completeQualification(com.example.qualifications.entity.notify.Notify notify) throws QualificationDontExistException {
        qualificationRepository.findQualificationByQualification(notify.getQualification().getExtOrderId()).ifPresentOrElse(value->{
            value.setStatus(notify.getQualification().getStatus());
            qualificationRepository.save(value);
        },()->{
            throw new QualificationDontExistException();
        });
    }
    public Qualification getQualificationByUuid(String uuid) {
        return qualificationRepository.findQualificationByUuid(uuid).orElseThrow(QualificationDontExistException::new);
    }
    public List<Qualification> getQualificationsByClient(String login) {
        return qualificationRepository.findQualificationByClient(login);
    }
}