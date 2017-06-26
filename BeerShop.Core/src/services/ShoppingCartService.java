package services;

import customexceptions.DatabaseNotFoundException;
import customexceptions.InvalidLoginException;
import helpers.DateTimeHelper;
import models.*;
import repositories.MongoUnitOfWork;
import repositories.UnitOfWork;
import enumerations.UserRole;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by mario on 27.5.2017..
 */
public class ShoppingCartService {

    UnitOfWork unitOfWork;

    public ShoppingCartService() throws FileNotFoundException, DatabaseNotFoundException {
        unitOfWork = new MongoUnitOfWork();
    }

    public void checkout(ShoppingCartViewModel model) throws FileNotFoundException, DatabaseNotFoundException, InvalidLoginException {

        User user;

        if (model.getUserRole().equals(UserRole.Customer.toString())) {

            user = getUserByEmail(model.getEmail());

            if (user != null) {

                Purchase purchase = new Purchase();
                purchase.setDate(DateTimeHelper.getCurrentDateTime());
                purchase.setTotalPrice(calculateTotal(model.getItems()));
                purchase.setPurchaseType(model.getPurchaseType());
                purchase.setItems(createItemList(model.getItems()));

                user.getPurchases().add(purchase);

                unitOfWork.getUserRepository().update(user);
            } else {
                throw new InvalidLoginException("User not found. login again.");
            }


        } else if (model.getUserRole().equals(UserRole.Guest.toString())) {

            user = getGuestUser();

            Purchase purchase = new Purchase();
            purchase.setDate(DateTimeHelper.getCurrentDateTime());
            purchase.setTotalPrice(calculateTotal(model.getItems()));
            purchase.setPurchaseType(model.getPurchaseType());
            purchase.setItems(createItemList(model.getItems()));
            purchase.setName(model.getName());
            purchase.setLastName(model.getLastName());
            purchase.setEmail(model.getEmail());

            Adress adress = new Adress();
            adress.setAdress(model.getAddress());
            adress.setZip(Integer.parseInt(model.getZip()));
            adress.setCity(model.getCity());

            purchase.setAdress(adress);

            user.getPurchases().add(purchase);

            unitOfWork.getUserRepository().update(user);
        }

    }

    public BillViewModel createBill(ShoppingCartViewModel model, boolean userSignedIn) throws FileNotFoundException, DatabaseNotFoundException {

        BillViewModel bill = new BillViewModel();
        bill.setTotalPrice(model.getTotalPrice());
        bill.setPurchaseType(model.getPurchaseType());
        bill.setDate(DateTimeHelper.getCurrentDateTime().toString());
        bill.setItems(model.getItems());

        bill.setEmail(model.getEmail());

       if (userSignedIn) {

           User user = getUserByEmail(model.getEmail());

           bill.setName(user.getName());
           bill.setLastName(user.getLastName());

           bill.setAddress(user.getAdress().getAdress());
           bill.setCity(user.getAdress().getCity());
           bill.setZip("" + user.getAdress().getZip());

       } else {

           bill.setName(model.getName());
           bill.setLastName(model.getLastName());

           bill.setAddress(model.getAddress());
           bill.setCity(model.getCity());
           bill.setZip(model.getZip());
       }

        return bill;
    }

    private double calculateTotal(ArrayList<CartItemViewModel> items) {

        double total = 0;

        for(CartItemViewModel item : items) {
            total += item.getPrice() * item.getQty();
        }

        return total;
    }

    private ArrayList<PurchaseItem> createItemList(ArrayList<CartItemViewModel> items) {

        ArrayList<PurchaseItem> purchaseItems = new ArrayList<>();

        for (CartItemViewModel item : items) {

            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setTotalPrice(item.getPrice() * item.getQty());
            purchaseItem.setQty(item.getQty());
            purchaseItem.setItem(item.getProductName());

            purchaseItems.add(purchaseItem);
        }

        return purchaseItems;
    }

    private User getUserByEmail(String email) throws FileNotFoundException, DatabaseNotFoundException {

        User user = unitOfWork.getUserRepository().getAll().stream().filter((u) -> u.getEmail().equals(email)).findFirst().get();

        return user;
    }

    private User getUserById(String id) throws FileNotFoundException, DatabaseNotFoundException {

        User user = unitOfWork.getUserRepository().getAll().stream().filter((u) -> u.getEmail().equals(id)).findFirst().get();

        return user;
    }

    private User getGuestUser() throws FileNotFoundException, DatabaseNotFoundException {

        User user = unitOfWork.getUserRepository().getAll().stream().filter((u) -> u.getRole().equals(UserRole.Guest.toString())).findFirst().get();

        return user;
    }

    public CartItemViewModel getCartItemByProductId(String productId) throws FileNotFoundException, DatabaseNotFoundException {

        CartItemViewModel model = null;

        ProductService productService = new ProductService();
        ProductViewModel product = productService.getProductById(productId);

        if (product != null) {

            model = new CartItemViewModel();
            model.setProductId(product.getProductId());
            model.setPrice(product.getPrice());
            model.setImageUrl(product.getImgUrl());
            model.setProductName(product.getName());
        }

        return model;
    }
}
