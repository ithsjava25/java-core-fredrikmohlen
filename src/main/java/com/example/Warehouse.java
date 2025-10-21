package com.example;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private static final Map<String, Warehouse> cache = new HashMap<>();
    private static final Warehouse defaultInstance = new Warehouse("default");

    private final String name;


    private Warehouse(String name) {
        this.name = name;
    }
    public static Warehouse getInstance() {
        return defaultInstance;
    }
    public static Warehouse getInstance(String name) {
         return cache.computeIfAbsent(name,Warehouse::new);
    }


    public void addProduct(Product p) {
        if(p == null) {
            throw  new IllegalArgumentException("Product cannot be null");
        }
    }

    public List<Product> getProducts() {
        //return an unmodifiable copy
        return  null;
    }
    public Product getProductById(UUID id) {
        Product Optional = null;
        return Optional;
    }

    public Product updateProductPrice(UUID id, BigDecimal price) {

    }

    public Product expiredProducts(){
        return null;
    }

    public List<Shippable> shippableProducts() {
        return null;
    }
    public Object remove(UUID id){
        return null;
    }


    public boolean isEmpty() {
    }

    public void clearProducts() {
    }
}
