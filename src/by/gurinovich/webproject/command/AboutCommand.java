package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.AuthenticationDAO;
import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AboutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.about");
        return page;
    }
}
