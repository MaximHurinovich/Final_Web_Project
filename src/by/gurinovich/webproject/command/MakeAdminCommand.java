package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.logic.AdminLogic;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class MakeAdminCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        AdminLogic logic = new AdminLogic();
        String username = request.getParameter("username");
        Router router = new Router();
        if(logic.makeAdmin(username)){
            ArrayList<Person> users = (ArrayList<Person>)request.getSession().getAttribute("usersList");
            for(Person user: users){
                if(username.equals(user.getUsername())){
                    users.remove(user);
                    break;
                }
            }
            request.getSession().setAttribute("usersList", users);
        }else {
            request.setAttribute("adminMessage", MessageManager.getProperty("message.unknowncommand"));
        }
        router.setPage(ConfigurationManager.getProperty("path.page.admin.users"));
        return router;
    }
}
