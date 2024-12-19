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
import org.springframework.web.client.HttpClientErrorException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class QualificationService {
    private QualificationRepository qualificationRepository;
    private static DeliverRepository deliverRepository;
    private static KoszykService koszykService;
    private static ItemService itemService;
    private static KoszykItemDTOToQualificationItems koszykItemDTOToItems;
    private static EmailService emailService;
    private static AuthService authService;



    private static Qualification save(Qualification qualification) {
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
        return QualificationRepository.saveAndFlush(qualification);
    }
    @Transactional
    public static String createQualification(Qualification Qualification, HttpServletRequest request, HttpServletResponse response) {
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
            ListKoszykItemDTO koszyk = koszykService.getKoszyk(value);
            if (koszyk.getKoszykProducts().isEmpty()) throw new EmptyBasketException();
            List<QualificationItems> items = new ArrayList<>();
            koszyk.getKoszykProducts().forEach(item -> {
                KoszykItems qualificationItems = koszykItemDTOToItems.toQualificationItems(item);
                QualificationItems.setQualification(finalQualification);
                QualificationItems.setUuid(UUID.randomUUID().toString());
                items.add(itemService.save(qualificationItems));
                koszykService.removeKoszyk(value,item.getUuid());
            });
                        value.setMaxAge(0);
            response.addCookie(value);
            emailService.sendActivation(Qualification.getEmail(),Qualification.getUuid());
        }, () -> {
            throw new BasketDontExistException();
        });
        return result.get();
    }
    public static void completeQualification(com.example.qualifications.entity.notify.Notify notify)throws QualificationDontExistException{
        QualificationRepository.findQualificationByQualifications(notify.getQualification().getExtQualificationId()).ifPresentOrElse(value->{
            Qualification.setStatus(notify.getQualification().getStatus());
            QualificationRepository.save(value);
        },()->{
            throw new QualificationDontExistException();
        });
    }
    public static Qualification getQualificationByUuid(String uuid) {
        return QualificationRepository.findQualificationByUuid(uuid).orElseThrow(QualificationDontExistException::new);
    }
    public static List<Qualification> getQualificationsByClient(String login) {
        return QualificationRepository.findQualificationByClient(login);
    }
}