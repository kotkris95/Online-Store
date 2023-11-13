package onlineShop;

import onlineShop.AdminsBase;
import onlineShop.UsersBase;
import onlineShop.models.OnlineShop;
import onlineShop.roles.Admin;
import onlineShop.roles.Customer;

import java.util.*;

public class SystemManager {
    OnlineShop onlineShop;

    public SystemManager(OnlineShop onlineShop) {
        this.onlineShop = onlineShop;
    }


    public Customer register() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        if (AdminsBase.admins.isEmpty()) {
            Admin admin = registerAdmin();
            onlineShop.startAdmin(admin);
        } else {
            System.out.println("Введите логин:");
            String login = scanner.nextLine();
            if (UsersBase.hasUser(login)) {
                System.out.println("Пользователь с таким логином уже существует");
                return null;
            }
            System.out.println("Введите пароль:");
            String password = scanner.nextLine();
            int verificationCode = generateVerificationCode();
            System.out.println(verificationCode);
            System.out.println("Введите указанный код для успешной регистрации: " + verificationCode);
            int code = scanner.nextInt();
            if (verificationCode == code) {
                System.out.println("Новый пользователь успешно создан");
                customer = new Customer(login, password);
            } else return null;
        }
        return customer;
    }

    public static int generateVerificationCode() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    public Customer login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        if (!UsersBase.hasUser(login)) {
            System.out.println("Пользователя с таким логином не существует");
        }
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        Customer user = UsersBase.getUser(login);
        if (!user.matchPassword(password)) {
            System.out.println("Неверный пароль!");
        } else {
            System.out.println("Вы успешно вошли");
        }
        return user;
    }

    public Admin loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        AdminsBase adminsBase = new AdminsBase();
        if (!adminsBase.hasAdmin(login)) {
            System.out.println("Администратора с таким логином не существует");
            return null;
        }
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        Admin admin = adminsBase.getAdmin(login);
        if (!admin.matchPassword(password)) {
            System.out.println("Неверный пароль!");
        } else {
            System.out.println("Вы успешно вошли");
        }
        return admin;
    }

    public Admin registerAdmin() {
        Scanner scanner = new Scanner(System.in);
        Admin admin;
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        AdminsBase adminsBase = new AdminsBase();
        if (adminsBase.hasAdmin(login)) {
            System.out.println("Администратор с таким логином уже существует");
            return null;
        }
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        System.out.println("Новый администартор успешно создан");
        admin = new Admin(login, password);
        AdminsBase.addAdmin(admin);
        return admin;
    }
}
