package Indications;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1> RegistrationService </h1>
 * Тестовый класс для проверки методов класса Indication
 */

class IndicationTest {

    private static Indication coldWaterActual;
    private static Indication hotWaterActual;
    private static Indication heatingActual;
    private static Indication coldWaterExpected;
    private static Indication hotWaterExpected;
    private static Indication heatingExpected;


    /**
     * Метод создает тестовые счетчики и истории показаний
     */
    @BeforeEach
    void initIndications() {
        coldWaterActual = new Indication(IndicationName.COLD_WATER); coldWaterExpected = new Indication(IndicationName.COLD_WATER);
        hotWaterActual = new Indication(IndicationName.HOT_WATER); hotWaterExpected = new Indication(IndicationName.HOT_WATER);
        heatingActual = new Indication(IndicationName.HEATING); heatingExpected = new Indication(IndicationName.HEATING);


        LinkedHashMap<LocalDate, Double> history = new LinkedHashMap<>();
        for(int i = 1; i <= 8; i++) {
            history.put(LocalDate.of(2023, i, i), (double) i);
            if (i == 8) {
                coldWaterActual.setActualDate(LocalDate.of(2023, i, i)); coldWaterActual.setActualValue(i);
                hotWaterActual.setActualDate(LocalDate.of(2023, i, i)); hotWaterActual.setActualValue(i);
                heatingActual.setActualDate(LocalDate.of(2023, i, i)); heatingActual.setActualValue(i);
                coldWaterExpected.setActualDate(LocalDate.of(2023, i, i)); coldWaterExpected.setActualValue(i);
                heatingExpected.setActualDate(LocalDate.of(2023, i, i)); heatingExpected.setActualValue(i);
                hotWaterExpected.setActualDate(LocalDate.of(2023, i, i)); hotWaterExpected.setActualValue(i);
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
         coldWaterExpected.setActualDate(nowDateExpected);
         coldWaterExpected.setActualValue(10.0);

        Assertions.assertEquals(coldWaterExpected.getHistory(), coldWaterActual.getHistory());
        Assertions.assertEquals(coldWaterExpected.getActualDate(), coldWaterActual.getActualDate());
        assertEquals(coldWaterExpected.getActualValue(), coldWaterActual.getActualValue());
    }
    @Test
    @DisplayName("Добавления показания горячей воды")
    void addIndicationHotWater() {
        hotWaterActual.addIndication(10);

        LocalDate nowDateExpected = LocalDate.now();
        hotWaterExpected.getHistory().put(nowDateExpected, 10.0);
        hotWaterExpected.setActualDate(nowDateExpected);
        hotWaterExpected.setActualValue(10.0);

        Assertions.assertEquals(hotWaterExpected.getHistory(), hotWaterActual.getHistory());
        Assertions.assertEquals(hotWaterExpected.getActualDate(), hotWaterActual.getActualDate());
        assertEquals(hotWaterExpected.getActualValue(), hotWaterActual.getActualValue());

    }
    @Test
    @DisplayName("Добавления показания отопления")
    void addIndicationHeating() {
        heatingActual.addIndication(10);

        LocalDate nowDateExpected = LocalDate.now();
        heatingExpected.getHistory().put(nowDateExpected, 10.0);
        heatingExpected.setActualDate(nowDateExpected);
        heatingExpected.setActualValue(10.0);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getActualDate(), heatingActual.getActualDate());
        assertEquals(heatingExpected.getActualValue(), heatingActual.getActualValue());

    }

    @Test
    @DisplayName("Попытка добавить показание меньше предыдущего")
    void addIndicationHeatingToLess() {
        heatingActual.addIndication(5);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getActualDate(), heatingActual.getActualDate());
        assertEquals(heatingExpected.getActualValue(), heatingActual.getActualValue());
    }

    @Test
    @DisplayName("Попытка добавить показание повторно за месяц")
    void addIndicationHeatingToDouble() {
        heatingActual.addIndication(10);
        heatingExpected.addIndication(10);
        heatingActual.addIndication(11);

        Assertions.assertEquals(heatingExpected.getHistory(), heatingActual.getHistory());
        Assertions.assertEquals(heatingExpected.getActualDate(), heatingActual.getActualDate());
        assertEquals(heatingExpected.getActualValue(), heatingActual.getActualValue());
    }

    @Test
    @DisplayName("Получение всей истории")
    void getHistoryIndications() {
        String actual = heatingActual.getHistoryIndications();
        String expected = heatingExpected.getHistoryIndications();
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
        Indication newHeating = new Indication(IndicationName.HEATING);
        String actual = newHeating.getActualIndication();
        String expected = "История пуста";
        assertEquals(expected, actual);
    }


}