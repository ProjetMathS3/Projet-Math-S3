package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GridPanel extends JPanel {
    private int rows;
    private int cols;
    private JButton[][] grid;
    private Dimension D;
    ArrayList<Point> positions;

    public ArrayList<Point> getPositions() {
        return positions;
    }

    public GridPanel(String title, int r, int c, Dimension d, ArrayList<Point> positions) {
        rows = r;
        cols = c;
        D=d;
        grid = new JButton[rows][cols];
        this.positions = positions;

        setLayout (new GridLayout(rows, cols));
        setBackground( new Color( 200,200,200 ) );

        class Listener implements ActionListener {
            int i;
            int j;
            JButton button;
            ArrayList<Point> positions;
            public Listener(JButton caller, int i, int j, ArrayList<Point> positions) {
                this.i = i;
                this.j = j;
                this.positions = positions;
                this.button = caller;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(i + ":" + j);
                positions.add(new Point(i, j));
                button.setBackground(Color.BLUE);
            }
        }

        for (int i = 0; i < rows; i++ ) {
            for (int j = 0; j < cols; j++ ) {
                grid[i][j] = new JButton();
                grid[i][j].setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                grid[i][j].setFont( new Font("Courier", Font.BOLD, 12));
                grid[i][j].setForeground(Color.WHITE);
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                grid[i][j].addActionListener(new Listener(grid[i][j], i,j, positions));
                add(grid[i][j]);
            }
        }
        setPreferredSize(D);
    }
}
