package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant,Long> {

    /*
        To solve N+1 problem.
     */
    @Query("from Restaurant r " +
           "inner join fetch r.kitchen k " +
           "left join fetch r.address.city c " +
           "left join fetch c.state " +
           "order by r.id")
    List<Restaurant> findAll();
}
