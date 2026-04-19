package br.com.clients.routes;

public class FreelancerRoutes {
    public static final String BASE_URL = "http://localhost:8080/autonomous";

    public static final String FREELANCER = BASE_URL + "/freelancer";
    public static final String FREELANCER_ACTIVES = FREELANCER + "/actives";
    public static final String FREELANCER_INACTIVES = FREELANCER + "/inactives";

    private FreelancerRoutes() {

    }
}
