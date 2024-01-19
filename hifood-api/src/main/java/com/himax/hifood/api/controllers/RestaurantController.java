package com.himax.hifood.api.controllers;

import com.himax.hifood.domain.model.Restaurant;
import com.himax.hifood.domain.service.RestaurantRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantRegistryService restaurantService;

    @GetMapping
    public List<Restaurant> findAll(){
        return restaurantService.findAll();
    }

    @GetMapping("{id}")
    public Restaurant find(@PathVariable Long id){
        return restaurantService.find(id);
    }

}
