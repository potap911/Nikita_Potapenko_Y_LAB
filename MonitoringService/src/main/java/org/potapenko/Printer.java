package org.potapenko;

import Indications.ColdWaterIndication;
import Indications.HeatingIndication;
import Indications.HotWaterIndication;
import Registration.User;

public class Printer {


    public static void printHistory(User user) {
            ColdWaterIndication coldWater = user.getColdWaterIndication();
            HotWaterIndication hotWater = user.getHotWaterIndication();
            HeatingIndication heating = user.getHeatingIndication();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Показания счетчика холодной воды: ");
            System.out.println(coldWater.getHistoryIndications());

            System.out.println("Показания счетчика горячей воды: ");
            System.out.println(hotWater.getHistoryIndications());

            System.out.println("Показания счетчика отопления: ");
            System.out.println(heating.getHistoryIndications());
            System.out.println();
    }

    public static void printActualIndications(User user) {
            ColdWaterIndication coldWater = user.getColdWaterIndication();
            HotWaterIndication hotWater = user.getHotWaterIndication();
            HeatingIndication heating = user.getHeatingIndication();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Актуальные показания счетчика холодной воды: " + coldWater.getActualIndication());
            System.out.println("Актуальные показания счетчика горячей воды: " + hotWater.getActualIndication());
            System.out.println("Актуальные показания счетчика отопления: " + heating.getActualIndication());
            System.out.println();

    }

    public static void printIndicationsToMonth(User user, int month) {
            ColdWaterIndication coldWater = user.getColdWaterIndication();
            HotWaterIndication hotWater = user.getHotWaterIndication();
            HeatingIndication heating = user.getHeatingIndication();

            System.out.println("\tПоказания счетчиков пользователя: " + user.getLogin());

            System.out.println("Показания счетчика холодной воды в этом месяце: " + coldWater.getIndicationToMonth(month));
            System.out.println("Показания счетчика горячей воды в этом месяце: " + hotWater.getIndicationToMonth(month));
            System.out.println("Показания счетчика отопленияв в этом месяце: " + heating.getIndicationToMonth(month));
            System.out.println();

    }
}
