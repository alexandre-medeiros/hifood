package com.himax.hifood.api.controllers;

import com.himax.hifood.domain.model.City;
import com.himax.hifood.domain.service.CityRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {
    private CityRegistryService cityService;

    @GetMapping
    public List<City> findAll(){
        return cityService.findAll();
    }

    @GetMapping("{id}")
    public City find(@PathVariable Long id){
        return cityService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
        return cityService.create(city);
    }

    @PutMapping("{id}")
    public City update(@RequestBody City city, @PathVariable Long id){
        return cityService.update(city,id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        cityService.remove(id);
    }
}
