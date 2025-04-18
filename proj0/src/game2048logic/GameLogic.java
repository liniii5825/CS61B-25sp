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

        // Move up as long as we're above minR and the cell above is empty
        while (newRow > minR && board[newRow - 1][c] == 0) {
            newRow--;
        }

        // Store the value of the current tile
        int tileValue = board[r][c];

        // Check if we can merge with the tile above (but not above minR)
        if (newRow > minR && board[newRow - 1][c] == tileValue) {
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
        // Keep track of the minimum row where the next tile can be placed
        // Start with 0 (the top row)
        int minRow = 0;
        
        // Process each tile in the column from top to bottom
        for (int r = 0; r < board.length; r++) {
            // Move the current tile up as far as possible, respecting minRow
            int mergeResult = moveTileUpAsFarAsPossible(board, r, c, minRow);
            
            // If a merge occurred, update minRow to be one past the merge location
            // This prevents double merges in the same column
            if (mergeResult > 0) {
                minRow = mergeResult;
            }
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // Apply tiltColumn to each column in the board
        for (int c = 0; c < board[0].length; c++) {
            tiltColumn(board, c);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // Handle different directions by rotating the board,
        // applying tiltUp, then rotating back
        switch (side) {
            case NORTH:
                // North is already up, so just tilt up
                tiltUp(board);
                break;
            case EAST:
                // Rotate back (90° counter-clockwise)
                rotateLeft(board);
                tiltUp(board);
                // Rotate board left once (90° clockwise)
                rotateRight(board);
                break;
            case SOUTH:
                // Rotate board left twice (180°)
                rotateRight(board);
                rotateRight(board);
                tiltUp(board);
                // Rotate back (180°)
                rotateLeft(board);
                rotateLeft(board);
                break;
            case WEST:
                // Rotate back (90° clockwise)
                rotateRight(board);
                tiltUp(board);
                // Rotate board left thrice (270° clockwise or 90° counter-clockwise)
                rotateLeft(board);
                break;
        }
    }
}
