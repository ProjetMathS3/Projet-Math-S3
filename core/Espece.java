package core;

import utils.Directions;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Espece {
    private Point position;
    private int mouvement;
    private int vision;
    private int tempsReproduction;
    private int frequenceReproduction;
    private int dureeDeVie;
    private int generation;
    private boolean reprodui;


    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getMouvement() {
        return mouvement;
    }

    public void setMouvement(int mouvement) {
        this.mouvement = mouvement;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getTempsReproduction() {
        return tempsReproduction;
    }

    public void setTempsReproduction(int tempsReproduction) {
        this.tempsReproduction = tempsReproduction;
    }

    public int getFrequenceReproduction() {
        return frequenceReproduction;
    }

    public void setFrequenceReproduction(int frequenceReproduction) {
        this.frequenceReproduction = frequenceReproduction;
    }

    public int getDureeDeVie() {
        return dureeDeVie;
    }

    public void setDureeDeVie(int dureeDeVie) {
        this.dureeDeVie = dureeDeVie;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isReprodui() {
        return reprodui;
    }

    public void setReprodui(boolean reprodui) {
        this.reprodui = reprodui;
    }


    public Espece trouverIndividuProche(ArrayList<Espece> list) {
        if (list.isEmpty()) {
            return null;
        }

        Espece individuProche = list.get(0);
        double distancePlusProche = position.distance(list.get(0).getPosition());

        for (int i = 1; i < list.size(); ++i) {
            double distanceLocale = position.distance(list.get(i).getPosition());
            if (distanceLocale < distancePlusProche) {
                distancePlusProche = distanceLocale;
                individuProche = list.get(i);
            }
        }

        return individuProche;
    }

    public void seReproduire() {

    }

    public void allerVersPosition(Point positionCible, int[][] positionsIndividus) {

    }

    private void allerSurCaseAdjacente(Directions direction, int[][] positionsIndividus) {
        switch (direction) {
            case Haut:
                if (positionsIndividus[position.x][position.y - 1] == )
                break;
            case Bas:
                break;
            case Gauche:
                break;
            case Droite:
                break;
        }
    }
}
