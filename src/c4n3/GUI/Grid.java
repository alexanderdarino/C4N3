package c4n3.GUI;

import c4n3.Grid.Listener;
import c4n3.Grid.PiecePlacedEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexander Darino
 * @author Pierre LaBorde
 */
public class Grid extends JPanel implements c4n3.Grid.Listener
{

    private c4n3.Grid.Listener listener = null;
    private c4n3.Grid grid;
    private Piece piece[][];
    private final HashMap<Piece, Integer[]> buttonToCoordinate = new HashMap();
    private int piecePlayerID = 1;
    private Color player1Color = Color.GREEN, player2Color=Color.RED;

    public Grid()
    {
        
    }

    public void place(int x, int y)
    {
        piecePlaced(piece[x][y]);
    }

    public void setGrid(c4n3.Grid grid)
    {
        if (this.grid != null)
        {
            for (int x = 0; x < c4n3.Grid.getWidth(); x++)
            {
                for (int y = 0; y < c4n3.Grid.getHeight(); y++)
                {
                    this.remove(piece[x][y]);
                }
            }
            //this.repaint();
            //setVisible(false);
            //setVisible(true);
            this.getParent().repaint();
        }
        this.grid = grid;
        piece = new Piece[c4n3.Grid.getWidth()][c4n3.Grid.getHeight()];

        for (int x = 0; x < c4n3.Grid.getWidth(); x++)
        {
            for (int y = 0; y < c4n3.Grid.getHeight(); y++)
            {
                piece[x][y] = new Piece(this);
                buttonToCoordinate.put(piece[x][y], new Integer[] {x, y});
                piece[x][y].addMouseListener(piece[x][y]);
                this.add(piece[x][y]);
                piece[x][y].setVisible(true);   
            }
        }
    }
    

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setPlayer1Color(Color player1Color) {
        this.player1Color = player1Color;
    }

    public void setPlayer2Color(Color player2Color) {
        this.player2Color = player2Color;
    }


    void piecePlaced(Piece piece)
    {
        setEnabled(false);
        int x = buttonToCoordinate.get(piece)[0];
        int y = buttonToCoordinate.get(piece)[1];
        if (set(x, y, piecePlayerID))
        {
            listener.piecePlaced(new PiecePlacedEvent(piecePlayerID, x, y, this));
            
        }
        else
        {
            setEnabled(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        
        for (int x = 0; x < c4n3.Grid.getHeight(); x++)
        {
            g.drawString(Integer.toString(x+1), (x+1) * getWidth()/(c4n3.Grid.getWidth()+2), g.getFontMetrics().getHeight());
            g.drawString(Integer.toString(x+1), (x+1) * getWidth()/(c4n3.Grid.getWidth()+2), getHeight() - g.getFontMetrics().getHeight());
        }
        for (int y = 0; y < c4n3.Grid.getHeight(); y++)
        {
            g.drawString(Character.toString((char)('A' + y)), 0, (y+2) * getHeight()/(c4n3.Grid.getHeight()+2) - g.getFontMetrics().getHeight());
            g.drawString(Character.toString((char)('A' + y)), getWidth() - getWidth()/(c4n3.Grid.getWidth()+2), (y+2) * getHeight()/(c4n3.Grid.getHeight()+2)- g.getFontMetrics().getHeight());
        }
        if (grid == null) return;
        for (int x = 0; x < c4n3.Grid.getWidth(); x++)
        {
            for (int y = 0; y < c4n3.Grid.getHeight(); y++)
            {
                piece[x][y].setLocation((x+1) * getWidth()/(c4n3.Grid.getWidth()+2), (y+1) * getHeight()/(c4n3.Grid.getHeight()+2));
                piece[x][y].setSize(getWidth()/(c4n3.Grid.getWidth()+2), getHeight()/(c4n3.Grid.getHeight()+2));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)Math.max(super.getPreferredSize().getHeight(), getMinimumSize().getWidth()),
                (int)Math.max(super.getPreferredSize().getHeight(), getMinimumSize().getHeight()));
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(300, 300);
    }


    boolean set(int x, int y, int piecePlayerID)
    {
        if (grid.get(x, y) != 0) return false;
        piece[x][y].place(piecePlayerID == 1 ? player1Color : player2Color);
        grid.set(x, y, piecePlayerID);
        return true;
    }

    public void piecePlaced(PiecePlacedEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPlayerTurn(int piecePlayerID)
    {
        this.piecePlayerID = piecePlayerID;
    }

    public static class Piece extends JButton implements MouseListener
    {
        private Color color = null;
        private Grid grid;

        protected Piece(Grid grid)
        {
            this.setRolloverEnabled(false);
            this.grid = grid;
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
            if (grid.isEnabled())
            {
                
                grid.piecePlaced(this);

            }
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
