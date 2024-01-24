package Registration;

import Registration.User;

import java.util.ArrayList;
import java.util.Date;

public class Admin {
    private String login;
    private String password;
    private Date dateRegistration;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
        dateRegistration = new Date();
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }
}
