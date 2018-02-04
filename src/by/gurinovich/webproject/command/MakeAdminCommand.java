package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.exception.LogicalException;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class MakeAdminCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        AdminLogic logic = new AdminLogic();
        String username = request.getParameter(Constant.PARAM_NAME_USERNAME);
        Router router = new Router();
        try {
            if (logic.makeAdmin(username)) {
                ArrayList<Person> users = (ArrayList<Person>) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER_LIST);
                for (Person user : users) {
                    if (username.equals(user.getUsername())) {
                        users.remove(user);
                        break;
                    }
                }
                request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_USER_LIST, users);
            } else {
                request.setAttribute(Constant.ATTRIBUTE_ADMIN_MESSAGE, MessageManager.getProperty("message.unknowncommand"));
            }
        } catch (LogicalException e) {
            throw new CommandException(e.getMessage());
        }
        router.setPage(ConfigurationManager.getProperty("path.page.admin.users"));
        return router;
    }
}
