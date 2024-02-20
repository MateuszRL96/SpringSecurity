package com.example.main.mediator;


import com.example.main.entity.Response;
import com.example.main.entity.dto.ProductDTO;
import com.example.main.entity.ProductEntity;
import com.example.main.entity.dto.ProductFormDTO;
import com.example.main.entity.dto.SimpleProductDTO;
import com.example.main.exceptions.CategoryDontExistException;
import com.example.main.service.CategoryService;
import com.example.main.service.ProductService;
import com.example.main.translator.ProductEntityToProductDTO;
import com.example.main.translator.ProductEntityToSimpleProduct;
import com.example.main.translator.ProductFormToProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final CategoryService categoryService;
    private final ProductEntityToSimpleProduct productEntityToSimpleProduct;
    private final ProductEntityToProductDTO productEntityToProductDTO;
    private final ProductFormToProductEntity formToProductEntity;
    @Value("${file-service.url}")
    private String FILE_SERVICE;

    public ResponseEntity<?> getProduct(int page, int limit, String name, String category, Float price_min, Float price_max, String data, String sort, String order) {
        if (name != null && !name.isEmpty()) {
            try {
                name = URLDecoder.decode(name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        List<ProductEntity> product = productService.getProduct(name, category, price_min, price_max, data, page, limit, sort, order);
        product.forEach(value->{
            for (int i = 0; i < value.getImageUrls().length; i++){
                value.getImageUrls()[i] = FILE_SERVICE+"?uuid="+value.getImageUrls()[i];
            }
        });

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

    public ResponseEntity<Response> saveProduct(ProductFormDTO productFormDTO) {
        try{
            ProductEntity product = formToProductEntity.toProductEntity(productFormDTO);
            categoryService.findCategoryByShortID(product.getCategory().getShortId()).ifPresentOrElse(product::setCategory,()->{
                throw new CategoryDontExistException();
            });
            productService.createProduct(product);
            return ResponseEntity.ok(new Response("Successful created a product"));
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(new Response("Can't create product category don't exist"));
        }

    }

    public ResponseEntity<Response> deleteProduct(String uuid) {
        try{
            productService.delete(uuid);
            return ResponseEntity.ok(new Response("Successful delete product"));
        }catch (RuntimeException e){
            return ResponseEntity.status(400).body(new Response("Product dont exist"));
        }

    }

}
