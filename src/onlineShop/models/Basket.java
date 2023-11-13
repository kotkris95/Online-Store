package onlineShop.models;


import java.util.*;

public class Basket {
    private final Map<String, Map<Product, Integer>> products;

    public Basket() {products = new HashMap<>(5);
    }

    public boolean add(Product product) {
        if (product == null) {
            return false;
        }
        Map<Product, Integer> value = new HashMap<>();
        if (products.containsKey(product.getName())) {
            value = products.get(product.getName());
            value.put(product, value.get(product) + 1);
        } else {
            value.put(product, 1);
            products.put(product.getName(), value);
        }
        return true;
    }


    public boolean remove(Product product) {
        if (product == null) {
            return false;
        }
        Map<Product, Integer> value = products.get(product.getName());
        if (value.get(product) > 1) {
            value.put(product, (value.get(product) - 1));
        } else {
            products.remove(product.getName());
        }
        return true;
    }

    public double calc() {
        double total = 0;
        for (Map.Entry<String, Map<Product, Integer>> entry : products.entrySet()) {
            String key = entry.getKey();
            Map<Product, Integer> value = entry.getValue();
            for (Map.Entry<Product, Integer> entry1 : products.get(key).entrySet()) {
                Product product = entry1.getKey();
                Integer amount = entry1.getValue();
                total += product.getPrice() * amount;
            }
        }
        return total;
    }

    public Map<String, Map<Product, Integer>> getProducts() {
        return products;
    }

    public String view() {
        StringBuilder sb = new StringBuilder("\n");
        int i = 1;
        Integer amount = null;
        for (Map.Entry<String, Map<Product, Integer>> entry : products.entrySet()) {
            String key = entry.getKey();
            Map<Product, Integer> value = entry.getValue();
            for (Map.Entry<Product, Integer> entry1 : products.get(key).entrySet()) {
                amount = entry1.getValue();
            }
            sb.append(i).append(". ")
                    .append(key)
                    .append(", количество: ")
                    .append(amount)
                    .append("\n");
            i++;
        }
        return sb.toString();
    }
}
