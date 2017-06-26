package repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import customexceptions.DatabaseNotFoundException;
import helpers.ConfigManager;
import models.Login;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 18.5.2017..
 */
public class LoginRepository implements Repository<Login> {

    private String COLLECTION_NAME = "loginCollection";

    private MongoClient client;
    private MongoDatabase db;

    public LoginRepository(MongoDatabase db, MongoClient client) {

        this.db = db;
        this.client = client;
    }

    public List<Login> getAll() {
        ArrayList<Login> logins = new ArrayList<>();

        getCollection().find().forEach((Block<Document>) (document) -> {

            Login login = new Login();
            login.setDate(document.getObjectId(Login.ID).toString());
            login.setDate(document.getString(Login.DATE));
            login.setEmail(document.getString(Login.EMAIL));
            login.setIp(document.getString(Login.IP));

            logins.add(login);
        });

        return logins;
    }

    public void create(Login object) {

        Document document = new Document();
        document.put(Login.EMAIL, object.getEmail());
        document.put(Login.DATE, object.getDate());
        document.put(Login.IP, object.getIp());

        getCollection().insertOne(document);
    }

    public void update(Login object) {

        BasicDBObject searchQuery = new BasicDBObject(Login.ID, new ObjectId(object.getId()));

        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append(Login.EMAIL, object.getEmail());
        updateFields.append(Login.DATE, object.getDate());
        updateFields.append(Login.IP, object.getIp());

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        getCollection().updateOne(searchQuery, setQuery);
    }

    public void delete(Login object) {
        getCollection().deleteOne(new Document(Login.ID, new ObjectId(object.getId())));
    }

    private MongoCollection<Document> getCollection() {
        MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);

        return collection;
    }
}
