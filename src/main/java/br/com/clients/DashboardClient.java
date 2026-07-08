package br.com.clients;

import br.com.dtos.responses.dashboard.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static br.com.clients.routes.DashboardRoutes.*;

public class DashboardClient extends BaseClient {

    public List<MonthlyScaleDTO> findMonthlyScalesByYear(int year) {
        return get(MONTHLY_SCALE + year, new TypeReference<List<MonthlyScaleDTO>>() {});
    }

    public List<ScaleStatusCountDTO> findScaleCompletedsAndCancelledsByYear(int year) {
        return get(STATISTICS_SCALE + year, new TypeReference<List<ScaleStatusCountDTO>>() {});
    }

    public List<FreelancerRankingDTO> findTopFreelancersScalesByYear(int year) {
        return get(FREELANCERS_SCALES + year, new TypeReference<List<FreelancerRankingDTO>>() {});
    }

    public List<FreelancerCostDTO> findTopFreelancersPaymentByYear(int year) {
        return get(FREELANCERS_PAYMENTS + year, new TypeReference<List<FreelancerCostDTO>>() {});
    }
}
