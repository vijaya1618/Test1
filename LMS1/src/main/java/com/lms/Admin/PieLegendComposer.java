package com.lms.Admin;
import org.zkoss.chart.Chart;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.PiePlotOptions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;
public class PieLegendComposer extends SelectorComposer<Div> {

    @Wire
    Charts chart;
    
    public void doAfterCompose(Div comp) throws Exception {
        super.doAfterCompose(comp);
        
        Chart chartOptional = chart.getChart();
        chartOptional.setPlotBorderWidth(0);
        chartOptional.setPlotShadow(false);
        
        chart.getTooltip().setPointFormat(
            "{series.name}: <b>{point.percentage:.1f}%</b>");
        
        PiePlotOptions plotOptions = chart.getPlotOptions().getPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor("pointer");
        plotOptions.getDataLabels().setEnabled(false);
        plotOptions.setShowInLegend(true);
        
        Series series = chart.getSeries();
        series.setType("pie");
        series.setName("Average");
        series.addPoint(new Point("Students", 45.0));
        series.addPoint(new Point("tutors", 12.8));
        Point point = new Point("Courses", 26.8);
        point.setSliced(true);
        point.setSelected(true);
        series.addPoint(point);
        series.addPoint(new Point("Exams", 13.4));
        series.addPoint(new Point("Reports", 2.0));
       
    }
}