package com.example;

import java.math.BigDecimal;

public interface Shippable {
    public static final BigDecimal SHIPPING_COST_ELECTRONICS = BigDecimal.valueOf(79);
    public static final BigDecimal SHIPPING_EXTRA_COST = BigDecimal.valueOf(49);
    public static final BigDecimal SHIPPING_COST_FOODPRODUCT = BigDecimal.valueOf(50);


    double weight();

    BigDecimal calculateShippingCost();
}
