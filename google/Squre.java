package google;

public class Squre {
	public static void main(String[] args) {
		int[][] matrix = { { 2, 1, -3, -4, 5 }, { 0, 6, 3, 4, 1 },
				{ 2, -2, -1, 4, -5 }, { -3, 3, 1, 0, 3 } };

		int[] a = { 2, -3, 4, 2, -4, 9 };
		new Squre().display(matrix);
		System.out.println(new Squre().dp(matrix));

	}

	public int dp(int[][] matrix) {
		int row = matrix.length;
		int col = matrix.length;
		int[] temp = new int[row];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < col; i++) {
			for (int j = i; j < col; j++) {
				if (j == i) {
					for (int m = 0; m < row; m++) {
						temp[m] = matrix[m][j];
					}
				}else
				{
					for (int m = 0; m < row; m++) {
						if(temp[m] != 0)
						temp[m] = temp[m] +  matrix[m][j];
					}
				}
				int currentMax = subSequence(temp);
				max = Math.max(max, currentMax);
			}
		}
		
		return max;

	}

	public int subSequence(int[] array) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i : array) {
			if (sum < 0)
				sum = i;
			else
				sum += i;
			max = Math.max(sum, max);
		}
		return max;
	}

	public void display(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
