package com.himax.hifood.api.controllers;

import com.himax.hifood.api.mapper.RestaurantMapper;
import com.himax.hifood.api.model.restaurant.RestaurantInputDto;
import com.himax.hifood.api.model.restaurant.RestaurantOutputDto;
import com.himax.hifood.domain.service.RestaurantRegistryService;
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
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantRegistryService restaurantService;
    private RestaurantMapper mapper;

    @GetMapping
    public List<RestaurantOutputDto> findAll(){
        return mapper.toListDto(restaurantService.findAll());
    }

    @GetMapping("{id}")
    public RestaurantOutputDto find(@PathVariable Long id){
        return mapper.toDto(restaurantService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantOutputDto create(@RequestBody @Valid RestaurantInputDto restaurant){
        return mapper.toDto(restaurantService.create(mapper.toDomain(restaurant)));
    }

    @PutMapping("{id}")
    public RestaurantOutputDto update(@RequestBody RestaurantInputDto dto, @PathVariable Long id){
        return mapper.toDto( restaurantService.update(mapper.toDomain(dto), id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        restaurantService.remove(id);
    }
}
