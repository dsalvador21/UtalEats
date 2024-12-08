package com.icc.ratings_svc.controllers;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.services.RatingService;
import com.icc.ratings_svc.domain.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<List<Rating>> getRatingsByStoreId(@RequestParam("storeId") Long storeId){
        List<Rating> ratings = ratingService.getRatingsByStoreId(storeId).orElse(List.of());
        return ResponseEntity.ok(ratings);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/score")
    public ResponseEntity<Double> getScoreByStoreId(@RequestParam("storeId") Long storeId){
        return ResponseEntity.ok(ratingService.getScoreByStoreId(storeId));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO ratingDTO){
        System.out.println("holaholahola");
        Rating savedRating = ratingService.addRating(
                ratingDTO.getAccountId(),
                ratingDTO.getStoreId(),
                ratingDTO.getScore(),
                ratingDTO.getComment());

        return ResponseEntity.ok(savedRating);
    }

}
