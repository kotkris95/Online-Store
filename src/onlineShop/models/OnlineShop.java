package onlineShop.models;


import onlineShop.roles.Admin;
import onlineShop.roles.Customer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OnlineShop {
    private List<Order> orderQueue;
    private final Catalog catalog;
    Scanner scanner = new Scanner(System.in);

    public OnlineShop(Catalog catalog) {
        this.catalog = catalog;
    }

    public void addOrder(Order order) {
        orderQueue.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orderQueue = orders;
    }

    public List<Order> getOrderQueue() {
        return orderQueue;
    }

    public Catalog getCatalog() {
        return catalog;
    }


    // Позволяет покупателю совершать покупку и добавлять товары в корзину
    public void shopping(Customer curr) {
        String categoryName = "";
        System.out.println("Выберите категорию товаров или \"0\" для завершения работы: ");
        catalog.showCategories();
        int cat = scanner.nextInt();
        System.out.println();
        if (cat == 0) {
            return;
        }
        int cnt = 1;
        for (Map.Entry<String, List<Product>> products : catalog.getCatalog().entrySet()) {
            if (cnt == cat) {
                categoryName = products.getKey();
                catalog.showProducts(products.getKey());
                break;
            }
            cnt++;
        }
        if (cnt != cat) {
            System.out.println("Введены неверные данные!");
            shopping(curr);
            return;
        }

        while (true) {
            System.out.println("Введите 0, чтобы вернуться к категориям.");
            System.out.println("Введите -1, чтобы перейти к своей корзине. ");
            System.out.println("Введите номер товара, чтобы добавить его в корзину: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice == 0) {
                shopping(curr);
                return;
            } else if (choice == -1) {
                System.out.println(curr.getBasket().view());
                System.out.println("Стоимость товаров = " + curr.getBasket().calc() + " $");
                System.out.println();
                System.out.println("1. Перейти к оформлению заказа.");
                System.out.println("2. Вернуться в меню.");
                System.out.println("3. Удалить товар.");
                System.out.println("Введите цифру для продолжения работы: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                if (choice == 1) {
                    if (curr.getBasket().getProducts().size() == 0) {
                        System.out.println("В Вашей корзине нет товаров!\n");
                        shopping(curr);
                        return;
                    }
                    Order order = new Order(curr.basket);
                    orderQueue.add(order);
                    order.setOrderNum(orderQueue.size());
                    System.out.println(" Ваш заказ " + order.getStatus() + "...\n");
                    shopping(curr);
                    return;
                } else if (choice == 2) {
                    shopping(curr);
                    return;
                } else if (choice == 3) {
                    System.out.println("Введите номер товара: ");
                    int productNum = scanner.nextInt() - 1;
                    System.out.println();
                    if (productNum >= curr.getBasket().getProducts().size()) {
                        System.out.println("Введены некорректные данные. ;(\n");
                        shopping(curr);
                        return;
                    } else {
                        curr.getBasket().remove(catalog.getCatalog().get(categoryName).get(productNum));
                        System.out.println("Товар успешно удален :-)\n");
                    }
                    System.out.println();
                } else {
                    System.out.println("Введены некорректные данные! ;(\n");
                    shopping(curr);
                    return;
                }
            } else {
                int size = catalog.getCatalog().get(categoryName).size();
                if (choice - 1 >= size)
                    System.out.println("Введены некорректные данные! ;(\nПопробуйте еще раз!\n");
                else {
                    curr.getBasket().add(catalog.getCatalog().get(categoryName).get(choice - 1));
                    System.out.println("Товар успешно добавлен :-)\n");
                }
            }
        }
    }


    // Позволяет администатору выполнять различные действия
    public void startAdmin(Admin curr) {
        String categoryName = "";
        System.out.println("1. Посмотреть каталог.");
        System.out.println("2. Посмотреть заказы.");
        System.out.println("3. Добаваить новую категорию.");
        System.out.println("4. Добавить новый товар.");

        System.out.println("Введите выбранную цифру для продолжения или \"0\" для завершения работы: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        if (choice == 0) {
            System.out.println("Не забудьте сохранить изменения! X_X\n");
            return;
        }
        if (choice == 1) {
            System.out.println("Выберите категорию: ");
            catalog.showCategories();
            System.out.println(" Введите номер категории или \"0\", чтобы выбрать все категории: ");
            int cat = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            int cnt = 1;
            for (Map.Entry<String, List<Product>> items : catalog.getCatalog().entrySet()) {
                if (cnt == cat) {
                    categoryName = items.getKey();
                    catalog.showProducts(items.getKey());
                    break;
                }
                cnt++;
            }
            if (cat == 0) {
                catalog.showCatalog();
            } else if (cnt != cat) {
                System.out.println("Введены некорректные данные!");
                startAdmin(curr);
                return;
            }

            while (true) {
                System.out.println("1.Изменить  данные товара");
                System.out.println("2.Удалить товар");
                System.out.println("3.Удалить категорию");
                System.out.println("Введите выбранную цифру или \"0\", чтобы вернуться: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                if (choice == 0) {
                    startAdmin(curr);
                    return;
                } else if (choice == 1) {
                    if (cat == 0) {
                        System.out.println("Введите название категории: ");
                        categoryName = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(categoryName) == null) {
                        System.out.println("Введены некорректные данные! ;(\n");
                    } else {
                        System.out.println("Введите номер товара: ");
                        int itemNum = scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.println();
                        if (catalog.getCatalog().get(categoryName).size() <= itemNum) {
                            System.out.println("Введены некорректные данные! ;(\n");
                        }
                        curr.updateProduct(catalog.getCatalog().get(categoryName).get(itemNum));
                        System.out.println("\nДанные товара успешно изменены :-)\n");
                    }
                } else if (choice == 2) {
                    if (cat == 0) {
                        System.out.println("Введите название категории: ");
                        categoryName = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(categoryName) == null) {
                        System.out.println("Введены некорректные данные! ;(\n");
                    } else {
                        System.out.println("Введите номер товара: ");
                        int item_num = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (catalog.getCatalog().get(categoryName).size() <= item_num) {
                            System.out.println("Введены некорректные данные! ;(\n");
                            continue;
                        }
                        curr.removeProduct((catalog.getCatalog().get(categoryName).get(item_num)), categoryName);
                        System.out.println("Товар успешно удален. :-)\n");
                    }
                } else if (choice == 3) {
                    if (cat == 0) {
                        System.out.println("Ввeдите название категории: ");
                        categoryName = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(categoryName) == null) {
                        System.out.println("Введены некорректные данные ;(\n");
                    } else {
                        curr.removeCategory(categoryName);
                        System.out.println("Категория успешно удалена ;]\n");
                    }
                } else {
                    System.out.println("Введены некорректные данные! ;(\n");
                }
            }

        } else if (choice == 2) {
            System.out.println("0. Все заказы");
            System.out.println("1. Заказы на очереди ");
            System.out.println("2. Отмененные заказы");
            System.out.println("3. Завершенные покупки");

            System.out.println("Введите выбранную цифру: ");
            int filter = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            String stat = "";
            boolean ok = false;
            if (filter == 0) {
                ok = true;
                curr.viewOrders();
            } else if (filter == 1)
                stat = "В очереди";
            else if (filter == 2)
                stat = "Отменненный";
            else if (filter == 3)
                stat = "Выполненный";
            else {
                System.out.println("Введены некорректные данные ;(\n");
                startAdmin(curr);
                return;
            }
            if (filter != 0) {
                for (Order order : orderQueue) {
                    if (order.getStatus().equals(stat)) {
                        order.viewOrder();
                        ok = true;
                    }
                }
            }
            if (!ok) {
                System.out.println("Пусто, нет " + stat + " заказов\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Введите номер заказа, чтобы изменить статус или \"0\", чтобы вернуться: ");
            int order_num = scanner.nextInt();
            scanner.nextLine();
            if (order_num == 0) {
                startAdmin(curr);
                return;
            }
            order_num--;
            if (order_num >= orderQueue.size() || order_num < 0) {
                System.out.println("Введены некорректные данные ;(\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Выбеорите новый статус: ");
            System.out.println("1. В очереди");
            System.out.println("2. Отмененный");
            System.out.println("3. Выполненный");
            System.out.println("Введите выбранную цифру: ");
            int orderStat = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (orderStat == 1) {
                orderQueue.get(order_num).updateStatus("В очереди");
            } else if (orderStat == 2) {
                orderQueue.get(order_num).updateStatus("Отмененный");
            } else if (orderStat == 3) {
                orderQueue.get(order_num).updateStatus("Выполненный");
            } else {
                System.out.println("Введены некорректные данные ;(\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Статус успешно изменен :-)\n");
            startAdmin(curr);
            return;
        } else if (choice == 3) {
            System.out.println("Введите название новой категории: ");
            curr.addCategory(scanner.nextLine());
            System.out.println("Категория успешно добавлена :-)\n");
            startAdmin(curr);
            return;
        } else if (choice == 4) {
            String item_s, item_cat;
            double item_d;
            Product newProduct = new Product();
            long cnt = 0;
            for (Map.Entry<String, List<Product>> entry : catalog.getCatalog().entrySet()) {
                cnt += entry.getValue().size();
            }
            newProduct.setId(cnt + 1);
            System.out.println("Введите категорию товара: ");
            item_cat = scanner.nextLine();
            System.out.println("Введите название товара: ");
            item_s = scanner.nextLine();
            newProduct.setName(item_s);
            System.out.println("Введите стоимость товара: ");
            item_d = Double.parseDouble(scanner.next());
            scanner.nextLine();
            newProduct.setPrice(item_d);
            curr.addProduct(newProduct, item_cat);
            System.out.println("Товар успешно добавлен! \n");
            startAdmin(curr);
            return;
        } else {
            System.out.println("Введены некорректные данные ;(\n");
            startAdmin(curr);
            return;
        }
    }
}
