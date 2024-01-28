package Indications;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashMap;

/**
 * <h1> Indication </h1>
 * Класс реализует хранение показаний счетчика, а также добавление и получение информации о показаниях
 */

@Getter
@Setter
public class Indication {
    /** Поле название показания*/
    private String name;
    /** Поле актуальная дата*/
    private LocalDate actualDate;
    /** Поле актуальное значение*/
    private double actualValue;

    /** Поле история показаний*/
    private LinkedHashMap<LocalDate, Double> history;

    /**
     * Констуктор для создания объекта класса Indication
     * @param name название счетчика
     */
    public Indication(String name) {
        this.name = name;
        actualDate = null;
        actualValue = 0.0;
        history = new LinkedHashMap<>();
    }

    /**
     * Метод реализует добавление нового показания в историю. Валидация переданного значения. Валидация даты добавления показания
     * @param value переданное показание
     */
    public void addIndication(double value) {
        LocalDate nowDate = LocalDate.now();

        if (history.isEmpty()) {
            actualDate = nowDate;
            actualValue = value;
            history.put(nowDate, actualValue);
            System.out.println("Показание успешно добавлено!");
        }
        else if (nowDate.getMonth().getValue() > actualDate.getMonth().getValue() || nowDate.getYear() > actualDate.getYear()) {
                if (actualValue <= value) {
                    actualDate = nowDate;
                    actualValue = value;
                    history.put(nowDate, actualValue);
                    System.out.println("Показание успешно добавлено!");
                } else System.out.println("Переданные показания меньше актуальных!");
            } else System.out.println("Нельзя добавить новое показание. В этом месяце показания уже поданы!");
    }

    /**
     * Метод реализует получение информации о истории подачи показаний
     * @return строка - список истории подачи показаний или отсутствие показаний
     */

    public String getHistoryIndications() {
        if (history.isEmpty()) return "История пуста";
        StringBuilder builder = new StringBuilder();
        for (LocalDate date : history.keySet()) {
            builder.append("\t").append(date).append(" - ").append(history.get(date)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Метод реализует получение информации о поданных показаниях за конкретный месяц
     * @param month запрашиваемый месяц
     * @return строка - показание за конкретный месяц или История пуста или отсутствуие показаний за месяц
     */

    public String getIndicationToMonth(int month) {
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
     * @return строка - актуальные показания или История пуста.
     */

    public String getActualIndication() {
        if (actualDate == null || actualValue == 0.0) return "История пуста";
        return actualDate + " - " + actualValue;
    }
}
