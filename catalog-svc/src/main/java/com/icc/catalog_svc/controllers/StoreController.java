package com.icc.catalog_svc.controllers;

import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "*")
public class StoreController {
    @Autowired
    private StoreService storeService;


    @GetMapping("")
    public ResponseEntity<List<Store>> getStores(@RequestParam("city") String city) {
        Optional<List<Store>> stores = storeService.getStoresByCity(city);

        if (stores.isPresent()) {
            return ResponseEntity.ok(stores.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}