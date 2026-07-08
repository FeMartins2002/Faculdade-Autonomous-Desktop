package br.com.services.utilities;

import br.com.dtos.responses.dashboard.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public class DatasetConverter {

    public static DefaultCategoryDataset toMonthlyScalesDataset(List<MonthlyScaleDTO> scales) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (scales == null || scales.isEmpty()) {
            return dataset;
        }

        for (MonthlyScaleDTO scale : scales) {
            dataset.addValue(
                    scale.getTotalScales(),
                    "Escalas",
                    scale.getlabel()
            );
        }

        return dataset;
    }

    public static DefaultPieDataset toScaleStatusDataset(List<ScaleStatusCountDTO> statusList) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        if (statusList == null || statusList.isEmpty()) {
            return dataset;
        }

        for (ScaleStatusCountDTO status : statusList) {
            dataset.setValue(
                    status.getStatus(),
                    status.getTotal()
            );
        }

        return dataset;
    }

    public static DefaultCategoryDataset toFreelancerRankingDataset(List<FreelancerRankingDTO> rankingList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (rankingList == null || rankingList.isEmpty()) {
            return dataset;
        }

        for (FreelancerRankingDTO ranking : rankingList) {
            dataset.addValue(
                    ranking.getTotalScales(),
                    "Escalas Concluídas",
                    ranking.getFreelancerName()
            );
        }

        return dataset;
    }

    public static DefaultCategoryDataset toFreelancerPaymentDataset(List<FreelancerCostDTO> paymentList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (paymentList == null || paymentList.isEmpty()) {
            return dataset;
        }

        for (FreelancerCostDTO payment : paymentList) {
            dataset.addValue(
                    payment.getTotalValue(),
                    "Valor Recebido",
                    payment.getFreelancerName()
            );
        }

        return dataset;
    }
}
