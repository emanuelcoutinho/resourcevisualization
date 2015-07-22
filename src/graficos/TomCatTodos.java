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
public class TomCatTodos {

    private int mv;
    private String arquivo;

    public TomCatTodos() {
        this.mv = 1;
    }

    public TomCatTodos(int mv) {
        this.mv = mv;
    }

    private XYDataset createDataset() throws IOException {
        TimeSeries timeseries1 = new TimeSeries("Tempo de Resposta 1");
        TimeSeries timeseries2 = new TimeSeries("Tempo de Resposta 2");
        TimeSeries timeseries3 = new TimeSeries("Tempo de Resposta 3");
        TimeSeries timeseries4 = new TimeSeries("Tempo de Resposta 4");

        ArrayList l1 = LeitorLog.arquivoColuna(LeitorLog.tomcat1, 5);
        ArrayList l11 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat1, 1);

        // utiliza o cpuuser para tudo

        for (int i = 0; i < l1.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l1.get(i);
                entidade.Log data = (entidade.Log) l11.get(i);
    //            RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries1.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCatTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList l2 = LeitorLog.arquivoColuna(LeitorLog.tomcat2, 5);
         ArrayList l22 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat2, 1);

        // utiliza o cpuuser para tudo

        for (int i = 0; i < l2.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l2.get(i);
                entidade.Log data = (entidade.Log) l22.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries2.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCatTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList l3 = LeitorLog.arquivoColuna(LeitorLog.tomcat3, 5);
        ArrayList l33 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat3, 1);
 
        // utiliza o cpuuser para tudo

        for (int i = 0; i < l3.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l3.get(i);
                entidade.Log data = (entidade.Log) l33.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries3.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCatTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ArrayList l4 = LeitorLog.arquivoColuna(LeitorLog.tomcat4, 5);
        ArrayList l44 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat4, 1);
 
        // utiliza o cpuuser para tudo

        for (int i = 0; i < l4.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l4.get(i);
                entidade.Log data = (entidade.Log) l44.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries4.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCatTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries1);
        timeseriescollection.addSeries(timeseries2);
        timeseriescollection.addSeries(timeseries3);
        timeseriescollection.addSeries(timeseries4);
        return timeseriescollection;
    }

    public void criaGrafico(JPanel jPanel1) throws IOException {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Tempo de Resposta - TomCat", "Data", "ms", createDataset(), true, true, false);
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
        xyitemrenderer.setSeriesPaint(3, Color.BLACK);
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        Font fonte = new Font("Courrier", Font.BOLD, 12);
        dateaxis.setTickLabelFont(fonte);
        dateaxis.setVisible(true); // tira o eixo x

        // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
    //    numberAxis.setTickUnit(new NumberTickUnit(1));
   //     numberAxis.setRange(0, 5);
        
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
