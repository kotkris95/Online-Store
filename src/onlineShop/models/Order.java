package onlineShop.models;


import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private LocalDateTime date;
    private int ID;
    private Basket basket;
    private double totalPrice;
    private String orderStatus;

    public Order(Basket basket) {
        this.date = LocalDateTime.now();
        this.basket = basket;
        setTotalPrice();
        updateStatus("� �������");
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
    }

    public void cancelOrder() {
        orderStatus = "Canceled";
    }

    public void setTotalPrice() {
        totalPrice = basket.calc();
    }

    public int getID() {
        return ID;
    }

    public void viewOrder() {
        System.out.println("����� # " + ID);
        System.out.println("���� � ����� ������: " + date);
        System.out.println("������: " + orderStatus);
        System.out.println("���������� ������: ");
        basket.view();
        System.out.println("������ ��������� �������: " + totalPrice + "BYN");
    }

    public String getStatus() {
        return orderStatus;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    public Map<String, Map<Product, Integer>> getProducts() {
        return basket.getProducts();
    }

    public double getPrice() {
        return totalPrice;
    }

    public void setOrderNum(int ID) {
        this.ID = ID;
    }

    public Order createOrder() {
        Order order = new Order(basket);
        return order;
    }
}
