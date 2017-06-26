package servlets;

import customexceptions.DatabaseNotFoundException;
import models.*;
import services.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mario on 13.5.2017..
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends BaseServlet {


    ProductService productService;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        ProductsViewModel model;

        try {
            productService = new services.ProductService();

            model = productService.getProducts();

            request.setAttribute(MODELATTRIBUTE, model);

            if (request.getSession().getAttribute(SHOPPINGCARTATTRIBUTE) == null) {
                request.getSession().setAttribute(SHOPPINGCARTATTRIBUTE, new ShoppingCartViewModel());
            }

            request.getRequestDispatcher(INDEX).forward(request, response);

        } catch (Exception e) {

            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }
}
