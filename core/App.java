package core;

import GUI.AfficheGraphe;
import utils.Case;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by r14003530 on 22/10/15.
 */
public class App {

    static final int TAILLE_MAP = 20;
    static final int NB_TOURS = 20;

    ArrayList<Espece> proies;
    ArrayList<Espece> predateurs;
    ArrayList<Espece> buffer;
    Case[][] positionsEspeces;
    int gridSize;

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
        proies = new ArrayList<>();
        predateurs = new ArrayList<>();
        buffer = new ArrayList<>();
        this.gridSize = gridSize;

        positionsEspeces = new Case[gridSize][gridSize];
        for (int x = 0; x < gridSize; ++x) {
            for (int y = 0; y < gridSize; ++y) {
                positionsEspeces[x][y] = Case.Vide;
            }
        }

        System.out.println("App created with size " + gridSize);
    }

    public void jouerSimulation(int nbTours) {
        int tour = 1;

/*        // Valeurs de test
        Predateur pred = new Predateur(3, 5, 1);
        ajouterEspece(pred);
        pred = new Predateur(4, 5, 1);
        ajouterEspece(pred);

*/


        Proie proie = new Proie(5, 3, 1);
        ajouterEspece(proie);
        proie = new Proie(3, 4, 1);
        ajouterEspece(proie);

        int[] nbProies = new int[nbTours];
        int[] nbPreds = new int[nbTours];

        while (tour <= nbTours) {
            System.out.println();
            System.out.println(tour);

            afficherPositions();

            System.out.println(getProies());
            for (Espece e : getProies()) {
                jouerTour(e, tour, getBuffer());
            }

            Iterator<Espece> it = getPredateurs().iterator();
            while(it.hasNext()) {
                Espece e = it.next();
                jouerTour(e, tour, getBuffer());
                if (tour > e.getGeneration() + e.getDureeDeVie()) {
                    e.mourir(getPositionsEspeces());
                    it.remove();
                }
            }

            for (Espece e : getBuffer()) {
                ajouterEspece(e);
            }

            nbProies[tour - 1] = getProies().size();
            nbPreds[tour - 1] = getPredateurs().size();

            tour++;
        }

        AfficheGraphe graphe = new AfficheGraphe("Graphe", nbProies, nbPreds);
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

    public void jouerTour(Espece e, int tour, ArrayList<Espece> buffer) {
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
        app.jouerSimulation(NB_TOURS);
    }
}
