package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import graphe.LodkaVolterra;


public class AfficheGraphe extends ApplicationFrame
{
    public AfficheGraphe(final String title)
    {
        super( title );
        final XYDataset dataset = createDataset(  );
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
    }
    private XYDataset createDataset( )
    {
    	LodkaVolterra test = new LodkaVolterra(53000, 0.09, 0.00001, 9000, 0.000005, 0.25) ;
    	// Valeurs par défaut pour avoir un graphe pas trop moche
        final TimeSeriesCollection series = new TimeSeriesCollection();
        final TimeSeries Proies = new TimeSeries ("Proies");
        final TimeSeries Predateurs = new TimeSeries ("Predateurs");
        Month current = new Month();
        double valueProie = 100.0;
        double valuePred = 100.0 ;
        for (int i = 0; i < 100; i++)
        {
            try
            {	
	    		valueProie = test.getNbProie(i);
	    		valuePred = test.getNbPredateur(i) ;
                Proies.add(current, new Double( valueProie ) );
                Predateurs.add(current, new Double (valuePred));
                current = ( Month ) current.next( );
                test.next(i) ;
            }
            catch ( SeriesException e )
            {
                System.err.println("Error adding to series");
            }
        }
        series.addSeries(Proies);
        series.addSeries(Predateurs);
        return series;
    }
    
    private JFreeChart createChart( final XYDataset dataset )
    {
        return ChartFactory.createTimeSeriesChart(
                "",
                "Années",
                "Population",
                dataset,
                true,
                false,
                false);
    }
    public static void main( final String[ ] args )
    {
        final String title = "Evolution Proie & Predateur";
        final AfficheGraphe Graphe = new AfficheGraphe( title );
        Graphe.pack( );
        RefineryUtilities.positionFrameRandomly( Graphe );
        Graphe.setVisible( true );
    }
}