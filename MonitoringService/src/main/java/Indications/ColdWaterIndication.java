package Indications;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;

public class ColdWaterIndication implements Indication{
    private double lastValue;
    private LocalDate lastDate;
    private LinkedHashMap<LocalDate, Double> history;

    public ColdWaterIndication() {
        history = new LinkedHashMap<>();
        lastValue = 0.0;
        lastDate = null;
    }

    @Override
    public void addIndication(double value) {
        Indication.super.addIndication(history, lastDate, lastValue, value);
    }

    @Override
    public void printHistory() {
        Indication.super.printHistory(history);
    }

    @Override
    public void printIndicationsToMonth(int month) {
        Indication.super.printIndicationsToMonth(history, month);
    }

    @Override
    public void printActualIndications() {
        Indication.super.printActualIndications(lastDate, lastValue);
    }
}
