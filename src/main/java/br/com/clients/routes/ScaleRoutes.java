package br.com.clients.routes;

public class ScaleRoutes {
    public static final String BASE_URL = "http://localhost:8080/autonomous";

    public static final String SCALE = BASE_URL + "/scale";
    public static final String FIND_SCALES = BASE_URL + "/scale?status=";

    public static final String FREELANCER_OPTIONS = BASE_URL + "/freelancer/options";
    public static final String STORE_OPTIONS = BASE_URL + "/store/options";

    private ScaleRoutes() {

    }
}
