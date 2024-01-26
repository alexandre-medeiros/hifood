package com.himax.hifood.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductPhotoOutputDto {
	private String fileName;
	private String description;
	private String contentType;
	private Long size;
}
