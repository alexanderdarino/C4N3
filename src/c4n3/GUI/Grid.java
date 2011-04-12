package c4n3.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Alexander Darino
 */
public class Grid extends JPanel {

    private final c4n3.Grid grid;
    private final Piece piece[][];

    public Grid()
    {
        grid = new c4n3.Grid();
        piece = new Piece[grid.getWidth()][grid.getHeight()];

        for (int x = 0; x < grid.getWidth(); x++)
        {
            for (int y = 0; y < grid.getHeight(); y++)
            {
                piece[x][y] = new Piece();
                piece[x][y].addMouseListener(piece[x][y]);
                this.add(piece[x][y]);
//                piece[x][y].setLocation(x * getWidth()/grid.getWidth(), y * getHeight()/grid.getHeight());
//                piece[x][y].setSize(getWidth()/grid.getWidth(), getHeight()/grid.getHeight());
                piece[x][y].setVisible(true);
            }
        }
        System.out.println();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        this.setSize(this.getParent().getSize());
        for (int x = 0; x < grid.getWidth(); x++)
        {
            for (int y = 0; y < grid.getHeight(); y++)
            {
                piece[x][y].setLocation(x * getWidth()/grid.getWidth(), y * getHeight()/grid.getHeight());
                piece[x][y].setSize(getWidth()/grid.getWidth(), getHeight()/grid.getHeight());
            }
        }
    }


    boolean set(int x, int y, Color playerColor)
    {
        if (grid.get(x, y) == 0) return false;
        piece[x][y].place(playerColor);
        return true;
    }

    public static class Piece extends JButton implements MouseListener
    {
        private Color color = null;

        public Piece()
        {
            this.setRolloverEnabled(false);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            //super.paintComponent(g);
           
            if (color != null)
            {
                g.setColor(color);
                g.fillOval(0, 0, getWidth(), getHeight());
            }
            else
            {
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, getWidth(), getHeight());
            }
        }

        public Color getColor()
        {
            return color;
        }

        public void setColor(Color color)
        {
            this.color = color;
        }

        public void place(Color playerColor)
        {
            setEnabled(false);
            this.color = playerColor;
        }

        public void mouseClicked(MouseEvent e)
        {
            place(Color.YELLOW);
        }

        public void mousePressed(MouseEvent e)
        {
            //
        }

        public void mouseReleased(MouseEvent e)
        {
            //
        }

        public void mouseEntered(MouseEvent e)
        {
            //
        }

        public void mouseExited(MouseEvent e)
        {
            //
        }



    }
}
