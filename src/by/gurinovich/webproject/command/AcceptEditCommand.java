package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.UserLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AcceptEditCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AcceptEditCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserLogic userLogic = new UserLogic();
        String page;
        String firstName = request.getParameter(Constant.PARAM_NAME_FIRSTNAME);
        String secondName = request.getParameter(Constant.PARAM_NAME_SECONDNAME);
        String email = request.getParameter(Constant.PARAM_NAME_EMAIL);
        String cardNumber = request.getParameter(Constant.PARAM_NAME_CARD_NUMBER);
        if (userLogic.checkData(firstName, secondName, email, cardNumber)) {
            try {
                if (userLogic.updateProfile(((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).getUsername(),
                        firstName, secondName, email, cardNumber)) {
                    ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).setFirstName(firstName);
                    ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).setSecondName(secondName);
                    ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).setEmail(email);
                    ((User) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).setCardNumber(cardNumber);
                    LOGGER.info(firstName + " " + secondName + "updated account");
                    page = ConfigurationManager.getProperty("path.page.account");
                    router.setPage(page);
                } else {
                    request.setAttribute(Constant.ATTRIBUTE_EDIT_MESSAGE, MessageManager.getProperty("message.wrongaction"));
                    page = ConfigurationManager.getProperty("path.page.edit");
                    router.setPage(page);
                }
            } catch (LogicalException e) {
                throw new CommandException(e.getMessage());

            }
        } else {
            request.setAttribute(Constant.ATTRIBUTE_EDIT_MESSAGE, userLogic.invalidateMessage(firstName, secondName, email, cardNumber));
            page = ConfigurationManager.getProperty("path.page.edit");
            router.setPage(page);
        }
        return router;
    }
}
