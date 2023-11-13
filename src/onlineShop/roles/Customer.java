package onlineShop.roles;

import onlineShop.UsersBase;
import onlineShop.models.Basket;
import onlineShop.models.Product;

import java.io.Serializable;

public class Customer implements Serializable {
    private final String login;
    private String password;
    public Basket basket;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
        this.basket = new Basket();
        UsersBase.addUser(this);
        //dataStorage.saveTo("user.obj");
    }

    public Customer setPassword(String newPassword) {
        password = newPassword;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public boolean add(Product product) {
        return basket.add(product);
    }

    public boolean remove(Product product) {
        return basket.remove(product);
    }

    public String getLogin() {
        return login;
    }

    public Basket getBasket() {
        return basket;
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
