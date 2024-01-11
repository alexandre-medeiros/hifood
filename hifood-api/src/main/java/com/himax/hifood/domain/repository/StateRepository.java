package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.State;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CustomJpaRepository<State,Long>{

}
