package models;

/**
 * Created by mario on 17.6.2017..
 */
public class DeleteCartItemViewModel {

    private int newItemCount;
    private double newPrice;


    public int getNewItemCount() {
        return newItemCount;
    }

    public void setNewItemCount(int newItemCount) {
        this.newItemCount = newItemCount;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
