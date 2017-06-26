package models;

/**
 * Created by mario on 18.6.2017..
 */
public class ModifyCartItemViewModel {

    private int newQty;
    private double newItemPrice;
    private double newPrice;

    public int getNewQty() {
        return newQty;
    }

    public void setNewQty(int newQty) {
        this.newQty = newQty;
    }

    public double getNewItemPrice() {
        return newItemPrice;
    }

    public void setNewItemPrice(double newItemPrice) {
        this.newItemPrice = newItemPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
