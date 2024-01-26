package com.himax.hifood.api.model.state;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StateInputDto {
    @NotBlank
    private String name;
}
