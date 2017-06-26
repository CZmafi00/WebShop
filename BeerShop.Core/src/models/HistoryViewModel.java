package models;

import java.util.ArrayList;

/**
 * Created by mario on 27.5.2017..
 */
public class HistoryViewModel {

    private String date;
    private String userName;
    private ArrayList<PurchaseViewModel> purchases;
    private ArrayList<LoginHistoryViewModel> logins;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<PurchaseViewModel> getPurchases() {

        if (purchases == null)
            purchases = new ArrayList<>();

        return purchases;
    }

    public void setPurchases(ArrayList<PurchaseViewModel> purchases) {
        this.purchases = purchases;
    }

    public ArrayList<LoginHistoryViewModel> getLogins() {

        if (logins == null)
            logins = new ArrayList<>();

        return logins;
    }

    public void setLogins(ArrayList<LoginHistoryViewModel> logins) {
        this.logins = logins;
    }
}
