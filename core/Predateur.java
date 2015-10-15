package core;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Predateur extends Espece {
    private int niveauFaim;
    private int etatLimiteFaim;

    public Predateur(Point position) {
        this.setPosition(position);
        this.setVision(0);
    }

    public Predateur(Point position, double vision, int niveauFaim, int etatLimiteFaim) {
        this(position);
        this.setVision(vision);
        this.niveauFaim = niveauFaim;
        this.etatLimiteFaim = etatLimiteFaim;
    }

    public Predateur (int x, int y){
        this(new Point(x, y));
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



    public void jouerTour() {

    }

    public void chasser(ArrayList<Espece> proiesList) {
        if (trouverIndividuProche(proiesList) != null) {

        }
    }

    /*ALLERVERSCONGENERE - Comportement:
      SI il y des congégères (aka même espèce),
      ALORS la créature se déplace vers eux et se reproduit SI elle ne l'a pas déjà fait
      SINON, elle ne fait rien
     */
    private void allerVersCongenere () {
        if (isCongenerePresent ()) {
            allerVersPosition (CongenereLePlusProche);
        }
    }
}
