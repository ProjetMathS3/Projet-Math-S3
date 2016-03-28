package GUI;

import utils.Case;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {
    private int rows;
    private int cols;
    private JButton[][] grid;
    private Dimension D;
    ArrayList<Point> positionsProies;
    ArrayList<Point> positionsPredateurs;

    public ArrayList<Point> getPositionsProies() {
        return positionsProies;
    }

    public ArrayList<Point> getPositionsPredateurs() {
        return positionsPredateurs;
    }

    public GridPanel(String title, int r, int c, Dimension d, ArrayList<Point> positionsProies, ArrayList<Point> positionsPredateurs) {
        rows = r;
        cols = c;
        D=d;
        grid = new JButton[rows][cols];
        this.positionsProies = positionsProies;
        this.positionsPredateurs = positionsPredateurs;

        setLayout (new GridLayout(rows, cols));
        setBackground( new Color( 200,200,200 ) );

        class Listener extends MouseAdapter {
            int i;
            int j;
            JButton button;
            ArrayList<Point> positionsProies;
            ArrayList<Point> positionsPredateurs;

            public Listener(JButton caller, int i, int j, ArrayList<Point> positionsProies, ArrayList<Point> positionsPredateurs) {
                this.i = i;
                this.j = j;
                this.positionsProies = positionsProies;
                this.positionsPredateurs = positionsPredateurs;
                this.button = caller;
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(i + ":" + j);

                if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    positionsProies.add(new Point(i, j));
                    button.setBackground(Color.BLUE);
                } else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    positionsPredateurs.add(new Point(i, j));
                    button.setBackground(Color.RED);
                }

            }
        }

        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < cols; j++ ) {
                grid[i][j] = new JButton();
                grid[i][j].setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                grid[i][j].setFont( new Font("Courier", Font.BOLD, 12));
                grid[i][j].setForeground(Color.WHITE);
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                grid[i][j].addMouseListener(new Listener(grid[i][j], i, j, positionsProies, positionsPredateurs));
                add(grid[i][j]);
            }
        }
        setPreferredSize(D);
    }

    public void setButtonColors(Case[][] positionsIndividus) {
        for (int i = 0; i < positionsIndividus.length; ++i) {
            for (int j = 0; j < positionsIndividus.length; ++j) {
                if (positionsIndividus[i][j] == Case.Proie)
                    grid[j][i].setBackground(Color.BLUE);
                else if (positionsIndividus[i][j] == Case.Predateur)
                    grid[j][i].setBackground(Color.RED);
                else
                    grid[j][i].setBackground(Color.WHITE);
            }
        }
    }
}
