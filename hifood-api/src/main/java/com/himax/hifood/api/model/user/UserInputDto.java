package com.himax.hifood.api.model.user;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserInputDto {
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
}
