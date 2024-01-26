package com.himax.hifood.api.model.restaurant;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RestaurantIdInputDto {
	@NotNull
	private Long id;
}
