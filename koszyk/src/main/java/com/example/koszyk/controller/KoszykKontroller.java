package com.example.koszyk.controller;


import com.example.koszyk.entity.KoszykItemadDTO;
import com.example.koszyk.entity.Response;
import com.example.koszyk.exceptions.KoszykItemDontExistException;
import com.example.koszyk.exceptions.NoKoszykInfoExcepption;
import com.example.koszyk.services.KoszykService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/koszyk")
@RequiredArgsConstructor
public class KoszykKontroller {

    private final KoszykService koszykService;

    public KoszykKontroller(KoszykService koszykService) {
        this.koszykService = koszykService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(
            @RequestBody KoszykItemadDTO koszykItemadDTO,
            HttpServletRequest request,
            HttpServletResponse response){
        return koszykService.addProduct(koszykItemadDTO, request, response);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestParam String uuid, HttpServletRequest request){
        return koszykService.delete(uuid,request);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getItems(HttpServletRequest request){
        return koszykService.getItems(request);
    }

    @ExceptionHandler(KoszykItemDontExistException.class)
    private ResponseEntity<Response> handleException(KoszykItemDontExistException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response(exception.getMessage()));
    }

    @ExceptionHandler(NoKoszykInfoExcepption.class)
    private ResponseEntity<Response> handleException(NoKoszykInfoExcepption exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response(exception.getMessage()));
    }

}
