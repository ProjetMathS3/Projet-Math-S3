package graphe;

import javax.swing.*;
import java.awt.*;

public class PanneauDessin extends JPanel {
    
	private double xMin = 0 ;
	private double xMax ;
	private double yMin ;
	private double yMax = 0 ;
	private double deltaX = 1000 ; // TEMPORAIRE
	private double deltaY = 1000 ; // Les deltas devront être en fonction du tableau
	
	private static final int nombrePas = 1000 ;
	private AfficheFonction cadre ;
	
	
    PanneauDessin(AfficheFonction cadre) {
    	this.cadre = cadre ;
    	setBackground(Color.WHITE);
    	
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	LodkaVolterra Modele = cadre.getLodkaVolterra() ;
    	
    	// parametres du tracé
    	Modele.calcul();
    	xMax = Modele.getMax() ;
    	yMin = Modele.getTempsMax() ;
    	// deltaX = ???
    	// deltaY = ???
    	
    	// transformations affines pour x et y
    	Dimension d = getSize() ;
    	double Ax = d.width / (xMax - xMin) ;
    	double Bx = -Ax * xMin ;
    	double Ay = -d.height / (yMax - yMin) ;
    	double By = -Ay * yMax ;
    	
    	//tracé de la fonction PROIES 
    	double dx = ( xMax - xMin) / nombrePas ;
    	int xp = 0, yp = 0 ;
    	for (double x = xMin ; x <= xMax ; x += dx) {
    		double y = Modele.getNbProie((int)Math.round(x)) ;
    		int xc = (int) (Ax * x + Bx) ;
    		int yc = (int) (Ay * y + By) ;
    		if (xc != 0) {
    			g.drawLine(xp, yp, xc, yc) ;
    		}
    	}
    	
    }
}