package com.himax.hifood.api.model.restaurant;

import com.himax.hifood.api.model.kitchen.KitchenOutputDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantSimpleOutputDto {
	private Long id;
	private String name;
	private BigDecimal deliveryFees;
	private KitchenOutputDto kitchen;
}
