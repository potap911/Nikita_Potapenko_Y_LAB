package Registration;

import Indications.Indication;
import Indications.IndicationName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    /** Поле счетчик холодной воды*/
    private Indication coldWater;
    /** Поле счетчик горячей воды*/
    private Indication hotWater;
    /** Поле счетчик отопления*/
    private Indication heating;
    /** Поле дата регистрации*/
    private LocalDate regDate;

    /**
     * Конструктор - для создания объектра Пользователь с логином и паролем
     * @param login логин
     * @param password пароль
     */

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        coldWater = new Indication(IndicationName.COLD_WATER);
        hotWater = new Indication(IndicationName.HOT_WATER);
        heating = new Indication(IndicationName.HEATING);
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
        this.coldWater = new Indication(IndicationName.COLD_WATER);
        this.hotWater = new Indication(IndicationName.HOT_WATER);
        this.heating = new Indication(IndicationName.HEATING);
        this.regDate = regDate;
    }
}
