/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import entidade.Log;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import util.Datas;
import util.LeitorLog;

/**
 *
 * @author leleco
 */
public class MemoriaTodos {

    private int mv;
    private String arquivo;

    public MemoriaTodos() {
        this.mv = 1;
    }

    public MemoriaTodos(int mv) {
        this.mv = mv;
    }

    private XYDataset createDataset() throws IOException {
        TimeSeries timeseries1 = new TimeSeries("Livre MV1");
        TimeSeries timeseries2 = new TimeSeries("Total MV1");
        TimeSeries timeseries3 = new TimeSeries("Usuário MV1");

        TimeSeries timeseries4 = new TimeSeries("Livre MV2");
        TimeSeries timeseries5 = new TimeSeries("Total MV2");
        TimeSeries timeseries6 = new TimeSeries("Usuário MV2");

        TimeSeries timeseries7 = new TimeSeries("Livre MV3");
        TimeSeries timeseries8 = new TimeSeries("Total MV3");
        TimeSeries timeseries9 = new TimeSeries("Usuário MV3");

        TimeSeries timeseries10 = new TimeSeries("Livre MV4");
        TimeSeries timeseries11 = new TimeSeries("Total MV4");
        TimeSeries timeseries12 = new TimeSeries("Usuário MV4");

        ArrayList l1 = LeitorLog.arquivo(LeitorLog.nodea);

        for (int i = 0; i < l1.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l1.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                Date d = Datas.stringToDataShellScript(log.getData());
                RegularTimePeriod p = new Millisecond(d);
                timeseries1.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaFree())));
                timeseries2.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaTotal())));
                timeseries3.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaUsed())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(MemoriaTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList l2 = LeitorLog.arquivo(LeitorLog.nodeb);

        for (int i = 0; i < l2.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l2.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                Date d = Datas.stringToDataShellScript(log.getData());
                RegularTimePeriod p = new Millisecond(d);
                timeseries4.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaFree())));
                timeseries5.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaTotal())));
                timeseries6.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaUsed())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(MemoriaTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList l3 = LeitorLog.arquivo(LeitorLog.nodec);

        for (int i = 0; i < l3.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l3.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                Date d = Datas.stringToDataShellScript(log.getData());
                RegularTimePeriod p = new Millisecond(d);
                timeseries7.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaFree())));
                timeseries8.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaTotal())));
                timeseries9.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaUsed())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(MemoriaTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ArrayList l4 = LeitorLog.arquivo(LeitorLog.noded);

        for (int i = 0; i < l4.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l4.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                Date d = Datas.stringToDataShellScript(log.getData());
                RegularTimePeriod p = new Millisecond(d);
                timeseries10.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaFree())));
                timeseries11.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaTotal())));
                timeseries12.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaUsed())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(MemoriaTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries1);
        timeseriescollection.addSeries(timeseries2);
        timeseriescollection.addSeries(timeseries3);
        timeseriescollection.addSeries(timeseries4);
        timeseriescollection.addSeries(timeseries5);
        timeseriescollection.addSeries(timeseries6);
        timeseriescollection.addSeries(timeseries7);
        timeseriescollection.addSeries(timeseries8);
        timeseriescollection.addSeries(timeseries9);
        timeseriescollection.addSeries(timeseries10);
        timeseriescollection.addSeries(timeseries11);
        timeseriescollection.addSeries(timeseries12);
        return timeseriescollection;
    }

    public void criaGrafico(JPanel jPanel1) throws IOException {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Memória nas MVs", "Data", "% Memória", createDataset(), true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.WHITE);
        xyplot.setDomainPannable(true);
        xyplot.setRangePannable(false);
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(false);
        }
        xyitemrenderer.setSeriesPaint(0, Color.blue);
        xyitemrenderer.setSeriesPaint(1, Color.RED);
        xyitemrenderer.setSeriesPaint(2, Color.MAGENTA);
        xyitemrenderer.setSeriesPaint(3, Color.blue);
        xyitemrenderer.setSeriesPaint(4, Color.RED);
        xyitemrenderer.setSeriesPaint(5, Color.MAGENTA);
        xyitemrenderer.setSeriesPaint(6, Color.blue);
        xyitemrenderer.setSeriesPaint(7, Color.RED);
        xyitemrenderer.setSeriesPaint(8, Color.MAGENTA);
        xyitemrenderer.setSeriesPaint(9, Color.blue);
        xyitemrenderer.setSeriesPaint(10, Color.RED);
        xyitemrenderer.setSeriesPaint(11, Color.MAGENTA);
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        
        Font fonte = new Font("Courrier", Font.BOLD, 14);
        dateaxis.setTickLabelFont(fonte);
        dateaxis.setVisible(true); // tira o eixo x

       // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
//        numberAxis.setTickUnit(new NumberTickUnit(20));
//       numberAxis.setRange(0, 100);
        
        Font fonteY = new Font("Courrier", Font.BOLD, 14);
        numberAxis.setLabelFont(fonteY);
        numberAxis.setTickLabelFont(fonteY);
        xyplot.setDomainGridlinesVisible(true); //mostra grid Y
        xyplot.setRangeGridlinesVisible(true); //mostra grid X
        xyplot.setRangeGridlinePaint(Color.black);
        xyplot.setDomainGridlinePaint(Color.black);
        
        ChartPanel chartpanel1 = new ChartPanel(jfreechart);
        chartpanel1.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        chartpanel1.setVisible(true);
        jPanel1.removeAll();
        jPanel1.add(chartpanel1);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
}
