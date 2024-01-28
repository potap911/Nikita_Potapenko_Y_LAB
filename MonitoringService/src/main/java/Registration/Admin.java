package Registration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <h1> Admin </h1>
 * Класс реализует хранение данных администратора
 */

@Getter
@Setter
public class Admin {
    /** Поле имя администратора*/
    private String name;
    /** Поле id администратора*/
    private int id;
    /** Поле логин администратора*/
    private String login;
    /** Поле пароль администратора*/
    private String password;
    /** Поле дата регистрации администратора*/
    private LocalDate regDate;

    /**
     * Конструктор для создания пользователя Администратор с логином и паролем
     * @param login логин
     * @param password пароль
     */
    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
        regDate = LocalDate.now();
    }

    /**
     * Конструктор для создания пользователя Администратор со всеми параметрами
     * @param name имя администратора
     * @param id id администратора
     * @param login логин
     * @param password - пароль
     */

    public Admin(String name, int id, String login, String password) {
        this.name = name;
        this.id = id;
        this.login = login;
        this.password = password;
        regDate = LocalDate.now();
    }

}
