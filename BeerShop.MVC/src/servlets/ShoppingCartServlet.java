package servlets;

import enumerations.UserRole;
import helpers.DateTimeHelper;
import helpers.EmailHelper;
import models.*;
import services.AccountService;
import services.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by mario on 16.6.2017..
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = "/shoppingcart")
public class ShoppingCartServlet extends BaseServlet {

    ShoppingCartService service;
    ShoppingCartViewModel model;
    LoginViewModel user = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            service = new ShoppingCartService();
            model = getShoppingCartmModelFromSession(request.getSession());

            boolean userSignedIn;

            if (model.getItems().size() < 1)
                throw new Exception();

            if (request.getSession().getAttribute(USERATTRIBUTE) != null && request.getSession().getAttribute(USERATTRIBUTE) instanceof LoginViewModel) {

                user = (LoginViewModel) request.getSession().getAttribute(USERATTRIBUTE);
                model.setEmail(user.getEmail());
                model.setUserRole(user.getRole());

                userSignedIn = true;

            } else {

                model.setUserRole(UserRole.Guest.toString());
                model.setEmail(request.getParameter(EMAILPARAMETER));
                model.setAddress(request.getParameter(ADRESSPARAMETER));
                model.setCity(request.getParameter(CITYPARAMETER));
                model.setZip(request.getParameter(ZIPPARAMETER));
                model.setName(request.getParameter(NAMEPARAMETER));
                model.setLastName(request.getParameter(LASTNAMEPARAMETER));

                userSignedIn = false;

                if (!validateModel())
                    throw new Exception();

            }

            String payment = request.getParameter(PAYMENTPARAMETER);

            if (payment == null)
                throw new Exception();

            model.setPurchaseType(payment);
            service.checkout(model);

            request.getSession().setAttribute(SHOPPINGCARTATTRIBUTE, new ShoppingCartViewModel());

            request.setAttribute(PURCHASEATTRIBUTE, service.createBill(model, userSignedIn));

            request.getRequestDispatcher(BILL).forward(request, response);


        } catch (Exception e) {

            ErrorPageViewModel mod = getInternalErrorPageModel();
            mod.setMessage(e.getMessage());

            request.setAttribute(ERRORATTRIBUTE, mod);
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            model = getShoppingCartmModelFromSession(request.getSession());

            request.getRequestDispatcher(SHOPPINGCART).forward(request, response);

        } catch (Exception e) {

            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }

    private boolean validateModel() {

        boolean valid = true;

        if (model.getAddress() == null || model.getAddress().equals(""))
            valid = false;
        if (model.getCity() == null || model.getCity().equals(""))
            valid = false;
        if (model.getLastName() == null || model.getLastName().equals(""))
            valid = false;
        if (model.getName() == null || model.getName().equals(""))
            valid = false;
        if (model.getEmail() == null || model.getEmail().equals(""))
            valid = false;
        if (model.getZip() == null || model.getZip().equals(""))
            valid = false;

        valid = EmailHelper.validateEmail(model.getEmail());

        return valid;
    }

}
