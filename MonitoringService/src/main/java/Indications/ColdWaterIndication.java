package Indications;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;

public class ColdWaterIndication extends Indication{
    private double lastValue;
    private LocalDate lastDate;
    private LinkedHashMap<LocalDate, Double> history;

    public ColdWaterIndication() {
        history = new LinkedHashMap<>();
    }

    @Override
    public void addIndication(double value) {
        super.addIndication(value);
    }

    @Override
    public LinkedHashMap<LocalDate, Double> getHistory() {
        return super.getHistory();
    }

    @Override
    public double getLastValue() {
        return super.getLastValue();
    }

    @Override
    public LocalDate getLastDate() {
        return super.getLastDate();
    }

    @Override
    public void printHistory() {
        super.printHistory();
    }
}
