package models;

/**
 * Created by mario on 18.5.2017..
 */
public class Login {

    public static String ID = "_id";
    public static String EMAIL = "email";
    public static String DATE = "date";
    public static String IP = "ip";

    private String id;
    private String email;
    private String date;
    private String ip;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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
