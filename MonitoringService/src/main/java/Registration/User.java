package Registration;

import Indications.Indication;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * <h1> User </h1>
 * Класс реализует хранение данных пользователя
 */
@Getter
@Setter
public class User {
    /** Поле имя пользователя*/
    private String name;

    /** Поле id пользователя*/
    private int id;

    /** Поле лицевой счет пользователя*/
    private int accountNumber;

    /** Поле логин пользователя*/
    private String login;

    /** Поле пароль пользователя*/
    private String password;
    /** Поле список счетчиков с историями показаний*/
    private HashMap<Integer, Indication> counters;
    /** Поле колличество счетчиков */
    private int cntCounter;
    /** Поле даты регистрации*/
    private LocalDate regDate;

    /**
     * Конструктор - для создания объектра Пользователь с логином и паролем
     * @param login логин
     * @param password пароль
     */

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        counters = new HashMap<>(RegistrationService.cntCounter);
        for (Integer number : RegistrationService.counterMap.keySet()) {
            counters.put(number, new Indication(RegistrationService.counterMap.get(number)));
        }
        cntCounter = RegistrationService.cntCounter;
        regDate = LocalDate.now();
    }

    /**
     * Конструктор - для создания объекта Пользователь со всеми параметрами
     * @param name имя пользователя
     * @param id id пользователя
     * @param accountNumber лицевой счет
     * @param login логин
     * @param password пароль
     * @param coldWater счетчик холодной воды
     * @param hotWater счетчик горячей воды
     * @param heating счетчик отопления
     * @param regDate дата регистрации
     */
    public User(String name, int id, int accountNumber, String login, String password, Indication coldWater, Indication hotWater, Indication heating, LocalDate regDate) {
        this.name = name;
        this.id = id;
        this.accountNumber = accountNumber;
        this.login = login;
        this.password = password;
        cntCounter = 3;
        this.regDate = regDate;
        counters = new HashMap<>(3);
        for (Integer number : RegistrationService.counterMap.keySet()) {
            counters.put(number, new Indication(RegistrationService.counterMap.get(number)));
        }
        cntCounter = 3;
    }

    /**
     * Метод реализует добавление нового счетчика
     * @param name название нового счетчика
     */
    public void addCounter(String name) {
        cntCounter++;
        counters.put(cntCounter, new Indication(name));
    }

}
