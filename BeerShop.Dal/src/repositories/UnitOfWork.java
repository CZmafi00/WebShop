package repositories;

import customexceptions.DatabaseNotFoundException;
import models.Login;
import models.Product;
import models.User;

import java.io.FileNotFoundException;

/**
 * Created by mario on 23.5.2017..
 */
public interface UnitOfWork {

    Repository<User> getUserRepository() throws FileNotFoundException, DatabaseNotFoundException;
    Repository<Product> getProductRepository() throws FileNotFoundException, DatabaseNotFoundException;
    Repository<Login> getLoginRepository() throws FileNotFoundException, DatabaseNotFoundException;
}
