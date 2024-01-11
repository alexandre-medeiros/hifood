package com.himax.hifood.domain.model.state;

import com.himax.hifood.domain.model.State;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class StateBuilderTest {
    private Long id;
    private String name;

    public State getState() {
        return new State(id,name);
    }
}

