package models;

/**
 * Created by mario on 27.5.2017..
 */
public class LoginHistoryViewModel {

    private String email;
    private String date;
    private String ip;

    public LoginHistoryViewModel() {

    }

    public LoginHistoryViewModel(Login login) {
        setEmail(login.getEmail());
        setIp(login.getIp());
        setDate(login.getDate());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
