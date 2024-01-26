package com.himax.hifood.api.model.kitchen;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KitchenIdDto {
    @NotNull
    private Long id;
}
