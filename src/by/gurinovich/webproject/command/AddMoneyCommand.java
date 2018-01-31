package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.CardDAO;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CardDAO dao = new CardDAO();
        request.getSession().setAttribute("cardAmount", dao.getCardAmount(((User)request.getSession().getAttribute("userfull")).getCardNumber()));
        String page = ConfigurationManager.getProperty("path.page.addmoney");
        return page;
    }
}
