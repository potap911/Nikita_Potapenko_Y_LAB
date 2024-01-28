package Out;

import Indications.Indication;
import Registration.User;

import java.util.HashMap;


    /**
    * <h1> Printer </h1>
    * Класс реализует консольный вывод для пользователя согласно запросу
    */
public class Printer {

        /**
         * Метод реализует консольный вывод истории подачи показаний переданного пользователя
         * @param user пользователь
         */

    public static void printHistory(User user) {
        HashMap<Integer, Indication> counterMap = user.getCounters();

        System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

        for (int number = 1; number <= user.getCntCounter(); number++) {
            Indication counter = counterMap.get(number);
            System.out.println("История показаний счетчика: " + number + "." + counter.getName());
            System.out.println(counter.getHistoryIndications());
        }
        System.out.println();
    }

        /**
         * Метод реализует консольный вывод актуальных показаний переданного пользователя
         * @param user пользователь
         */

    public static void printActualIndications(User user) {
        HashMap<Integer, Indication> counterMap = user.getCounters();

        System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

        for (int number = 1; number <= user.getCntCounter(); number++) {
            Indication counter = counterMap.get(number);
            System.out.println("Актуальные показания счетчика: " + number + "." + counter.getName());
            System.out.println("\t" + counter.getActualIndication());
        }
        System.out.println();
    }

        /**
         * Метод реализует консольный вывод показаний за конкретный месяц переданного пользователя
         * @param user пользователь
         * @param month конкретный месяц
         */

    public static void printIndicationsToMonth(User user, int month) {
        HashMap<Integer, Indication> counterMap = user.getCounters();

        System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

        for (int number = 1; number <= user.getCntCounter(); number++) {
            Indication counter = counterMap.get(number);
            System.out.println("показания счетчика: " + number + "." + counter.getName() + " в " + month + " месяце");
            System.out.println("\t" + counter.getIndicationToMonth(month));
        }
        System.out.println();
    }
    /**
     * Метод реализует консольный вывод списка доступных счетчиков
     * @param user пользователь
     */

    public static void printCounterList(User user) {
        HashMap<Integer, Indication> counterMap = user.getCounters();
        for (int number = 1; number <= user.getCntCounter(); number++) {
            System.out.println(number + "." + counterMap.get(number).getName());
        }
    }

    /**
     * Метод печатает справку для пользователя
     */
    public static void printHelp() {
        System.out.println("\tСписок команд:\n" +
                "status : статус авторизации\n" +
                "login  : авторизоваться\n" +
                "logout : разлогиниться\n" +
                "reg    : зарегестрировать пользователя\n" +
                "get    : получить информацию о показаниях\n" +
                "post   : отправить показания\n" +
                "add    : добавить счетчик\n" +
                "log    : получить аудит действий пользователя\n" +
                "q      : выйти и разлогиниться"
        );
    }

        /**
         * Метод реализует консольный вывод переданного сообщения
         * @param message сообщение для пользователя
         */

    public static void message(String message) {
        System.out.println(message);
    }
}
