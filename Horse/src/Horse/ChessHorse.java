package Horse;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * The main program for Horse.
 *
 * A chess board with a horse and some other pieces on it.
 * The squares are labeled by x axis (a integer between 0 and 7)
 * adn y axis (a integer between 0 and 7).
 *
 * Try to get the sequence of the traversal of the chess board made by
 * a horse without repeating steps using DFS. For advanced version,
 * add some pieces that can block the horse.
 *
 * Moves on this board are denoted by Moves.
 *
 *  @author Henry Xu
 *  */

public class ChessHorse {
    /** Chess board. */
    private int[][] _board;
    /** x of the current position of the horse. */
    private int _currentX;
    /** y of the current position of the horse. */
    private int _currentY;
    /** Store all visited point in time order. */
    private Stack<Move> _visited;
    /** The depth of the searching. */
    private int _num;
    /** The size of the chess board. */
    private int _size;

    /** Constructor of ChessHorse.
     * @param x x of the initial position of the horse.
     * @param y y of the initial position of the horse.
     * @param size size of the chess board.*/
    public ChessHorse(int x, int y, int size) {
        _size = size;
        _board = new int[size][size];
        _visited = new Stack<Move>();
        _num = 1;
        _currentX = x;
        _currentY = y;
        _board[x][y] = 1;
    }

    /** Set block pieces on the board.
     * @param x x of the position of the block piece.
     * @param y y of the position of the block piece.
     * @return if the block piece is successfully set.*/
    public boolean setBlock(int x, int y) {
        if (x == _currentX && y == _currentY) {
            return false;
        }
        if (x < 0 || x > _size - 1 || y < 0 || y > _size - 1) {
            return false;
        }
        _board[x][y] = 2;
        _num += 1;
        return true;
    }

    /** Indicate if the traversal is over. */
    public boolean isOver() {
        return _num == _size * _size;
    }

    /** Check if the given move is a legal move on current board.
     * @param move the move to be checked. */
    public boolean isLegalMove(Move move) {
        if (move.x1 < 0 || move.x1 > _size - 1 || move.y1 < 0 || move.y1 > _size - 1) {
            return false;
        }
        if (_board[move.x1][move.y1] == 1 || _board[move.x1][move.y1] == 2) {
            return false;
        }
        if (_board[move.blockX][move.blockY] == 2) {
            return false;
        }
        return true;
    }

    /** Get a move according to its type and the current position.
     * @param type Type of the move you want to get.
     * @return move of this type of the current horse.*/
    public Move getMove(int type) {
        return new Move(_currentX, _currentY, type);
    }

    /** Undo a move. Reset changes made by this move,
     * including current position, visited points and so on. */
    public void undo() {
        _board[_currentX][_currentY] = 0;
        Move move = _visited.pop();
        _currentX = move.x0;
        _currentY = move.y0;
        _num -= 1;
    }

    /** Actually make a move. Set changes to be made by it,
     * including current position, visited points and so on.
     * @param move the move to be made.*/
    public void makeMove(Move move) {
        _currentX = move.x1;
        _currentY = move.y1;
        _board[_currentX][_currentY] = 1;
        _num += 1;
        _visited.push(move);
    }

    /** Get the number of all legal moves on current status.
     * @return the number of all legal moves on current status. */
    public int getMoveNum() {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            Move move = getMove(i);
            if (isLegalMove(move)) {
                num += 1;
            }
        }
        return num;
    }

    /** Prepare to use the Greedy Algorithm to optimize the program.
     * Get the move that has the lest legal moves after making it.
     * @param moves The set of all current legal moves.
     * @return the move having the lest legal moves after making it.*/
    public Move getBestMove(Stack<Move> moves) {
        Move result = null;
        int num = 9;
        for (Move move : moves) {
            makeMove(move);
            int temp = getMoveNum();
            undo();
            if (temp < num) {
                num = temp;
                result = move;
            }
        }
        return result;
    }

    /** Get all legal moves of the current status.
     * @return The set of all current legal moves. */
    public Stack<Move> getAllLegalMoves() {
        Stack<Move> result = new Stack<Move>();
        for (int i = 0; i < 8; i++) {
            Move move = getMove(i);
            if (isLegalMove(move)) {
                result.push(move);
            }
        }
        return result;
    }

    /** Execute the traversal. Get the sequence made by the horse from given beginning
     * point to cover all squares on the board without repeating steps. */
    public void traversal() {
        if (isOver()) {
            return;
        }
        Stack<Move> moves = getAllLegalMoves();
        while (!moves.isEmpty()) {
            Move move = getBestMove(moves);
            makeMove(move);
            traversal();
            if (isOver()) {
                return;
            }
            undo();
            moves.remove(move);
        }

        return;
    }

    /** Print the result sequence. */
    public void printResult() {
        if (_visited.isEmpty()) {
            System.out.println("无法完全遍历");
            return;
        }
        ArrayList<Move> result = new ArrayList<Move>();
        while (!_visited.isEmpty()) {
            result.add(_visited.pop());
        }
        int size = result.size();
        for (int i = size - 1; i >= 0; i--) {
            System.out.println(result.get(i));
        }
    }

    /** Main function. User's interface. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = -1;
        int y = -1;
        System.out.println("请输入棋盘边长：");
        int size = scanner.nextInt();
        System.out.println("请输入起始坐标：");
        while (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            System.out.print("x: ");
            x = scanner.nextInt();
            System.out.print("y: ");
            y = scanner.nextInt();
            if (x < 0 || x > size - 1|| y < 0 || y > size - 1) {
                System.out.println("坐标无效，请重新输入: ");
            }
        }
        ChessHorse h = new ChessHorse(x, y, size);
        System.out.println("请输入蹩马腿的棋子坐标，以-1结束：");
        int bx = 0; int by = 0;
        while (true) {
            System.out.print("x: ");
            bx = scanner.nextInt();
            if (bx == -1) {
                break;
            }
            System.out.print("y: ");
            by = scanner.nextInt();
            if (!h.setBlock(bx, by)) {
                System.out.println("坐标无效，请重新输入: ");
            }
        }
        h.traversal();
        h.printResult();
    }
}
