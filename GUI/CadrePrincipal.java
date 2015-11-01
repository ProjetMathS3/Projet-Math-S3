/**
 * Created by w14007405 on 09/10/15.
 */
package GUI;

import core.App;
import core.Proie;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.CategoryMarker;

import java.awt.*;
import java.beans.Expression;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.table.JTableHeader;
import GUI.AfficheGraphe;


public class CadrePrincipal extends JFrame {
    public int c ;

    private static final int nombreParametres = 11; //Modifier cette valeur si on veux ajouter ou supprimer des paramètres
    private static final String[] nomsParametres =
            { "Déplacement proie", "Fréquence reproduction proies", "Taux de reproduction proies", "Mortalité des proies", "Vision proies",
                    "Déplacement prédateur", "Fréquence reproduction prédateurs", "Taux de reproduction prédateurs",
                    "Mortalité des prédateurs", "Vision prédateurs", "Famine"};
    private int[] valeurParametres = { 1, 1, 2, 0, 20, 1, 3, 1, 5, 20, 3 }; //Paramètres par défaut
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
    private JRadioButtonMenuItem OrdiIUT = new JRadioButtonMenuItem("1900 x 1080");
    private JRadioButtonMenuItem MoyenneRes = new JRadioButtonMenuItem("1600 x 900");
    private JRadioButtonMenuItem petiteRes = new JRadioButtonMenuItem("1366 x 768");
    private JRadioButtonMenuItem automatique = new JRadioButtonMenuItem("Automatique");

    App app;
    ArrayList<Point> positionsIndividus;



