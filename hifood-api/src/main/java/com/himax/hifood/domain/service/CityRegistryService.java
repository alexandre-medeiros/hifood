package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.City;
import com.himax.hifood.domain.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CityRegistryService {

    private CityRepository cityRepository;
    private StateRegistryService stateService;

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public City find(Long id){
        return cityRepository.findOrFail(id);
    }

    @Transactional
    public City create(City city){
        stateService.findChild(city.getState().getId());
        return cityRepository.save(city);
    }

    @Transactional
    public City update(City city, Long id){
        find(id);
        stateService.findChild(city.getState().getId());
        return create(city);
    }
    @Transactional
    public void remove(Long id){
        find(id);
        cityRepository.deleteOrFail(id);
    }
}
