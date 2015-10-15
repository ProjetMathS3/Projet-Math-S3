package core;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Proie extends Espece {

    /*PREDATEURPRESENT - Comportement:
      Renvoie TRUE uniquement si au moins un prédateur est dans le champs de vision (getVision) de la proie
     */
    private boolean isPredateurPresent (){
        //todo
    } //predateurPresent


    /*CONGENERELEPLUSPROCHE - Comportement:
      Parcourt le tableau d'espèce congénère et retourne le point du congénère le plus proche
     */
    private Point ouFuir () {
        //todo
    } //congenereLePlusProche


    /*FUIR - Comportement:
      La proie regarde dans son champs de vision (getVision) si il y a un ou plusieurs prédateurs
      SI c'est le cas, ALORS elle se déplace dans la direction opposé au prédateur le plus proche
      SINON, elle ne fait rien
     */
    private void fuir() {
        if (isPredateurPresent ()) {
            allerVersPosition (ouFuir ());
        }
    } //fuir ()


    /*CONGENERELEPLUSPROCHE - Comportement:
      Parcourt le tableau d'espèce congénère et retourne le point du congénère le plus proche
     */
    private Point congenereLePlusProche () {
        //todo
    } //congenereLePlusProche


    /*ALLERVERSCONGENERE - Comportement:
      SI il y des congénères (aka même espèce),
      ALORS la créature se déplace vers eux et se reproduit SI elle ne l'a pas déjà fait
      SINON, elle ne fait rien
     */
    private void allerVersCongenere (ArrayList<Espece> especes) {
        if (isCongenerePresent ()) {
            allerVersPosition (congenereLePlusProche ());
            seReproduire ();
        }
    }


    /*JOUERTOUR - Comportement:
      SI un prédateur ou plus est dans son champs de vision,
      ALORS la proie fuit,
      SINON SI une autre proie ou plus est dans son champs de vision,
      ALORS elle se reproduit,
      SINON, elle ne fait rien
     */
    public void jouerTour() {
        this.age += 1;
        seReproduire ();
        fuir();
        allerVersCongenere();
    } //jouerTour ()

}
