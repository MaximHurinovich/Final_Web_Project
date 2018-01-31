package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.EditDAO;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.logic.AcceptEditLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AcceptEditCommand implements ActionCommand {
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_SECONDNAME = "secondname";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_CARD_NUMBER = "cardnumber";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        String secondName = request.getParameter(PARAM_SECONDNAME);
        String email = request.getParameter(PARAM_EMAIL);
        String cardNumber =request.getParameter(PARAM_CARD_NUMBER);
        if(AcceptEditLogic.checkData(firstName,secondName, email, cardNumber)){
            EditDAO dao = new EditDAO();
            if(dao.updateProfile(((User)request.getSession().getAttribute("userfull")).getUsername(), firstName, secondName, email, cardNumber))
            {
                ((User) request.getSession().getAttribute("userfull")).setFirstName(firstName);
                ((User) request.getSession().getAttribute("userfull")).setSecondName(secondName);
                ((User) request.getSession().getAttribute("userfull")).setEmail(email);
                ((User) request.getSession().getAttribute("userfull")).setCardNumber(cardNumber);
                page = ConfigurationManager.getProperty("path.page.account");
                return page;
            }
            else{
                request.setAttribute("editMessage", MessageManager.getProperty("message.wrongaction"));
                page = ConfigurationManager.getProperty("path.page.edit");
                return page;
            }
        }
        else{
            request.setAttribute("editMessage", AcceptEditLogic.invalidateMessage(firstName, secondName, email, cardNumber));
            page = ConfigurationManager.getProperty("path.page.edit");
            return page;
        }
    }
}
