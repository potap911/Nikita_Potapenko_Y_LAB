package In;

import Registration.Registration;

import java.util.Scanner;

public final class MonitoringService {
    public static void startService() {
        Scanner scanner = new Scanner(System.in);
        Registration.init();
        String input = "";

        System.out.println("Добро пожаловать!");
        printHelp();

        while (!input.equals("q")) {
            System.out.println("Введите команду:");
            input = scanner.nextLine().trim();
            command(input);
        }
    }

    private static void command(String input) {
        switch (input) {
            case "help" : printHelp(); break;
            case "status" : Registration.printStatusAuthorization(); break;
            case "login"  : Registration.logIn(); break;
            case "logout"  : Registration.logOut(); break;
            case "reg"  : Registration.addUser(); break;
            //case "rm"   : Registration.remove(); break;
            case "get -h" : Registration.getInfo(1); break;
            case "get -a"  : Registration.getInfo(2); break;
            case "get -m"  : Registration.getInfo(3); break;
            case "put"  : Registration.putIndication(); break;
            case "q"  : System.exit(0); break;
            default :
                System.out.println("Неверная команда, попробуйте еще раз");
        }
    }

    private static void printHelp() {
        System.out.println("\tСписок команд:\n" +
                        "login  : авторизоваться\n" +
                        "status  : статус авторизации\n" +
                        "logout : разлогиниться\n" +
                        "reg    : зарегестрировать пользователя\n" +
                        //"rm     : удалить пользователя\n" +
                        "get -h : получить историю показаний\n" +
                        "get -a : получить актуальные показания\n" +
                        "get -m : получить показания за конкретный месяц\n" +
                        "put    : отправить показания\n" +
                        "q      : выйти и разлогиниться"
        );
    }
}
