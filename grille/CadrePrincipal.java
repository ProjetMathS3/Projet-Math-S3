/**
 * Created by w14007405 on 09/10/15.
 */
package grille;

import java.awt.*;
import java.beans.Expression;
import javax.swing.*;



public class CadrePrincipal extends JFrame {
    private static final int nombreParametres = 6; //Modifier cette valeur si on veux ajouter ou supprimer des paramètres
    private static final String[] nomsParametres =
            { "Nombre de proie", "Nombre de prédateur", "Taux de reproduction", "Famine", "Déplacement proie", "Déplacement prédateur" };
    private double[] valeurParametres = { 10, 10, 0.2, 0.5, 10, 10 }; //Paramètres par défaut
    private JTextField[] champsParametres;
    private JTextField champExpression;
    //private PanneauDessin dessin;
    private Expression expression = null;

    public CadrePrincipal () {
        super ("Simulateur d'évolution");

        //Panneau à droite Les paramètres !
        champsParametres = new JTextField[nombreParametres];
        Box panneauDeParametres = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < nombreParametres; ++i) {
            JLabel etiquette = new JLabel(nomsParametres[i]);
            etiquette.setPreferredSize(new Dimension(300, 20)); //Modifie la taille pour le text des param (300) et 20 c'est la hauteur de l'input

            JTextField champ = champsParametres[i] = new JTextField(6);
            champ.setHorizontalAlignment(JTextField.RIGHT);
            champ.setFont(new Font("Monospaced", Font.PLAIN, 12));
            champ.setText("" + valeurParametres[i]);

            Box uneLigne = new Box(BoxLayout.X_AXIS);
            uneLigne.add(etiquette);
            uneLigne.add(champ);
            panneauDeParametres.add(uneLigne);
            if (2 == i)
                panneauDeParametres.add(Box.createVerticalStrut(10));
        }

            JButton bouton = new JButton("Mise à jour des paramètres");
            panneauDeParametres.add(Box.createVerticalStrut(10));
            panneauDeParametres.add(bouton);

            panneauDeParametres.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder(
                                    BorderFactory.createEtchedBorder(),
                                    "Paramètres"),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

            JPanel panneauDeDroite = new JPanel();
            panneauDeDroite.add(panneauDeParametres);

            // panneau de gauche (Partie david) La grille !
            JPanel panneauDeGauche = new JPanel();
            DisplayFrame disp = new DisplayFrame("Proie/Prédateur", 50, 50 );
            panneauDeGauche.add(disp);



            // assemblage final
            JPanel panneauCentral = new JPanel();
            panneauCentral.setLayout(new BorderLayout(10, 10));
            panneauCentral.setLayout(new GridLayout(1, 2));
            panneauCentral.add(panneauDeGauche);
            panneauCentral.add(panneauDeDroite);


            JPanel panneauTotal = new JPanel();
            panneauTotal.setLayout(new BorderLayout(10, 10));
            panneauTotal.add(panneauCentral, BorderLayout.CENTER);

            panneauTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            setContentPane(panneauTotal);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new CadrePrincipal();
    }
}


