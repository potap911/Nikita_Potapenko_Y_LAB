package Registration;

import Indications.ColdWaterIndication;
import Indications.HeatingIndication;
import Indications.HotWaterIndication;

import java.time.LocalDate;

public class User {
    private String login;
    private String password;
    private ColdWaterIndication coldWater;
    private HotWaterIndication hotWater;
    private HeatingIndication heating;
    private LocalDate dateRegistration;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        coldWater = new ColdWaterIndication();
        hotWater = new HotWaterIndication();
        heating = new HeatingIndication();
        dateRegistration = LocalDate.now();
    }

    public ColdWaterIndication getColdWaterIndication() {
        return coldWater;
    }

    public HotWaterIndication getHotWaterIndication() {
        return hotWater;
    }

    public HeatingIndication getHeatingIndication() {
        return heating;
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
