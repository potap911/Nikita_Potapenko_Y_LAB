package Out;

import Indications.Indication;
import Registration.User;


/**
 * Класс реализует консольный вывод для пользователя согласно запросу
 */
public class Printer {

        /**
         * Метод реализует консольный вывод истории подачи показаний переданного пользователя
         * @param user - пользователь
         */

    public static void printHistory(User user) {
            Indication coldWater = user.getColdWater();
            Indication hotWater = user.getHotWater();
            Indication heating = user.getHeating();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Показания счетчика холодной воды: ");
            System.out.println(coldWater.getHistoryIndications());
            System.out.println();

            System.out.println("Показания счетчика горячей воды: ");
            System.out.println(hotWater.getHistoryIndications());
            System.out.println();

            System.out.println("Показания счетчика отопления: ");
            System.out.println(heating.getHistoryIndications());
            System.out.println();
    }

        /**
         * Метод реализует консольный вывод актуальных показаний переданного пользователя
         * @param user - пользователь
         */

    public static void printActualIndications(User user) {
            Indication coldWater = user.getColdWater();
            Indication hotWater = user.getHotWater();
            Indication heating = user.getHeating();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Актуальные показания счетчика холодной воды: " + coldWater.getActualIndication());
            System.out.println("Актуальные показания счетчика горячей воды: " + hotWater.getActualIndication());
            System.out.println("Актуальные показания счетчика отопления: " + heating.getActualIndication());
            System.out.println();
    }

        /**
         * Метод реализует консольный вывод показаний за конкретный месяц переданного пользователя
         * @param user - пользователь
         * @param month - конкретный месяц
         */

    public static void printIndicationsToMonth(User user, int month) {
            Indication coldWater = user.getColdWater();
            Indication hotWater = user.getHotWater();
            Indication heating = user.getHeating();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Показания счетчика холодной воды в этом месяце: " + coldWater.getIndicationToMonth(month));
            System.out.println("Показания счетчика горячей воды в этом месяце: " + hotWater.getIndicationToMonth(month));
            System.out.println("Показания счетчика отопленияв в этом месяце: " + heating.getIndicationToMonth(month));
            System.out.println();
    }
}
