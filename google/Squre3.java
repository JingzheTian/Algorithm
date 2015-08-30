package google;

import java.util.Stack;

public class Squre3 {
	public static void main(String[] args) {
		// int[][] matrix = { { 1,0,1,0},{1,0,1,1},{1,0,1,1},{1,1,1,1} };
		int[][] matrix = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 } };
		int[] a = { 1 };
		// System.out.println(new Squre3().subSequence(a));
		// new Squre().display(matrix);
		System.out.println(new Squre3().maximalRectangle(matrix));

	}

	public int maximalRectangle(int[][] matrix) {

		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		if (col == 0)
			return 0;
		int[] temp = new int[row];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < col; i++) {
			if (i == 0) {
				for (int m = 0; m < row; m++) {
					temp[m] = matrix[m][i];
				}
			} else {
				for (int m = 0; m < row; m++) {
					if (matrix[m][i] != 0)
						temp[m] = temp[m] + matrix[m][i];
					else
						temp[m] = 0;
				}
			}
			int currentMax = subSequence2(temp);
			max = Math.max(max, currentMax);
		}
		// max = (int)Math.sqrt(max) * (int)Math.sqrt(max);
		return max;
	}

	public int subSequence2(int[] h) {
		    Stack<Integer> s = new Stack<Integer>();
			int max = 0;
			for (int i = 0; i <= h.length; i++) {
				int height = (i == h.length?0:h[i]);
				if(s.isEmpty() || height > h[s.peek()]){
				    s.push(i);
				}else{
				    int th = s.pop();
				    int length = (s.isEmpty()?i:i- 1-s.peek());
				    int edge = Math.min(h[th], length);
				    max = Math.max(max, edge*edge);
				    i--;
				}
			}

			return max;
	}

	public int subSequence(int[] height) {
		int len = height.length;
		Stack<Integer> s = new Stack<Integer>();
		int maxArea = 0;
		for (int i = 0; i <= len; i++) {
			int h = (i == len ? 0 : height[i]);
			if (s.isEmpty() || h >= height[s.peek()]) {
				s.push(i);
			} else {
				int tp = s.pop();
				int length = s.isEmpty() ? i : i - 1 - s.peek();
				System.out.println("i= " + tp + " height[i]= " + height[tp]
						+ " length= " + length);
				maxArea = Math.max(maxArea, height[tp]
						* (s.isEmpty() ? i : i - 1 - s.peek()));
				i--;
			}
			System.out.println(s);
			System.out.println("maxarea: " + maxArea);
			System.out.println("--------");
		}
		return maxArea;
	}

	public void display(int[][] matrix) {
		int row = matrix.length;
		int col = matrix.length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
