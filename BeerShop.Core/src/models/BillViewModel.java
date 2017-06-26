package models;

import java.util.ArrayList;

/**
 * Created by mario on 19.6.2017..
 */
public class BillViewModel {

    private String date;
    private double totalPrice;
    private String purchaseType;
    private ArrayList<CartItemViewModel> items;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String zip;
    private String UserRole;

    public BillViewModel() {

    }

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

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public ArrayList<CartItemViewModel> getItems() {

        if (items == null)
            items = new ArrayList<>();

        return items;
    }

    public void setItems(ArrayList<CartItemViewModel> items) {
        this.items = items;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getFullName() { return name + " " + lastName; }

    public String getFullAddress() {return address + " " + zip + " " + city;}
}
