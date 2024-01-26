package com.himax.hifood.api.model.city;

import com.himax.hifood.api.model.state.StateIdDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityInputDto {
    @NotBlank
    private String name;
    @Valid
    @NotNull
    private StateIdDto state;
}
