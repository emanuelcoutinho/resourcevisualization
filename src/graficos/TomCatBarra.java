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
public class TomCatBarra extends JFrame {

    private int mv;
    private String arquivo;

    public TomCatBarra() {
        this.mv = 1;
    }

    public TomCatBarra(int mv) {
        this.mv = mv;
    }

    private IntervalXYDataset createDataset() throws IOException {
        TimeSeries timeseries = new TimeSeries("Tempo de Resposta");
       // DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
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
                
                //dataset.addValue(Double.parseDouble(String.valueOf(log.getCpuUser())), "MV1", p);

                
            } catch (ParseException ex) {
                Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);

        return timeseriescollection;
    }

    public void criaGrafico(JPanel jPanel1) throws IOException{
        //JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Tempo de Resposta - TomCat", "Data", "ms", createDataset(), true, true, false);

//        CategoryDataset dataset = createDataset();
//        JFreeChart chart = TomCatBarra.createBarChart(dataset);

        JFreeChart jfreechart = ChartFactory.createXYBarChart("Tempo de Resposta - TomCat", "", true, "ms", createDataset(), PlotOrientation.VERTICAL, true, false, false);
        jfreechart.setBackgroundPaint(Color.white);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        xyitemrenderer.setBaseToolTipGenerator(standardxytooltipgenerator);
        xyplot.setBackgroundPaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.black);
        xyitemrenderer.setSeriesPaint(0, Color.blue);
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


        

        
        
//JFreeChart chart = ChartFactory.createBarChart(
//"Tempo de Resposta - TomCat", //Titulo
//"Data", // Eixo X
//"ms", //Eixo Y
//dataset, // Dados para o grafico
//PlotOrientation.VERTICAL, //Orientacao do grafico
//true, false, false); // exibir: legendas, tooltips, url
        
//        CategoryPlot p = (CategoryPlot)chart.getPlot();
//        CategoryPlot p = chart.getCategoryPlot();
//        p.setBackgroundPaint(Color.WHITE);
//        p.setDomainGridlinePaint(Color.blue);
//        p.setRangeGridlinePaint(Color.black); //gridline horizontal
//
//        
//        org.jfree.chart.renderer.category.CategoryItemRenderer pitemrenderer = p.getRenderer();
//        pitemrenderer.setSeriesPaint(0, Color.blue);
//        pitemrenderer.setBaseItemLabelPaint(Color.blue);
//        pitemrenderer.setBaseOutlinePaint(Color.blue);
//        pitemrenderer.setBasePaint(Color.blue);
//        pitemrenderer.setSeriesItemLabelPaint(0, Color.blue);
//        pitemrenderer.setSeriesOutlinePaint(0, Color.blue);
//        
//        BarRenderer renderer = (BarRenderer) p.getRenderer();
//        renderer.setDrawBarOutline(true);
//        renderer.setBaseOutlinePaint(Color.blue);
//        renderer.setBasePaint(Color.blue);
//        renderer.setBaseFillPaint(Color.blue);
//
//        GradientPaint gp0 = new GradientPaint(
//            0.0f, 0.0f, Color.blue, 
//            0.0f, 0.0f, Color.blue
//        );
//        renderer.setSeriesPaint(0, gp0);
//
//        
//        Font fonte = new Font("Courrier", Font.BOLD, 14);
//        CategoryAxis dateaxis = (CategoryAxis) p.getDomainAxis();
//        dateaxis.setTickLabelFont(fonte);
//        dateaxis.setVisible(true); // tira o eixo x
//
//        NumberAxis numberAxis = (NumberAxis) p.getRangeAxis();
//        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        numberAxis.setAutoRangeIncludesZero(false);
//    
//        
//        Font fonteY = new Font("Courrier", Font.BOLD, 14);
//        numberAxis.setLabelFont(fonteY);
//        numberAxis.setTickLabelFont(fonteY);
//        
        ///
        
        
//        p.setDomainGridlinesVisible(true); //mostra grid Y
//        p.setRangeGridlinesVisible(true); //mostra grid X
//        p.setRangeGridlinePaint(Color.black);
//        p.setDomainGridlinePaint(Color.black);

        
        /*
        
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

*/
        ChartPanel chartpanel1 = new ChartPanel(jfreechart);
        chartpanel1.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        chartpanel1.setVisible(true);
        jPanel1.removeAll();
        jPanel1.add(chartpanel1);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    
public TomCatBarra(String title) {
super(title);
CategoryDataset dataset = TomCatBarra.createDatasetBarra();
JFreeChart chart = TomCatBarra.createBarChart(dataset);
ChartPanel panel = new ChartPanel(chart);
panel.setPreferredSize(new Dimension(400, 300));
setContentPane(panel);
}

private static CategoryDataset createDatasetBarra() {
DefaultCategoryDataset dataset = new DefaultCategoryDataset();

dataset.addValue(1, "MV1", "data1");
dataset.addValue(2, "MV1", "data2");
dataset.addValue(3, "MV1", "data3");
dataset.addValue(4, "MV1", "data4");
dataset.addValue(3, "MV1", "data5");
dataset.addValue(2, "MV1", "data6");
dataset.addValue(1, "MV1", "data7");

return dataset;
}

private static JFreeChart createBarChart(CategoryDataset dataset) {
JFreeChart chart = ChartFactory.createBarChart(
"Escolha de cor por veículo", //Titulo
"Veículo", // Eixo X
"Quantidade", //Eixo Y
dataset, // Dados para o grafico
PlotOrientation.VERTICAL, //Orientacao do grafico
true, false, false); // exibir: legendas, tooltips, url
return chart;
}
    
    
    public static void main(String []args){
        
        TomCatBarra chart = new TomCatBarra("Teste Bar Chart");
        chart.pack();
        chart.setVisible(true);        
        
   //     String s = "[12/Sep/2013:21:52:30 -0300]";
        String s = "[12/Sep/2013:21:52:30 +0000]";
        try {
            System.out.println( Datas.stringToDataTomcat(s) );
        } catch (ParseException ex) {
            Logger.getLogger(TomCat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
