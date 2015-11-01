package core;

import utils.Case;
import utils.Directions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by r14003530 on 09/10/15.
 */
public abstract class Espece {
    private Point position;
    private int generation;
    private int mouvementParTour = 2;
    private double vision = 15;
    private int nombreReproduction = 1;
    private int frequenceReproduction = 2;
    private int tempsDerniereReproduction = frequenceReproduction;
    private int dureeDeVie = 3;
    private boolean reprodui = false;


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

    public int getnombreReproduction() {
        return nombreReproduction;
    }

    public void setnombreReproduction(int nombreReproduction) {
        this.nombreReproduction = nombreReproduction;
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

    public int getTempsDerniereReproduction() {
        return tempsDerniereReproduction;
    }

    public void setTempsDerniereReproduction(int tempsDerniereReproduction) {
        this.tempsDerniereReproduction = tempsDerniereReproduction;
    }

    // Constructeurs
    public Espece(Point position, int generation) {
        this.position = position;
        this.generation = generation;
        this.tempsDerniereReproduction = this.frequenceReproduction;
    }

    public Espece(Point position, int mouvementParTour, int generation) {
        this.position = position;
        this.mouvementParTour = mouvementParTour;
        this.generation = generation;
        this.tempsDerniereReproduction = this.frequenceReproduction;
    }

    public Espece(Point position, int mouvementParTour, double vision, int nombreReproduction, int frequenceReproduction, int dureeDeVie, int generation) {
        this.position = position;
        this.mouvementParTour = mouvementParTour;
        this.vision = vision;
        this.nombreReproduction = nombreReproduction;
        this.frequenceReproduction = frequenceReproduction;
        this.tempsDerniereReproduction = frequenceReproduction;
        this.dureeDeVie = dureeDeVie;
        this.generation = generation;
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
        return (positionATester.x == position.x && positionATester.y == position.y + 1) ||
                (positionATester.x == position.x && positionATester.y == position.y - 1) ||
                (positionATester.x == position.x + 1 && positionATester.y == position.y) ||
                (positionATester.x == position.x - 1 && positionATester.y == position.y);
    }

    /**
     * Si un autre individu est sur la même case :
     *  crée une nouvelle espèce sur une case adjacente
     * @param especes   liste de proies ou de prédateurs
     */
    public void seReproduire(ArrayList<Espece> espece, Case[][] positionsEspeces, int Generation, ArrayList<Espece> buffer) {
        Espece CongenereDeGenerationLaPlusProche = trouverIndividuCaseAdjacente(espece);

        if (CongenereDeGenerationLaPlusProche != null && ! CongenereDeGenerationLaPlusProche.isReprodui() && ! isReprodui() &&
                tempsDerniereReproduction > frequenceReproduction) {
            System.out.println("bim");
            tempsDerniereReproduction = 0;
            setReprodui(true);
            CongenereDeGenerationLaPlusProche.setReprodui(true);
            for (int i=0; i < nombreReproduction; i++) {
                Point positionNouveauNe = choisirCaseNaissance(this, CongenereDeGenerationLaPlusProche, positionsEspeces);
                if (positionNouveauNe != null) {
                    System.out.println(CongenereDeGenerationLaPlusProche.getClass().toString() + positionNouveauNe);
                    Espece nouveauNe;
                    if (this instanceof Proie) {
                        nouveauNe = new Proie (positionNouveauNe, Generation);
                    }
                    else {
                        nouveauNe = new Predateur(positionNouveauNe, Generation);
                    }
                    buffer.add(nouveauNe);
                }
            }
        }
    }

    private Point choisirCaseNaissance(Espece e1, Espece e2, Case[][] positionsEspeces) {
        ArrayList<Point> casesDisponibles = new ArrayList<>();
        for (int deltaX = -1; deltaX <= 1; deltaX += 2) {
            ajouterCaseDispo(e1, positionsEspeces, deltaX, 0, casesDisponibles);
            ajouterCaseDispo(e2, positionsEspeces, deltaX, 0, casesDisponibles);
        }
        for (int deltaY = -1; deltaY <= 1; deltaY += 2) {
            ajouterCaseDispo(e1, positionsEspeces, 0, deltaY, casesDisponibles);
            ajouterCaseDispo(e2, positionsEspeces, 0, deltaY, casesDisponibles);
        }

        //System.out.println(e1.getClass().toString() + casesDisponibles);
        if (! casesDisponibles.isEmpty()) {
            Random rand = new Random();
            return casesDisponibles.get(rand.nextInt(casesDisponibles.size()));
        } else {
            return null;
        }
    }

    private void ajouterCaseDispo(Espece e, Case[][] positionsEspeces, int deltaX, int deltaY, ArrayList<Point> casesDisponibles) {
        Point caseAdjacente = new Point(e.getPosition());
        caseAdjacente.translate(deltaX, deltaY);
        if (caseAdjacente.x >= 0 && caseAdjacente.x < positionsEspeces.length &&
                caseAdjacente.y >= 0 && caseAdjacente.y < positionsEspeces.length &&
                positionsEspeces[caseAdjacente.x][caseAdjacente.y] == Case.Vide) {
            casesDisponibles.add(caseAdjacente);
        }
    }

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

    private boolean estCaseValide(int x, int y, int tailleMap) {
        return x >= 0 && x < tailleMap
                && y >= 0 && y < tailleMap;
    }

    private void seDeplacer(int deltaX, int deltaY, Case[][] positionsIndividus) {
        if (estCaseValide(position.x + deltaX, position.y + deltaY, positionsIndividus.length) && positionsIndividus[position.x + deltaX][position.y + deltaY] == Case.Vide) {
            positionsIndividus[position.x][position.y] = Case.Vide;
            position.move(position.x + deltaX, position.y + deltaY);
            positionsIndividus[position.x][position.y] = (this instanceof Proie ? Case.Proie : Case.Predateur);
        }
    }

    /**
     * Se déplace vers le congénère le plus proche
     * @param especes   liste des congénères
     */
    public void allerVersCongenere (ArrayList <Espece> especes, Case[][] positionEspeces) {
        Espece individuProche = trouverIndividuProche(especes);
        if (individuProche != null) {
            allerVersPosition(individuProche.getPosition(), positionEspeces);
        }
    }

    public void mourir(Case[][] positionsEspeces) {
        positionsEspeces[position.x][position.y] = Case.Vide;
    }

    public void reinitTour() {
        reprodui = false;
    }

    /**
     * Jouer un tour de l'individu
     */
    protected abstract void jouerTour(ArrayList <Espece> Proie, ArrayList <Espece> Predateur, Case[][] positionsEsp, int Generation, int mapSize, ArrayList<Espece> buffer);

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                ", generation=" + generation +
                '}';
    }

    public static void main(String[] args) {

    }
}
