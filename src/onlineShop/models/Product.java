package onlineShop.models;

public class Product {
    long ID;
    private String name;
    private double price;
    private double rating;


    public Product(String nameOfProduct, double price) {
        this.name = nameOfProduct;
        this.price = price;
    }

    public Product() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void viewProduct() {
        System.out.println("1. Товар: " + name);
        System.out.println("2. Стоимость: " + price);
        System.out.println("3. Рейтинг: " + rating);
        System.out.println("------------------------------------------");
    }

    public void setId(long l) {
        ID = l;
    }
}
