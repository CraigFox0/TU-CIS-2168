import java.util.Arrays;

public class Sudoku {

    //Solves a sudoku board
    public static void main(String[] args) {
        int[][] board = new int[][]{
                //Put problem here
                {0, 9, 0, 0, 4, 2, 6, 0, 0},
                {4, 0, 0, 0, 5, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 7, 2, 0, 0, 3, 4},
                {0, 0, 6, 0, 0, 0, 1, 0, 0},
                {7, 3, 0, 0, 9, 4, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 7, 0, 0, 0, 8},
                {0, 0, 9, 5, 3, 0, 0, 2, 0}
        };
        solve(board, 0);
    }

    //pos is position from first spot. Row is pos/9 and col is pos%9
    static boolean solve(int[][] board, int pos) {
        if (pos >= 81) {
            for (int i = 0; i < 9; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
            return true;
        }
        if (board[pos/9][pos%9] != 0) return solve(board, pos+1);
        else {
            for (int i = 1; i < 10; i++) {
                if(validMove(board, i, pos/9, pos%9)) {
                    board[pos/9][pos%9] = i;
                    if (solve(board, pos+1)) return true;
                    else board[pos/9][pos%9] = 0;
                }
            }
        }
        return false;
    }

    static boolean validMove(int[][] board, int num, int row, int col) {
        //checks box path
        int rowBox = row/3;
        int colBox = col/3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i+(3*rowBox)][j+(3*colBox)] == num) return false;
            }
        }
        //checks horizontal path
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == num) return false;
        }
        //checks vertical path
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == num) return false;
        }
        return true;
    }
}
