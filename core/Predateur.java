﻿package core;

import utils.Case;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Predateur extends Espece {

    //CONSTRUCTEURS

    public Predateur(Point position) {
        super(position);
    }

    public Predateur(int x, int y) {
        this(new Point(x,y));
    }

<<<<<<< HEAD
    public Predateur(Point position, int generation, int niveauFaim) {
        super(position, generation);
        this.niveauFaim = niveauFaim;
    }

    public Predateur(Point position, int mouvementParTour, int etatLimiteFaim) {
        super(position, mouvementParTour);
        this.etatLimiteFaim = etatLimiteFaim;
    }

    public Predateur(Point position, int mouvementParTour, double vision, int nombreReproduction, int periodeReproduction, int dureeDeVie, int etatLimiteFaim) {
        super(position, mouvementParTour, vision, nombreReproduction, periodeReproduction, dureeDeVie);
        this.etatLimiteFaim = etatLimiteFaim;
    }

    //GETTERS-SETTERS

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


    /**JOUER TOUR - Comportement:
     * SI le prédateur n'est pas affamé,
     * ALORS il se rapproche d'un congénère et se reproduit
     * SINON il chasse
     */

    public void jouerTour(ArrayList<Espece> proies, ArrayList<Espece> predateurs, Case[][] posEspeces, int tour, int mapSize, ArrayList<Espece> buffer) {
        seReproduire(predateurs, posEspeces, tour, buffer);
        mouvementRestant = getMouvementParTour() ;
        if (trouverIndividuProche(proies) == null || trouverIndividuProche(predateurs) != null && niveauFaim < etatLimiteFaim) {
            allerVersCongenere(predateurs, posEspeces);
            seReproduire(predateurs, posEspeces, tour, buffer);
        } else {
            while (mouvementRestant > 0) {
                System.out.println("mvt : " + mouvementRestant);
                chasser(proies, posEspeces);
            }
        }
        niveauFaim++;
    }

    /** CHASSER - Comportement:
     *  Va vers la proie la plus proche en vue,
     *  la fait mourir
     */

    public void chasser(ArrayList<Espece> proiesList, Case[][] positionsIndividus) {
        Espece proieProche = trouverIndividuProche(proiesList);
        if (proieProche != null) {
            allerVersPosition(proieProche.getPosition(), positionsIndividus);
        } else {
            mouvementRestant = 0;
        }
        System.out.println("proie :" + proieProche);
        Proie p = (Proie)trouverIndividuCaseAdjacente(proiesList);
        if (p != null) {
            System.out.println("PROIE DED");
            p.mourir(positionsIndividus);
            proiesList.remove(p);
            niveauFaim = 0;
            if (getMouvementRestant() > 0) {
                allerVersPosition(p.getPosition(), positionsIndividus);
            }
        }
    }

    @Override
    public String toString() {
        return "Predateur{" +  super.toString() +
                ", niveauFaim=" + niveauFaim +
                "} ";
    }
}
