package core;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Predateur extends Espece {
    private int niveauFaim;
    private int etatLimiteFaim;

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

    public void chasser() {

    }
}