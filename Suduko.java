import java.util.HashSet;

public class Suduko {
	static boolean finish;

	public static void main(String[] args) {
		String[] a = { "53..7....", "6..195...", ".98....6..", "8...6...3",
				"4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79" };
		char[][] board = new char[9][9];
		for (int i = 0; i < 9; i++) {
			board[i] = a[i].toCharArray();
		}

		printb(board);
		solveSudoku(board);

	}

	
	
	
	public boolean exist(char[][] board, String word) {
        if(word.length() == 0)return true;
        char[] c = word.toCharArray();
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                if(board[i][j] == c[0]){
                    if(bt(board,word, 0 ,i,j))
                    return true;
                }
            }
    }
		return false;
	}

	public boolean bt(char[][]board, String word, int index, int row, int col){
        if(row<0 || row>board.length || col<0 || col>board[0].length || board[row][col] == '#' || board[row][col] != word.charAt(index))
        return false;
        
        if(board[row][col] == word.charAt(index) && index == word.length()-1)return true;
        
        char temp = board[row][col];
        
        board[row][col] = '#';
        boolean res = bt(board, word, index+1, row+1,col) ||bt(board, word, index+1, row-1,col) || bt(board, word, index+1, row,col+1) || bt(board, word, index+1, row,col-1);
        board[row][col] = temp;
        return res;
        
    }

	public static void solveSudoku(char[][] board) {

		finish = false;
		dfs(board, 0, 0);
		printb(board);

	}

	public static void printb(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}

	public static boolean isValidSudoku(char[][] b) {
		for (int i = 0; i < 9; i++) {
			HashSet<Character> hsr = new HashSet<Character>();
			HashSet<Character> hsc = new HashSet<Character>();
			HashSet<Character> hss = new HashSet<Character>();
			for (int j = 0; j < 9; j++) {
				if (b[i][j] != '.' && !hsr.add(b[i][j]))
					return false;

				if (b[j][i] != '.' && !hsc.add(b[j][i]))
					return false;

				int RowIndex = 3 * (i / 3);
				int ColIndex = 3 * (i % 3);
				if (b[RowIndex + j / 3][ColIndex + j % 3] != '.'
						&& !hss.add(b[RowIndex + j / 3][ColIndex + j % 3]))
					return false;
			}
		}
		return true;
	}

	public static void dfs(char[][] board, int x, int y) {
		if (!isValidSudoku(board)) {
			return;
		}

		if (x == 9) {
			x = 0;
			y = y + 1;
		}
		if (y == 9) {
			finish = true;
			// for (int i = 0; i < 9; i++) {
			// for (int j = 0; j < 9; j++) {
			// System.out.print(board[i][j] + " ");
			// }
			// System.out.println();
			// }
			return;
		}

		if (board[x][y] != '.') {
			dfs(board, x + 1, y);
		} else {
			for (int i = 1; i <= 9; i++) {
				board[x][y] = (char) (i + '0');
				dfs(board, x + 1, y);
				if (finish)
					return;
				board[x][y] = '.';
			}
		}

	}

}
