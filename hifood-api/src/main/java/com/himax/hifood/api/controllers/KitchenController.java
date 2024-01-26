package com.himax.hifood.api.controllers;

import com.himax.hifood.api.mapper.KitchenMapper;
import com.himax.hifood.api.model.kitchen.KitchenInputDto;
import com.himax.hifood.api.model.kitchen.KitchenOutputDto;
import com.himax.hifood.domain.service.KitchenRegistryService;
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
@RequestMapping("/kitchens")
public class KitchenController {
    private KitchenRegistryService kitchenService;
    private KitchenMapper mapper;

    @GetMapping
    public List<KitchenOutputDto> findAll(){
        return mapper.toListDto(kitchenService.findAll());
    }

    @GetMapping("{id}")
    public KitchenOutputDto find(@PathVariable Long id){
        return mapper.toDto(kitchenService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenOutputDto create(@RequestBody @Valid KitchenInputDto kitchen){
        return mapper.toDto(kitchenService.create(mapper.toDomain(kitchen)));
    }

    @PutMapping("{id}")
    public KitchenOutputDto update(@RequestBody KitchenInputDto dto, @PathVariable Long id){
        return mapper.toDto( kitchenService.update(mapper.toDomain(dto), id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        kitchenService.remove(id);
    }
}