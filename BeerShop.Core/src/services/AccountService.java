package services;

import customexceptions.DatabaseNotFoundException;
import customexceptions.InvalidLoginException;
import helpers.DateTimeHelper;
import models.*;
import repositories.MongoUnitOfWork;
import repositories.UnitOfWork;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by mario on 27.5.2017..
 */
public class AccountService {

    UnitOfWork unitOfWork;

    public AccountService() throws FileNotFoundException, DatabaseNotFoundException {
        unitOfWork = new MongoUnitOfWork();
    }

    public LoginViewModel login(LoginViewModel model) throws FileNotFoundException, DatabaseNotFoundException, InvalidLoginException {

        List<User> users = unitOfWork.getUserRepository().getAll();
        User user;

        try {
            user = users.stream().filter((u) -> u.getEmail().equals(model.getEmail())).findFirst().get();

            if (!user.getPassword().equals(model.getPassword()))
                throw new InvalidLoginException("Wrong password.");

            model.setRole(user.getRole());

            createLog(user, model.getIp());

        } catch (NoSuchElementException e) {
            throw new InvalidLoginException("User with email " + model.getEmail() + " does not exists.");
        }

        LoginViewModel newModel = new LoginViewModel();
        newModel.setEmail(user.getEmail());
        newModel.setPassword(user.getPassword());
        newModel.setRole(user.getRole());

        return newModel;
    }

    private void createLog(User user, String ip) throws FileNotFoundException, DatabaseNotFoundException {

        Login login = new Login();

        login.setEmail(user.getEmail());
        login.setIp(ip);
        login.setId(user.getId());
        login.setDate(DateTimeHelper.getCurrentDateTime());

        unitOfWork.getLoginRepository().create(login);
    }

    public HistoryViewModel getHistory(SearchFilterViewModel searchFilters) throws FileNotFoundException, DatabaseNotFoundException {

        HistoryViewModel model = new HistoryViewModel();

        model.setDate(searchFilters.getDate());
        model.setUserName(searchFilters.getEmail());
        model.setLogins(getLogins(searchFilters));
        model.setPurchases(getPurchases(searchFilters));

        return model;
    }

    private ArrayList<LoginHistoryViewModel> getLogins(SearchFilterViewModel searchFilter) throws FileNotFoundException, DatabaseNotFoundException {

        ArrayList<LoginHistoryViewModel> model = new ArrayList<>();

        ArrayList<Login> logins = (ArrayList<Login>) unitOfWork.getLoginRepository().getAll();

        if (searchFilter == null)
            logins.stream().forEach((l) -> model.add(new LoginHistoryViewModel(l)));

        if (!(searchFilter.getEmail() == null)) {
            logins.stream().filter((l) -> l.getEmail().startsWith(searchFilter.getEmail())).
                    forEach((l) -> model.add(new LoginHistoryViewModel(l)));
        }

        if (!(searchFilter.getDate() == null) && !searchFilter.getDate().equals("")) {

            if (model.size() > 0) {

                List<LoginHistoryViewModel> filteredModel;

                filteredModel = model.stream().filter((l) -> {
                    try {
                        return DateTimeHelper.laterThen(searchFilter.getDate(), l.getDate());
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());

                return (ArrayList<LoginHistoryViewModel>) filteredModel;
            } else {
                logins.stream().filter((l) -> {
                    try {
                        return DateTimeHelper.laterThen(searchFilter.getDate(), l.getDate());
                    } catch (ParseException e) {
                        return false;
                    }
                }).forEach((l) -> model.add(new LoginHistoryViewModel(l)));
            }

        }

        return model;
    }

    private ArrayList<PurchaseViewModel> getPurchases(SearchFilterViewModel searchFilter) throws FileNotFoundException, DatabaseNotFoundException {

        ArrayList<PurchaseViewModel> model = new ArrayList<>();

        ArrayList<User> users = (ArrayList<User>) unitOfWork.getUserRepository().getAll();

        if (searchFilter == null) {
            return null;
        }

        User user = getUserByEmail(searchFilter.getEmail());

        if (user != null) {

            user.getPurchases().stream().forEach((p) -> {
                PurchaseViewModel purchaseModel = new PurchaseViewModel(p);
                purchaseModel.setUserName(user.getEmail());

                model.add(purchaseModel);
            });
        } else {
            return null;
        }


        if (!(searchFilter.getDate() == null) && !searchFilter.getDate().equals("")) {

            if (model.size() > 0) {

                List<PurchaseViewModel> filteredModel;

                filteredModel = model.stream().filter((p) -> {
                    try {
                        return DateTimeHelper.laterThen(searchFilter.getDate(), p.getDate());
                    } catch (ParseException e) {
                        return false;
                    }
                }).collect(Collectors.toList());

                return (ArrayList<PurchaseViewModel>) filteredModel;
            } else {

                users.stream().forEach((u) -> u.getPurchases().stream().filter((p) -> {
                    try {
                        return DateTimeHelper.laterThen(searchFilter.getDate(), p.getDate());
                    } catch (ParseException e) {
                        return false;
                    }
                }).forEach((p) -> {
                    PurchaseViewModel purchaseModel = new PurchaseViewModel(p);
                    purchaseModel.setUserName(user.getEmail());

                    model.add(purchaseModel);
                }));
            }

        }

        return model;
    }

    private User getUserByEmail(String email) throws FileNotFoundException, DatabaseNotFoundException {

        User user = unitOfWork.getUserRepository().getAll().stream().filter((u) -> u.getEmail().equals(email)).findFirst().get();

        return user;
    }

    public HistoryViewModel getHistoryForAdmin(SearchFilterViewModel searchFilters) throws FileNotFoundException, DatabaseNotFoundException {

        HistoryViewModel model = new HistoryViewModel();

        model.setDate(searchFilters.getDate());
        model.setUserName(searchFilters.getEmail());

        if (searchFilters.getEmail() == null || searchFilters.getEmail().equals("")) {

            List<User> users = unitOfWork.getUserRepository().getAll();

            for (User user : users) {

                searchFilters.setEmail(user.getEmail());

                HistoryViewModel hm = getHistory(searchFilters);

                model.getPurchases().addAll(hm.getPurchases());
                model.getLogins().addAll(hm.getLogins());

            }
        } else {

            User user = null;

            try {

                user = getUserByEmail(searchFilters.getEmail());

            } catch (NoSuchElementException e) {
                return model;
            }

            if (user != null) {
                HistoryViewModel hm = getHistory(searchFilters);

                model.getPurchases().addAll(hm.getPurchases());
                model.getLogins().addAll(hm.getLogins());
            }

        }

        return model;
    }
}
