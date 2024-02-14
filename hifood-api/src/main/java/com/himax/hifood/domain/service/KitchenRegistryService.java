package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.Kitchen;
import com.himax.hifood.domain.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@AllArgsConstructor
@Component
public class KitchenRegistryService {
    private KitchenRepository kitchenRepository;

    public List<Kitchen> findAll(){
        return kitchenRepository.findAll();
    }

    public Kitchen find(Long id){
        return kitchenRepository.findOrFail(id);
    }

    @Transactional
    public Kitchen create(Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public Kitchen update(Kitchen kitchen){
        return create(kitchen);
    }

    @Transactional
    public void remove(Long id){
        find(id);
        kitchenRepository.deleteOrFail(id);
    }
}
