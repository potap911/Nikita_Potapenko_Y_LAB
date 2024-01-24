package Registration;

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

        int status = chengStatus();

        String login = inputLogin();
        String password = inputPassword();

        if (status == 1 && admins.get(login).equals(password)) {
            currAdmin = admins.get(login);
            currUser = null;
        }

        if (status == 2 && users.get(login).equals(password)) {
            currUser = users.get(login);
            currAdmin = null;
        }
    }

    public static void add() {
        System.out.println("\tРегистрация нового пользователя");

        int status = chengStatus();

        String login = inputLogin();
        String password = inputPassword();

        if (status == 1 && !admins.containsKey(login)) {
            admins.put(login, new Admin(login, password));
            System.out.println("Успешная регистрация администратора " + login);
        } else if (status == 2 && users.containsKey(login)) {
            users.put(login, new User(login, password));
            System.out.println("Успешная регистрация пользователя " + login);
        } else System.out.println("Такой пользователь уже зарегестрирован ранее!");
    }

    public static void remove() {
        System.out.println("\tУдаление пользователя");

        int status = chengStatus();

        System.out.println("Введите логин:");
        String login = scanner.nextLine();

        if (agreeWarning(login)) {
            if (status == 1 && admins.containsKey(login)) {
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

    private static int chengStatus() {
        System.out.println("Выберите, кого хотите зарегестрировать или авторизовать:");
        System.out.println("1. Администратор");
        System.out.println("2. Пользователь");
        System.out.println("Введите: '1' или '2'");
        return scanner.nextInt();
    }

    private static String inputLogin() {
        System.out.println("Введите логин:");
        return scanner.nextLine();
    }

    private static String inputPassword() {
        System.out.println("Введите пароль:");
        return scanner.nextLine();
    }
}