package com.himax.hifood.api.model.order;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemOutputDto {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal priceUnit;
    private BigDecimal priceTotal;
    private String observation;
}
