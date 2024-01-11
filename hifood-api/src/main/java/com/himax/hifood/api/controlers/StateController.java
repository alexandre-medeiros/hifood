package com.himax.hifood.api.controlers;

import com.himax.hifood.domain.model.State;
import com.himax.hifood.domain.service.StateRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {
    private StateRegistryService stateService;

    @GetMapping
    public List<State> findAll(){
        return stateService.findAll();
    }

    @GetMapping("{id}")
    public State find(@PathVariable Long id){
        return stateService.find(id);
    }

}
