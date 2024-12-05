package com.icc.ratings_svc.controllers;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.services.RatingService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;


    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatingsByStoreId(@RequestParam("storeId") Long storeId){
        List<Rating> ratings = ratingService.getRatingsByStoreId(storeId).orElse(List.of());
        return ResponseEntity.ok(ratings);
    }

    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
        Rating createdRating= ratingService.addRating(rating);
        return ResponseEntity.ok(createdRating);
    }




}
