package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product {
    protected ElectronicsProduct(UUID id, String name, Category category, BigDecimal price) {
        super(id, name, category, price);
    }

    @Override
    public void productDetails() {

    }
}
