package by.gurinovich.webproject.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gurinovich.webproject.command.ActionCommand;
import by.gurinovich.webproject.command.ActionFactory;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Constant;

@WebServlet("/jsp/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

        ActionFactory client = new ActionFactory();
        try {
        ActionCommand command = client.defineCommand(request);
        Router router;
            router = command.execute(request);

        if (router.getPage() != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(router.getPage());
            if (router.getRoute() == Router.RouteType.FORWARD) {
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute(Constant.ATTRIBUTE_NULL_PAGE, MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
        } catch (CommandException e) {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute(Constant.ATTRIBUTE_NULL_PAGE, MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);}
    }
}
