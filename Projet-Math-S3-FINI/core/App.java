package core;

import GUI.AfficheGraphe;
import GUI.GridPanel;
import utils.Case;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by r14003530 on 22/10/15.
 */
public class App {

    static final int TAILLE_MAP = 20;
    static final int NB_TOURS = 20;

    ArrayList<Espece> PresetProies = new ArrayList<Espece>() ;
    ArrayList<Espece> PresetPred = new ArrayList<Espece>() ;

    int tour;
    ArrayList<Espece> proies;
    ArrayList<Espece> predateurs;
    ArrayList<Espece> buffer;
    Case[][] positionsEspeces;
    int gridSize;
    AfficheGraphe graphe;

    public void setPresetProies () {
        ajouterEspece(new Proie(new Point(12, 11), 2, 15, 2, 1, 10, 20));
        ajouterEspece(new Proie(new Point(10, 10), 2, 15, 2, 1, 10, 20));
        ajouterEspece(new Proie(new Point(9, 15), 2, 15, 2, 1, 10, 20));
        ajouterEspece(new Proie(new Point(11, 14), 2, 15, 2, 1, 10, 20));
        ajouterEspece(new Proie(new Point(15, 14), 2, 15, 2, 1, 10, 20));
        ajouterEspece(new Proie(new Point(6, 19), 2, 15, 2, 1, 10, 20));
        //proies = PresetProies  ;
    }

    public void setPresetPredateurs() {
        ajouterEspece(new Predateur(new Point(10, 8), 4, 20, 1, 3, 7, 1, 2));
        ajouterEspece(new Predateur(new Point(8, 11), 4, 20, 1, 3, 7, 1, 2)) ;
        //predateurs = PresetPred ;
    }

    public int getTour() {
        return tour;
    }

    public ArrayList<Espece> getProies() {
        return proies;
    }

    public ArrayList<Espece> getPredateurs() {
        return predateurs;
    }

    public ArrayList<Espece> getBuffer() {
        return buffer;
    }

    public Case[][] getPositionsEspeces() {
        return positionsEspeces;
    }



    public App(int gridSize) {
        tour = 1;
        this.gridSize = gridSize;
        resetSimulation();
        System.out.println("App created with size " + gridSize);
    }

    public void resetSimulation() {
        proies = new ArrayList<>();
        predateurs = new ArrayList<>();
        buffer = new ArrayList<>();

        positionsEspeces = new Case[gridSize][gridSize];
        for (int x = 0; x < gridSize; ++x) {
            for (int y = 0; y < gridSize; ++y) {
                positionsEspeces[x][y] = Case.Vide;
            }
        }
    }

    private void ajouterBuffer() {
        for (Espece e : getBuffer()) {
            ajouterEspece(e);
        }
        buffer = new ArrayList<>();
    }

    public void jouerUnTour(int tour) {
        Iterator<Espece> it = getProies().iterator();
        while(it.hasNext()) {
            Espece e = it.next();
            jouerTourEspece(e, tour, getBuffer());
        }
        System.out.println(proies);

        it = getPredateurs().iterator();
        while(it.hasNext()) {
            Espece e = it.next();
            jouerTourEspece(e, tour, getBuffer());
            if (e.niveauFaim >= e.getDureeDeVie()) {
                e.mourir(getPositionsEspeces());
                it.remove();
            }
            System.out.println("App/ e.niveauFaim : " + e.niveauFaim + ";" + e.getDureeDeVie());
        }
        System.out.println(predateurs);
        ajouterBuffer();
    }

    public void initSimulationTPT() {
        tour = 1;
    }

    public void playSimulationTPT(GridPanel panel) {
        System.out.println(tour);
        afficherPositions();

        jouerUnTour(tour);
        panel.setButtonColors(positionsEspeces);

        tour++;
    }

    public void jouerSimulation(int nbTours, GridPanel panel) {
        System.out.println("---- NOUVELLE SIMULATION ----");
        tour = 1;

        int[] nbProies = new int[nbTours];
        int[] nbPreds = new int[nbTours];

        while (tour <= nbTours) {
            System.out.println(tour);
            afficherPositions();

            jouerUnTour(tour);

            nbProies[tour - 1] = getProies().size();
            nbPreds[tour - 1] = getPredateurs().size();

            tour++;
        }
        panel.setButtonColors(positionsEspeces);
        graphe = new AfficheGraphe("Graphe", nbProies, nbPreds);
    }

    public void afficherGraphe() {
        graphe.pack();
        graphe.setVisible(true);
    }

    public void ajouterEspece(Espece e) {
        if (positionsEspeces[e.getPosition().x][e.getPosition().y] == Case.Vide) {
            if (e instanceof Proie) {
                proies.add(e);
                positionsEspeces[e.getPosition().x][e.getPosition().y] = Case.Proie;
            }
            else if (e instanceof Predateur) {
                predateurs.add(e);
                positionsEspeces[e.getPosition().x][e.getPosition().y] = Case.Predateur;
            }
        }
    }

    public void jouerTourEspece(Espece e, int tour, ArrayList<Espece> buffer) {
        e.jouerTour(proies, predateurs, positionsEspeces, tour, gridSize, buffer);
        e.reinitTour();
    }

    public void afficherPositions() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (positionsEspeces[x][y] == Case.Vide) {
                    System.out.print(String.format("%10s",  x + ":" + y));
                } else {
                    System.out.print(String.format("%10s", positionsEspeces[x][y]));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        App app = new App(TAILLE_MAP);
        //app.jouerSimulation(NB_TOURS);
    }
}
