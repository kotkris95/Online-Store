package onlineShop;

import DataStorage.DataStorage;
import onlineShop.logger.Logger;
import onlineShop.models.*;
import onlineShop.roles.Admin;
import onlineShop.roles.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static List<Product> clothes = List.of(
            new Product("Пальто", 300),
            new Product("Жакет", 350),
            new Product("Блузка", 150)
    );
    public static List<Product> dishes = List.of(
            new Product("Тарелка", 10),
            new Product("Чашка", 7),
            new Product("Ложки", 20)
    );

    public static final String PATH_TO_PROPERTIES = "C:\\Users\\sasha\\IdeaProjects\\final-task\\resources\\shop.properties";


    public static void main(String[] args) throws Exception {
        // создали покупателей
        Customer user1 = new Customer("Kristina", "1234");
        Customer user2 = new Customer("Valeriya", "123456");

        //саздали каталог товаров
        Map<String, List<Product>> categories = new HashMap<>();
        categories.put("ОДЕЖДА", clothes);
        categories.put("ПОСУДА", dishes);
        Catalog catalog = new Catalog(categories);
        Scanner scanner = new Scanner(System.in);

        // загружаем properties
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PropertiesWrapper pw = new PropertiesWrapper(properties);
        // Посредством использования properties выбрали тип логгера
        Logger logger = Logger.Type.valueOf(pw.getLogger());
        // Посредством использования properties выбрали тип хранения данных
        DataStorage dataStorage = DataStorage.Type.valueOf(pw.getDataStorage());
        File f = new File("database.obj");


        // выбираем язык: RU ENG BY и локаль
        Locale localeRU = new Locale("ru", "RU");
        Locale localeBY = new Locale("be", "BY");
        Locale localeUS = new Locale("en", "US");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("language");
        System.out.println(resourceBundle.getString("language.selection"));
        String arg = scanner.nextLine();
        switch (arg) {
            case ("1") -> {
                Locale.setDefault(localeRU);
                resourceBundle = ResourceBundle.getBundle("language");
                System.out.println(resourceBundle.getString("language"));

            }
            case ("2") -> {
                Locale.setDefault(localeUS);
                resourceBundle = ResourceBundle.getBundle("language");
                System.out.println(resourceBundle.getString("language"));
            }
            case ("3") -> {
                Locale.setDefault(localeBY);
                resourceBundle = ResourceBundle.getBundle("language", localeBY);
                System.out.println(resourceBundle.getString("language"));
            }
            default -> {
                System.out.println(resourceBundle.getString("default.language"));
                return;
            }
        }
        startStore(catalog);
    }

    public static void startStore(Catalog catalog) {
        Scanner scanner = new Scanner(System.in);
        OnlineShop onlineShop = new OnlineShop(catalog);
        SystemManager sys = new SystemManager(onlineShop);

        System.out.println("1. Регистрация");
        System.out.println("2. Войти");
        System.out.println("3. Посмотреть каталог товаров");
        System.out.println("4. Войти как администратор");
        System.out.print("Введите выбранную цифру: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                sys.register();
                System.out.println("Зарегетрируйтесь для входа.");
                Customer currCustomer = sys.register();
                onlineShop.shopping(currCustomer);
                break;
            case 2:
                currCustomer = sys.login();
                onlineShop.shopping(currCustomer);
                break;
            case 3:
                onlineShop.getCatalog().showCatalog();
                startStore(catalog);
                return;
            case 4:
                Admin currAdmin = sys.loginAdmin();
                if (currAdmin == null) {
                    startStore(catalog);
                    return;
                }
                while (true) {
                    System.out.println("1. Управление магазином");
                    System.out.println("2. Добавить нового администратора");
                    System.out.println("3. Сохранить изменения и выйти");
                    System.out.println("Введите выбранную цифру: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    if (choice == 1)
                        onlineShop.startAdmin(currAdmin);
                    else if (choice == 2)
                        sys.registerAdmin();
                    else if (choice == 3) {
                        System.out.println("Изменения успешно сохранены ^.^");
                        return;
                    } else {
                        System.out.println("Введены некорректные данные\n");
                        startStore(catalog);
                        return;
                    }
                }
            default:
                System.out.println("Введены некорректные данные\n");
                startStore(catalog);
                return;
        }
        System.out.println("Спасибо за покупки!");
    }

}
