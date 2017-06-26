package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mario on 26.6.2017..
 */
@WebServlet(name = "ErrorServlet", urlPatterns = "/error")
public class ErrorServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");

            if(statusCode.intValue() == 404) {

                request.setAttribute(ERRORATTRIBUTE, getPageNotFoundErrorPageModel());
                request.getRequestDispatcher(ERRORJSP).forward(request, response);
            } else {
                throw new Exception();
            }

        } catch (Exception e) {

            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }
}
