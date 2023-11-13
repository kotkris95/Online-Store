package onlineShop;

import onlineShop.roles.Admin;

import java.util.HashMap;
import java.util.Map;

public class AdminsBase {
    public static Map<String, Admin> admins = new HashMap<>();

    public static void addAdmin(Admin admin) {
        admins.put(admin.getLogin(), admin);
    }

    public Admin getAdmin(String login) {
        return admins.get(login);
    }

    public boolean hasAdmin(String login) {
        return admins.containsKey(login);
    }
}
