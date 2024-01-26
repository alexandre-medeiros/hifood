package com.himax.hifood.api.model.order;

import com.himax.hifood.api.model.address.AddressInputDto;
import com.himax.hifood.api.model.paymentway.PaymentWayIdDto;
import com.himax.hifood.api.model.restaurant.RestaurantIdInputDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class OrderInputDto {
	@Valid
	@NotNull
	private RestaurantIdInputDto restaurant;
	@Valid
	@NotNull
	private AddressInputDto address;
	@Valid
	@NotNull
	private PaymentWayIdDto paymentWay;
	@Valid
	@Size(min = 1)
	@NotNull
	private List<OrderInputDto> items;
}