    public CadrePrincipal () {
        super ("Simulateur d'évolution");
        positionsIndividus = new ArrayList<>();
        JPanel panneauDeGauche = InitAndAddPanneauDeGauche(); //Initialise le panneau de gauche avec la grille et les paramètres
        JPanel panneauDuBas = InitAndAddTableau();

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
        bg2.add(automatique);
        bg2.add(OrdiIUT);
        bg2.add(MoyenneRes);
        bg2.add(petiteRes);
        //On présélectionne la première radio
        automatique.setSelected(true);

        this.test3_2.add(automatique);
        this.test3_2.add(OrdiIUT);
        this.test3_2.add(MoyenneRes);
        this.test3_2.add(petiteRes);
        //Ajout d'un sous menu à notre menu
        this.test3.add(this.test3_2);

        item3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (Point p : positionsIndividus) {
                    System.out.println(p);
                    app.ajouterEspece(new Proie(new Point(p.y, p.x), valeurParametres[0], valeurParametres[4], valeurParametres[2], valeurParametres[1], valeurParametres[3], 1));
                }
                System.out.println(app.getProies());
                app.jouerSimulation(10);
            }
        });
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
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        final int LE = (int)tailleEcran.getHeight();
        final int HE = (int)tailleEcran.getWidth();
        //Ajout des listener au JMenuItem
        OrdiIUT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setSize(1900, 1080);

            }
        });
        MoyenneRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setSize(1600, 900);


            }
        });
        petiteRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setSize(1366, 768);

            }
        });
        automatique.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setSize(LE, HE);
                pack();
            }
        });


        if (c > 20 && LE <= 1600) {
            String[] Resolution = {"1900 * 1080", "Automatique", "Continuez avec cette résolution"};
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
            String Res = (String)jop.showInputDialog(null,
                    "La résolution avec la qu'elle vous lancer l'application risque de créer des soucis d'affichage, veuillez saisir une autre résolution",
                    "Problème d'affichage",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Resolution,
                    Resolution[2]);
            if (Res == "1900 * 1080"){ setSize(1900, 1080);
                                       OrdiIUT.setSelected(true);
                jop2.showMessageDialog(null, "Votre résolution est " + Res, "Résolution changée", JOptionPane.INFORMATION_MESSAGE);
            }
            if (Res == "Continuez avec cette résolution"){
                setSize(LE,HE);
                jop2.showMessageDialog(null, "Votre résolution actuelle peut entrainer un problème d'affichage, si c'est le cas, veuillez la modifier dans l'onglet affichage", "Résolution inchangée", JOptionPane.WARNING_MESSAGE);
            }
            if (Res == "Automatique"){
                int largeurEcran =(int)tailleEcran.getHeight();
                int HauteurEcran =(int)tailleEcran.getWidth();
                if (largeurEcran < 1600) {
                    jop2.showMessageDialog(null, "Votre résolution d'écran est trop faible pour afficher correctement la grille avec cette taille ", "Résolution trop faible", JOptionPane.ERROR_MESSAGE);
                }
                    int ResTailleG = Integer.parseInt(jop.showInputDialog(null, "Veuillez saisir la nouvelle taille de la grille ( inférieure à la précédente)", "Nouvelle taille de la grille", JOptionPane.QUESTION_MESSAGE));
                    c = ResTailleG;
                panneauDeGauche = InitAndAddPanneauDeGaucheAutomatique();

            }

            // assemblage final
            JPanel AffichageApplication = new JPanel();
            AffichageApplication.setLayout(new BorderLayout());
            AffichageApplication.add(panneauDeGauche, BorderLayout.WEST);
            //AffichageApplication.add(panneauDeDroite, BorderLayout.CENTER);
            AffichageApplication.add(panneauDuBas, BorderLayout.SOUTH);

            this.setJMenuBar(menuBar);
            this.add(AffichageApplication);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setVisible(true);
            setResizable(false);//Interdit le redimensionnement à la main
        }

        else {
            // assemblage final
            JPanel AffichageApplication = new JPanel();
            AffichageApplication.setLayout(new BorderLayout());
            AffichageApplication.add(panneauDeGauche, BorderLayout.WEST);
            //AffichageApplication.add(panneauDeDroite, BorderLayout.CENTER);
            AffichageApplication.add(panneauDuBas, BorderLayout.SOUTH);

            this.setJMenuBar(menuBar);
            this.add(AffichageApplication);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setSize(LE, HE);
            pack();
            setVisible(true);
            setResizable(false);//Interdit le redimensionnement à la main
        }
    }

    private JPanel InitAndAddTableau() {
        //Panneau du bas : Affichage nombres espèces
        Object[][] donnees = {
                {"1000", "1300", "13000", true},
                {"999", "952", "12596", true},
                {"840", "450", "14000", true},
                {"756", "940", "26", false},
                {"648", "800", "1000", false},
                {"698", "1100", "5946", false},
                {"750", "1200", "6541", true}
        };
        String[] entetes = {"Proie", "prédateur"};
        JTable tableau = new JTable(donnees, entetes);
        JTableHeader header = tableau.getTableHeader();
        JPanel panneauDuBas = new JPanel();
        panneauDuBas.setLayout(new BorderLayout());
        panneauDuBas.add(header, BorderLayout.NORTH);
        panneauDuBas.add(tableau, BorderLayout.CENTER);
        return panneauDuBas;
    }

    private JPanel InitAndAddPanneauDeGauche() {
        //Panneau à gauche partie I : Les paramètres !
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
            if (4 == i)
                panneauDeParametres.add(Box.createVerticalStrut(10));
        }

        JButton bouton = new JButton("Mise à jour des paramètres");
        bouton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < champsParametres.length; i++) {
                    valeurParametres[i] = Integer.parseInt(champsParametres[i].getText());
                }
            }
        });
        panneauDeParametres.add(Box.createVerticalStrut(10));
        panneauDeParametres.add(bouton);

        panneauDeParametres.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createEtchedBorder(),
                                "Paramètres"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        //Panneau de gauche partie II : La grille
        String taille;
        taille=JOptionPane.showInputDialog(this,"Taille de la grille  : (ex:10 => 10 Lignes & 10 Colonnes ");
        c=Integer.parseInt(taille);
        JPanel panneauDeGauche = new JPanel();
        // Choix dynamique de la taille de la grille en fonction du nombre de case
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
        else if(c > 20 && c <= 100){
            hauteurG = (800);
            largeurG = (800);
        }
        else if(c > 100 && c < 200){
            hauteurG = (900);
            largeurG = (900);
        }
        Dimension Dim = new Dimension(hauteurG, largeurG );
        GridPanel disp = new GridPanel("Proie/Prédateur", c, c, Dim, positionsIndividus);

        app = new App(c);

        panneauDeGauche.add(disp);
        panneauDeGauche.add(panneauDeParametres);
        return panneauDeGauche;
    }

    private JPanel InitAndAddPanneauDeGaucheAutomatique() { //Ne redemande pas la taille de la grille, choisit comme taille de fenêtre la résolution de l'écran de l'utilisateur
        //Panneau à gauche partie I : Les paramètres !
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
        //Panneau de gauche partie II : La grille
        JPanel panneauDeGauche = new JPanel();
        // Choix dynamique de la taille de la grille en fonction du nombre de case
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
        else if(c > 20 && c <= 100){
            hauteurG = (800);
            largeurG = (800);
        }
        else if(c > 100 && c < 200){
            hauteurG = (900);
            largeurG = (900);
        }
        Dimension Dim = new Dimension(hauteurG, largeurG );
        GridPanel disp = new GridPanel("Proie/Prédateur", c, c, Dim, positionsIndividus);
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int LargeurEcran =(int)tailleEcran.getHeight();
        int HauteurEcran =(int)tailleEcran.getWidth();
        setSize(HauteurEcran,LargeurEcran);

        panneauDeGauche.add(disp);
        panneauDeGauche.add(panneauDeParametres);
        return panneauDeGauche;
    }



    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new CadrePrincipal();

    }
}


