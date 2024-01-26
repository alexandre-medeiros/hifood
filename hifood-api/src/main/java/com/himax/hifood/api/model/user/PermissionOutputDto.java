package com.himax.hifood.api.model.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionOutputDto {
	private Long id;
	private String name;
	private String description;
}
