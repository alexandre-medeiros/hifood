package com.himax.hifood.api.model.kitchen;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenInputDto {
    @NotBlank
    private String name;
}
