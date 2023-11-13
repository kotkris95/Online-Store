package onlineShop.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Catalog {
    private final Map<String, List<Product>> categories;

    public Catalog(Map<String, List<Product>> categories) {
        this.categories = categories;
    }

    //Вывод на экран все товары указанной категории
    public void showProducts(String category) {
        List<Product> items = categories.get(category);
        if (items == null) {
            System.out.println("Такой категории в магазине нет.");
            return;
        }

        int i = 1;
        for (Product product : items) {
            System.out.println("Товар " + i + ":");
            System.out.println("Название: " + product.getName());
            System.out.println("Цена: " + product.getPrice());
            System.out.println("Рейтинг: " + product.getRating());
            System.out.println("------------------------------------------");
            i++;
        }
    }

    //Вывод на экран всех категорий
    public void showCategories() {
        int cnt = 1;
        for (String category : categories.keySet()) {
            System.out.println(cnt + ". " + category);
            cnt++;
        }
    }

    // Возвращает ссылку на map категорий
    public Map<String, List<Product>> getCatalog() {
        return categories;
    }

    //Добавить товар в категорию
    public void addProduct(Product product, String category) {
        List<Product> products = categories.get(category);
        if (product == null) {
            products = new ArrayList<>();
            categories.put(category, products);
        }
        products.add(product);
    }

    //удаление товара из категории
    public void removeProduct(Product product, String category) {
        List<Product> products = categories.get(category);
        if (products == null) {
            return;
        }
        products.remove(product);
    }

    //Добавить категорию
    public void addCategory(String category) {
        categories.put(category, new ArrayList<>());
    }

    // Удалить категорию
    public void removeCategory(String cat) {
        categories.remove(cat);
    }

    // Вывод всех товаров всех категорий
    public void showCatalog() {
        for (Map.Entry<String, List<Product>> products : categories.entrySet()) {
            int i = 1;
            System.out.println(products.getKey() + ":");
            System.out.println("______________");
            for (Product product : products.getValue()) {
                System.out.println("Товар # " + i + ":");
                System.out.println("Название: " + product.getName());
                System.out.println("Цена: " + product.getPrice());
                System.out.println("Рейтинг: " + product.getRating());
                System.out.println("-------------------------------------");
                i++;
            }
            System.out.println();
        }
    }
}
