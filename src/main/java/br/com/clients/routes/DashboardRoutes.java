package br.com.clients.routes;

public class DashboardRoutes {
    public static final String BASE_URL = "http://localhost:8080/autonomous";
    public static final String STATISTICS = BASE_URL + "/statistics";

    public static final String STATISTICS_SCALE = STATISTICS + "/scales?year=";
    public static final String MONTHLY_SCALE = STATISTICS + "/monthly-scales?year=";

    public static final String FREELANCERS_SCALES =  STATISTICS + "/top-freelancers-scales?year=";
    public static final String FREELANCERS_PAYMENTS =  STATISTICS + "/top-freelancers-payments?year=";

    private DashboardRoutes() {

    }
}
