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
    /**
     * Поле список администраторов
     */
    private static HashMap<String, Admin> admins;
    /**
     * Поле список пользователей
     */
    private static HashMap<String, User> users;
    /**
     * Поле текущий администратор
     */
    private static Admin currAdmin;
    /**
     * Поле текущий пользователь
     */
    private static User currUser;
    /**
     * Поле объект класса Scanner
     */
    private static Scanner scan;

    private static StringBuilder auditUser;
    /**
     * Поле список счетчиков
     */
    public static HashMap<Integer, String> counterMap;
    /**
     * Поле колличество счетчиков
     */
    public static int cntCounter;


    /**
     * Метод руализует авторизацию пользователя
     */

    public static void logIn() {
        Printer.message("\tАвторизация");

        int status = choiceStatusUser();
        if (status == 0) return;

        String login = inputLogin();
        String password = encrypt(inputPassword());

        try {
            if (status == 1 && admins.get(login).getPassword().equals(password)) {
                currAdmin = admins.get(login);
                currUser = null;
                Printer.message("Добро пожаловать, " + currAdmin.getLogin());
            } else if (status == 2 && users.get(login).getPassword().equals(password)) {
                currUser = users.get(login);
                currAdmin = null;
                Printer.message("Добро пожаловать, " + currUser.getLogin());
                auditUser.append("Пользователь с ником ").append(login).append(" авторизовался").append('\n');
            } else Printer.message("Нет такого пользователя");
        } catch (NullPointerException exception) {
            Printer.message("Нет такого пользователя");
        }
    }

    /**
     * Метод реализует разлогирование пользователя
     */

    public static void logOut() {
        if (currUser != null)
            auditUser.append("Пользователь с ником ").append(currUser.getLogin()).append(" разлогинился").append('\n');
        currAdmin = null;
        currUser = null;
        Printer.message("Вы успешно разлогированы!");

    }

    /**
     * Метод реализует консольный вывод информации о статусе авторизации
     */

    public static void getStatusAuthorization() {
        if (currUser == null && currAdmin != null) Printer.message("Вы авторизованы как администратор!");
        else if (currUser != null && currAdmin == null) Printer.message("Вы авторизованы как пользователь!");
        else Printer.message("Вы не авторизованы!");
    }

    /**
     * Метод реализует регистрацию нового пользователя
     */

    public static void reg() {
        Printer.message("\tРегистрация нового пользователя");

        int status = choiceStatusUser();
        if (status == 0) return;

        String login = inputLogin();
        String password = encrypt(inputPassword());

        if (status == 1 && !admins.containsKey(login)) {
            admins.put(login, new Admin(login, password));
            Printer.message("Успешная регистрация администратора " + login);
        } else if (status == 2 && !users.containsKey(login)) {
            users.put(login, new User(login, password));
            Printer.message("Успешная регистрация пользователя " + login);
            auditUser.append("Пользователь с ником ").append(login).append(" зарегестрировался").append('\n');
        } else Printer.message("Такой пользователь уже зарегестрирован ранее!");
    }

    /**
     * Метод реализует выбор статуса пользователя
     */

    private static int choiceStatusUser() {
        int input;

        while (true) {
            Printer.message("Выберите, кого хотите зарегестрировать или авторизовать:");
            Printer.message("0. Вернуться в стартовое меню");
            Printer.message("1. Администратор");
            Printer.message("2. Пользователь");
            Printer.message("Введите: '1' или '2' или '0' для возврата");
            while (!scan.hasNextInt()) {
                Printer.message("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input == 1 || input == 2 || input == 0) break;
            Printer.message("Нет такого пункта, попробуйте еще раз");
        }

        return input;
    }

    /**
     * Метод реализует выбор вида счетчика
     */

    private static int choiceStatusIndication() {
        int input;

        while (true) {
            Printer.message("Выберите, кого рода показания хотите отправить:");
            Printer.message("0. Вернуться в стартовое меню");
            Printer.printCounterList(currUser);
            while (!scan.hasNextInt()) {
                Printer.message("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input >= 1 && input <= currUser.getCntCounter() || input == 0) break;
            Printer.message("Нет такого пункта, попробуйте еще раз");
        }

        return input;
    }

    /**
     * Метод реализует консольный ввод логина
     */

    private static String inputLogin() {
        String login;
        while (true) {
            Printer.message("Введите логин:");
            login = scan.next().trim();
            if (!login.isBlank()) break;
            else Printer.message("Вы не ввели логин попробуйте еще!");
        }

        return login;
    }

    /**
     * Метод реализует ввод паспорта
     */

    private static String inputPassword() {
        String password;
        while (true) {
            Printer.message("Введите пароль от 8 знаков и больше:");
            password = scan.next();
            if (password.length() >= 8 && !password.isBlank()) break;
            else Printer.message("Введенный пароль слишком простой!");
        }

        return password;
    }

    /**
     * Метод реализует ввод значение подоваемого показания
     */

    private static double inputValue() {
        double input;

        while (true) {
            Printer.message("Введите значение:");
            while (!scan.hasNextDouble()) {
                Printer.message("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextDouble();
            if (input > 0) break;
            else Printer.message("Введите значение больше нуля!");
        }
        return input;
    }

    /**
     * Метод реализует подачу показания
     */

    public static void postIndication() {
        if (currUser != null && currAdmin == null) {
            int status = choiceStatusIndication();
            if (status == 0) return;
            double value = inputValue();

            currUser.getCounters().get(status).addIndication(value);
            auditUser.append("Пользователь с ником ").append(currUser.getLogin()).append(" добавил показание").append('\n');

        } else Printer.message("Вы не авторизованы как пользователь!");
    }


    /**
     * Метод реализует получение информации о показаниях
     */
    public static void getInfo() {
        if (currAdmin == null && currUser == null) {
            Printer.message("Вы не авторизованы!");
            return;
        }

        int status = choiceStatusInfo();

        int month = 0;
        if (status == 3) {
            Printer.message("Введите искомый месяц:");
            month = scan.nextInt();
        }

        if (currAdmin != null && currUser == null) {
            for (String login : users.keySet()) {
                if (status == 1) Printer.printHistory(users.get(login));
                if (status == 2) Printer.printActualIndications(users.get(login));
                if (status == 3) Printer.printIndicationsToMonth(users.get(login), month);
            }
        }

        if (currAdmin == null && currUser != null) {
            if (status == 1) {
                Printer.printHistory(currUser);
                auditUser.append("Пользователь с ником ").append(currUser.getLogin()).append(" запросил историю показаний").append('\n');
            }
            if (status == 2) {
                Printer.printActualIndications(currUser);
                auditUser.append("Пользователь с ником ").append(currUser.getLogin()).append(" запросил актуальные показания").append('\n');
            }
            if (status == 3) {
                Printer.printIndicationsToMonth(currUser, month);
                auditUser.append("Пользователь с ником ").append(currUser.getLogin()).append(" запросил показания за конкретный месяц").append('\n');
            }

        }
    }

    /**
     * Метод реализует получение вида информации о показаниях
     */

    private static int choiceStatusInfo() {
        int input;

        while (true) {
            Printer.message("Выберите, когого рода информацию хотите получить:");
            Printer.message("0. Вернуться в стартовое меню");
            Printer.message("1. Получить всю историю показаний");
            Printer.message("2. Получить актуальные показания");
            Printer.message("3. Получить показания за конкретный месяц");
            Printer.message("Введите: '1' или '2' или '3' или '0' для возврата");
            while (!scan.hasNextInt()) {
                Printer.message("Это не число, попробуйте еще раз!");
                scan.next();
            }
            input = scan.nextInt();
            if (input == 1 || input == 2 || input == 3 || input == 0) break;
            Printer.message("Нет такого пункта, попробуйте еще раз");
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
        auditUser = new StringBuilder();

        counterMap = new HashMap<>(3);
        counterMap.put(1, "Счетчик холодной воды");
        counterMap.put(2, "Счетчик горячей воды");
        counterMap.put(3, "Счетчик отопления");
        cntCounter = 3;
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

    /**
     * Метод реализует добавление нового счетчика в систему под правами администратора
     */
    public static void addNewCounter() {
        if (currUser == null && currAdmin != null) {
            Printer.message("Введите название счетчика");
            String name = scan.nextLine().trim();
            if (!name.isBlank()) {
                cntCounter++;
                counterMap.put(cntCounter, name);
                for (String login : users.keySet()) {
                    users.get(login).addCounter(name);
                }
                Printer.message("Счетчик успешно добавлен!");
            } else Printer.message("Вы не ввели название счетчика, попробуйте еще!");
        } else Printer.message("Вы не авторизованы как админитратор!");
    }

    /**
     * Метод реализует консольный вывод аудита пользователя
     */
    public static void getAuditUserInfo() {
        if (currAdmin != null && currUser == null) Printer.message(auditUser.toString());
        else Printer.message("Вы не авторизованы как администратор!");
    }
}