package Indications;

import java.time.LocalDate;
import java.util.LinkedHashMap;

interface Indication {
    void addIndication(double value);
    String getHistoryIndications();
    String getIndicationToMonth(int month);
    String getActualIndication();

    default LocalDate addIndication(LinkedHashMap<LocalDate, Double> history, LocalDate lastDate, double lastValue, double value) {
        LocalDate nowDate = LocalDate.now();

        if (history.isEmpty()) {
            lastValue = value;
            history.put(nowDate, lastValue);
            System.out.println("Показание успешно добавлено!");
        }
        else if (nowDate.getMonth().getValue() > lastDate.getMonth().getValue() || nowDate.getYear() > lastDate.getYear()) {
                if (lastValue <= value) {
                    lastValue = value;
                    history.put(nowDate, lastValue);
                    System.out.println("Показание успешно добавлено!");
                } else {
                    System.out.println("Переданные показания меньше актуальных!");
                    return lastDate;
                }
            } else {
            System.out.println("Нельзя добавить новое показание. В этом месяце показания уже поданы!");
            return lastDate;
        }

        return nowDate;
    }

    default String getHistoryIndications(LinkedHashMap<LocalDate, Double> history) {
        if (history.isEmpty()) return "История пуста";
        StringBuilder builder = new StringBuilder();
        for (LocalDate date : history.keySet()) {
            builder.append("\t").append(date).append(" - ").append(history.get(date)).append("\n");
        }
        return builder.toString();
    }

    default String getIndicationToMonth(LinkedHashMap<LocalDate, Double> history, int month) {
        if (history.isEmpty()) return "История пуста";
        for (LocalDate date : history.keySet()) {
            if (date.getMonth().getValue() == month) {
                return date + " - " + history.get(date);
            }
        }
        return "Нет показаний за такой месяц!";
    }

    default String getActualIndication(LocalDate lastDate, double lastValue) {
        if (lastDate == null || lastValue == 0.0) return "История пуста";
        return lastDate + " - " + lastValue;
    }
}
