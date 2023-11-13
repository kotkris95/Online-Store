package onlineShop;

import onlineShop.roles.Customer;

import java.util.HashMap;
import java.util.Map;

public class UsersBase {
    static Map<String, Customer> users = new HashMap<>();

    public static void addUser(Customer user) {
        users.put(user.getLogin(), user);
    }

    public static Customer getUser(String login) {
        return users.get(login);
    }

    public static boolean hasUser(String login) {
        return users.containsKey(login);
    }
}
