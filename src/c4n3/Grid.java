package c4n3;

/**
 * Represents the field of play for Connect-4-Not-3.
 * Allows player pieces to be placed and retrieved from the game board.
 * Can be queried to test if a piece is part of a sequence of a given length.
 * @author Alexander Darino
 * @author Pierre LaBorde
 */
public class Grid {

    /**
     * Width of game field 
     */
    protected static final int WIDTH = 10;

    /**
     * Height of game field
     */
    protected static final int HEIGHT = 10;
    
    /**
     *
     */
    protected final int cell[][];

    /**
     * Creates playfield with default dimensions 10x10
     */
    public Grid()
    {
        cell = new int[WIDTH][HEIGHT];
    }

    /**
     * Returns playfield height
     * @return playfield height
     */
    public static int getHeight()
    {
        return HEIGHT;
    }

    /**
     * Returns playfield height
     * @return playfield width
     */
    public static int getWidth()
    {
        return WIDTH;
    }

    /**
     *
     * @param x
     * @param y
     * @param playerID
     * @return
     */
    public boolean set(int x, int y, int playerID)
    {
        if (!isEmpty(x, y)) return false;
        cell[x][y] = playerID;
        return true;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public int get(int x, int y)
    {
        if (!isWithinBounds(x, y)) return -1;
        return cell[x][y];
    }

    /**
     * Determines whether the cell positioned at (x, y) belongs to a
     * contiguous sequence of cells occupied by the same player.
     * Returns true if
     *
     * @param x x position of the cell being queried
     * @param y y position of the cell being queried
     * @param length minimum
     *
     * @return
     */
    public boolean isPartOfSequenceOfAtLeastLength(int x, int y, int length)
    {
        return  this.isPartofHorizontalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofVerticalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofDownLeftDiagonalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofUpRightDiagonalSequenceOfAtLeastLength(x, y, length);
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(int x, int y)
    {
        if (!isWithinBounds(x, y)) return false;
        return cell[x][y] == 0;
    }

    public boolean isWithinBounds(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >=0 && y < HEIGHT;
    }

    private boolean isPartofHorizontalSequenceOfAtLeastLength(int x, int y, int length)
    {
        int count = 1;
        final int playerID = get(x, y);
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x - delta, y) == playerID) count++;
            else break;
        }
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x + delta, y) == playerID) count++;
            else break;
        }
        return count >= length;
    }
    private boolean isPartofVerticalSequenceOfAtLeastLength(int x, int y, int length)
    {
        int count = 1;
        final int playerID = get(x, y);
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x, y - delta) == playerID) count++;
            else break;
        }
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x, y + delta) == playerID) count++;
            else break;
        }
        return count >= length;
    }
    private boolean isPartofDownLeftDiagonalSequenceOfAtLeastLength(int x, int y, int length)
    {
        int count = 1;
        final int playerID = get(x, y);
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x + delta, y - delta) == playerID) count++;
            else break;
        }
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x - delta, y + delta) == playerID) count++;
            else break;
        }
        return count >= length;
    }
    private boolean isPartofUpRightDiagonalSequenceOfAtLeastLength(int x, int y, int length)
    {
        int count = 1;
        final int playerID = get(x, y);
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x + delta, y + delta) == playerID) count++;
            else break;
        }
        for (int delta = 1; delta < length; delta++)
        {
            if (get(x - delta, y - delta) == playerID) count++;
            else break;
        }
        return count >= length;
    }

    public static interface Listener
    {
        public void piecePlaced(PiecePlacedEvent e);
    }
    public static class PiecePlacedEvent
    {
        protected final int x, y, playerID;
        protected final Object source;

        public PiecePlacedEvent(int playerID, int x, int y, Object source) {
            this.x = x;
            this.y = y;
            this.playerID = playerID;
            this.source = source;
        }

        public Object getSource() {
            return source;
        }

        /*public PiecePlacedEvent() {
            throw new UnsupportedOperationException("Not yet implemented");
        }*/

        public int getPlayerID() {
            return playerID;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }
}
