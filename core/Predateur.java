package core;

import utils.Case;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Predateur extends Espece {
    private int niveauFaim;
    private int etatLimiteFaim;

    public Predateur(Point position, int generation) {
        super(position, generation);
    }

    public Predateur(int x, int y, int generation) {
        this(new Point(x,y), generation);
    }

    public Predateur(Point position, int generation, int etatLimiteFaim) {
        super(position, generation);
        this.etatLimiteFaim = etatLimiteFaim;
        this.niveauFaim = etatLimiteFaim;
    }

    public Predateur(Point position, int mouvementParTour, int generation, int etatLimiteFaim) {
        super(position, mouvementParTour, generation);
        this.etatLimiteFaim = etatLimiteFaim;
    }

    public Predateur(Point position, int mouvementParTour, double vision, int nombreReproduction, int frequenceReproduction, int dureeDeVie, int generation, int etatLimiteFaim) {
        super(position, mouvementParTour, vision, nombreReproduction, frequenceReproduction, dureeDeVie, generation);
        this.etatLimiteFaim = etatLimiteFaim;
    }

    public int getEtatLimiteFaim() {
        return etatLimiteFaim;
    }

    public void setEtatLimiteFaim(int etatLimiteFaim) {
        this.etatLimiteFaim = etatLimiteFaim;
    }

    public int getNiveauFaim() {
        return niveauFaim;
    }

    public void setNiveauFaim(int niveauFaim) {
        this.niveauFaim = niveauFaim;
    }



    public void jouerTour(ArrayList<Espece> proies, ArrayList<Espece> predateurs, Case[][] posEspeces, int tour, int mapSize, ArrayList<Espece> buffer) {
        if (niveauFaim < etatLimiteFaim) {
            seReproduire(predateurs, posEspeces, tour, buffer);
        }
        chasser(proies, posEspeces);
        niveauFaim++;
    }

    public void chasser(ArrayList<Espece> proiesList, Case[][] positionsIndividus) {
        Espece proieProche = trouverIndividuProche(proiesList);
        if (proieProche != null) {
            allerVersPosition(proieProche.getPosition(), positionsIndividus);
        }
        Proie p = (Proie)trouverIndividuCaseAdjacente(proiesList);
        if (p != null) {
            System.out.println("PROIE DED");
            p.mourir(positionsIndividus);
            if (getMouvementParTour() > 0) {
                allerVersPosition(p.getPosition(), positionsIndividus);
            }
            proiesList.remove(p);
            niveauFaim = 0;
        }
    }

    /*ALLERVERSCONGENERE - Comportement:
      SI il y des congégères (aka même espèce),
      ALORS la créature se déplace vers eux et se reproduit SI elle ne l'a pas déjà fait
      SINON, elle ne fait rien
     */
    private void allerVersCongenere () {
/*        if (isCongenerePresent ()) {
            allerVersPosition (CongenereLePlusProche);
        }*/
    }

    @Override
    public String toString() {
        return "Predateur{" +  super.toString() +
                ", niveauFaim=" + niveauFaim +
                ", etatLimiteFaim=" + etatLimiteFaim +
                "} ";
    }
}
