package Indications;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;

public abstract class Indication {
    private double lastValue;
    private Date lastDate;
    private LinkedHashMap<Date, Double> history;

    public void addIndication(double value) {
        Date currDate = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        if (currDate.before(lastDate)) {
            lastDate = new Date();
            lastValue = value;
            history.put(lastDate, lastValue);
        } else System.out.println("Нельзя добавить новое показание. С момента последнего показания не прошел месяц");
    }

    public LinkedHashMap<Date, Double> getHistory() {
        return history;
    }

    public double getLastValue() {
        return lastValue;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void printHistory() {
        for (Date date : history.keySet()) {
            System.out.println(date + " - " + history.get(date));
        }
    }



}
