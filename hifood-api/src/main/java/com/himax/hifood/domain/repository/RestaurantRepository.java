package com.himax.hifood.domain.repository;

import com.himax.hifood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant,Long> {

    /*
        To solve N+1 problem.
        Join with Kitchen because all Restaurant has one Kitcken.
        How the relation between Restaurant and PaymentWay is ManyToMany,
        is naturally lazy. So it's necessary put 'fetch'.
        In relation ManyToOne is not necessary, but I use so.
        Finally, Restaurant could not have Payment, so I must use outer join.
     */
    @Query("from Restaurant r join fetch r.kitchen k left join fetch r.paymentWay")
    List<Restaurant> findAll();
}
