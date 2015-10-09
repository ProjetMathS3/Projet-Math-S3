package graphe;

import javax.swing.*;
import java.awt.*;

public class AfficheFonction extends JFrame {
	
	static final String[] titresParametres = { 
		"Nb proies", "Natalite proies",  "Mortalite proies", "Nb predateurs", "Natalite predateurs", "Mortalite predateurs"
	} ;
	static final Font POLICE = new Font("Monospaced", Font.PLAIN, 12) ;
	
	private double[] valeurParametres = {53000,0.09,0.00001, 9000, 0.000001, 0.25} ;
	private JTextField[] champsParametres ;
	
	private JPanel panneauDeDessin ;
	
	private LodkaVolterra Modele = new LodkaVolterra(53000, 0.09, 0.00001, 9000, 0.000001, 0.25, 10) ; ;
	
	public AfficheFonction() {
		super("Equation de Lotka Volterra");
		
		champsParametres = new JTextField[titresParametres.length] ;
		for (int i = 0 ; i < titresParametres.length; i++)
			champsParametres[i] = new JTextField(5) ;
		panneauDeDessin = new PanneauDessin(this) ;

    JPanel panA = new JPanel(new GridLayout(0, 2, 5, 5));
    for (int i = 0; i < titresParametres.length; i++) {
        champsParametres[i] = new JTextField(8);
        champsParametres[i].setHorizontalAlignment(JTextField.RIGHT);
        champsParametres[i].setFont(POLICE);
        champsParametres[i].setText(valeurParametres[i] + "");
        panA.add(new JLabel(titresParametres[i]));
        panA.add(champsParametres[i]);
    }
    
    panA.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Paramètres"), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    JButton boutonTracer = new JButton("Tracer");
    JPanel panB = new JPanel(new FlowLayout());
    panB.add(boutonTracer);
    
    JPanel panneauDeGauche = new JPanel(new BorderLayout());
    panneauDeGauche.add(panA, BorderLayout.NORTH);
    panneauDeGauche.add(panB, BorderLayout.CENTER);
    panneauDeGauche.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
    
    JPanel panneauDeContenu = new JPanel(new BorderLayout());
    panneauDeContenu.add(panneauDeGauche, BorderLayout.WEST);
    panneauDeContenu.add(panneauDeDessin, BorderLayout.CENTER);
    panneauDeContenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    setContentPane(panneauDeContenu);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
	}
	
	public LodkaVolterra getLodkaVolterra() {
		return Modele ;
	}
	
	public double getNbProies() {
		return valeurParametres[0] ;
	}
	
	public double getNataliteProies() {
		return valeurParametres[1] ;
	}
	
	public double getMortaliteProies() {
		return valeurParametres[2] ;
	}
	
	public double getNbPredateur() {
		return valeurParametres[3] ;
	}
	
	public double getNatalitePredateur() {
		return valeurParametres[4] ;
	}
	
	public double getMortalitePredateur() {
		return valeurParametres[5] ;
	}
	
	public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new AfficheFonction();

	}

}
