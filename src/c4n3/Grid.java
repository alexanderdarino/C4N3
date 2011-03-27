package c4n3;

/**
 *
 * @author Alexander Darino
 */
public class Grid {
    protected final int WIDTH = 10, HEIGHT = 10;
    protected final int cell[][];

    public Grid()
    {
        cell = new int[WIDTH][HEIGHT];
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
        if (!isEmpty(x, y)) return false;
        cell[x][y] = playerID;
        return true;
    }

    public int get(int x, int y)
    {
        if (!isWithinBounds(x, y)) return 0;
        return cell[x][y];
    }

    public boolean isPartOfSequenceOfAtLeastLength(int x, int y, int length)
    {
        return  this.isPartofHorizontalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofVerticalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofDownLeftDiagonalSequenceOfAtLeastLength(x, y, length) ||
                this.isPartofUpRightDiagonalSequenceOfAtLeastLength(x, y, length);
    }

    public boolean isEmpty(int x, int y)
    {
        if (!isWithinBounds(x, y)) return false;
        return cell[x][y] == 0;
    }

    protected boolean isWithinBounds(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >=0 && y < HEIGHT;
    }

    private boolean isPartofHorizontalSequenceOfAtLeastLength(int x, int y, int length)
    {
        int count = 0;
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
        int count = 0;
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
        int count = 0;
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
        int count = 0;
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
}
