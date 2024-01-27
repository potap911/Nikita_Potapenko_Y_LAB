package Indications;

import java.time.LocalDate;
import java.util.LinkedHashMap;


interface Indication {
    void addIndication(double value);
    String getHistoryIndications();
    String getIndicationToMonth(int month);
    String getActualIndication();

    /**
     * Метод реализует добавление нового показания в историю. Валидация переданного значения. Валидация даты добавления показания
     * @param history - история показаний
     * @param actualDate - актуальная дата
     * @param actualValue - актуальное значение
     * @param value - переданное показание
     * @return - возвращается новая актуальная дата, в случае невозможности добавления показания возвращается null
     */
    default LocalDate addIndication(LinkedHashMap<LocalDate, Double> history, LocalDate actualDate, double actualValue, double value) {
        LocalDate nowDate = LocalDate.now();

        if (history.isEmpty()) {
            actualValue = value;
            history.put(nowDate, actualValue);
            System.out.println("Показание успешно добавлено!");
        }
        else if (nowDate.getMonth().getValue() > actualDate.getMonth().getValue() || nowDate.getYear() > actualDate.getYear()) {
                if (actualValue <= value) {
                    actualValue = value;
                    history.put(nowDate, actualValue);
                    System.out.println("Показание успешно добавлено!");
                } else {
                    System.out.println("Переданные показания меньше актуальных!");
                    return actualDate;
                }
            } else {
            System.out.println("Нельзя добавить новое показание. В этом месяце показания уже поданы!");
            return actualDate;
        }

        return nowDate;
    }

    /**
     * Метод реализует получение информации о истории подачи показаний
     * @param history - история показаний
     * @return строка - список истории подачи показаний или отсутствие показаний
     */

    default String getHistoryIndications(LinkedHashMap<LocalDate, Double> history) {
        if (history.isEmpty()) return "История пуста";
        StringBuilder builder = new StringBuilder();
        for (LocalDate date : history.keySet()) {
            builder.append("\t").append(date).append(" - ").append(history.get(date)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Метод реализует получение информации о поданных показаниях за конкретный месяц
     * @param history - история показаний
     * @param month - запрашиваемый месяц
     * @return строка - показание за конкретный месяц или История пуста или отсутствуие показаний за месяц
     */

    default String getIndicationToMonth(LinkedHashMap<LocalDate, Double> history, int month) {
        if (history.isEmpty()) return "История пуста";
        for (LocalDate date : history.keySet()) {
            if (date.getMonth().getValue() == month) {
                return date + " - " + history.get(date);
            }
        }
        return "Нет показаний за такой месяц!";
    }

    /**
     * Метод реализует получение информации об актуальных показаниях
     * @param actualDate - актуальная дата
     * @param actualValue - актуальное значение
     * @return строка - актуальные показания или История пуста.
     */

    default String getActualIndication(LocalDate actualDate, double actualValue) {
        if (actualDate == null || actualValue == 0.0) return "История пуста";
        return actualDate + " - " + actualValue;
    }
}
