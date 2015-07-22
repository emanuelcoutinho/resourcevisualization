/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author leleco
 */
public class NewClass1 {
    
  private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1000.0, "01/2012", "Mês/Ano");
        dataset.addValue(1750.0, "02/2012", "Mês/Ano");
        dataset.addValue(1500.0, "03/2012", "Mês/Ano");
        dataset.addValue(1000.0, "04/2012", "Mês/Ano");
        dataset.addValue(1750.0, "05/2012", "Mês/Ano");
        dataset.addValue(1500.0, "06/2012", "Mês/Ano");
        dataset.addValue(1000.0, "07/2012", "Mês/Ano");
        dataset.addValue(1750.0, "08/2012", "Mês/Ano");
        dataset.addValue(1500.0, "09/2012", "Mês/Ano");
        return dataset;
    }

  public void criaGrafico(JPanel jPanel1) {
        CategoryDataset cds = createDataset();
        String titulo = "Gráfico de Teste";
        String eixoy = "Valores";
        String txt_legenda = "Ledenda:";
        boolean legenda = true;
        boolean tooltips = true;
        boolean urls = true;
        JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);
        ChartPanel myChartPanel = new ChartPanel(graf, true);
        myChartPanel.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        myChartPanel.setVisible(true);
        jPanel1.removeAll();
        jPanel1.add(myChartPanel);
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    
}
