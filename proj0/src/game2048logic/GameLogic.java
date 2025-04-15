package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c     the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // If the current tile is 0 (empty), no need to move anything
        if (board[r][c] == 0) {
            return 0;
        }
        
        // Start checking from the row above the current position
        int newRow = r;
        
        // Move up as long as we're not at row 0 and the cell above is empty
        while (newRow > 0 && board[newRow - 1][c] == 0) {
            newRow--;
        }
        
        // Store the value of the current tile
        int tileValue = board[r][c];
        
        // Check if we can merge with the tile above
        if (newRow > 0 && board[newRow - 1][c] == tileValue) {
            // Merge: double the value in the destination cell
            board[newRow - 1][c] *= 2;
            // Clear the original cell
            board[r][c] = 0;
            // Return 1 + the row where merge occurred
            return 1 + (newRow - 1);
        } else {
            // If the tile moved but no merge, update the board
            if (newRow != r) {
                board[newRow][c] = tileValue;
                board[r][c] = 0;
            }
            // No merge occurred
            return 0;
        }
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.EAST) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else {
            return;
        }
    }
}
