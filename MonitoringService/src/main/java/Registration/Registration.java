package Registration;

import org.potapenko.Printer;

import java.util.HashMap;
import java.util.Scanner;

public final class Registration {
    private static HashMap<String, Admin> admins;
    private static HashMap<String, User> users;
    private static Admin currAdmin;
    private static User currUser;
    private static Scanner scanner;

    public static void logIn() {
        System.out.println("\tАвторизация");

        int status = choiceStatusUser();

        String login = inputLogin();
        String password = inputPassword();

        if (admins != null) {
            if (status == 1 && admins.get(login).getPassword().equals(password)) {
                currAdmin = admins.get(login);
                currUser = null;
                System.out.println("Добро пожаловать, " + currAdmin.getLogin());
            } else System.out.println("Нет такого администратора");
        }

        else if (users != null) {
            if (users != null && status == 2 && users.get(login).getPassword().equals(password)) {
                currUser = users.get(login);
                currAdmin = null;
                System.out.println("Добро пожаловать, " + currUser.getLogin());
            } else System.out.println("Нет такого пользователя");
        } else System.out.println("Нет такого пользователя");
    }

    public static void logOut() {
        currAdmin = null;
        currUser = null;
        System.out.println("Вы успешно разлогированы!");

    }

    public static void add() {
        System.out.println("\tРегистрация нового пользователя");

        int status = choiceStatusUser();

        String login = inputLogin();
        String password = inputPassword();

        if (status == 1  && !admins.containsKey(login)) {
            admins.put(login, new Admin(login, password));
            System.out.println("Успешная регистрация администратора " + login);
        } else if (status == 2 && users.containsKey(login)) {
            users.put(login, new User(login, password));
            System.out.println("Успешная регистрация пользователя " + login);
        } else System.out.println("Такой пользователь уже зарегестрирован ранее!");
    }

    public static void remove() {
        System.out.println("\tУдаление пользователя");

        int status = choiceStatusUser();

        System.out.println("Введите логин:");
        String login = scanner.nextLine();

        if (agreeWarning(login)) {
            if (status == 1 && admins != null && admins.containsKey(login)) {
                admins.remove(login);
                System.out.println(login + " удален!");
            } else if (status == 2 && users.containsKey(login)) {
                users.remove(login);
                System.out.println(login + " удален!");
            } else System.out.println("Нет такого пользователя");
        }
    }

    private static boolean agreeWarning(String login) {
        System.out.println("Точно хотите удалить пользователя с логином: " + login + "?");
        System.out.println("Введите Y/N или enter - 'Y' по умолчанию");
        if (scanner.nextLine().equals("Y") || scanner.nextLine().equals("\n")) return true;
        else return false;
    }

    private static int choiceStatusUser() {
        System.out.println("Выберите, кого хотите зарегестрировать или авторизовать:");
        System.out.println("1. Администратор");
        System.out.println("2. Пользователь");
        System.out.println("Введите: '1' или '2'");
        return scanner.nextInt();
    }

    private static int choiceStatusIndication() {
        System.out.println("Выберите, кого рода показания хотите отправить:");
        System.out.println("1. Показания счетчика холодной воды");
        System.out.println("2. Показания счетчика горячей воды");
        System.out.println("3. Показания счетчика отопления");
        System.out.println("Введите: '1' или '2' или '3'");
        return scanner.nextInt();
    }



    private static String inputLogin() {
        System.out.println("Введите логин:");
        return scanner.next();
    }

    private static String inputPassword() {
        System.out.println("Введите пароль:");
        return scanner.next();
    }

    private static int inputValue() {
        System.out.println("Значение:");
        return scanner.nextInt();
    }

    public static void putIndication() {
        if (currUser != null && currAdmin == null) {
            int status = choiceStatusIndication();
            int value = inputValue();

            if (status == 1) currUser.getColdWaterIndication().addIndication(value);
            if (status == 2) currUser.getHotWaterIndication().addIndication(value);
            if (status == 3) currUser.getHeatingIndication().addIndication(value);
        } else System.out.println("Вы не авторизованы как пользователь!");
    }

    // 1 - history, 2 - actual, 3 - toMonth
    public static void printInfo(int status) {
        int month = 0;
        if (status == 3) {
            System.out.println("Введите искомы месяц:");
            month = scanner.nextInt();
        }

        if (currAdmin != null && currUser == null) {
            for(String login : users.keySet()) {
                if (status == 1) Printer.printHistory(users.get(login));
                if (status == 2) Printer.printActualIndications(users.get(login));
                if (status == 3) Printer.printIndicationsToMonth(users.get(login), month);
            }
        }

        if (currAdmin == null && currUser != null) {
            if (status == 1) Printer.printHistory(currUser);
            if (status == 2) Printer.printActualIndications(currUser);
            if (status == 3) Printer.printIndicationsToMonth(currUser, month);
        }
    }
    public static void init() {
        admins = new HashMap<>();
        users = new HashMap<>();
        currAdmin = null;
        currUser = null;
        scanner = new Scanner(System.in);
    }
}