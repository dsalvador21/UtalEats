package com.icc.ratings_svc.services;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.repositories.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestTemplate restTemplate;

    public RatingService(RatingRepository ratingRepository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.ratingRepository = ratingRepository;
    }

    public Optional<List<Rating>> getRatingsByStoreId(Long storeId){
        return Optional.ofNullable(ratingRepository.findByStoreId(storeId));
    }

    public Rating addRating(Rating rating) {
        // Validar si el storeId existe en el servicio de catalog
        String catalogUrl = "http://localhost:8082/store/" + rating.getStoreId();
        Boolean storeExists = restTemplate.getForObject(catalogUrl, Boolean.class);

        if (storeExists != null && storeExists) {
            return ratingRepository.save(rating);
        } else {
            throw new IllegalArgumentException("Store ID not found in Catalog Service");
        }
    }


}
