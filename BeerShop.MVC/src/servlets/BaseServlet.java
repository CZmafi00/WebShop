package servlets;

import models.ErrorPageViewModel;
import models.ShoppingCartViewModel;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mario on 27.5.2017..
 */
public class BaseServlet extends javax.servlet.http.HttpServlet {

    protected static String DATEATTRIBUTE = "date";
    protected static String ERRORATTRIBUTE = "error";
    protected static String HISTORYATTRIBUTE = "history";
    protected static String MODELATTRIBUTE = "model";
    protected static String PURCHASEATTRIBUTE = "purchase";
    protected static String SHOPPINGCARTATTRIBUTE = "shoppingcart";
    protected static String USERATTRIBUTE = "user";

    protected static String ADRESSPARAMETER = "adress";
    protected static String CITYPARAMETER = "city";
    protected static String DATEPARAMETER = "date";
    protected static String EMAILPARAMETER = "email";
    protected static String LASTNAMEPARAMETER = "lastName";
    protected static String LOGOUTPARAMETER = "logout";
    protected static String NAMEPARAMETER = "name";
    protected static String PASSPARAMETER = "pass";
    protected static String PAYMENTPARAMETER = "payment";
    protected static String PRODUCTIDPARAMETER = "productId";
    protected static String QTYPARAMETER = "qty";
    protected static String ZIPPARAMETER = "zip";

    protected static String BILL = "bill.jsp";
    protected static String ERRORJSP = "errorpage.jsp";
    protected static String HISTORY = "history.jsp";
    protected static String INDEX = "index.jsp";
    protected static String LOGIN = "login.jsp";
    protected static String SHOPPINGCART = "shoppingcart.jsp";

    protected void setAjaxResponseHeaders(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }
    protected ShoppingCartViewModel getShoppingCartmModelFromSession(HttpSession session) {

        ShoppingCartViewModel shoppingCart;

        if (session.getAttribute(SHOPPINGCARTATTRIBUTE) != null) {

            if (session.getAttribute(SHOPPINGCARTATTRIBUTE) instanceof ShoppingCartViewModel)
                shoppingCart = (ShoppingCartViewModel) session.getAttribute(SHOPPINGCARTATTRIBUTE);
            else
                shoppingCart = new ShoppingCartViewModel();

        } else {
            shoppingCart = new ShoppingCartViewModel();
        }

        return shoppingCart;
    }

    protected ErrorPageViewModel getInternalErrorPageModel() {

        ErrorPageViewModel errorModel = new ErrorPageViewModel();
        errorModel.setErrorCode(500);
        errorModel.setMessage("Internal server error. Return home.");

        return errorModel;
    }
    protected ErrorPageViewModel getUnAuthorizedErrorPageModel() {

        ErrorPageViewModel errorModel = new ErrorPageViewModel();
        errorModel.setErrorCode(401);
        errorModel.setMessage("Unauthorized access. Return to home page.");

        return errorModel;
    }
    protected ErrorPageViewModel getPageNotFoundErrorPageModel() {

        ErrorPageViewModel errorModel = new ErrorPageViewModel();
        errorModel.setErrorCode(404);
        errorModel.setMessage("Page not found. Return to home page.");

        return errorModel;
    }
}
