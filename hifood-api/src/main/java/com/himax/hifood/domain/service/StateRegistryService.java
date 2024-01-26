package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.State;
import com.himax.hifood.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public State create(State state){
        return stateRepository.save(state);
    }

    @Transactional
    public State update(State state, Long id){
        find(id);
        return create(state);
    }
    @Transactional
    public void remove(Long id){
        find(id);
        stateRepository.deleteOrFail(id);
    }
}
