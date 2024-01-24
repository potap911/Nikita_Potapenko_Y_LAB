package org.potapenko;

import Registration.Admin;
import Registration.Registration;
import Registration.User;

import java.util.HashMap;
import java.util.Scanner;

public final class MonitoringService {
    private static HashMap<String, Admin> admins;
    private static HashMap<String, User> users;

    public static void startService() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите команду:");
        String input = scanner.nextLine();


    }

    private static void command(String input) {
        switch (input) {
            case "help" : printHelp();
            case "log"  : Registration.logIn();
            case "reg"  : Registration.add();
            case "rm"   : Registration.remove();
            case "get"  :
        }
    }

    private static void printHelp() {
        System.out.println("\tСписок команд:\n" +
                        "log - авторизоваться\n" +
                        "reg - зарегестрировать пользователя\n" +
                        "rm - удалить пользователя\n" +
                        "get - получить показания\n" +
                        "q   - выход"
        );
    }
}
