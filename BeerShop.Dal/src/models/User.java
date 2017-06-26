package models;

import enumerations.UserRole;

import java.util.ArrayList;

/**
 * Created by mario on 18.5.2017..
 */
public class User {

    public static String ID = "_id";
    public static String NAME = "name";
    public static String LASTNAME = "lastName";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String ADRESS = "adress";
    public static String ROLE = "role";
    public static String PURCHASES = "purchases";

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Adress adress;
    private String role;
    private ArrayList<Purchase> purchases;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getType() {
        return getRole();
    }

    public void setType(UserRole role) {
        this.setRole(role.toString());
    }

    public void setType(String role) {
        this.setRole(role);
    }

    public ArrayList<Purchase> getPurchases() {

        if (purchases == null)
            purchases = new ArrayList<>();

        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
