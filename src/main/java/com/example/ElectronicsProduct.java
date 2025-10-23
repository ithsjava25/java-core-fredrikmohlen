package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {
    int warrantyMonths;
    BigDecimal weight;

    protected ElectronicsProduct(UUID id, String name, Category category,
                                 BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);
        if (warrantyMonths < 1) throw new IllegalArgumentException("Warranty months cannot be negative.");
        this.warrantyMonths = warrantyMonths;
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight can't be null");
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }

    @Override
    public double weight() {
        return this.weight.doubleValue();
    }

    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal cost = SHIPPING_COST_ELECTRONICS;
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0)
            cost = cost.add(SHIPPING_EXTRA_COST);

        return cost;
    }
}
