package com.himax.hifood.api.model.address;

import com.himax.hifood.api.model.city.CityIdDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInputDto {
    @NotBlank
    private String zipCode;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotNull
    @Valid
    private CityIdDto city;

    public Long getCityId(){
        return city.getId();
    }
}
