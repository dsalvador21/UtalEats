package com.icc.ratings_svc.repositories;

import com.icc.ratings_svc.models.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByStoreId(Long storeId);
}
