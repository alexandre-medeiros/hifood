package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.Kitchen;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen,Long> {

}
