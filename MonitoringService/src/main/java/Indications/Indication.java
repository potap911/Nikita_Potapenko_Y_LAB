package Indications;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

interface Indication {
    void addIndication(double value);
    void printHistory();
    void printIndicationsToMonth(int month);
    void printActualIndications();

    default void addIndication(LinkedHashMap<LocalDate, Double> history, LocalDate lastDate, double lastValue, double value) {
        LocalDate currDate = LocalDate.from(ZonedDateTime.now().minusMonths(1).toInstant());
        if (currDate.isBefore(lastDate)) {
            lastDate = LocalDate.now();
            lastValue = value;
            history.put(lastDate, lastValue);
            System.out.println("Показание успешно добавлено!");
        } else System.out.println("Нельзя добавить новое показание. С момента последнего показания не прошел месяц");
    }

    default void printHistory(LinkedHashMap<LocalDate, Double> history) {
        if (history.isEmpty()) System.out.println("История пуста");
        for (LocalDate date : history.keySet()) {
            System.out.println(date + " - " + history.get(date));
        }
    }

    default void printIndicationsToMonth(LinkedHashMap<LocalDate, Double> history, int month) {
        if (history.isEmpty()) System.out.println("История пуста");
        for (LocalDate date : history.keySet()) {
            if (date.getMonth().getValue() == month) {
                System.out.println(date + " - " + history.get(date));
                return;
            }
        }
    }

    default void printActualIndications(LocalDate lastDate, double lastValue) {
        if (lastDate == null || lastValue == 0.0) System.out.println("История пуста");
        System.out.println(lastDate + " - " + lastValue);
    }


}
