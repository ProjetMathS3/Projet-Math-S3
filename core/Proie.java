package core;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Proie extends Espece {

    /**OUFUIR - Comportement:
     * La proie va à l'opposé du prédateur le plus proche trouvé dans la limite de ses points de vitesse et de la taille de la carte
     */
    private Point ouFuir () {
        //todo
    } //ouFuir


    /**FUIR - Comportement:
      La proie regarde dans son champs de vision (getVision) si il y a un ou plusieurs prédateurs
      SI c'est le cas, ALORS elle se déplace dans la direction opposé au prédateur le plus proche
      SINON, elle ne fait rien
     */
    private void fuir(ArrayList <Espece> Predateur) {
        if (trouverIndividuProche (Predateur)) {
            allerVersPosition (ouFuir ());
        }
    } //fuir ()

    /**ALLERVERSCONGENERE - Comportement:
     * SI il y des congénères (aka même espèce),
     * ALORS la créature se déplace vers eux et se reproduit SI elle ne l'a pas déjà fait
     * SINON, elle ne fait rien
     */
    private void allerVersCongenere (ArrayList <Espece> Espece) {
        if (trouverIndividuProche (Espece)) {
            allerVersPosition (trouverIndividuProche (Espece));
            seReproduire ();
        }
    }


    /**JOUERTOUR - Comportement:
     * SI un prédateur ou plus est dans son champs de vision,
     * ALORS la proie fuit,
     * SINON SI une autre proie ou plus est dans son champs de vision,
     * ALORS elle se reproduit,
     * SINON, elle ne fait rien
     */
    public void jouerTour(ArrayList <Espece> Proie, ArrayList <Espece> Predateur) {
        this.age += 1;
        seReproduire (Proie);
        fuir (Predateur);
        allerVersCongenere (Proie);
    } //jouerTour ()

}
