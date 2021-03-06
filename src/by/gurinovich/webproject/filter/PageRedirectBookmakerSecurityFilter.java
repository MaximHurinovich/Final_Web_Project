package by.gurinovich.webproject.filter;

import by.gurinovich.webproject.entity.Person;
import by.gurinovich.webproject.util.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/bookmaker/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectBookmakerSecurityFilter implements Filter{
    private String indexPath;

    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)==null ||
                !Constant.BOOKMAKER_ROLE.equals(((Person)httpRequest.getSession().getAttribute(Constant.ATTRIBUTE_NAME_USER)).getRole())) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }else{
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }
}



