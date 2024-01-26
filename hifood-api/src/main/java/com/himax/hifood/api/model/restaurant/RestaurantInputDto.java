package com.himax.hifood.api.model.restaurant;

import com.himax.hifood.api.model.kitchen.KitchenIdDto;
import com.himax.hifood.domain.model.Address;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInputDto {
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;
    @Positive
    private BigDecimal deliveryFees;
    @NotNull
    private KitchenIdDto kitchen;
    @NotNull
    private Address address;
}
