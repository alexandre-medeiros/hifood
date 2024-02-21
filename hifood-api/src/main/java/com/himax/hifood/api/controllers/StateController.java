package com.himax.hifood.api.controllers;

import com.himax.hifood.api.mapper.StateMapper;
import com.himax.hifood.api.model.state.StateInputDto;
import com.himax.hifood.api.model.state.StateOutputDto;
import com.himax.hifood.domain.model.State;
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
@RequestMapping("/states")
public class StateController {
    private StateRegistryService stateService;
    private StateMapper mapper;

    @GetMapping
    public List<StateOutputDto> findAll(){
        return mapper.toListDto(stateService.findAll());
    }

    @GetMapping("{id}")
    public StateOutputDto find(@PathVariable Long id){
        return mapper.toDto(stateService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateOutputDto create(@RequestBody @Valid StateInputDto state){
        return mapper.toDto(stateService.create(mapper.toDomain(state)));
    }

    @PutMapping("{id}")
    public StateOutputDto update(@RequestBody StateInputDto dto, @PathVariable Long id){
        State existing = stateService.find(id);
        State updated = mapper.toDomain(dto);
        return mapper.toDto( stateService.update(mapper.toUpdate(updated, existing)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        stateService.find(id);
        stateService.remove(id);
    }
}