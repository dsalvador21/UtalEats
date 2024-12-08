package com.icc.catalog_svc.controllers;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getProductsByStore(@RequestParam("storeId") Long storeId) {
        List<Product> products = productService.getProductsByStore(storeId).orElse(List.of());
        return ResponseEntity.ok(products);
    }

}
