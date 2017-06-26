package repositories;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import customexceptions.DatabaseNotFoundException;
import helpers.ConfigManager;
import models.Login;
import models.Product;
import models.User;

import java.io.FileNotFoundException;

/**
 * Created by mario on 18.5.2017..
 */
public class MongoUnitOfWork implements UnitOfWork {

    private Repository<User> userRepository;
    private Repository<Product> productRepository;
    private Repository<Login> loginRepository;

    private ConfigManager config = null;
    private MongoClient client;
    private MongoDatabase db;

    public MongoUnitOfWork() throws FileNotFoundException, DatabaseNotFoundException {

        try {

            config = new ConfigManager();

            client = new MongoClient(config.getHost(), config.getPort());
            db = client.getDatabase(config.getDbName());

        } catch (FileNotFoundException e) {
            throw e;
        } catch (NullPointerException ne) {
            throw new DatabaseNotFoundException();
        }

    }

    public Repository<User> getUserRepository() {

        if(userRepository == null)
            userRepository = new UserRepository(db, client);

        return userRepository;
    }

    public Repository<Product> getProductRepository() {

        if (productRepository == null)
            productRepository = new ProductRepository(db, client);

        return productRepository;
    }

    public Repository<Login> getLoginRepository() {

        if (loginRepository == null)
            return new LoginRepository(db, client);

        return loginRepository;
    }
}
