package com.example;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Category {
    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
        }

    public static Category of(String name) {
        if (name.equals(null)) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String normalized = capitalize(name.trim());
        return CACHE.computeIfAbsent(normalized, Category::new);

    }
    private static String capitalize(String input) {
        if (input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category other = (Category) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}


