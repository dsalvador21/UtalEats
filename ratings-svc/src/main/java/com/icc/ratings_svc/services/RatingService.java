package com.icc.ratings_svc.services;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public Optional<List<Rating>> getRatingsByStoreId(Long storeId){
        return Optional.ofNullable(ratingRepository.findByStoreId(storeId));
    }

    public Double getScoreByStoreId(Long storeId) {
        List<Rating> ratings = ratingRepository.findByStoreId(storeId);
        Double sum = 0.0;

        for (Rating rating : ratings) {
            sum += rating.getScore();
        }

        if (ratings.size() == 0) {
            return 0.0; // Manejo de divisiones por 0
        }

        Double average = sum / ratings.size();

        // Redondear a un decimal
        return Math.round(average * 10.0) / 10.0;
    }


    public Rating addRating(Long accountId, Long storeId, Double score, String comment) {
        Rating rating = new Rating();
        rating.setAccountId(accountId);
        rating.setStoreId(storeId);
        rating.setScore(score);
        rating.setComment(comment);
        return ratingRepository.save(rating);
    }
    
}
