package grille;

import javax.swing.*;



import javax.swing.*;
import java.util.*;

// Affiche un cadre contenant une grille

public class DisplayFrame extends JPanel {
    private GridPanel grid;

    // Accepte une chaine de caractère comme titre,
    //  x:Nombre de colonnes
    //  y: Nombre de lignes
    public DisplayFrame(String title, int x, int y) {
        //setTitle( title );
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

        grid = new GridPanel(y, x);
        add( grid );

        //pack();
        //setVisible( true );
    }

    // Définit les cellules de la grille avec des valeurs données dans un tableau de donnée(pas encore utilisé)
    public void setGrid( char [][] data ) {
        for (int i = 0; i < data.length; i++ ) {
            for (int j = 0; j < data[i].length; j++ ) {
                grid.setGrid( i, j, data[i][j] );
            }
        }
    }
}
