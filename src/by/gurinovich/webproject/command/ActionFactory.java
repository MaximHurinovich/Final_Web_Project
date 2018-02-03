package by.gurinovich.webproject.command;

import javax.servlet.http.HttpServletRequest;

import by.gurinovich.webproject.resource.MessageManager;
import by.gurinovich.webproject.util.Constant;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(Constant.PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(Constant.ATTRIBUTE_WRONG_ACTION_MESSAGE, action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}