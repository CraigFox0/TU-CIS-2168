import java.util.Arrays;

public class Chess {

    //Solves the Eight Queens problem. Prints out a board showing where the queens should be located
    public static void main(String[] args) {
        //Board must be square
        int[][] board = new int[8][8];
        solve(board, 0);
    }

    static boolean solve(int[][] board, int pos) {
        if (pos >= board[0].length) {
            for (int i = 0; i < board.length; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
            return true;
        }
        for (int i = 0; i < board[pos].length; i++) {
            if(validMove(board, pos, i)) {
                //Ones represent Queens
                board[pos][i] = 1;
                if (solve(board, pos + 1)) return true;
                else board[pos][i] = 0;
            }
        }
        return false;
    }

    static boolean validMove(int[][] board, int row, int col) {
        //checks diagonal paths
        int lowestValue = Math.min(row, col);
        int maxValue = Math.max(row, col);
        for (int i = -lowestValue; i + maxValue < board[0].length ; i++) {
            if(board[row + i][col + i] == 1) return false;
        }
        for (int i = 0; (row + i < board[0].length) && (col - i >= 0); i++) {
            if(board[row + i][col - i] == 1) return false;
        }
        for (int i = 0; (col + i < board[0].length) && (row - i >= 0); i++) {
            if(board[row - i][col + i] == 1) return false;
        }
        //checks horizontal path
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == 1) return false;
        }
        //checks vertical path
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == 1) return false;
        }
        return true;
    }
}
