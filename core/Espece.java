package core;

import java.awt.*;

/**
 * Created by r14003530 on 09/10/15.
 */
public class Espece {
    private Point position;
    private int mouvement;
    private int vision;
    private int tempsReproduction;
    private int frequenceReproduction;
    private int age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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


    public boolean seReproduire() {
        return true;
    }

    public boolean seDeplacer() {
        return true;
    }



}
