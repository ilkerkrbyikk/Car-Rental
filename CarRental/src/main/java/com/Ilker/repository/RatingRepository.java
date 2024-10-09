package com.Ilker.repository;

import com.Ilker.entitiy.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
