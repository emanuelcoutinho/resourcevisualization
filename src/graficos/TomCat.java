/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
public class TomCat {

    private int mv;
    private String arquivo;

    public TomCat() {
        this.mv = 1;
    }

    public TomCat(int mv) {
        this.mv = mv;
    }

    private XYDataset createDataset() throws IOException {
        TimeSeries timeseries = new TimeSeries("Tempo de Resposta");
        
        switch (mv) {

            case 1:
                arquivo = LeitorLog.tomcat1;
                break;
            case 2:
                arquivo = LeitorLog.tomcat2;
                break;
            case 3:
                arquivo = LeitorLog.tomcat3;
                break;
            case 33:
                arquivo = LeitorLog.tomcat4;
                break;
            default:
                arquivo = LeitorLog.tomcat1;
        }

        ArrayList l = LeitorLog.arquivoColuna(arquivo, 5);
        ArrayList l11 = LeitorLog.arquivoColunaTexto(arquivo, 1);

            // utiliza o cpuuser para tudo
        
        for (int i = 0; i < l.size(); i++) {
            try {
                entidade.Log log = (entidade.Log) l.get(i);
                entidade.Log data = (entidade.Log) l11.get(i);
                //RegularTimePeriod p = new Millisecond(i, 1, 1, 1, 1, 1, 2000);
                RegularTimePeriod p = new Millisecond(Datas.stringToDataTomcat(data.getTexto()));
                timeseries.addOrUpdate(p, Double.parseDouble(String.valueOf(log.getCpuUser())));
                System.out.println(log.toString());
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);

        return timeseriescollection;
    }

    public void criaGrafico(JPanel jPanel1) throws IOException{
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
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        
        Font fonte = new Font("Courrier", Font.BOLD, 14);
        dateaxis.setTickLabelFont(fonte);
        dateaxis.setVisible(true); // tira o eixo x
        
        // Definindo a escala do eixo y do gráfico 
        
        NumberAxis numberAxis = (NumberAxis) xyplot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberAxis.setAutoRangeIncludesZero(false);
   //     numberAxis.setTickUnit(new NumberTickUnit(1));
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
    
    
    public static void main(String []args){
   //     String s = "[12/Sep/2013:21:52:30 -0300]";
        String s = "[12/Sep/2013:21:52:30 +0000]";
        try {
            System.out.println( Datas.stringToDataTomcat(s) );
        } catch (ParseException ex) {
            Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
