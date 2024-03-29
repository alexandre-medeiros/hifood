package com.himax.hifood.api.controllers;

import com.himax.hifood.api.mapper.CityMapper;
import com.himax.hifood.api.model.city.CityInputDto;
import com.himax.hifood.api.model.city.CityOutputDto;
import com.himax.hifood.domain.model.City;
import com.himax.hifood.domain.service.CityRegistryService;
import com.himax.hifood.domain.service.StateRegistryService;
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
@RequestMapping("/cities")
public class CityController {
    private CityRegistryService cityService;
    private StateRegistryService stateService;
    private CityMapper mapper;

    @GetMapping
    public List<CityOutputDto> findAll(){
        return mapper.toListDto(cityService.findAll());
    }

    @GetMapping("{id}")
    public CityOutputDto find(@PathVariable Long id){
        return mapper.toDto(cityService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityOutputDto create(@RequestBody @Valid CityInputDto dto){
        stateService.findChild(dto.getStateId());
        return mapper.toDto(cityService.create(mapper.toDomain(dto)));
    }

    @PutMapping("{id}")
    public CityOutputDto update(@RequestBody CityInputDto dto, @PathVariable Long id){
        stateService.findChild(dto.getStateId());
        City existing = cityService.find(id);
        City updated = mapper.toDomain(dto);
        City city = mapper.toUpdate(updated, existing);
        return mapper.toDto(cityService.update(city));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        cityService.find(id);
        cityService.remove(id);
    }
}