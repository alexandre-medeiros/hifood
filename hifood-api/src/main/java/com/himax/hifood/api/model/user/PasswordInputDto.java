package com.himax.hifood.api.model.user;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PasswordInputDto {
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newPassword;
}
