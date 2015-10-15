package core;

import graphe.AfficheFonction;

import utils.Case;
import utils.Directions;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Proie extends Espece {

    /**OUFUIR - Comportement:
     * Définit un point opposé à la position du predateur le plus proche
     */
    private Point ouFuir () {
        //todo
    } //ouFuir ()


    /**FUIR - Comportement:
     * La proie regarde dans son champs de vision (getVision) si il y a un ou plusieurs prédateurs
     * SI c'est le cas, ALORS elle se déplace dans la direction opposé au prédateur le plus proche
     * SINON, elle ne fait rien
     */
    private void fuir (ArrayList <Espece> Predateur) {
        allerVersPosition (ouFuir ());
    } //fuir ()

    /**JOUERTOUR - Comportement:
     * La proie se reproduit si une autre est directement dans son entourage
     * SI un prédateur ou plus est dans son champs de vision,
     * ALORS la proie fuit,
     * SINON SI une autre proie ou plus est dans son champs de vision,
     * ALORS elle va vers elle pour se reproduir,
     * SINON, elle ne fait rien
     */
    public void jouerTour(ArrayList <Espece> Proie, ArrayList <Espece> Predateur) {
        ++this.age;
        seReproduire (Proie);
        if (trouverIndividuProche(Predateur) != null)
            fuir (Predateur);
        else {
            allerVersCongenere(Proie);
            seReproduire(Proie);
        }
    } //jouerTour ()

}
