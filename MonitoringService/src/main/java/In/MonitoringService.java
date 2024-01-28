package In;

import Out.Printer;
import Registration.RegistrationService;

import java.util.Scanner;

/**
 * <h1> MonitoringService </h1>
 * Статический класс MonitoringService реализует старт сервиса, получение пользовательского ввода,
 * обработку его команд и вызов соответствующего команде алгоритма
 */
public final class MonitoringService {

    /**
     * Метод реализует ввод пользователя и инициализацию класса Registration
     */
    public static void startService() {
        Scanner scanner = new Scanner(System.in);
        RegistrationService.init();
        String input = "";

        System.out.println("Добро пожаловать!");
        Printer.printHelp();

        while (!input.equals("q")) {
            System.out.println("\n\tВведите команду:");
            input = scanner.nextLine().trim();
            command(input);
        }
    }

    /**
     * Метод реализует обработку ввода пользователя и вызывает соответствующие фабричные методы класса Registration
     * @param input ввод пользователя
     */

    private static void command(String input) {
        switch (input) {
            case "help" : Printer.printHelp(); break;
            case "status" : RegistrationService.getStatusAuthorization(); break;
            case "login"  : RegistrationService.logIn(); break;
            case "logout"  : RegistrationService.logOut(); break;
            case "reg"  : RegistrationService.reg(); break;
            case "log"  : RegistrationService.getAuditUserInfo(); break;
            case "add"  : RegistrationService.addNewCounter(); break;
            case "get" : RegistrationService.getInfo(); break;
            case "post"  : RegistrationService.postIndication(); break;
            case "q"  : System.exit(0); break;
            default :
                System.out.println("Неверная команда, попробуйте еще раз, или введите команду 'help'");
        }
    }
}
