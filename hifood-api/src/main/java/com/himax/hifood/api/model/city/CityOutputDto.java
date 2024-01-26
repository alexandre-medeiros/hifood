package com.himax.hifood.api.model.city;

import com.himax.hifood.api.model.state.StateOutputDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityOutputDto {
	private Long id;
	private String name;
	private StateOutputDto state;
}
