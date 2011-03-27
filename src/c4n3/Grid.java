package c4n3;

/**
 *
 * @author Alexander Darino
 */
public class Grid {
    protected final int WIDTH = 10, HEIGHT = 10;
    protected final int cells[][];

    public Grid()
    {
        cells = new int[WIDTH][HEIGHT];
    }

    public int getHeight()
    {
        return HEIGHT;
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public boolean set(int x, int y, int playerID)
    {
        throw new UnsupportedOperationException();
    }

    public int get(int x, int y)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isPartOfSequenceOfLength(int x, int y, int playerID, int length)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }
}
