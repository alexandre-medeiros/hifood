package com.himax.hifood.api.model.order;

import com.himax.hifood.api.model.restaurant.RestaurantNameDto;
import com.himax.hifood.api.model.user.UserOutputDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class OrderSimpleOutputDto {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal deliveryFees;
	private BigDecimal total;
	private String status;
	private OffsetDateTime createdAt;
	private RestaurantNameDto restaurant;
	private UserOutputDto client;
}
