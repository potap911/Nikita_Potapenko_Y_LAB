package Indications;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashMap;

/**
 * Класс счетчика отопления с полями <b>lastValue</b>, <b>lastDate</b>, <b>history</b>,
 * Реализует интерфейс Indication
 */

@Getter
@Setter
public class HeatingIndication implements Indication {
    /** Поле актуальное значение*/
    private double actualValue;
    /** Поле актуальная дата*/
    private LocalDate actualDate;
    /** Поле история показаний*/
    private LinkedHashMap<LocalDate, Double> history;

    public HeatingIndication() {
        history = new LinkedHashMap<>();
        actualValue = 0.0;
        actualDate = null;
    }

    @Override
    public void addIndication(double value) {
        actualDate = Indication.super.addIndication(history, actualDate, actualValue, value);
        if (actualDate != null) actualValue = history.get(actualDate);
    }

    @Override
    public String getHistoryIndications() {
        return Indication.super.getHistoryIndications(history);
    }

    @Override
    public String getIndicationToMonth(int month) {
        return Indication.super.getIndicationToMonth(history, month);
    }

    @Override
    public String getActualIndication() {
        return Indication.super.getActualIndication(actualDate, actualValue);
    }

}
