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

    ArrayList<Espece> proies;
    ArrayList<Espece> predateurs;
    ArrayList<Espece> buffer;
    Case[][] positionsEspeces;

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



    public App() {
        proies = new ArrayList<>();
        predateurs = new ArrayList<>();
        buffer = new ArrayList<>();

        positionsEspeces = new Case[TAILLE_MAP][TAILLE_MAP];
        for (int x = 0; x < TAILLE_MAP; ++x) {
            for (int y = 0; y < TAILLE_MAP; ++y) {
                positionsEspeces[x][y] = Case.Vide;
            }
        }
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
        e.jouerTour(proies, predateurs, positionsEspeces, tour, TAILLE_MAP, buffer);
        e.reinitTour();
    }

    public void afficherPositions() {
        for (int y = 0; y < TAILLE_MAP; y++) {
            for (int x = 0; x < TAILLE_MAP; x++) {
                if (positionsEspeces[x][y] == Case.Vide) {
                    System.out.print(String.format("%10s",  x + " " + y));
                } else {
                    System.out.print(String.format("%10s", positionsEspeces[x][y]));
                }
            }
            System.out.println();
        }
    }

    static final int NB_TOURS = 20;

    public static void main(String[] args) {
        App app = new App();
        int tour = 1;

        Predateur pred = new Predateur(3, 5, 1);
        app.ajouterEspece(pred);
        pred = new Predateur(4, 5, 1);
        app.ajouterEspece(pred);

        Proie proie = new Proie(15, 3, 1);
        app.ajouterEspece(proie);
        proie = new Proie(13, 4, 1);
        app.ajouterEspece(proie);

        int[] nbProies = new int[NB_TOURS];
        int[] nbPreds = new int[NB_TOURS];

        while (tour <= NB_TOURS) {
            System.out.println();
            System.out.println(tour);

            app.afficherPositions();

            for (Espece e : app.getProies()) {
                app.jouerTour(e, tour, app.getBuffer());
            }

            Iterator<Espece> it = app.getPredateurs().iterator();
            while(it.hasNext()) {
                Espece e = it.next();
                app.jouerTour(e, tour, app.getBuffer());
                if (tour > e.getGeneration() + e.getDureeDeVie()) {
                    e.mourir(app.getPositionsEspeces());
                    it.remove();
                }
            }

            for (Espece e : app.getBuffer()) {
                app.ajouterEspece(e);
            }

            System.out.println(app.getProies());
            System.out.println(app.getPredateurs());

            nbProies[tour - 1] = app.getProies().size();
            nbPreds[tour - 1] = app.getPredateurs().size();

            tour++;
        }

        AfficheGraphe graphe = new AfficheGraphe("Graphe", nbProies, nbPreds);
        graphe.pack();
        graphe.setVisible(true);
    }
}
