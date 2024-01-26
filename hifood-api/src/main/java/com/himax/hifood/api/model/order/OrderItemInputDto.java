package com.himax.hifood.api.model.order;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
public class OrderItemInputDto {
	@NotNull
	private Long productId;
	@NotNull
	@PositiveOrZero
	private Integer quantity;
	private String observation;
}
