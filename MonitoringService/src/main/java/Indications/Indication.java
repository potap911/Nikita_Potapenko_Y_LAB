package Indications;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

public abstract class Indication {
    private double lastValue;
    private LocalDate lastDate;
    private LinkedHashMap<LocalDate, Double> history;

    public void addIndication(double value) {
        LocalDate currDate = LocalDate.from(ZonedDateTime.now().minusMonths(1).toInstant());
        if (currDate.isBefore(lastDate)) {
            lastDate = LocalDate.now();
            lastValue = value;
            history.put(lastDate, lastValue);
            System.out.println("Показание успешно добавлено!");
        } else System.out.println("Нельзя добавить новое показание. С момента последнего показания не прошел месяц");
    }

    public LinkedHashMap<LocalDate, Double> getHistory() {
        return history;
    }

    public double getLastValue() {
        return lastValue;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void printHistory() {
        for (LocalDate date : history.keySet()) {
            System.out.println(date + " - " + history.get(date));
        }
    }

    public void printIndicationsToMonth(int month) {
        for (LocalDate date : history.keySet()) {
            if (date.getMonth().getValue() == month) {
                System.out.println(date + " - " + history.get(date));
                return;
            }
        }
    }




}
