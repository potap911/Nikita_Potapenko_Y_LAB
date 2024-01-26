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
            System.out.println("\n\tВведите команду:");
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
            case "get" : Registration.getInfo(); break;
            case "post"  : Registration.putIndication(); break;
            case "q"  : System.exit(0); break;
            default :
                System.out.println("Неверная команда, попробуйте еще раз, или введите команду 'help'");
        }
    }

    private static void printHelp() {
        System.out.println("\tСписок команд:\n" +
                        "status : статус авторизации\n" +
                        "login  : авторизоваться\n" +
                        "logout : разлогиниться\n" +
                        "reg    : зарегестрировать пользователя\n" +
                        "get    : получить информацию о показаниях\n" +
                        "post   : отправить показания\n" +
                        "q      : выйти и разлогиниться"
                        //"rm     : удалить пользователя\n" +
        );
    }
}
