package com.himax.hifood.api.model.state;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StateIdDto {
    @NotNull
    private Long id;
}
