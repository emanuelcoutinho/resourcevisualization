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
import telas.Teste1;
import util.Datas;
import util.LeitorLog;

/**
 *
 * @author leleco
 */
public class Rede {
    
        private int mv;
    private String arquivo;

    public Rede() {
        this.mv = 1;
    }

    public Rede(int mv) {
        this.mv = mv;
    }


    private XYDataset  createDataset() throws IOException {
        
        TimeSeries timeseries4 = new TimeSeries("Pacotes Recebidos");
        TimeSeries timeseries5 = new TimeSeries("Pacotes Enviados");
        
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
            default:
                arquivo = LeitorLog.nodea;
        }

        ArrayList l = LeitorLog.arquivo(arquivo);        
        for (int i = 0; i < l.size(); i++) {
            try {
                Log log = (Log) l.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                Date d = Datas.stringToDataShellScript(log.getData());
                RegularTimePeriod p = new Millisecond(d);
                timeseries4.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getRedeIn())));
                timeseries5.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getRedeOut())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(Rede.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries4);
        timeseriescollection.addSeries(timeseries5);
        return timeseriescollection;
        
    }

    public void criaGrafico(JPanel jPanel1) throws IOException {
        
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Rede", "Data", "Kb/s", createDataset(), true, true, false);
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
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        
        Font fonte = new Font("Courrier", Font.BOLD, 14);
        dateaxis.setTickLabelFont(fonte);
        dateaxis.setVisible(true); // tira o eixo x
               
   // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
    //    numberAxis.setTickUnit(new NumberTickUnit(20));
    //    numberAxis.setRange(0, 100);
        
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
