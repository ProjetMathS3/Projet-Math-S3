package core;

import utils.Case;

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

    public Predateur (int x, int y, double vision){
        this(new Point(x, y));
        this.setVision(vision);
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

    public void chasser(ArrayList<Espece> proiesList, Case[][] positionsIndividus) {
        Espece proieProche = trouverIndividuProche(proiesList);
        if (proieProche != null) {
            allerVersPosition(proieProche.getPosition(), positionsIndividus);
        }
        if (this.getPosition().equals(proieProche.getPosition())) {
            proieProche.mourir();
            proiesList.remove(proieProche);
        }
    }

    public void seReproduire(ArrayList<Espece> especes) {
        // foreach (Espece e in especes)
        especes.stream().filter(e -> getPosition().equals(e.getPosition())).forEach(e -> {
            setReprodui(true);
            e.setReprodui(true);
        });
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
