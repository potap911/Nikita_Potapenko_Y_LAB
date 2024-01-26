package Indications;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndicationTest {

    private static ColdWaterIndication coldWaterActual;
    private static HotWaterIndication hotWaterActual;
    private static HeatingIndication heatingActual;
    private static ColdWaterIndication coldWaterExpected;
    private static HotWaterIndication hotWaterExpected;
    private static HeatingIndication heatingExpected;

    @BeforeEach
    void initIndications() {
        coldWaterActual = new ColdWaterIndication(); coldWaterExpected = new ColdWaterIndication();
        hotWaterActual = new HotWaterIndication(); hotWaterExpected = new HotWaterIndication();
        heatingActual = new HeatingIndication(); heatingExpected = new HeatingIndication();


        LinkedHashMap<LocalDate, Double> history = new LinkedHashMap<>();
        for(int i = 1; i <= 8; i++) {
            history.put(LocalDate.of(2023, i, i), (double) i);
            if (i == 8) {
                coldWaterActual.setLastDate(LocalDate.of(2023, i, i)); coldWaterActual.setLastValue(i);
                hotWaterActual.setLastDate(LocalDate.of(2023, i, i)); hotWaterActual.setLastValue(i);
                heatingActual.setLastDate(LocalDate.of(2023, i, i)); heatingActual.setLastValue(i);
                coldWaterExpected.setLastDate(LocalDate.of(2023, i, i)); coldWaterExpected.setLastValue(i);
                heatingExpected.setLastDate(LocalDate.of(2023, i, i)); heatingExpected.setLastValue(i);
                hotWaterExpected.setLastDate(LocalDate.of(2023, i, i)); hotWaterExpected.setLastValue(i);
            }
        }
        coldWaterActual.setHistory(history); coldWaterExpected.setHistory(history);
        hotWaterActual.setHistory(history); hotWaterExpected.setHistory(history);
        heatingActual.setHistory(history); heatingExpected.setHistory(history);
    }
    @Test
    @DisplayName("Добавления показания холодной воды")
    void addIndicationColdWater() {
         coldWaterActual.addIndication(10);

         LocalDate nowDateExpected = LocalDate.now();
         coldWaterExpected.getHistory().put(nowDateExpected, 10.0);
         coldWaterExpected.setLastDate(nowDateExpected);
         coldWaterExpected.setLastValue(10.0);

        Assertions.assertEquals(coldWaterExpected.getHistory(), coldWaterActual.getHistory());
        Assertions.assertEquals(coldWaterExpected.getLastDate(), coldWaterActual.getLastDate());
        assertEquals(coldWaterExpected.getLastValue(), coldWaterActual.getLastValue());
    }
    @Test
    @DisplayName("Добавления показания горячей воды")
    void addIndicationHotWater() {
        hotWaterActual.addIndication(10);

        LocalDate nowDateExpected = LocalDate.now();
        hotWaterExpected.getHistory().put(nowDateExpected, 10.0);
        hotWaterExpected.setLastDate(nowDateExpected);
        hotWaterExpected.setLastValue(10.0);

        Assertions.assertEquals(hotWaterExpected.getHistory(), hotWaterActual.getHistory());
        Assertions.assertEquals(hotWaterExpected.getLastDate(), hotWaterActual.getLastDate());
        assertEquals(hotWaterExpected.getLastValue(), hotWaterActual.getLastValue());

    }
    @Test
    @DisplayName("Добавления показания отопления")
    void addIndicationHeating() {
        heatingActual.addIndication(10);

        LocalDate nowDateExpected = LocalDate.now();
        heatingExpected.getHistory().put(nowDateExpected, 10.0);
        heatingExpected.setLastDate(nowDateExpected);
        heatingExpected.setLastValue(10.0);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getLastDate(), heatingActual.getLastDate());
        assertEquals(heatingExpected.getLastValue(), heatingActual.getLastValue());

    }

    @Test
    @DisplayName("Попытка добавить показание меньше предыдущего")
    void addIndicationHeatingToLess() {
        heatingActual.addIndication(5);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getLastDate(), heatingActual.getLastDate());
        assertEquals(heatingExpected.getLastValue(), heatingActual.getLastValue());
    }

    @Test
    @DisplayName("Попытка добавить показание повторно за месяц")
    void addIndicationHeatingToDouble() {
        heatingActual.addIndication(10);
        heatingExpected.addIndication(10);
        heatingActual.addIndication(11);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getLastDate(), heatingActual.getLastDate());
        assertEquals(heatingExpected.getLastValue(), heatingActual.getLastValue());
    }

    @Test
    @DisplayName("Получение всей истории")
    void getHistoryIndications() {
        String actual = heatingActual.getHistoryIndications();
        String expected = heatingExpected.getHistoryIndications(heatingExpected.getHistory());
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Получения показания за конкретный месяц")
    void getIndicationToMonth1() {
        String actual = heatingActual.getIndicationToMonth(6);
        String expected = "2023-06-06 - 6.0";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Попытка получить показание за несуществующий месяц")
    void getIndicationToMonth2() {
        String actual = heatingActual.getIndicationToMonth(9);
        String expected = "Нет показаний за такой месяц!";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Получение актуальных показаний")
    void getActualIndication() {
        String actual = heatingActual.getActualIndication();
        String expected = "2023-08-08 - 8.0";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Получение актуальных показаний из пустого списка")
    void getActualIndicationAbsent() {
        HeatingIndication newHeating = new HeatingIndication();
        String actual = newHeating.getActualIndication();
        String expected = "История пуста";
        assertEquals(expected, actual);
    }


}