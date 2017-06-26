package repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import customexceptions.DatabaseNotFoundException;
import helpers.ConfigManager;
import models.Product;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 18.5.2017..
 */
public class ProductRepository implements Repository<Product> {

    private String COLLECTION_NAME = "beerCollection";

    private MongoClient client;
    private MongoDatabase db;

    public ProductRepository(MongoDatabase db, MongoClient client) {

        this.db = db;
        this.client = client;
    }

    public List<Product> getAll() {

        ArrayList<Product> allProducts = new ArrayList<>();

        getCollection().find().forEach((Block<Document>) (document) -> {
            Product product = null;

            try {

                product = new Product();
                product.setId(document.getObjectId(Product.ID).toString());
                product.setName(document.getString(Product.NAME));
                product.setCategory(document.getString(Product.CATEGORY));
                product.setImgUrl(document.getString(Product.IMGURL));
                product.setPrice(Double.parseDouble(document.getString(Product.PRICE)));

            } catch (ClassCastException nfe) {
                if (product != null) {
                    product.setPrice(0);
                }
            }

            allProducts.add(product);
        });

        return allProducts;
    }

    public void create(Product object) {

        Document document = new Document();
        document.put(Product.NAME, object.getName());
        document.put(Product.IMGURL, object.getImgUrl());
        document.put(Product.PRICE, object.getPrice());
        document.put(Product.CATEGORY, object.getCategory());

        getCollection().insertOne(document);
    }

    public void update(Product object) {

        BasicDBObject searchQuery = new BasicDBObject(Product.ID, new ObjectId(object.getId()));

        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append(Product.NAME, object.getName());
        updateFields.append(Product.CATEGORY, object.getCategory());
        updateFields.append(Product.PRICE, object.getPrice());
        updateFields.append(Product.IMGURL, object.getImgUrl());

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        getCollection().updateOne(searchQuery, setQuery);
    }

    public void delete(Product object) {

        getCollection().deleteOne(new Document(Product.ID, new ObjectId(object.getId())));
    }

    private MongoCollection<Document> getCollection() {
        MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);

        return collection;
    }
}
