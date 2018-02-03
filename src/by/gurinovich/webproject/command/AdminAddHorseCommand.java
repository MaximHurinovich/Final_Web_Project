package by.gurinovich.webproject.command;

import by.gurinovich.webproject.entity.Horse;
import by.gurinovich.webproject.resource.ConfigurationManager;
import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.servlet.Router;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdminAddHorseCommand implements ActionCommand {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String horseName = request.getParameter(Constant.PARAM_HORSE_NAME);
        if (horseName == null) {
            request.setAttribute(Constant.ATTRIBUTE_ADD_RACE_MESSAGE, MessageManager.getProperty("message.infoerror"));
            router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
            return router;
        }
        if (request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_HORSES) == null) {
            HashSet<String> horseNames = new HashSet<>();
            horseNames.add(horseName);
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_HORSES, horseNames);
        } else {
            HashSet<String> horseNames = (HashSet<String>) request.getSession().getAttribute(Constant.ATTRIBUTE_NAME_HORSES);
            horseNames.add(horseName);
            request.getSession().setAttribute(Constant.ATTRIBUTE_NAME_HORSES, horseNames);
        }
        router.setPage(ConfigurationManager.getProperty("path.page.admin.addrace"));
        request.setAttribute(Constant.ATTRIBUTE_ADD_HORSE_MESSAGE, MessageManager.getProperty("message.addhorse"));
        return router;
    }
}
