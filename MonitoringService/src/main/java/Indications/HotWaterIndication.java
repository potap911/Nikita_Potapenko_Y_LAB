package Indications;

import java.util.Date;
import java.util.LinkedHashMap;

public class HotWaterIndication extends Indication {
    private double lastValue;
    private Date lastDate;
    private LinkedHashMap<Date, Double> history;

    public HotWaterIndication() {
        history = new LinkedHashMap<>();
    }

    @Override
    public void addIndication(double value) {
        super.addIndication(value);
    }

    @Override
    public LinkedHashMap<Date, Double> getHistory() {
        return super.getHistory();
    }

    @Override
    public double getLastValue() {
        return super.getLastValue();
    }

    @Override
    public Date getLastDate() {
        return super.getLastDate();
    }

    @Override
    public void printHistory() {
        super.printHistory();
    }
}
