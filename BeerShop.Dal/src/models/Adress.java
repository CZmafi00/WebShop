package models;

/**
 * Created by mario on 18.5.2017..
 */
public class Adress {

    public static String ADRESS = "adress";
    public static String CITY = "city";
    public static String ZIP = "zip";

    private String adress;
    private String city;
    private int zip;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
