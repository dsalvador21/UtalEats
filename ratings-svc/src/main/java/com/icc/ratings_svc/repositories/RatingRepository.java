package com.icc.ratings_svc.repositories;

import src.main.java.com.icc.ratings_svc.models.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByStoreId(Long storeId);

}
