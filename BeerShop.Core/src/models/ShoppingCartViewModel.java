package models;

import java.util.ArrayList;

/**
 * Created by mario on 27.5.2017..
 */
public class ShoppingCartViewModel {

    private ArrayList<CartItemViewModel> items;
    private String purchaseType;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String zip;
    private String UserRole;

    public ArrayList<CartItemViewModel> getItems() {

        if (items == null)
            items = new ArrayList<>();

        return items;
    }

    public void setItems(ArrayList<CartItemViewModel> items) {
        this.items = items;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
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

    public double getTotalPrice() {

        int total = 0;

        for (CartItemViewModel item : getItems()) {
            total += item.getQty() * item.getPrice();
        }

        return total;
    }

    public int getNumberOfItems() {

        return getItems().size();
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
}
