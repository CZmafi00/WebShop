package repositories;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import enumerations.UserRole;
import models.Adress;
import models.Purchase;
import models.PurchaseItem;
import models.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 18.5.2017..
 */
public class UserRepository implements Repository<User> {

    private String COLLECTION_NAME = "usersCollection";

    private MongoClient client;
    private MongoDatabase db;

    public UserRepository(MongoDatabase db, MongoClient client) {

        this.db = db;
        this.client = client;
    }

    public List<User> getAll() {

        ArrayList<User> users = new ArrayList<>();

        getCollection().find().forEach((Block<Document>) (document) -> {

            User user = new User();
            user.setId(document.getObjectId(User.ID).toString());
            user.setName(document.getString(User.NAME));
            user.setLastName(document.getString(User.LASTNAME));
            user.setEmail(document.getString(User.EMAIL));
            user.setPassword(document.getString(User.PASSWORD));
            user.setRole(document.getString(User.ROLE));

            if (document.get(User.ADRESS) != null)
                user.setAdress(getAdress((Document) document.get(User.ADRESS)));

            if (document.get(User.PURCHASES) != null)
                user.setPurchases(getPurchases((ArrayList<Document>) document.get(User.PURCHASES)));

            users.add(user);
        });


        return users;
    }

    public void create(User object) {

        Document document = new Document();
        document.put(User.NAME, object.getName());
        document.put(User.LASTNAME, object.getLastName());
        document.put(User.ROLE, object.getRole());
        document.put(User.EMAIL, object.getEmail());
        document.put(User.PASSWORD, object.getPassword());
        document.put(User.ADRESS, object.getAdress());
        document.put(User.PURCHASES, object.getPurchases());

        getCollection().insertOne(document);
    }

    public void update(User object) {

        if (object.getRole().equals(UserRole.Guest.toString()))
            updateGuest(object);
        else if (object.getRole().equals(UserRole.Customer.toString()))
            updateCustomer(object);

    }

    public void delete(User object) {

        getCollection().deleteOne(new Document(User.ID, new ObjectId(object.getId())));
    }

    private MongoCollection<Document> getCollection() {
        MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);

