package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.State;
import com.himax.hifood.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class StateRegistryService {

    private StateRepository stateRepository;

    public List<State> findAll(){
        return stateRepository.findAll();
    }

    public State find(Long id){
        return stateRepository.findOrFail(id);
    }

    public State findChild(Long id){
        return stateRepository.findChildOrFail(id);
    }
}
