package Horse;

/**
 * Moves made by the horse on the board.
 *
 * There are 8 kinds of moves that a horse can make. Each one
 * has a beginning point and a ending points, and also contains
 * its block points.
 *
 *  @author Henry Xu
 *  */
public class Move {
    /** x of the beginning point. */
    public int x0;
    /** y of the beginning point. */
    public int y0;
    /** x of the ending point. */
    public int x1;
    /** y of the ending point. */
    public int y1;
    /** x of the block point. */
    public int blockX;
    /** y of the block point. */
    public int blockY;

    /** Constructor of Move. Construct a move according to the type.
     * @param x0 x of beginning point
     * @param y0 y of beginning point
     * @param type type of the move*/
    public Move(int x0, int y0, int type) {
        this.x0 = x0;
        this.y0 = y0;
        switch (type) {
            case 0: {
                x1 = x0 - 2;
                y1 = y0 + 1;
                blockX = x0 - 1;
                blockY = y0;
                break;
            }
            case 1: {
                x1 = x0 - 1;
                y1 = y0 + 2;
                blockX = x0;
                blockY = y0 + 1;
                break;
            }
            case 2: {
                x1 = x0 + 1;
                y1 = y0 + 2;
                blockX = x0;
                blockY = y0 + 1;
                break;
            }
            case 3: {
                x1 = x0 + 2;
                y1 = y0 + 1;
                blockX = x0 + 1;
                blockY = y0;
                break;
            }
            case 4: {
                x1 = x0 - 2;
                y1 = y0 - 1;
                blockX = x0 - 1;
                blockY = y0;
                break;
            }
            case 5: {
                x1 = x0 - 1;
                y1 = y0 - 2;
                blockX = x0;
                blockY = y0 - 1;
                break;
            }
            case 6: {
                x1 = x0 + 1;
                y1 = y0 - 2;
                blockX = x0;
                blockY = y0 - 1;
                break;
            }
            case 7: {
                x1 = x0 + 2;
                y1 = y0 - 1;
                blockX = x0 + 1;
                blockY = y0;
                break;
            }
            default: break;
        }
    }

    /** Print a move.
     * @return representation of a move. */
    @Override
    public String toString() {
        return "(" + x0 + ", " + y0 + ") --> (" + x1 + ", " + y1 + ")";
    }
}
