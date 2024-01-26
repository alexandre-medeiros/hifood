package com.himax.hifood.api.model.address;

import com.himax.hifood.api.model.city.CitySimpleOutputDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressOutputDto {
	private String zipCode;
	private String street;
	private String number;
	private CitySimpleOutputDto city;

}
