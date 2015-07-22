/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import entidade.Log;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import util.Datas;
import util.LeitorLog;

/**
 *
 * @author leleco
 */
public class TomCatBarraTodos {

    private int mv;
    private String arquivo;

    public TomCatBarraTodos() {
        this.mv = 1;
    }

    public TomCatBarraTodos(int mv) {
        this.mv = mv;
    }

    private IntervalXYDataset createDataset() throws IOException {
        TimeSeries timeseries1 = new TimeSeries("Tempo de Resposta 1");
        TimeSeries timeseries2 = new TimeSeries("Tempo de Resposta 2");
        TimeSeries timeseries3 = new TimeSeries("Tempo de Resposta 3");
        TimeSeries timeseries4 = new TimeSeries("Tempo de Resposta 4");

//        ArrayList l1 = LeitorLog.arquivoColuna(arquivo, 5);
//        ArrayList l11 = LeitorLog.arquivoColunaTexto(arquivo, 1);
        ArrayList l1 = LeitorLog.arquivoColuna(LeitorLog.tomcat1, 5);
        ArrayList l11 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat1, 1);

            // utiliza o cpuuser para tudo
        
        for (int i = 0; i < l1.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l1.get(i);
                entidade.Log data = (entidade.Log) l11.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries1.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        ArrayList l2 = LeitorLog.arquivoColuna(arquivo, 5);
//        ArrayList l12 = LeitorLog.arquivoColunaTexto(arquivo, 1);
        ArrayList l2 = LeitorLog.arquivoColuna(LeitorLog.tomcat2, 5);
        ArrayList l12 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat2, 1);

            // utiliza o cpuuser para tudo
        
        for (int i = 0; i < l2.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l2.get(i);
                entidade.Log data = (entidade.Log) l12.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries2.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        
        
//        ArrayList l3 = LeitorLog.arquivoColuna(arquivo, 5);
//        ArrayList l13 = LeitorLog.arquivoColunaTexto(arquivo, 1);
        ArrayList l3 = LeitorLog.arquivoColuna(LeitorLog.tomcat3, 5);
        ArrayList l13 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat3, 1);

            // utiliza o cpuuser para tudo
        
        for (int i = 0; i < l3.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l3.get(i);
                entidade.Log data = (entidade.Log) l13.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries3.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        ArrayList l4= LeitorLog.arquivoColuna(arquivo, 5);
//        ArrayList l14 = LeitorLog.arquivoColunaTexto(arquivo, 1);
        ArrayList l4 = LeitorLog.arquivoColuna(LeitorLog.tomcat4, 5);
        ArrayList l14 = LeitorLog.arquivoColunaTexto(LeitorLog.tomcat4, 1);

            // utiliza o cpuuser para tudo
        
        for (int i = 0; i < l4.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l4.get(i);
                entidade.Log data = (entidade.Log) l14.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries4.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries1);
        timeseriescollection.addSeries(timeseries2);
        timeseriescollection.addSeries(timeseries3);
        timeseriescollection.addSeries(timeseries4);
        return timeseriescollection;
     
    }

    public void criaGrafico(JPanel jPanel1) throws IOException{
        JFreeChart jfreechart = ChartFactory.createXYBarChart("Tempo de Resposta - TomCat", "", true, "ms", createDataset(), PlotOrientation.VERTICAL, true, false, false);
        jfreechart.setBackgroundPaint(Color.white);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
        xyplot.setBackgroundPaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.black);
        xyitemrenderer.setSeriesPaint(0, Color.blue);
        xyitemrenderer.setSeriesPaint(1, Color.RED);
        xyitemrenderer.setSeriesPaint(2, Color.MAGENTA);
        xyitemrenderer.setSeriesPaint(3, Color.BLACK);
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        dateaxis.setLowerMargin(0.01D);
        dateaxis.setUpperMargin(0.01D);
        
        // fonte do eixo X

        Font fonte = new Font("Courrier", Font.BOLD, 14);
        dateaxis.setTickLabelFont(fonte);
        dateaxis.setVisible(true); // tira o eixo x
        
        // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
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