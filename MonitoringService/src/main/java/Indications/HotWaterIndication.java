package Indications;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;

public class HotWaterIndication implements Indication {
    private double lastValue;
    private LocalDate lastDate;
    private LinkedHashMap<LocalDate, Double> history;

    public HotWaterIndication() {
        history = new LinkedHashMap<>();
        lastValue = 0.0;
        lastDate = null;
    }

    @Override
    public void addIndication(double value) {
        Indication.super.addIndication(history, lastDate, lastValue, value);
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
        return Indication.super.getActualIndication(lastDate, lastValue);
    }

}
