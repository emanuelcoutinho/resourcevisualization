/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import entidade.Log;
import java.awt.BasicStroke;
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

public class ElasticidadeFisica {

    private int mv;
    private String arquivo;
    private double maior = 0;
    private double menor = 0;
    private double maiorERA = 0;
    private double menorERA = 0;
    private double maiorERD = 0;
    private double menorERD = 0;


    public ElasticidadeFisica() {
        this.mv = 6;
    }

    public ElasticidadeFisica(int mv) {
        this.mv = mv;
    }

    private XYDataset createDataset() throws IOException {
//        TimeSeries timeseries1 = new TimeSeries("SQN");
//        TimeSeries timeseries2 = new TimeSeries("TRA");
//        TimeSeries timeseries3 = new TimeSeries("TRD");
        TimeSeries timeseries4 = new TimeSeries("ERA");
        TimeSeries timeseries5 = new TimeSeries("ERD");
//        TimeSeries timeseries6 = new TimeSeries("ED");

        switch (mv) {
            case 1:
                arquivo = LeitorLog.nodea;
                break;
            case 2:
                arquivo = LeitorLog.nodeb;
                break;
            case 3:
                arquivo = LeitorLog.nodec;
                break;
            case 33:
                arquivo = LeitorLog.noded;
                break;
            case 4:
                arquivo = LeitorLog.media;
                break;
            case 5:
                arquivo = LeitorLog.alocacao;
                break;
            case 6:
                arquivo = LeitorLog.elasticidadefisica;
                break;
            default:
                arquivo = LeitorLog.nodea;
        }

        
        ArrayList l = LeitorLog.arquivoElasticidade(arquivo);
    // Tempo	SQN	TRA	TRD	ERA	ERD	ED

        entidade.Log inicial = (entidade.Log) l.get(0);
        maior = Double.parseDouble(String.valueOf(inicial.getMemoriaTotal()));
        menor = Double.parseDouble(String.valueOf(inicial.getMemoriaTotal()));
//        maiorERA = Double.parseDouble(String.valueOf(inicial.getMemoriaTotal()));
//        menorERA = Double.parseDouble(String.valueOf(inicial.getMemoriaTotal()));
//        maiorERD = Double.parseDouble(String.valueOf(inicial.getMemoriaUsed()));
//        menorERD = Double.parseDouble(String.valueOf(inicial.getMemoriaUsed()));

        for (int i = 0; i < l.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l.get(i);
    //            RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
//                Date d = Datas.stringToDataShellScript(log.getData());
                Date d = Datas.stringToDataElasticidade(log.getData());
                RegularTimePeriod p = new Millisecond(d);
//                timeseries1.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
//                timeseries2.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuSys())));
//                timeseries3.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuIdle())));
                timeseries4.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaTotal())));
                timeseries5.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaUsed())));
//                timeseries6.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getMemoriaFree())));
                System.out.println(log.toString());
                
                double x = Double.parseDouble(String.valueOf(log.getMemoriaTotal()));
                double y = Double.parseDouble(String.valueOf(log.getMemoriaUsed()));
                if (menor > x) {menor = x;}
                if (menor > y) {menor = y;}
                if (maior < x) {maior = x;}
                if (maior < y) {maior = y;}
                
            } catch (ParseException ex) {
                Logger.getLogger(Cpu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
//        timeseriescollection.addSeries(timeseries1);
//        timeseriescollection.addSeries(timeseries2);
//        timeseriescollection.addSeries(timeseries3);
        timeseriescollection.addSeries(timeseries4);
        timeseriescollection.addSeries(timeseries5);
        //timeseriescollection.addSeries(timeseries5);
        return timeseriescollection;
    }

    public void criaGrafico(JPanel jPanel1) throws IOException {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Elasticidade Física", "Data", "", createDataset(), true, true, false);
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
            
//			xylineandshaperenderer.setBaseShapesVisible(true);
//			xylineandshaperenderer.setBaseShapesFilled(true);
//			xyitemrenderer.setSeriesStroke(0, new BasicStroke(2.0F));
//			xyitemrenderer.setSeriesStroke(1, new BasicStroke(2.0F));            

		xylineandshaperenderer.setAutoPopulateSeriesStroke(false);
		xylineandshaperenderer.setBaseStroke(new BasicStroke(2F, 1, 1));
		xylineandshaperenderer.setDrawSeriesLineAsPath(true);
        }
        
    // Tempo	SQN	TRA	TRD	ERA	ERD	ED
        
//        xyitemrenderer.setSeriesPaint(0, Color.BLUE);
//        xyitemrenderer.setSeriesPaint(1, Color.RED);
//        xyitemrenderer.setSeriesPaint(2, Color.MAGENTA);
        xyitemrenderer.setSeriesPaint(0, Color.BLUE);
        xyitemrenderer.setSeriesPaint(1, Color.RED);
//        xyitemrenderer.setSeriesPaint(5, Color.BLACK);
        
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        
        Font fonte = new Font("Courrier", Font.BOLD, 14);
        dateaxis.setTickLabelFont(fonte);

        dateaxis.setVisible(true); // tira o eixo x
        
   // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
//        numberAxis.setTickUnit(new NumberTickUnit(3));
        
        double tick = Math.abs(menor) + Math.abs(maior);
        if (tick<=15) tick = 3;
        if ((tick>15) && (tick<=20)) tick = 4;
        if ((tick>20) && (tick<=25)) tick = 5;
        if ((tick>25) && (tick<=30)) tick = 6;
        if (tick>30) tick = 7;
        numberAxis.setTickUnit(new NumberTickUnit(tick));
        //numberAxis.setRange(-9, 9);
        numberAxis.setRange(menor, maior);

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
