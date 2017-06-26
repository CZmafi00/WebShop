package servlets;

import com.google.gson.Gson;
import models.*;
import services.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by mario on 17.6.2017..
 */
@WebServlet(name = "AjaxShoppingCartServlet", urlPatterns = ("/aShoppingcart"))
@MultipartConfig(location = "/aProduct", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AjaxShoppingCartServlet extends BaseServlet {

    ShoppingCartService service;
    ShoppingCartViewModel model;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            service = new ShoppingCartService();
            model = getShoppingCartmModelFromSession(request.getSession());
            ModifyCartItemViewModel modifyItemModel = new ModifyCartItemViewModel();

            String productId = request.getParameter(PRODUCTIDPARAMETER);
            int qty = Integer.parseInt(request.getParameter(QTYPARAMETER));

            CartItemViewModel item = getItemFromCart(productId);

            if (item == null)
                throw new InvalidParameterException();

            item.setQty(qty);

            modifyItemModel.setNewPrice(model.getTotalPrice());
            modifyItemModel.setNewItemPrice(item.getTotalPrice());
            modifyItemModel.setNewQty(item.getQty());

            setAjaxResponseHeaders(response);
            response.setStatus(200);

            Gson gson = new Gson();
            String json = gson.toJson(modifyItemModel);

            response.getWriter().write(json);

        }catch (NoSuchElementException ne) {

            response.setStatus(206);
            response.getWriter().write("Element with this id does not exists in cart, please reload the page and try again.");
        } catch (InvalidParameterException ie) {

            response.setStatus(206);
            response.getWriter().write("Element with this id does not exists in cart, please reload the page and try again.");
        } catch (NumberFormatException nfe) {

            response.setStatus(206);
            response.getWriter().write("Value of quantity is not valid. Please insert correct number value.");
        } catch (Exception e) {

            response.setStatus(500);
            response.getWriter().write("Internal server error, please reload the page and try again.");
        }

    }//NumberFormatException

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            service = new ShoppingCartService();
            model = getShoppingCartmModelFromSession(request.getSession());
            DeleteCartItemViewModel cartItemModel = new DeleteCartItemViewModel();

            String productId = request.getParameter(PRODUCTIDPARAMETER);

            CartItemViewModel item = getItemFromCart(productId);

            if (item == null)
                throw new InvalidParameterException();

            if (removeItemFromCart(item)) {

                cartItemModel.setNewItemCount(model.getNumberOfItems());
                cartItemModel.setNewPrice(model.getTotalPrice());
            } else {
                throw new NoSuchElementException();
            }

            setAjaxResponseHeaders(response);
            response.setStatus(200);

            Gson gson = new Gson();
            String json = gson.toJson(cartItemModel);

            response.getWriter().write(json);

        } catch (NoSuchElementException ne) {

            response.setStatus(206);
            response.getWriter().write("Element with this id does not exists in cart, please reload the page and try again.");
        } catch (InvalidParameterException ie) {

            response.setStatus(206);
            response.getWriter().write("Element with this id does not exists in cart, please reload the page and try again.");
        } catch (Exception ex) {

            response.setStatus(500);
            response.getWriter().write("Internal server error, please reload the page and try again.");
        }
    }

    private CartItemViewModel getItemFromCart(String productId) {

        CartItemViewModel item = null;

        List<CartItemViewModel> items = model.getItems().stream().filter((p) -> p.getProductId().equals(productId)).collect(Collectors.toList());

        if (items.size() > 0)
            item = items.get(0);

        return item;
    }

    private boolean removeItemFromCart(CartItemViewModel item) {

        boolean exists = model.getItems().remove(item);

        return exists;
    }

}
