package com.himax.hifood.api.model.restaurant;

import com.himax.hifood.api.model.address.AddressOutputDto;
import com.himax.hifood.api.model.kitchen.KitchenOutputDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantOutputDto {
	private Long id;
	private String name;
	private BigDecimal deliveryFees;
	private KitchenOutputDto kitchen;
	private Boolean active;
	private Boolean open;
	private AddressOutputDto address;
}
