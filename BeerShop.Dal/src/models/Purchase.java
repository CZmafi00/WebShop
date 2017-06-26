package models;

import enumerations.PurchaseType;

import java.util.ArrayList;

/**
 * Created by mario on 18.5.2017..
 */
public class Purchase {

    public static String DATE = "date";
    public static String TOTALPRICE = "totalPrice";
    public static String PURCHASETYPE = "purchaseType";
    public static String ITEMS = "items";
    public static String ADRESS = "adress";
    public static String NAME = "name";
    public static String LASTNAME = "lastName";
    public static String EMAIL = "email";

    private String date;
    private double totalPrice;
    private String purchaseType;
    private ArrayList<PurchaseItem> items;
    private Adress adress;
    private String name;
    private String lastName;
    private String email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<PurchaseItem> getItems() {

        if (items == null)
            items = new ArrayList<>();

        return items;
    }

    public void setItems(ArrayList<PurchaseItem> items) {
        this.items = items;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType.toString();
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
