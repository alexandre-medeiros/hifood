package com.himax.hifood.api.model.city;

import com.himax.hifood.api.model.state.StateIdDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CityInputDto {

    @Size(min = 3, max = 80)
    private String name;
    @Valid
    @NotNull
    private StateIdDto state;
}
