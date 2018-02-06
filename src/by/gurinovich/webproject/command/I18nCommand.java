package by.gurinovich.webproject.command;

import by.gurinovich.webproject.exception.CommandException;
import by.gurinovich.webproject.servlet.Router;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class I18nCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String REG_EX_JSP = "/jsp.+";
    private static final String PARAM_CHANGE_LANGUAGE = "changeLanguage";
    private static final String PARAM_PAGE_PATH = "pagePath";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        LOGGER.info("lkjhgf");
        Router router = new Router();
        if (request.getSession().getAttribute(PARAM_CHANGE_LANGUAGE) == null){
            request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE , "ru");
        }
        if ("en".equals(request.getSession().getAttribute(PARAM_CHANGE_LANGUAGE))){
            request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE , "ru");
        }
        else{
            request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE , "en");
        }


        String page = null;
        Pattern p = Pattern.compile(REG_EX_JSP);
        Matcher m = p.matcher(request.getParameter(PARAM_PAGE_PATH));
        if(m.find()){
            page = m.group();
        }
        router.setPage(request.getContextPath()+page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }
}

