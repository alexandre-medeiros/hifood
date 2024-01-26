package com.himax.hifood.api.model.group;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GroupInputDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
