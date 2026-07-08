package br.com.clients.routes;

public class ManagerRoutes {
    public static final String BASE_URL = "http://localhost:8080/autonomous";
    public static final String MANAGER = "/manager";

    public static final String AUTH = BASE_URL + "/authentication" + MANAGER;
    public static final String PASSWORD = BASE_URL + "/authentication/password";
    public static final String TOKEN = BASE_URL + "/authentication/token?email=";

    private ManagerRoutes() {

    }
}
