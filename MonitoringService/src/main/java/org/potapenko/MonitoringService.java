package org.potapenko;

import Registration.Admin;
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
            input = scanner.nextLine();
            command(input);
        }


    }

    private static void command(String input) {
        switch (input) {
            case "help" : printHelp(); break;
            case "login"  : Registration.logIn(); break;
            case "logout"  : Registration.logOut(); break;
            case "reg"  : Registration.add(); break;
            case "rm"   : Registration.remove(); break;
            case "get -h" : Registration.printInfo(1); break;
            case "get -a"  : Registration.printInfo(2); break;
            case "get -m"  : Registration.printInfo(3); break;
            case "q"  : System.exit(0); break;
            default :
                System.out.println("Неверная команда, попробуйте еще");
        }
    }

    private static void printHelp() {
        System.out.println("\tСписок команд:\n" +
                        "login  : авторизоваться\n" +
                        "logout : разлогиниться\n" +
                        "reg    : зарегестрировать пользователя\n" +
                        "rm     : удалить пользователя\n" +
                        "get -h : получить историю показаний\n" +
                        "get -a : получить актуальные показания\n" +
                        "get -m : получить показания за конкретный месяц\n" +
                        "put    : отправить показания\n" +
                        "q      : выйти и разлогиниться"
        );
    }
}
