package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant,Long> {

}
