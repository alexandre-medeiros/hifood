package com.himax.hifood.api.model.product;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductOutputDto {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean active;
}