        return collection;
    }

    private ArrayList<Purchase> getPurchases(ArrayList<Document> documents) {

        ArrayList<Purchase> purchases = new ArrayList<>();

        for (Document doc : documents) {

            Purchase purchase = new Purchase();
            purchase.setDate(doc.getString(Purchase.DATE));
            purchase.setPurchaseType(doc.getString(Purchase.PURCHASETYPE));
            try {
                purchase.setTotalPrice(doc.getDouble(Purchase.TOTALPRICE));
            } catch (NumberFormatException nfe) {
                purchase.setTotalPrice(0);
            } catch (ClassCastException cce) {
                purchase.setTotalPrice(0);
            }

            if (doc.get(Purchase.NAME) != null)
                purchase.setName(doc.getString(Purchase.NAME));
            if (doc.get(Purchase.LASTNAME) != null)
                purchase.setLastName(doc.getString(Purchase.LASTNAME));
            if (doc.get(Purchase.EMAIL) != null)
                purchase.setEmail(doc.getString(Purchase.EMAIL));
            if (doc.get(Purchase.ADRESS) != null)
                purchase.setAdress(getAdress((Document) doc.get(Purchase.ADRESS)));

            purchase.setItems(getPurchaseItems((ArrayList<Document>) doc.get(Purchase.ITEMS)));

            purchases.add(purchase);
        }

        return purchases;
    }

    private ArrayList<PurchaseItem> getPurchaseItems(ArrayList<Document> documents) {

        ArrayList<PurchaseItem> items = new ArrayList<>();

        for (Document doc : documents) {

            PurchaseItem item = new PurchaseItem();

            item.setItem(doc.getString(PurchaseItem.ITEM));

            try {
                item.setQty(doc.getInteger(PurchaseItem.QTY));
            } catch (NumberFormatException nfe) {
                item.setQty(0);
            } catch (ClassCastException cce) {
                item.setQty(0);
            }

            try {
                item.setTotalPrice(doc.getDouble(PurchaseItem.TOTALPRICE));
            } catch (NumberFormatException nfe) {
                item.setTotalPrice(0);
            } catch (ClassCastException cce) {
                item.setTotalPrice(0);
            }

            items.add(item);
        }

        return items;
    }

    private Adress getAdress(Document adressDocument) {

        Adress adress = new Adress();

        adress.setAdress(adressDocument.getString(Adress.ADRESS));
        adress.setCity(adressDocument.getString(Adress.CITY));
        try {
            if (adressDocument.getString(Adress.ZIP) != null)
                adress.setZip(Integer.parseInt(adressDocument.getString(Adress.ZIP)));
            else
                adress.setZip(0);

        } catch (NumberFormatException nfe) {
            adress.setZip(0);
        } catch (ClassCastException cce) {
            adress.setZip(0);
        }

        return adress;
    }

    private void updateGuest(User object) {

        BasicDBObject searchQuery = new BasicDBObject(User.ID, new ObjectId(object.getId()));

        BasicDBObject userObject = new BasicDBObject();
        BasicDBList purchasesObject = new BasicDBList();

        userObject.append(User.NAME, object.getName());
        userObject.append(User.EMAIL, object.getEmail());
        userObject.append(User.ROLE, object.getRole());

        for (Purchase purchase : object.getPurchases()) {

            BasicDBObject purchaseObject = new BasicDBObject();

            purchaseObject.append(Purchase.DATE, purchase.getDate());
            purchaseObject.append(Purchase.TOTALPRICE, purchase.getTotalPrice());
            purchaseObject.append(Purchase.PURCHASETYPE, purchase.getPurchaseType());
            purchaseObject.append(Purchase.NAME, purchase.getName());
            purchaseObject.append(Purchase.LASTNAME, purchase.getLastName());
            purchaseObject.append(Purchase.EMAIL, purchase.getEmail());

            BasicDBObject adressObject = new BasicDBObject();
            adressObject.append(Adress.ADRESS, purchase.getAdress().getAdress());
            adressObject.append(Adress.CITY, purchase.getAdress().getCity());
            adressObject.append(Adress.ZIP, purchase.getAdress().getZip());

            BasicDBList itemsObject = new BasicDBList();

            for (PurchaseItem item : purchase.getItems()) {

                BasicDBObject purchaseItemObject = new BasicDBObject();

                purchaseItemObject.append(PurchaseItem.ITEM, item.getItem());
                purchaseItemObject.append(PurchaseItem.QTY, item.getQty());
                purchaseItemObject.append(PurchaseItem.TOTALPRICE, item.getTotalPrice());

                itemsObject.add(purchaseItemObject);
            }

            purchaseObject.append(Purchase.ADRESS, adressObject);
            purchaseObject.append(Purchase.ITEMS, itemsObject);
            purchasesObject.add(purchaseObject);
        }

        userObject.append(User.PURCHASES, purchasesObject);

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", userObject);

        getCollection().updateOne(searchQuery, setQuery);
    }

    private void updateCustomer(User object) {

        BasicDBObject searchQuery = new BasicDBObject(User.ID, new ObjectId(object.getId()));

        BasicDBObject userObject = new BasicDBObject();
        BasicDBObject adressObject = new BasicDBObject();
        BasicDBList purchasesObject = new BasicDBList();

        userObject.append(User.NAME, object.getName());
        userObject.append(User.LASTNAME, object.getLastName());
        userObject.append(User.EMAIL, object.getEmail());
        userObject.append(User.PASSWORD, object.getPassword());
        userObject.append(User.ROLE, object.getRole());

        adressObject.append(Adress.ADRESS, object.getAdress().getAdress());
        adressObject.append(Adress.CITY, object.getAdress().getCity());
        adressObject.append(Adress.ZIP, object.getAdress().getZip());

        userObject.append(User.ADRESS, adressObject);

        for (Purchase purcahse : object.getPurchases()) {

            BasicDBObject purchaseObject = new BasicDBObject();
            BasicDBList itemsObject = new BasicDBList();

            purchaseObject.append(Purchase.DATE, purcahse.getDate());
            purchaseObject.append(Purchase.TOTALPRICE, purcahse.getTotalPrice());
            purchaseObject.append(Purchase.PURCHASETYPE, purcahse.getPurchaseType());

            for (PurchaseItem item : purcahse.getItems()) {

                BasicDBObject purchaseItem = new BasicDBObject();
                purchaseItem.append(PurchaseItem.ITEM, item.getItem());
                purchaseItem.append(PurchaseItem.QTY, item.getQty());
                purchaseItem.append(PurchaseItem.TOTALPRICE, item.getTotalPrice());

                itemsObject.add(purchaseItem);
            }

            purchaseObject.append(Purchase.ITEMS, itemsObject);
            purchasesObject.add(purchaseObject);
        }

        userObject.append(User.PURCHASES, purchasesObject);

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", userObject);

        getCollection().updateOne(searchQuery, setQuery);
    }
}
