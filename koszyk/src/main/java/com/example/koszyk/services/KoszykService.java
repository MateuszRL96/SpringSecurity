package com.example.koszyk.services;

import com.example.koszyk.entity.*;
import com.example.koszyk.exceptions.KoszykItemDontExistException;
import com.example.koszyk.exceptions.NoKoszykInfoExcepption;
import com.example.koszyk.repo.KoszykItemRepo;
import com.example.koszyk.repo.KoszykRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class KoszykService {

    private KoszykItemRepo koszykItemRepo;
    private KoszykRepo koszykRepo;
    private RestTemplate restTemplate;
    private CookieService cookieService;
    @Value("${product.service.url}")
    private String PRODUCT_SERVICE_URL;

    public ResponseEntity<?> addProduct(KoszykItemadDTO koszykItemadDTO, HttpServletRequest request, HttpServletResponse response){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Cookie> cookies = new ArrayList<>();
        if(request.getCookies() != null){
            cookies.addAll(List.of(request.getCookies()));
        }
        cookies.stream().filter(value -> value.getName().equals("koszyk"))
                .findFirst().ifPresentOrElse(value -> {
                    koszykRepo.findByUuid(value.getValue()).ifPresentOrElse(koszyk -> {
                        addProductTokoszyk(koszyk, koszykItemadDTO);
                        Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
                        if (sum == null) sum = 0L;
                        httpHeaders.add("X-Total-Count", String.valueOf(sum));
                    }, () -> {
                        Koszyk koszyk = createkoszyk();
                        response.addCookie(cookieService.generateCookie("koszyk", koszyk.getUuid()));
                        addProductTokoszyk(koszyk, koszykItemadDTO);
                        Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
                        if (sum == null) sum = 0L;
                        httpHeaders.add("X-Total-Count", String.valueOf(sum));
                    });
                }, () -> {
                    Koszyk koszyk = createkoszyk();
                    response.addCookie(cookieService.generateCookie("koszyk", koszyk.getUuid()));
                    addProductTokoszyk(koszyk, koszykItemadDTO);
                    Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
                    if (sum == null) sum = 0L;
                    httpHeaders.add("X-Total-Count", String.valueOf(sum));
                });
        return ResponseEntity.ok().headers(httpHeaders).body(new Response("Successful add item to koszyk"));
    }

    private Koszyk createkoszyk() {
        Koszyk koszyk = new Koszyk();
        koszyk.setUuid(UUID.randomUUID().toString());
        return koszykRepo.saveAndFlush(koszyk);
    }

    private void addProductTokoszyk(Koszyk koszyk, KoszykItemadDTO koszykItemAddDTO) {
        KoszykItems koszykItems = new KoszykItems();
        try {
            Product product = getProduct(koszykItemAddDTO.getProduct());
            if (product != null) {
                koszykItemRepo.findByKoszykAndProduct(koszyk, product.getUid()).ifPresentOrElse(koszykItems1 -> {
                    koszykItems1.setQuantity(koszykItems1.getQuantity() + koszykItemAddDTO.getQuantity());
                    koszykItemRepo.save(koszykItems1);
                }, () -> {
                    koszykItems.setKoszyk(koszyk);
                    koszykItems.setUuid(UUID.randomUUID().toString());
                    koszykItems.setQuantity(koszykItemAddDTO.getQuantity());
                    koszykItems.setProduct(product.getUid());
                    koszykItemRepo.save(koszykItems);
                });
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Product getProduct(String uuid) throws URISyntaxException {
        URI uri = new URIBuilder(PRODUCT_SERVICE_URL + "/getExternal").addParameter("uuid", uuid).build();
        ResponseEntity<?> response = restTemplate.getForEntity(uri, Product.class);
        if (response.getStatusCode().isError()) {
            return null;
        }
        return (Product) response.getBody();
    }

    public ResponseEntity<Response> delete(String uuid, HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Cookie> cookies = new ArrayList<>();
        if (request.getCookies() != null) {
            cookies.addAll(List.of(request.getCookies()));
        }
        cookies.stream().filter(value -> value.getName().equals("koszyk"))
                .findFirst().ifPresentOrElse(value -> {
                    koszykRepo.findByUuid(value.getValue()).ifPresentOrElse(koszyk -> {
                        deleteItem(uuid,koszyk);
                        Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
                        if (sum == null) sum = 0L;
                        httpHeaders.add("X-Total-Count", String.valueOf(sum));
                    }, () -> {
                        throw new NoKoszykInfoExcepption("koszyk doesn't exist");
                    });
                }, () -> {
                    throw new NoKoszykInfoExcepption("No koszyk info in request");
                });
        return ResponseEntity.ok().headers(httpHeaders).body(new Response("Successful delete item from koszyk"));
    }

    private void deleteItem(String uuid,Koszyk koszyk) throws KoszykItemDontExistException {
        koszykItemRepo.findKoszykItemsByProductAndKoszyk(uuid,koszyk).ifPresentOrElse(koszykItemRepo::delete,()->{
            throw new KoszykItemDontExistException("koszyk item dont exist");
        });
        Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
        if (sum==null || sum == 0) koszykRepo.delete(koszyk);
    }

    public ResponseEntity<?> getItems(HttpServletRequest request) {
        List<Cookie> cookies = new ArrayList<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (request.getCookies() != null) {
            cookies.addAll(List.of(request.getCookies()));
        }
        ListKJoszykItemDTO listkoszykItemDTO = new ListKJoszykItemDTO();
        listkoszykItemDTO.setKoszykProducts(new ArrayList<>());
        cookies.stream().filter(value -> value.getName().equals("koszyk"))
                .findFirst().ifPresentOrElse(value->{
                    Koszyk koszyk = koszykRepo.findByUuid(value.getValue()).orElseThrow(NoKoszykInfoExcepption::new);
                    Long sum = koszykItemRepo.sumKoszykItems(koszyk.getId());
                    if (sum == null) sum = 0L;
                    httpHeaders.add("X-Total-Count", String.valueOf(sum));
                    koszykItemRepo.findKoszykItemsByKoszyk(koszyk).forEach(item->{
                        try {
                            Product product = getProduct(item.getProduct());
                            assert product != null;
                            listkoszykItemDTO.getKoszykProducts().add(new KoszykItemDTO(
                                    product.getUid(),
                                    product.getName(),
                                    item.getQuantity(),
                                    product.getImageUrls()[0],
                                    product.getPrice(),
                                    product.getPrice() * item.getQuantity()));
                            listkoszykItemDTO.setSummaryPrice(listkoszykItemDTO.getSummaryPrice()+ (item.getQuantity()*product.getPrice()));
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    });
                },()->{
                    throw new NoKoszykInfoExcepption("No koszyk info in request");
                });
        if (httpHeaders.isEmpty()) httpHeaders.add("X-Total-Count", String.valueOf(0));
        return ResponseEntity.ok().headers(httpHeaders).body(listkoszykItemDTO);
    }

}

