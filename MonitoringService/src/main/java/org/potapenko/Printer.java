package org.potapenko;

import Indications.ColdWaterIndication;
import Indications.HeatingIndication;
import Indications.HotWaterIndication;
import Registration.User;

import java.util.HashMap;

public class Printer {

    public static void printHistory(HashMap<String, User> users) {
        for (String login : users.keySet()) {
            User user = users.get(login);

            ColdWaterIndication coldWaterIndication = user.getColdWaterIndication();
            HotWaterIndication hotWaterIndication = user.getHotWaterIndication();
            HeatingIndication heatingIndication = user.getHeatingIndication();

            System.out.println("\tПоказания счетчика холодной воды:");
            coldWaterIndication.printHistory();

            System.out.println("\tПоказания счетчика горячей воды:");
            hotWaterIndication.printHistory();

            System.out.println("\tПоказания счетчика отопления:");
            heatingIndication.printHistory();
        }
    }

    public static void printActualIndications(HashMap<String, User> users) {
        for (String login : users.keySet()) {
            User user = users.get(login);

            ColdWaterIndication coldWaterIndication = user.getColdWaterIndication();
            HotWaterIndication hotWaterIndication = user.getHotWaterIndication();
            HeatingIndication heatingIndication = user.getHeatingIndication();

            System.out.println("\tАктуальные показания счетчика холодной воды:");
            System.out.println(coldWaterIndication.getLastDate() + " - " + coldWaterIndication.getLastValue());

            System.out.println("\tАктуальные показания счетчика горячей воды:");
            System.out.println(hotWaterIndication.getLastDate() + " - " + hotWaterIndication.getLastValue());

            System.out.println("\tАктуальные показания счетчика отопления:");
            System.out.println(heatingIndication.getLastDate() + " - " + heatingIndication.getLastValue());
        }
    }

    public static void printIndicationsToMonth(HashMap<String, User> users) {
        for (String login : users.keySet()) {
            User user = users.get(login);

            ColdWaterIndication coldWaterIndication = user.getColdWaterIndication();
            HotWaterIndication hotWaterIndication = user.getHotWaterIndication();
            HeatingIndication heatingIndication = user.getHeatingIndication();

            System.out.println("\tПоказания счетчика холодной воды в месяце: " + coldWaterIndication);
            System.out.println(coldWaterIndication.getLastDate() + " - " + coldWaterIndication.getLastValue());

            System.out.println("\tАктуальные показания счетчика горячей воды:");
            System.out.println(hotWaterIndication.getLastDate() + " - " + hotWaterIndication.getLastValue());

            System.out.println("\tАктуальные показания счетчика отопления:");
            System.out.println(heatingIndication.getLastDate() + " - " + heatingIndication.getLastValue());
        }
    }



}
