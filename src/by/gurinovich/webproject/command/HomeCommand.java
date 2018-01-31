package by.gurinovich.webproject.command;

import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        RacesDAO racesDAO = new RacesDAO();
        String page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
