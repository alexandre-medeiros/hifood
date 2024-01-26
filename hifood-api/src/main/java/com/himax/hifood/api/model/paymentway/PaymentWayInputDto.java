package com.himax.hifood.api.model.paymentway;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class PaymentWayInputDto {
    @NotBlank
    private String description;
}
