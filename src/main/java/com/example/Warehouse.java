package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static final Map<String, Warehouse> cache = new HashMap<>();
    private static final Warehouse defaultInstance = new Warehouse("default");
    private final String name;
    private final Map<UUID, Product> products = new HashMap<>();
    private final Set<UUID> changedProducts = new HashSet<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        return defaultInstance;
    }

    public static Warehouse getInstance(String name) {
        return cache.computeIfAbsent(name, Warehouse::new);
    }


    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (products.containsKey(product.uuid()))
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        products.put(product.uuid(), product);
    }

    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void updateProductPrice(UUID id, BigDecimal price) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        product.setPrice(price);
        changedProducts.add(id);
    }

    public List<Product> getChangedProducts() {
        List<Product> result = new ArrayList<>();
        for (UUID uuid : changedProducts) {
            Product product = products.get(uuid);
            if (product != null) result.add(product);
        }
        return result;
    }

    public List<Perishable> expiredProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p)
                .filter(Perishable::isExpired)
                .collect(Collectors.toList());
    }

    public List<Shippable> shippableProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toList());
    }

    public void remove(UUID id) {
        products.remove(id);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clearProducts() {
        products.clear();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        if (products.isEmpty()) {
            return Collections.emptyMap();
        } else
            return products.values().stream()
                    .collect(Collectors.groupingBy(Product::category));

    }
}
