package core;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Proie extends Espece {

    //CONSTRUCTEURS

    public Proie (Point position, int generation) {
        super(position, generation);
    }

    public Proie (int x, int y, int generation) {
        super(new Point(x,y), generation);
    }

    public Proie (Point position, int mouvementParTour, int generation) {
        super(position, mouvementParTour, generation);
    }

    public Proie (Point position, int mouvementParTour, double vision, int nombreReproduction, int frequenceReproduction, int dureeDeVie, int generation) {
        super(position, mouvementParTour, vision, nombreReproduction, frequenceReproduction, dureeDeVie, generation);
    }

    protected void jouerTour(ArrayList<Espece> Proie, ArrayList<Espece> Predateur, Case[][] positionsEsp, int mapsize) {
        fuir(Predateur, mapsize, positionsEsp);
    }

    /**OUFUIR - Comportement:
     * Définit un point opposé à la position du predateur le plus proche
     */
    private Point ouFuir (ArrayList <Espece> Predateur, int MapSize) {
        Point Position = new Point (getPosition ());
        Point PositionPredateur = new Point(trouverIndividuProche(Predateur).getPosition());
        Point VecteurDirection = new Point (Position.x - PositionPredateur.x, Position.y - PositionPredateur.y);
        Point Deplacement = new Point (Position);
        
        while (Deplacement.x < MapSize - 1 && Deplacement.x > 0) {
            Deplacement.x += VecteurDirection.x;
        }
        for (int i = 0; i != (VecteurDirection.x + 1) && Deplacement.x < MapSize && Deplacement.x > 0; ++i) {
            ++Deplacement.x;
        }
        while ((Position.y + Deplacement.y) < MapSize && Deplacement.y > 0) {
            Deplacement.y += VecteurDirection.y;
        }
        for (int i = 0; i != (VecteurDirection.y + 1) && Deplacement.y < MapSize && Deplacement.y > 0; ++i) {
            ++Deplacement.y;
        }
        return Deplacement;
    } //ouFuir ()


    public void mourir(Case[][] positionsEspeces) {
        positionsEspeces[getPosition().x][getPosition().y] = Case.Vide;
    }


    /**FUIR - Comportement:
     * La proie regarde dans son champs de vision (getVision) si il y a un ou plusieurs prédateurs
     * SI c'est le cas, ALORS elle se déplace dans la direction opposé au prédateur le plus proche
     * SINON, elle ne fait rien
     */
    private void fuir (ArrayList <Espece> Predateur, int MapSize, Case[][] Matrice) {
        allerVersPosition(ouFuir(Predateur, MapSize), Matrice);
    } //fuir ()

    /**JOUERTOUR - Comportement:
     * La proie se reproduit si une autre est directement dans son entourage
     * SI un prédateur ou plus est dans son champs de vision,
     * ALORS la proie fuit,
     * SINON SI une autre proie ou plus est dans son champs de vision,
     * ALORS elle va vers elle pour se reproduir,
     * SINON, elle ne fait rien
     */
    public void jouerTour(ArrayList <Espece> Proie, ArrayList <Espece> Predateur, int MapSize, Case[][] Matrice) {
        seReproduire (Proie);
        if (trouverIndividuProche(Predateur) != null)
            fuir (Predateur, MapSize, Matrice);
        else {
            allerVersCongenere(Proie, Matrice);
            seReproduire(Proie);
        }
    } //jouerTour ()

}
