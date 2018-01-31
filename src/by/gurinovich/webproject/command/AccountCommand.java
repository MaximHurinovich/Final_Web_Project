package by.gurinovich.webproject.command;

import by.gurinovich.webproject.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AccountCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.account");
        return page;
    }
}
