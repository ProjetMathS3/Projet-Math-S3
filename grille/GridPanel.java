package grille;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class GridPanel extends JPanel {
    private int rows;
    private int cols;
    private JLabel[][] grid;
    private Dimension D;

    public GridPanel(int r, int c, Dimension d) {
        rows = r;
        cols = c;
        D=d;
        grid = new JLabel[rows][cols];

        setLayout (new GridLayout(rows, cols));
        setBackground( new Color( 200,200,200 ) );

        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < cols; j++ ) {
                grid[i][j] = new JLabel();
                grid[i][j].setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                grid[i][j].setFont( new Font("Courier", Font.BOLD, 12));
                grid[i][j].setForeground(Color.WHITE);
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                add( grid[i][j] );
            }
        }
        setPreferredSize(D);
    }

    public void setGrid(int i, int j, char value) {
        if (value == '.')
            value = ' '; // Remplace les point en espaces

        grid[i][j].setText( "" + value );
        switch (value) {
            case 'o': // Une espèce
                grid[i][j].setBackground( Color.MAGENTA );
                grid[i][j].setOpaque(true);
                break;
            case 'x': // Une autre espèce
                grid[i][j].setBackground( Color.BLUE );
                grid[i][j].setOpaque(true);
                break;
            case 'S': // Encore une autre espèce
                grid[i][j].setBackground( Color.RED );
                grid[i][j].setOpaque(true);
                break;
            default: // Si il n'y a rien sur la case
                grid[i][j].setOpaque(false);
                break;
        }
    }
}
