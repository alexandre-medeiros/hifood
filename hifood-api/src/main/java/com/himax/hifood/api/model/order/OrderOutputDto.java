package com.himax.hifood.api.model.order;

import com.himax.hifood.api.model.address.AddressOutputDto;
import com.himax.hifood.api.model.paymentway.PaymentWayOutputDto;
import com.himax.hifood.api.model.restaurant.RestaurantNameDto;
import com.himax.hifood.api.model.user.UserOutputDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class OrderOutputDto {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal deliveryFees;
	private BigDecimal total;
	private String status;
	private OffsetDateTime createdAt;
	private OffsetDateTime confirmedAt;
	private OffsetDateTime canceledAt;
	private OffsetDateTime deliveredAt;
	private RestaurantNameDto restaurant;
	private UserOutputDto client;
	private PaymentWayOutputDto paymentWay;
	private AddressOutputDto address;
	private List<OrderItemOutputDto> items;
}
