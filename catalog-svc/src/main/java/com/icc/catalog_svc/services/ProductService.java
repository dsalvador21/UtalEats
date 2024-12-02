package com.icc.catalog_svc.services;

import com.icc.catalog_svc.models.Product;
import com.icc.catalog_svc.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<List<Product>> getProductsByStore(Long storeId) {
        return Optional.ofNullable(productRepository.findByStoreId(storeId));
    }
}
