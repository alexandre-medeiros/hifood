package com.himax.hifood.api.model.product;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductInputDto {
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	@PositiveOrZero
	private BigDecimal price;
	@NotNull
	private Boolean active;
}
