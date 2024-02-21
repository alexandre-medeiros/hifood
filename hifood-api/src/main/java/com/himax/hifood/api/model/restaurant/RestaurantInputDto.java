package com.himax.hifood.api.model.restaurant;

import com.himax.hifood.api.model.address.AddressInputDto;
import com.himax.hifood.api.model.kitchen.KitchenIdDto;
import com.himax.hifood.core.validation.limited.Limited;
import com.himax.hifood.domain.model.Address;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInputDto {
    @Limited(min = 5, max = 20)
    private String name;
    @NotNull
    @Positive
    private BigDecimal deliveryFees;
    @NotNull
    @Valid
    private KitchenIdDto kitchen;
    @NotNull
    private AddressInputDto address;

    public Long getKitchenId(){
        return kitchen.getId();
    }

    public Long getCityId(){ return address.getCityId(); }
}
