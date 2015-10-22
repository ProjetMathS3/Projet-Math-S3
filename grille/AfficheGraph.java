/**
 * Created by w14007405 on 22/10/15.
 */
package grille;
import java.awt.Color;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class AfficheGraph extends ApplicationFrame
{
    public AfficheGraph( String applicationTitle, String chartTitle )
    {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                "Catégorie" ,
                "Population" ,
                createDataset() ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset( )
    {
        final XYSeries proie = new XYSeries( "Proie" );
        proie.add(1.0, 1.0);
        proie.add(2.0, 4.0);
        proie.add(3.0, 3.0);
        final XYSeries prédateur = new XYSeries( "Prédateur" );
        prédateur.add(1.0, 4.0);
        prédateur.add(2.0, 5.0);
        prédateur.add(3.0, 6.0);
        final XYSeries nourriture = new XYSeries( "Nourriture" );
        nourriture.add(3.0, 4.0);
        nourriture.add(4.0, 5.0);
        nourriture.add(5.0, 4.0);
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( proie );
        dataset.addSeries( prédateur );
        dataset.addSeries( nourriture );
        return dataset;
    }

    public static void main( String[ ] args )
    {
        AfficheGraph chart = new AfficheGraph("Proie/Prédateur", "Evolution des différentes espèces en fonction du temps");
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
