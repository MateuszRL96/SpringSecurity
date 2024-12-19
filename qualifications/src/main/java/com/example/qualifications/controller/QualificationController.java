package com.example.qualifications.controller;


import com.example.qualifications.entity.QualificationDTO;
import com.example.qualifications.entity.Response;
import com.example.qualifications.entity.notify.Notify;
import com.example.qualifications.exception.*;
import com.example.qualifications.mediator.QualificationMediator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/qualification")
@RequiredArgsConstructor
public class QualificationController {
    private QualificationMediator qualificationMediator;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createQualification(@RequestBody QualificationDTO qualificationDTO, HttpServletRequest request, HttpServletResponse response){
        return qualificationMediator.createQualification(qualificationDTO,request,response);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/notification")
    public ResponseEntity<Response> notifyqualification(@RequestBody Notify notify, HttpServletRequest request){
        return qualificationMediator.handleNotify(notify,request);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam(required = false) String uuid, HttpServletRequest request){
        return qualificationMediator.getQualification(uuid,request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyBasketException.class)
    public Response handleValidationExceptions(EmptyBasketException ex){
        return new Response("Basket is empty");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BasketDontExistException.class)
    public Response handleValidationExceptions(BasketDontExistException ex){
        return new Response("Basket dont exist for this session");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UknowDeliverTypException.class)
    public Response handleValidationExceptions(UknowDeliverTypException ex){
        return new Response("Deliver don't exist with this uuid");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDontLoginException.class)
    public Response handleValidationExceptions(UserDontLoginException ex){
        return new Response("User is not logged in");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QualificationDontExistException.class)
    public Response handleValidationExceptions(QualificationDontExistException ex){
        return new Response("qualification don't exist");
    }
}

