package Indications;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;

public class HeatingIndication implements Indication {
    private double lastValue;
    private LocalDate lastDate;
    private LinkedHashMap<LocalDate, Double> history;


    public HeatingIndication() {
        history = new LinkedHashMap<>();
        lastValue = 0.0;
        lastDate = null;
    }

    @Override
    public void addIndication(double value) {
        lastDate = Indication.super.addIndication(history, lastDate, lastValue, value);
        if (lastDate != null) lastValue = history.get(lastDate);
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

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public LinkedHashMap<LocalDate, Double> getHistory() {
        return history;
    }

    public void setHistory(LinkedHashMap<LocalDate, Double> history) {
        this.history = history;
    }

}
