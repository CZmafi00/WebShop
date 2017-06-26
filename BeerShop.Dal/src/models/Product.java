package models;

/**
 * Created by mario on 18.5.2017..
 */
public class Product {

    public static String ID = "_id";
    public static String NAME = "name";
    public static String CATEGORY = "category";
    public static String IMGURL = "imgurl";
    public static String PRICE = "price";

    private String id;
    private String name;
    private String category;
    private String imgUrl;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
