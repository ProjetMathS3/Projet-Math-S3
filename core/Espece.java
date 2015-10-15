package core;

import utils.Case;
import utils.Directions;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public abstract class Espece {
    private Point position;
    private int mouvementParTour;
    private double vision;
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

    public int getMouvementParTour() {
        return mouvementParTour;
    }

    public void setMouvementParTour(int mouvementParTour) {
        this.mouvementParTour = mouvementParTour;
    }

    public double getVision() {
        return vision;
    }

    public void setVision(double vision) {
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


    /**
     * Renvoie l'individu le plus proche dans list
     * @param list  une liste d'espèces
     * @return      l'individu trouvé
     */
    public Espece trouverIndividuProche(ArrayList<Espece> list) {
        Espece individuProche = null;
        double distancePlusProche = vision;

        double distanceLocale;
        for (int i = 0; i < list.size(); ++i) {
            distanceLocale = position.distance(list.get(i).getPosition()); // Distance entre l'espece courante et l'espece i dans la liste
            if (distanceLocale < distancePlusProche) {
                distancePlusProche = distanceLocale;
                individuProche = list.get(i);
            }
        }

        return individuProche;
    }

    public Espece trouverIndividuCaseAdjacente(ArrayList<Espece> list) {
        for (Espece e : list) {
            if (estSurCaseAdjacente(e.getPosition())) {
                return e;
            }
        }
        return null;
    }

    private boolean estSurCaseAdjacente(Point positionATester) {
        return positionATester.equals(position) ||
                (positionATester.x == position.x && positionATester.y == position.y + 1) ||
                (positionATester.x == position.x && positionATester.y == position.y - 1) ||
                (positionATester.x == position.x + 1 && positionATester.y == position.y) ||
                (positionATester.x == position.x - 1 && positionATester.y == position.y);
    }

    /**
     * Si un autre individu est sur la même case :
     *  crée une nouvelle espèce sur une case adjacente
     * @param especes   liste de proies ou de prédateurs
     */
    public abstract void seReproduire(ArrayList<Espece> especes);


    /**
     * Se déplace vers positionCible autant que mouvement le permette et si aucun obstacle ne l'en empêche
     * @param positionCible         position cible
     * @param positionsIndividus    une grille contenant l'information sur les positions occupées ou non
     */
    public void allerVersPosition(Point positionCible, Case[][] positionsIndividus) {
        int mouvementRestant = mouvementParTour;
        boolean bloque = false;

        while (mouvementRestant > 0 && ! position.equals(positionCible)) {
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

    protected void allerVersCongenere (ArrayList <Espece> especes, Case[][] positionEspeces) {
        Espece individuProche = trouverIndividuProche(especes);
        if (individuProche != null) {
            allerVersPosition (individuProche.getPosition(), positionEspeces);
        }
    }

    public void reinitTour() {
        reprodui = false;
    }

    /**
     * Jouer un tour de l'individu
     */
    protected abstract void jouerTour(ArrayList <Espece> Proie, ArrayList <Espece> Predateur);

    public static void main(String[] args) {
        ArrayList<Espece> list = new ArrayList<Espece>();
        list.add(new Predateur(2,3, 5));
        list.add(new Predateur(2,6, 5));
        System.out.println(list.get(0).trouverIndividuProche(list));
    }
}
