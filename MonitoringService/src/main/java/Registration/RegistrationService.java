package Registration;

import Out.Printer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

/**
 * <h1> RegistrationService </h1>
 *  Статический класс для регистрации, авторизации, хранения пользователей и регистрации действий с ними
 */
public final class RegistrationService {
    /** Поле список администраторов*/
    private static HashMap<String, Admin> admins;
    /** Поле список пользователей*/
    private static HashMap<String, User> users;
    /** Поле текущий администратор*/
    private static Admin currAdmin;
    /** Поле текущий пользователь*/
    private static User currUser;
    /** Поле объект класса Scanner*/
    private static Scanner scan;


    /**
     * Метод руализует авторизацию пользователя
     */

    public static void logIn() {
        System.out.println("\tАвторизация");

        int status = choiceStatusUser();
        if (status == 0) return;

        String login = inputLogin();
        String password = encrypt(inputPassword());

        try {
            if (status == 1 && admins.get(login).getPassword().equals(password)) {
                currAdmin = admins.get(login);
                currUser = null;
                System.out.println("Добро пожаловать, " + currAdmin.getLogin());
            }

            else if (status == 2 && users.get(login).getPassword().equals(password)) {
                currUser = users.get(login);
                currAdmin = null;
                System.out.println("Добро пожаловать, " + currUser.getLogin());
            } else System.out.println("Нет такого пользователя");
        } catch (NullPointerException exception) {
            System.out.println("Нет такого пользователя");
        }
    }

    /**
     * Метод реализует разлогирование пользователя
     */

    public static void logOut() {
        currAdmin = null;
        currUser = null;
        System.out.println("Вы успешно разлогированы!");

    }

    /**
     * Метод реализует консольный вывод информации о статусе авторизации
     */

    public static void printStatusAuthorization() {
        if (currUser == null && currAdmin != null) System.out.println("Вы авторизованы как администратор!");
        else if (currUser != null && currAdmin == null) System.out.println("Вы авторизованы как пользователь!");
        else System.out.println("Вы не авторизованы!");
    }

    /**
     * Метод реализует регистрацию нового пользователя
     */

    public static void reg() {
        System.out.println("\tРегистрация нового пользователя");

        int status = choiceStatusUser();
        if (status == 0) return;

        String login = inputLogin();
        String password = encrypt(inputPassword());

        if (status == 1  && !admins.containsKey(login)) {
            admins.put(login, new Admin(login, password));
            System.out.println("Успешная регистрация администратора " + login);
        } else if (status == 2 && !users.containsKey(login)) {
            users.put(login, new User(login, password));
            System.out.println("Успешная регистрация пользователя " + login);
        } else System.out.println("Такой пользователь уже зарегестрирован ранее!");
    }

    /**
     * Метод реализует выбор статуса пользователя
     */

