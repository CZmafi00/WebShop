package models;

/**
 * Created by mario on 27.5.2017..
 */
public class CartItemViewModel {

    private String IMAGELOCATION = "content/images/beer/";

    private String productId;
    private String productName;
    private String imageUrl;
    private int qty;
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return IMAGELOCATION + imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getTotalPrice() { return getPrice() * getQty(); }
}
