package com.himax.hifood.api.model.paymentway;


import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentWayIdDto {
    @NotNull
    private Long id;
}
