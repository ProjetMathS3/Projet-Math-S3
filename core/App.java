package core;

import utils.Case;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

/**
 * Created by r14003530 on 22/10/15.
 */
public class App {

    static final int TAILLE_MAP = 10;

    ArrayList<Espece> proies;
    ArrayList<Espece> predateurs;
    Case[][] positionsEspeces;

    public ArrayList<Espece> getProies() {
        return proies;
    }

    public ArrayList<Espece> getPredateurs() {
        return predateurs;
    }

    public Case[][] getPositionsEspeces() {
        return positionsEspeces;
    }



    public App() {
        proies = new ArrayList<>();
        predateurs = new ArrayList<>();

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
        else {
            System.err.println("Position " + e.getPosition() + " deja occupee");
        }
    }

    public void jouerTour(Espece e) {
        e.jouerTour(proies, predateurs, positionsEspeces);
    }

    public void jouerTour(Proie p) {
        p.jouerTour(proies, predateurs, positionsEspeces, TAILLE_MAP);
    }

    public void afficherPositions() {
        for (int y = 0; y < TAILLE_MAP; y++) {
            for (int x = 0; x < TAILLE_MAP; x++) {
                System.out.print(String.format("%10s",  positionsEspeces[x][y]));
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        App app = new App();
        int tour = 1;

        Predateur pred = new Predateur(3, 3, 1);
        app.ajouterEspece(pred);
        pred = new Predateur(4, 5, 1);
        app.ajouterEspece(pred);

        Proie proie = new Proie(6, 3, 1);
        app.ajouterEspece(proie);

        while (tour <= 8) {
            System.out.println();

            System.out.println(app.getProies());
            app.afficherPositions();

            for (Espece e : app.getProies()) {
                app.jouerTour((Proie)e);
            }
            for (Espece e : app.getPredateurs()) {
                app.jouerTour(e);
            }

            tour++;
        }
    }
}
