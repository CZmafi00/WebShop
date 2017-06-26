package models;

/**
 * Created by mario on 18.5.2017..
 */
public class PurchaseItem {

    public static String ITEM = "item";
    public static String TOTALPRICE = "totalPrice";
    public static String QTY = "qty";

    private String item;
    private double totalPrice;
    private int qty;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
