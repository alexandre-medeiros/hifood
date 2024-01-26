package com.himax.hifood.api.model.user;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserAndPasswordInputDto {
	@NotBlank
	private String senha;
}
