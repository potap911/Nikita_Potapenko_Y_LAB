package Registration;

import Indications.ColdWaterIndication;
import Indications.HeatingIndication;
import Indications.HotWaterIndication;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private ColdWaterIndication coldWaterIndication;
    private HotWaterIndication hotWaterIndication;
    private HeatingIndication heatingIndication;
    private Date dateRegistration;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        coldWaterIndication = new ColdWaterIndication();
        hotWaterIndication = new HotWaterIndication();
        heatingIndication = new HeatingIndication();
        dateRegistration = new Date();
    }

    public ColdWaterIndication getColdWaterIndication() {
        return coldWaterIndication;
    }

    public HotWaterIndication getHotWaterIndication() {
        return hotWaterIndication;
    }

    public HeatingIndication getHeatingIndication() {
        return heatingIndication;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }


}