    private static int choiceStatusUser() {
        int input;

        while (true) {
            System.out.println("Выберите, кого хотите зарегестрировать или авторизовать:");
            System.out.println("0. Вернуться в стартовое меню");
            System.out.println("1. Администратор");
            System.out.println("2. Пользователь");
            System.out.println("Введите: '1' или '2' или '0' для возврата");
            while (!scan.hasNextInt()) {
                System.out.println("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input == 1 || input == 2 || input == 0) break;
            System.out.println("Нет такого пункта, попробуйте еще раз");
        }

        return input;
    }

    /**
     * Метод реализует выбор вида счетчика
     */

    private static int choiceStatusIndication() {
        int input;

        while (true) {
            System.out.println("Выберите, кого рода показания хотите отправить:");
            System.out.println("0. Вернуться в стартовое меню");
            System.out.println("1. Показания счетчика холодной воды");
            System.out.println("2. Показания счетчика горячей воды");
            System.out.println("3. Показания счетчика отопления");
            System.out.println("Введите: '1' или '2' или '3' или '0' для возврата");
            while (!scan.hasNextInt()) {
                System.out.println("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input == 1 || input == 2 || input == 3 || input == 0) break;
            System.out.println("Нет такого пункта, попробуйте еще раз");
        }

        return input;
    }

    /**
     * Метод реализует консольный ввод логина
     */

    private static String inputLogin() {
        System.out.println("Введите логин:");
        return scan.next();
    }

    /**
     * Метод реализует ввод паспорта
     */

    private static String inputPassword() {
        String password;
        while (true) {
            System.out.println("Введите пароль от 8 знаков и больше:");
            password = scan.next();
            if (password.length() >= 8) break;
            else System.out.println("Введенный пароль слишком простой!");
        }

        return password;
    }

    /**
     * Метод реализует ввод значение подоваемого показания
     */

    private static double inputValue() {
        double input;

        while (true) {
            System.out.println("Введите значение:");
            while (!scan.hasNextDouble()) {
                System.out.println("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextDouble();
            if (input > 0) break;
            else System.out.println("Введите значение больше нуля!");
        }
        return input;
    }

    /**
     * Метод реализует подачу показания
     */

    public static void putIndication() {
        if (currUser != null && currAdmin == null) {
            int status = choiceStatusIndication();
            if (status == 0) return;
            double value = inputValue();

            if (status == 1) currUser.getColdWater().addIndication(value);
            if (status == 2) currUser.getHotWater().addIndication(value);
            if (status == 3) currUser.getHeating().addIndication(value);
        } else System.out.println("Вы не авторизованы как пользователь!");
    }


    /**
     * Метод реализует получение информации о показаниях
     */
    public static void getInfo() {
        if (currAdmin == null && currUser == null) {
            System.out.println("Вы не авторизованы!");
            return;
        }

        int status = choiceStatusInfo();

        int month = 0;
        if (status == 3) {
            System.out.println("Введите искомый месяц:");
            month = scan.nextInt();
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

    /**
     * Метод реализует получение вида информации о показаниях
     */

    private static int choiceStatusInfo() {
        int input;

        while (true) {
            System.out.println("Выберите, когого рода информацию хотите получить:");
            System.out.println("0. Вернуться в стартовое меню");
            System.out.println("1. Получить всю историю показаний");
            System.out.println("2. Получить актуальные показания");
            System.out.println("3. Получить показания за конкретный месяц");
            System.out.println("Введите: '1' или '2' или '3' или '0' для возврата");
            while (!scan.hasNextInt()) {
                System.out.println("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input == 1 || input == 2 || input == 3 || input == 0) break;
            System.out.println("Нет такого пункта, попробуйте еще раз");
        }

        return input;
    }

    /**
     * Метод реализует инициализацию полей класса Registration
     */
    public static void init() {
        admins = new HashMap<>();
        users = new HashMap<>();
        currAdmin = null;
        currUser = null;
        scan = new Scanner(System.in);
    }

    /**
     * Метод реализует шифрование передаваемого пароля
     */
    private static String encrypt(String password) {
        String encodedHash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            encodedHash = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encodedHash;
    }

    /*
    public static void remove() {
        System.out.println("\tУдаление пользователя");

        int status = choiceStatusUser();
        if (status == 0) return;

        System.out.println("Введите логин:");
        String login = inputLogin();

        try {
            if (agreeWarning(login)) {
                if (status == 1 && admins.containsKey(login)) {
                    admins.remove(login);
                    System.out.println(login + " удален!");
                } else if (status == 2 && users.containsKey(login)) {
                    users.remove(login);
                    System.out.println(login + " удален!");
                } else System.out.println("Нет такого пользователя");
            }
        } catch (NullPointerException exception) {
            System.out.println("Нет такого пользователя");
        }
    }
    */

    /*private static boolean agreeWarning(String login) {
        System.out.println("Точно хотите удалить пользователя с логином: " + login + "?");
        System.out.println("Введите Y/N или enter - 'Y' по умолчанию");
        if (scan.next().equals("Y") || scan.next().equals("\n")) return true;
        else return false;
    }*/
}