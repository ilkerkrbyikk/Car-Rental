package com.Ilker.repository;

import com.Ilker.entitiy.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review getReviewById(Long id);
}
