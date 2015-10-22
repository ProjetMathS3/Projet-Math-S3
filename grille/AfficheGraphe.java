/**
 * Created by w14007405 on 22/10/15.
 */
package grille;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartFactory;

import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class AfficheGraphe extends ApplicationFrame
{
    public AfficheGraphe(final String title)
    {
        super( title );
        final XYDataset dataset = createDataset( );
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset( )
    {
        final TimeSeries series = new TimeSeries( "Random Data" );
        Second current = new Second( );
        double value = 100.0;
        for (int i = 0; i < 4000; i++)
        {
            try
            {
                value = value + Math.random( ) - 0.5;
                series.add(current, new Double( value ) );
                current = ( Second ) current.next( );
            }
            catch ( SeriesException e )
            {
                System.err.println("Error adding to series");
            }
        }

        return new TimeSeriesCollection(series);
    }

    private JFreeChart createChart( final XYDataset dataset )
    {
        return ChartFactory.createTimeSeriesChart(
                "Proie",
                "Seconds",
                "Valeurs",
                dataset,
                false,
                false,
                false);
    }

    public static void main( final String[ ] args )
    {
        final String title = "Evolution des population en fonction du temps";
        final AfficheGraphe demo = new AfficheGraphe( title );
        demo.pack( );
        RefineryUtilities.positionFrameRandomly( demo );
        demo.setVisible( true );
    }
}