package by.gurinovich.webproject.servlet;

public class Router {
    public enum RouteType{
        FORWARD, REDIRECT
    }
    private String page;
    private RouteType route = RouteType.FORWARD;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if(route==null){
            this.route = RouteType.FORWARD;
        }
        this.route = route;
    }
}
