package com.himax.hifood.api.model.kitchen;

import com.himax.hifood.core.validation.limited.Limited;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenInputDto {
    @Limited(min = 3, max = 20)
    private String name;
}
