package core;

import utils.Case;
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

    public void allerVersPosition(Point positionCible, Case[][] positionsIndividus) {
        int mouvementRestant = mouvement;

        while (mouvementRestant > 0) {
            int distanceX = positionCible.x - position.x;
            int distanceY = positionCible.y - position.y;

            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (distanceX > 0) {
                    allerSurCaseAdjacente(Directions.Droite, positionsIndividus);
                }
                else {
                    allerSurCaseAdjacente(Directions.Gauche, positionsIndividus);
                }
            }
            else {
                if (distanceY > 0) {
                    allerSurCaseAdjacente(Directions.Bas, positionsIndividus);
                }
                else {
                    allerSurCaseAdjacente(Directions.Haut, positionsIndividus);
                }
            }

            mouvementRestant--;
        }
    }

    private void allerSurCaseAdjacente(Directions direction, Case[][] positionsIndividus) {
        switch (direction) {
            case Haut:
                seDeplacer(0, -1, positionsIndividus);
                break;
            case Bas:
                seDeplacer(0, 1, positionsIndividus);
                break;
            case Gauche:
                seDeplacer(-1, 0, positionsIndividus);
                break;
            case Droite:
                seDeplacer(1, 0, positionsIndividus);
                break;
        }
    }

    private void seDeplacer(int deltaX, int deltaY, Case[][] positionsIndividus) {
        if (positionsIndividus[position.x + deltaX][position.y + deltaY] == Case.Vide) {
            position.move(position.x + deltaX, position.y + deltaY);
        }
    }
}

