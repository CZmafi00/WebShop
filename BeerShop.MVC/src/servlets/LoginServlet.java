package servlets;

import customexceptions.InvalidLoginException;
import models.ErrorPageViewModel;
import models.LoginViewModel;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mario on 16.6.2017..
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends BaseServlet {

    AccountService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            service = new AccountService();

            LoginViewModel model = new LoginViewModel();
            model.setEmail(request.getParameter(EMAILPARAMETER));
            model.setPassword(request.getParameter(PASSPARAMETER));
            model.setIp(request.getRemoteAddr());

            model = service.login(model);

            request.getSession().setAttribute(USERATTRIBUTE, model);

            request.getRequestDispatcher(LOGIN).forward(request, response);
        } catch (InvalidLoginException e) {

            ErrorPageViewModel errorModel = new ErrorPageViewModel();
            errorModel.setErrorCode(209);
            errorModel.setMessage(e.getMessage());

            request.setAttribute(ERRORATTRIBUTE, errorModel);
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } catch (Exception ex) {

            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            if (request.getParameter(LOGOUTPARAMETER) != null) {
                if (request.getParameter(LOGOUTPARAMETER).toString().equals("true")) {
                    HttpSession session = request.getSession();

                    if (session.getAttribute(USERATTRIBUTE) != null)
                        session.removeAttribute(USERATTRIBUTE);

                } else {
                    ErrorPageViewModel erroModel = new ErrorPageViewModel();
                    erroModel.setMessage("Invalid url logout parameter.");

                    request.setAttribute(ERRORATTRIBUTE, erroModel);
                }
            }

            request.getRequestDispatcher(LOGIN).forward(request, response);

        } catch (Exception e) {
            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }
    }
}
