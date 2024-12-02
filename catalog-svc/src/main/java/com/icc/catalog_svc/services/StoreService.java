package com.icc.catalog_svc.services;

import com.icc.catalog_svc.models.Store;
import com.icc.catalog_svc.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Optional<List<Store>> getStoresByCity(String city){
        return Optional.ofNullable(storeRepository.findStoresByCity(city));
    }

}
