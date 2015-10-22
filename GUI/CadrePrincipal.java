/**
 * Created by w14007405 on 09/10/15.
 */
package GUI;

import java.awt.*;
import java.beans.Expression;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;




public class CadrePrincipal extends JFrame {
    public int c ;

    private static final int nombreParametres = 8; //Modifier cette valeur si on veux ajouter ou supprimer des paramètres
    private static final String[] nomsParametres =
            { "Nombre de proie", "Nombre de prédateur", "Taux de reproduction", "Mortalité des proies", "Mortalité des prédateurs", "Famine", "Déplacement proie", "Déplacement prédateur" };
    private double[] valeurParametres = { 10, 10, 0.2, 0.5, 1, 0.5, 10, 10 }; //Paramètres par défaut
    private JTextField[] champsParametres;
    private JTextField champExpression;
    //private PanneauDessin dessin;
    private Expression expression = null;

    //Bar de menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu test1 = new JMenu("Fichier");
    private JMenu test1_2 = new JMenu("Sous ficher");
    private JMenu test2 = new JMenu("Simulation");
    private JMenu test3 = new  JMenu ("Affichage");
    private JMenu test3_2 = new JMenu("Résolution ");

    private JMenuItem item1 = new JMenuItem("Ouvrir");
    private JMenuItem item2 = new JMenuItem("Fermer");
    private JMenuItem item3 = new JMenuItem("Lancer ");
    private JMenuItem item4 = new JMenuItem("Arrêter ");


    private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
    private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");

    private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
    private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");
    private JRadioButtonMenuItem jrmi3 = new JRadioButtonMenuItem("1900 x 1080");
    private JRadioButtonMenuItem jrmi4 = new JRadioButtonMenuItem("1600 x 900");
    private JRadioButtonMenuItem jrmi5 = new JRadioButtonMenuItem("1366 x 768");
    private JRadioButtonMenuItem jrmi6 = new JRadioButtonMenuItem("Automatique");






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

            // panneau de gauche La GUI !
            String taille;
            taille=JOptionPane.showInputDialog(this,"Taille de la grille  : (ex:10 => 10 Lignes & 10 Colonnes ");
            c=Integer.parseInt(taille);
            JPanel panneauDeGauche = new JPanel();
            // Choix dynamique de la taille de la GUI en fonction du nombre de case
            int hauteurG = (0);
            int largeurG = (0);
            if (c <= 10){
                 hauteurG = (400);
                 largeurG = (400);
            }
            else if(c > 10 && c <= 20){
                hauteurG = (500);
                largeurG = (500);
            }
            else if(c > 20 && c <= 200){
                hauteurG = (800);
                largeurG = (800);
            }
            Dimension Dim = new Dimension(hauteurG, largeurG );
            DisplayFrame disp = new DisplayFrame("Proie/Prédateur", c, c, Dim);

            panneauDeGauche.add(disp);




            // assemblage final
            JPanel panneauCentral = new JPanel();
            panneauCentral.setLayout(new BorderLayout());
            //panneauCentral.setSize(2*c,2*5);
            panneauCentral.add(panneauDeGauche, BorderLayout.WEST);
            panneauCentral.add(panneauDeDroite, BorderLayout.CENTER);


            final JPanel panneauTotal = new JPanel();
            panneauTotal.add(panneauCentral);




            //BAR DE MENU
            //On initialise nos menus
            this.test1.add(item1);

            //On ajoute les éléments dans notre sous-menu
            this.test1_2.add(jcmi1);
            this.test1_2.add(jcmi2);
            //Ajout d'un séparateur
            this.test1_2.addSeparator();
            //On met nos radios dans un ButtonGroup
            ButtonGroup bg = new ButtonGroup();
            bg.add(jrmi1);
            bg.add(jrmi1);
            //On présélectionne la première radio
            jrmi1.setSelected(true);

            this.test1_2.add(jrmi1);
            this.test1_2.add(jrmi2);

            //Ajout du sous-menu dans notre menu
            this.test1.add(this.test1_2);
            //Ajout d'un séparateur
            this.test1.addSeparator();
            item2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0) {
                    System.exit(0);
                }
            });

            //Ajout d'un séparateur
            this.test3_2.addSeparator();
            //On met nos radios dans un ButtonGroup
            ButtonGroup bg2 = new ButtonGroup();
            bg2.add(jrmi6);
            bg2.add(jrmi3);
            bg2.add(jrmi4);
            bg2.add(jrmi5);
            //On présélectionne la première radio
            jrmi6.setSelected(true);

            this.test3_2.add(jrmi6);
            this.test3_2.add(jrmi3);
            this.test3_2.add(jrmi4);
            this.test3_2.add(jrmi5);
            //Ajout d'un sous menu à notre menu
            this.test3.add(this.test3_2);

            this.test1.add(item2);
            this.test2.add(item3);
            this.test2.add(item4);


            //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
            //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
            this.menuBar.add(test1);
            this.menuBar.add(test2);
            this.menuBar.add(test3);
            this.setJMenuBar(menuBar);
            this.setVisible(true);
            Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            final int LE = (int)tailleEcran.getHeight();
            final int HE = (int)tailleEcran.getWidth();
            //Ajout des listener au JMenuItem
            jrmi3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setSize(1900,1080);

                }
            });
            jrmi4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setSize(1600,900);


                }
            });
            jrmi5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setSize(1366,768);

                }
            });
            jrmi6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setSize(LE,HE);
                    pack();
                }
            });

            /* Test Mode pleine écran

             */


            this.setJMenuBar(menuBar);
            this.add(panneauTotal);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setSize(LE,HE);
            pack();
            setVisible(true);


    }

    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new CadrePrincipal();

    }
}


