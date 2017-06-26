package servlets;

import customexceptions.DatabaseNotFoundException;
import models.CartItemViewModel;
import models.ErrorPageViewModel;
import models.ShoppingCartViewModel;
import services.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mario on 17.6.2017..
 */
@WebServlet(name = "AjaxProductServlet", urlPatterns = ("/aProduct"))
@MultipartConfig(location = "/aProduct", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AjaxProductServlet extends BaseServlet {

    ShoppingCartService service;
    ShoppingCartViewModel model;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            service = new ShoppingCartService();
            String responseText;

            String productId = request.getParameter(PRODUCTIDPARAMETER);
            int qty = Integer.parseInt(request.getParameter(QTYPARAMETER).toString());

            model = getShoppingCartmModelFromSession(request.getSession());
            boolean exists = addProductToCartItem(productId, qty);

            setAjaxResponseHeaders(response);

            if (exists) {
                response.setStatus(202);
                responseText = "Product quantity successfully increasead in cart.";
            } else {
                response.setStatus(201);
                responseText = "New Product successfully added to cart.";
            }

            response.getWriter().write(responseText);

        } catch (InvalidParameterException ex) {

            response.setStatus(206);
            response.getWriter().write("Product not found, please reload the page and try again.");
        } catch (Exception e) {

            response.setStatus(500);
            response.getWriter().write("Internal server error, please reload the page and try again. " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean addProductToCartItem(String productId, int qty) throws FileNotFoundException, DatabaseNotFoundException, InvalidParameterException {

        boolean exists = false;

        List<CartItemViewModel> items = model.getItems().stream().filter((p) -> p.getProductId().equals(productId)).collect(Collectors.toList());

        if (items.size() > 0) {
            items.get(0).setQty(items.get(0).getQty() + qty);
            exists = true;
        } else {
            CartItemViewModel cartItem = service.getCartItemByProductId(productId);
            exists = false;

            if (cartItem == null)
                throw new InvalidParameterException();

            cartItem.setQty(qty);
            model.getItems().add(cartItem);
        }

        return exists;
    }

}
