package sv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
	public static void main(String[] args) {
		new NQueen().solveNQueens(11);
	}
	
	public List<String[]> solveNQueens(int n) {
		List<String[]> res = new ArrayList<String[]>();
		char[][] matrix = new char[n][n];
		for(int i = 0;i<n;i++){
			Arrays.fill(matrix[i], '.');
		}
		
		backtracking(matrix, 0, n, res);
		
		System.out.println(res.size());
		
		return res;
	}

	public void backtracking(char[][] matrix, int row, int n,
			List<String[]> res) {		
		if (row == n) {
			
			String[] sarray = new String[n];
			for (int i = 0; i < n; i++) {
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < n; j++) {
					sb.append(matrix[i][j]);
				}
				System.out.println(sb.toString());
				sarray[i] = sb.toString();
			}
			res.add(sarray);
			System.out.println("----------------");
			return;
		}
		
		for(int i = 0;i<n;i++){
			if(varifiy(matrix, row, i, n)){
				matrix[row][i] = 'Q';
				backtracking(matrix, row+1, n, res);
				matrix[row][i] = '.';
			}
		}
		return;
		
		
	}

	public boolean varifiy(char[][] matrix, int row, int col, int n) {
		int tempr = 0;
		int tempc = 0;

		for (int i = 0; i < n; i++) {
			if (matrix[row][i] == 'Q')
				return false;
			if (matrix[i][col] == 'Q')
				return false;
		}

		tempr = row;
		tempc = col;

		while (tempr >= 0 && tempc >= 0) {
			if (matrix[tempr][tempc] == 'Q')
				return false;
			tempr--;
			tempc--;
		}
		tempr = row;
		tempc = col;
		while (tempr < n && tempc < n) {
			if (matrix[tempr][tempc] == 'Q')
				return false;
			tempr++;
			tempc++;
		}
		tempr = row;
		tempc = col;
		while (tempr >= 0 && tempc < n) {
			if (matrix[tempr][tempc] == 'Q')
				return false;
			tempr--;
			tempc++;
		}
		tempr = row;
		tempc = col;
		while (tempr < n && tempc >= 0) {
			if (matrix[tempr][tempc] == 'Q')
				return false;
			tempr++;
			tempc--;
		}

		return true;

	}
}
