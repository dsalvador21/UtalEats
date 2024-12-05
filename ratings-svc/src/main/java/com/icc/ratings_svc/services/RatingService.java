package com.icc.ratings_svc.services;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Optional<List<Rating>> getRatingsByStoreId(Long storeId){
        return Optional.ofNullable(ratingRepository.findByStoreId(storeId));
    }

    public Rating addRating(Rating rating){
        return ratingRepository.save(rating);
    }

}
