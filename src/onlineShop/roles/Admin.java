package onlineShop.roles;

import onlineShop.models.OnlineShop;
import onlineShop.models.Order;
import onlineShop.models.Product;

import java.util.List;
import java.util.Scanner;

public class Admin {
    private final String login;
    private String password;
    private OnlineShop onlineShop;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
        changePassword();
        //dataStorage.saveTo("admin.obj");
    }

    public String getLogin() {
        return login;
    }

    public void changePassword(){
        System.out.println(login +  " - администратор. Для дальнейшей безопасной работы " +
                " введите новый пароль: ");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();
        password = newPassword;
        System.out.println("Пароль изменен.");
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void addProduct(Product product, String category) {
        onlineShop.getCatalog().addProduct(product, category);
    }

    public void removeProduct(Product product, String category) {
        onlineShop.getCatalog().removeProduct(product, category);
    }
    public void updateProduct(Product product) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String s;
        double d;
        System.out.println("Что Вы хотите изменить?\n");
        product.viewProduct();
        System.out.println("Введите выбранную цифру или \"0\" для завершения работы: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        if (choice == 0) {
            scanner.close();
            return;
        }
        switch (choice) {
            case 1 -> {
                System.out.println("Введите новое название товара: ");
                s = scanner.nextLine();
                product.setName(s);
            }
            case 2 -> {
                System.out.println("Введите новую стоимость: ");
                d = scanner.nextDouble();
                product.setPrice(d);
            }
            case 3 -> {
                System.out.println("Введите новый рейтинг: ");
                choice = scanner.nextInt();
                product.setRating(choice);
            }
            default -> {
                System.out.println("Введены некорректные данные");
                updateProduct(product);
            }
        }
    }
    public void addCategory(String cat){
        onlineShop.getCatalog().addCategory(cat);
    }
    public void removeCategory(String cat){
        onlineShop.getCatalog().removeCategory(cat);
    }
    public void viewOrders() {
        List<Order> orders = onlineShop.getOrderQueue();
        for (Order order : orders){
            order.viewOrder();
            System.out.println("___________________________________\n");
        }
    }

    @Override
    public String toString() {
        return "Администратор{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
