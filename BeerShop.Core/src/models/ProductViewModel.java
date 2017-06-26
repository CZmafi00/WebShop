package models;

/**
 * Created by mario on 27.5.2017..
 */
public class ProductViewModel {

    private String productId;
    private String name;
    private double price;
    private String imgUrl;
    private String category;

    public ProductViewModel() {

    }

    public ProductViewModel(Product product) {
        setProductId(product.getId());
        setName(product.getName());
        setPrice(product.getPrice());
        setCategory(product.getCategory());
        setImgUrl(product.getImgUrl());
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
