package com.icc.catalog_svc.controllers;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<Product> getProductsByStore(@RequestParam("storeId") Long storeId) {
        System.out.println("Hola");
        return productService.getProductsByStore(storeId).orElse(List.of());
    }

}
