package com.example.main.mediator;


import com.example.main.entity.dto.ProductDTO;
import com.example.main.entity.ProductEntity;
import com.example.main.entity.dto.SimpleProductDTO;
import com.example.main.service.ProductService;
import com.example.main.translator.ProductEntityToProductDTO;
import com.example.main.translator.ProductEntityToSimpleProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMediator {
    private final ProductService productService;
    private final ProductEntityToSimpleProduct productEntityToSimpleProduct;
    private final ProductEntityToProductDTO productEntityToProductDTO;

    public ResponseEntity<?> getProduct(int page, int limit, String name, String category, Float price_min, Float price_max, String data, String sort, String order) {
        List<ProductEntity> product = productService.getProduct(name, category, price_min, price_max, data, page, limit, sort, order);
        if (name != null && !name.isEmpty()) {
            try {
                name = URLDecoder.decode(name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        if (name == null || name.isEmpty() || data == null || data.isEmpty()) {
            List<SimpleProductDTO> simpleProductDTOS = new ArrayList<>();
            long totalCount = productService.countActiveProducts(name, category, price_min, price_max);
            product.forEach(value -> {
                simpleProductDTOS.add(productEntityToSimpleProduct.toSimpleProduct(value));
            });
            return ResponseEntity.ok().header("X-Total-Count", String.valueOf(totalCount)).body(simpleProductDTOS);
        }
        ProductDTO productDTO = productEntityToProductDTO.toProductDTO(product.get(0));
        return ResponseEntity.ok().body(productDTO);
    }
}

