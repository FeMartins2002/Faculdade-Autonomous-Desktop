package br.com.services.utilities;

import br.com.entities.Freelancer;
import br.com.entities.Scale;
import br.com.entities.Store;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.function.Function;

public class TableModelConverter {

    private DefaultTableModel createModel(String[] columns) {
        return new DefaultTableModel(columns, 0);
    }

    private <T> DefaultTableModel buildModel(
            List<T> list,
            String[] columns,
            Function<T, Object[]> rowMapper
    ) {
        DefaultTableModel model = createModel(columns);

        if (list == null || list.isEmpty()) {
            return model;
        }

        for (T item : list) {
            model.addRow(rowMapper.apply(item));
        }

        return model;
    }

    public DefaultTableModel createFreelancerModel(List<Freelancer> freelancers) {
        String[] columns = {"CPF", "Nome", "E-mail", "Telefone", "Gestor"};

        return buildModel(freelancers, columns, f -> new Object[]{
                f.getCpf(),
                f.getName(),
                f.getEmail(),
                f.getPhone(),
                f.getManagerName()
        });
    }

    public DefaultTableModel createScaleModel(List<Scale> scales) {
        String[] columns = {"Status", "Valor", "Data", "Freelancer", "Gestor", "Loja", "Entrada", "Saida"};

        return buildModel(scales, columns, s -> new Object[]{
                s.getScaleStatus(),
                s.getScaleValue(),
                s.getScaleDateTime(),
                s.getFreelancerName(),
                s.getManagerName(),
                s.getStoreName(),
                getSafePoint(s, 0),
                getSafePoint(s, 1)
        });
    }

    public DefaultTableModel createStoreModel(List<Store> stores) {
        String[] columns = {"Nome", "Telefone", "Endereço", "Ativa"};

        return buildModel(stores, columns, s -> new Object[]{
                s.getName(),
                s.getPhone(),
                s.getAddress(),
                s.isActive(),
        });
    }

    private String getSafePoint(Scale scale, int index) {
        if (scale.getPoints() == null || scale.getPoints().size() <= index) {
            return "Sem ponto";
        }

        var point = scale.getPoints().get(index);
        return point.getFormattedPoint();
    }
}