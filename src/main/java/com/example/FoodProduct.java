package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price,
                       LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, validatePrice(price));
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date can't be null");
        }
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.expirationDate = expirationDate;
        this.weight = weight;

    }
    private static BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative. ");
        return price;
    }

    @Override
    public void productDetails() {
        System.out.println("Food: " + getName() + ", Expires: " + expirationDate);
    }
//    @Override
//    public String productDetails() {
//        return String.format("Food: %s, Expires: %s", name(), expirationDate);
//    }


    @Override
    public double weight() {
        return 0;
    }

    @Override
    public void calculateShippingCost() {
        BigDecimal cost = weight.multiply(BigDecimal.valueOf(50));
    }

}
