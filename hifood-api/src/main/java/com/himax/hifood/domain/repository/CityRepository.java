package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CustomJpaRepository<City,Long>{

}
