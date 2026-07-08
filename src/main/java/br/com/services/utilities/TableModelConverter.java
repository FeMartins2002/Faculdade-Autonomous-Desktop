package br.com.services.utilities;

import br.com.entities.Freelancer;
import br.com.entities.Scale;
import br.com.entities.Store;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public class TableModelConverter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,##0.00");

    public DefaultTableModel createScaleModel(List<Scale> scales) {
        String[] columns = {"ID", "Data", "Entrada", "Saída", "Freelancer", "Loja", "Valor", "Ponto de Entrada", "Ponto de Saída"};

        return buildModel(scales, columns, s -> new Object[]{
                s.getId(),
                formatDate(s.getScaleDate()),
                s.getStartTime(),
                s.getEndTime(),
                s.getFreelancerName(),
                s.getStoreName(),
                formatValue(s.getScaleValue()),
                getSafePoint(s, 0),
                getSafePoint(s, 1)
        });
    }

    public DefaultTableModel createScaleExtendedModel(List<Scale> scales) {
        String[] columns = {"ID", "Status","Data", "Entrada", "Saída", "Freelancer", "Loja", "Valor", "Ponto de Entrada", "Ponto de Saída"};

        return buildModel(scales, columns, s -> new Object[]{
                s.getId(),
                s.getScaleStatus(),
                formatDate(s.getScaleDate()),
                s.getStartTime(),
                s.getEndTime(),
                s.getFreelancerName(),
                s.getStoreName(),
                formatValue(s.getScaleValue()),
                getSafePoint(s, 0),
                getSafePoint(s, 1)
        });
    }

    public DefaultTableModel createFreelancerModel(List<Freelancer> freelancers) {
        String[] columns = {"ID", "CPF", "Nome", "Telefone", "E-mail"};

        return buildModel(freelancers, columns, f -> new Object[]{
                f.getId(),
                f.getCpf(),
                f.getName(),
                f.getPhone(),
                f.getEmail(),
        });
    }

    public DefaultTableModel createStoreModel(List<Store> stores) {
        String[] columns = {"ID", "Nome", "Telefone", "Endereço"};

        return buildModel(stores, columns, s -> new Object[]{
                s.getId(),
                s.getName(),
                s.getPhone(),
                s.getAddress(),
        });
    }

    private <T> DefaultTableModel buildModel(List<T> list, String[] columns, Function<T, Object[]> rowMapper) {
        DefaultTableModel model = createModel(columns);

        if (list == null || list.isEmpty()) {
            return model;
        }

        for (T item : list) {
            model.addRow(rowMapper.apply(item));
        }

        return model;
    }

    private DefaultTableModel createModel(String[] columns) {
        return new DefaultTableModel(columns, 0);
    }

    private String getSafePoint(Scale scale, int index) {
        if (scale.getPoints() == null || scale.getPoints().size() <= index) {
            return "Sem ponto";
        }

        var point = scale.getPoints().get(index);
        return point.getFormattedPoint();
    }

    private String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }

        return date.format(DATE_FORMATTER);
    }

    private String formatValue(double value) {
        return MONEY_FORMAT.format(value);
    }
}
