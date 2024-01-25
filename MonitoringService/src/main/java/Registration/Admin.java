package Registration;

import Registration.User;

import java.time.LocalDate;

public class Admin {
    private String login;
    private String password;
    private LocalDate dateRegistration;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
        dateRegistration = LocalDate.now();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }
}
