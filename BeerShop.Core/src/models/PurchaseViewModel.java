package models;

import java.util.ArrayList;

/**
 * Created by mario on 27.5.2017..
 */
public class PurchaseViewModel {

    private String date;
    private double totalPrice;
    private String purchaseType;
    private ArrayList<CartItemViewModel> items;
    private String userName;

    public PurchaseViewModel() {

    }

    public PurchaseViewModel(Purchase purchase) {

        setDate(purchase.getDate());
        setTotalPrice(purchase.getTotalPrice());
        setPurchaseType(purchase.getPurchaseType());
        setUserName(purchase.getEmail());

        for(PurchaseItem item : purchase.getItems()) {

            CartItemViewModel cartItem = new CartItemViewModel();
            cartItem.setProductName(item.getItem());
            cartItem.setQty(item.getQty());
            cartItem.setPrice(item.getTotalPrice());

            getItems().add(cartItem);
        }
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
