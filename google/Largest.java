package google;

import java.util.Stack;

public class Largest {
	public int largestRectangleArea(int[] h) {
		Stack<Integer> height = new Stack<Integer>();
		int max = 0;
		int count = 1;
		for (int i = 0; i < h.length; i++) {
			if (height.isEmpty()) {
				height.push(h[i]);
			} else {
				if (h[i] >= height.peek()) {
					height.push(h[i]);
				} else {
					count = 1;
					while (!height.isEmpty()&&h[i] < height.peek()) {
						max = Math.max(max, height.pop() * count);
						count++;
					}
					height.push(h[i]);
				}
			}
		}

		count = 1;
		while (!height.isEmpty()) {
			max = Math.max(max, height.pop() * count);
			count++;
		}

		return max;
	}
	
	public static void main(String[] args) {
		int[] a = {1,1,1,1,1,1001,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		System.out.println(new Largest().largestRectangleArea(a));
	}
}
