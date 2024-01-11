package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.Restaurant;
import com.himax.hifood.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@Component
public class RestaurantRegistryService {
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant find(Long id) {
        return restaurantRepository.findOrFail(id);
    }

    public Restaurant save(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }
}
