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

import java.util.ArrayList;


public class AfficheGraphe extends ApplicationFrame
{
    public AfficheGraphe(final String title, double NbProie, double NataProie, double MortaProie, double NbPred, double NataPred, double MortaPred)
    {
        super( title );
        final XYDataset dataset = createVolterraDataset(NbProie, NataProie, MortaProie, NbPred, NataPred, MortaPred);
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
    }

    public AfficheGraphe(final String title, int[] ListeNbProie, int[] ListeNbPred) {
        super(title) ;
        final XYDataset dataset = createNormalDataset(ListeNbProie, ListeNbPred) ;
        final JFreeChart chart = createChart( dataset );
        final ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );
        chartPanel.setMouseZoomable( true , false );
        setContentPane( chartPanel );
    }

    private XYDataset createNormalDataset(int[] ListeNbProie, int[] ListeNbPred) {
        final TimeSeriesCollection series = new TimeSeriesCollection();
        final TimeSeries Proies = new TimeSeries ("Proies");
        final TimeSeries Predateurs = new TimeSeries ("Predateurs");
        Month current = new Month();
        int valueProie = 100;
        int valuePred = 100 ;
/*        int ListSize ;
        if (ListeNbPred.size() > ListSize) {
            ListSize = ListeNbPred.size() ;
        }
        else {
            ListSize = ListeNbProies.size() ;
        } // Les listes sont supposés avoir la même taille mais on sait jamais donc je prend la plus grande*/
        for (int i = 0 ; i < ListeNbProie.length ; i++) {
            try {
                valueProie = ListeNbProie[i];
                valuePred = ListeNbPred[i];
                Proies.add(current, valueProie);
                Predateurs.add(current, valuePred);
				current = ( Month ) current.next( );
            }
            catch ( SeriesException e ){
                System.err.println("Error adding to series");
            }
        }
        series.addSeries(Proies);
        series.addSeries(Predateurs);
        return series;
    }

    private XYDataset createVolterraDataset(double NbProie, double NataProie, double MortaProie, double NbPred, double NataPred, double Mortapred)
    {
    	LodkaVolterra ValeursLodka = new LodkaVolterra(NbProie, NataProie, MortaProie, NbPred, NataPred, Mortapred) ;
    	// Valeurs par défaut pour avoir un graphe pas trop moche : 53000, 0.09, 0.00001, 9000, 0.000005, 0.25
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
	    		valueProie = ValeursLodka.getNbProie(i);
	    		valuePred = ValeursLodka.getNbPredateur(i) ;
                Proies.add(current, new Double( valueProie ) );
                Predateurs.add(current, new Double (valuePred));
                current = ( Month ) current.next( );
                ValeursLodka.next(i) ;
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
        // PROGRAMME TEST
        final String title = "Lodka Volterra";
        final String title2= "Projet Math" ;

        int[] Liste1 = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9} ;
        int[] Liste2 = new int[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0} ;

        final AfficheGraphe GrapheLodka = new AfficheGraphe( title, 53000, 0.09, 0.00001, 9000, 0.000005, 0.25 );
        final AfficheGraphe GrapheProjet = new AfficheGraphe (title2, Liste1, Liste2) ;

        GrapheLodka.pack( );
        GrapheProjet.pack() ;
        RefineryUtilities.positionFrameRandomly( GrapheLodka );
        RefineryUtilities.positionFrameRandomly( GrapheProjet );
        GrapheLodka.setVisible( true );
        GrapheProjet.setVisible(true) ;

        /* Ce programme a été codé dans le noir, j'ai un bug sur IntelliJ et je suis dans l'impossibilité de faire des tests :(
        / La fonction affiche graphe peut s'appeller de deux mannière :
            - Avec deux ArrayList<int> en parametre elle les affiche simplement leur contenu
            - Avec les valeurs de LodkaVolterra (que l'on est sencé récupérer selon les inputs de l'utilisateur) et fait le calcul auto grace a LodkaVolterra.java
        */
    }
}