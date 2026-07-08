package br.com.view.utilities;

import br.com.enums.ScaleStatus;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.TextAnchor;

import java.awt.*;

public class ChartFormatter {
    private static final Color COLOR_BARS = new Color(59, 111, 146);
    private static final Color COLOR_SUCCESS = new Color(46, 204, 113);
    private static final Color COLOR_CANCEL = new Color(231, 76, 60);
    private static final Color COLOR_BACKGROUND = Color.WHITE;

    public void applyModernVerticalBarChartStyle(JFreeChart chart) {
        chart.setBackgroundPaint(COLOR_BACKGROUND);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, COLOR_BARS);
        renderer.setMaximumBarWidth(0.08);
        renderer.setShadowVisible(false);

        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator()
        );

        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(Color.BLACK);

        renderer.setBaseItemLabelFont(
                new Font("Arial", Font.BOLD, 12)
        );

        renderer.setBasePositiveItemLabelPosition(
                new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE12,
                        TextAnchor.BOTTOM_CENTER
                )
        );

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelsVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setLabel(null);
        rangeAxis.setUpperMargin(0.20);
    }

    public void applyModernHorizontalBarChartStyle(JFreeChart chart) {
        chart.setBackgroundPaint(COLOR_BACKGROUND);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, COLOR_BARS);
        renderer.setMaximumBarWidth(0.15);
        renderer.setShadowVisible(false);

        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator()
        );

        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelPaint(Color.BLACK);

        renderer.setBaseItemLabelFont(
                new Font("Arial", Font.BOLD, 12)
        );


        renderer.setBasePositiveItemLabelPosition(
                new ItemLabelPosition(
                        ItemLabelAnchor.OUTSIDE3,
                        TextAnchor.CENTER_LEFT
                )
        );

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelsVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setLabel(null);
        rangeAxis.setUpperMargin(0.15);
    }

    public void applyRingChartStyle(JFreeChart chart) {
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setSectionPaint(ScaleStatus.CONCLUIDO, COLOR_SUCCESS);
        plot.setSectionPaint(ScaleStatus.CANCELADO, COLOR_CANCEL);

        plot.setLabelGenerator(
                new StandardPieSectionLabelGenerator(
                        "{0} = {1}"
                )
        );

        plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
        plot.setLabelPaint(Color.BLACK);
        plot.setSimpleLabels(true);
    }
}
