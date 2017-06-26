package servlets;

import customexceptions.UnathorizedException;
import enumerations.UserRole;
import models.ErrorPageViewModel;
import models.HistoryViewModel;
import models.LoginViewModel;
import models.SearchFilterViewModel;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mario on 23.6.2017..
 */
@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends BaseServlet {

    AccountService service;
    HistoryViewModel model;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            LoginViewModel loginViewModel;

            if (request.getSession().getAttribute(USERATTRIBUTE) != null && request.getSession().getAttribute(USERATTRIBUTE) instanceof LoginViewModel) {
                loginViewModel = (LoginViewModel) request.getSession().getAttribute(USERATTRIBUTE);

                if (!loginViewModel.getRole().equals(UserRole.Admin.toString()))
                    throw new UnathorizedException("User not authorized.");
            }
            else
                throw new UnathorizedException("User not authorized.");

            service = new AccountService();

            String user = request.getParameter(EMAILPARAMETER);
            String date = request.getParameter(DATEPARAMETER);

            SearchFilterViewModel filter = new SearchFilterViewModel();
            filter.setEmail(user);
            filter.setDate(date);

            model = service.getHistoryForAdmin(filter);

            request.setAttribute(HISTORYATTRIBUTE, model);
            request.setAttribute(USERATTRIBUTE, user);
            request.setAttribute(DATEATTRIBUTE, date);
            request.getRequestDispatcher(HISTORY).forward(request, response);

        } catch (UnathorizedException ex) {

            request.setAttribute(ERRORATTRIBUTE, getUnAuthorizedErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        } catch (Exception e) {

            request.setAttribute(ERRORATTRIBUTE, getInternalErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            LoginViewModel loginViewModel;

            if (request.getSession().getAttribute(USERATTRIBUTE) != null && request.getSession().getAttribute(USERATTRIBUTE) instanceof LoginViewModel)
                loginViewModel = (LoginViewModel) request.getSession().getAttribute(USERATTRIBUTE);
            else
                throw new UnathorizedException("User not authorized.");

            service = new AccountService();

            SearchFilterViewModel filter = new SearchFilterViewModel();


            if (loginViewModel.getRole().equals(UserRole.Admin.toString())){
                model = service.getHistoryForAdmin(filter);
                filter.setEmail(null);
            }
            else {
                filter.setEmail(loginViewModel.getEmail());
                model = service.getHistory(filter);
            }

            request.setAttribute(HISTORYATTRIBUTE, model);
            request.getRequestDispatcher(HISTORY).forward(request, response);

        } catch (UnathorizedException ex) {

            request.setAttribute(ERRORATTRIBUTE, getUnAuthorizedErrorPageModel());
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        } catch (Exception e) {

            ErrorPageViewModel mod = getInternalErrorPageModel();
            mod.setMessage(mod.getMessage() + " " + e.getMessage());

            request.setAttribute(ERRORATTRIBUTE, mod);
            request.getRequestDispatcher(ERRORJSP).forward(request, response);
        }

    }
}
