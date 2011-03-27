package c4n3;

/**
 * Represents the field of play for Connect-4-Not-3.
 * Allows player pieces to be placed and retrieved from the game board.
 * Can be queried to test if a piece is part of a sequence of a given length.
 * @author Alexander Darino
 */
public class Grid {
    /**
     * Width of game field 
     */
    protected final int WIDTH = 10;

    /**
     * Height of game field
     */
    protected final int HEIGHT = 10;
    
    /**
     *
     */
    protected final int cells[][];

    /**
     * Creates field of play with default dimensions 10x10
     */
    public Grid()
    {
        cells = new int[WIDTH][HEIGHT];
    }

    /**
     * Returns playfield height
     * @return playfield height
     */
    public int getHeight()
    {
        return HEIGHT;
    }

    /**
     * Returns playfield height
     * @return playfield width
     */
    public int getWidth()
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
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public int get(int x, int y)
    {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }
}
