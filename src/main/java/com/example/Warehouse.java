package com.example;

import java.math.BigDecimal;
import java.util.*;

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
         return cache.computeIfAbsent(name,Warehouse::new);
    }


    public void addProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }else
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

    public List<Perishable> expiredProducts(){
      //  List<Product> result = new ArrayList<>();
        //for (Product product : products.values()) {
            //if(product)  result.add(product);

        return null;
   }

    public List<Shippable> shippableProducts() {
        return null;
    }

    public Object remove(UUID id){
        return null;
    }


    public boolean isEmpty() {
    return false;
    }

    public void clearProducts() {
    }

    public Map<Category, Product> getProductsGroupedByCategories() {
    return null;
    }
}
