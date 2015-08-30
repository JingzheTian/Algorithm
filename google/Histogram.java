package google;

import java.util.Arrays;
import java.util.Stack;

public class Histogram {
	public static void main(String[] args) {
		int[] a = {1,10000};
		System.out.println(new Histogram().maximumGap(a));
	}

	 public int maximumGap(int[] nums) {
	        if(nums.length == 0 || nums.length == 1)return 0;
	        
	        int left = Integer.MAX_VALUE;
			int right = 0;
			for(int i:nums){
	            right = Math.max(right, i);
	            left = Math.min(left, i);
	        }
			int gap = (right -left)/nums.length + 1;
			
			int[] maxv = new int[nums.length];
			int[] minv = new int[nums.length];
			Arrays.fill(maxv, Integer.MIN_VALUE);
			Arrays.fill(minv, Integer.MAX_VALUE);
			
			for(int i:nums){
		            int idx = (i - left) / gap;
		            minv[idx] = Math.min(i, minv[idx]);
		            maxv[idx] = Math.max(i, maxv[idx]);
			}
	        
			    int maxGap = Integer.MIN_VALUE;
		        int previous = right;
		        for (int i = 0; i < nums.length - 1; i++) {
		            if (minv[i] == Integer.MAX_VALUE && maxv[i] == Integer.MIN_VALUE)
		                continue;
		            maxGap = Math.max(maxGap, minv[i] - previous);
		            previous = maxv[i];
		        }
		        maxGap = Math.max(maxGap, right - previous); // updata the final max value gap
		        return maxGap;
	 }
	
	public int largestRectangleArea(int[] h) {
		Stack<Integer> s = new Stack<Integer>();
		int max = 0;
		for (int i = 0; i <= h.length; i++) {
			int height = (i == h.length?0:h[i]);
			if(s.isEmpty() || height > h[s.peek()]){
			    s.push(i);
			}else{
			    int th = s.pop();
			    int length = (s.isEmpty()?i:i- 1-s.peek());
			    max = Math.max(max, h[th]*length);
			    i--;
			}
		}

		return max;
	}

	public int largestRectangleArea1(int[] height) {
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
				maxArea = Math.max(maxArea, height[tp]
						* (s.isEmpty() ? i : i - 1 - s.peek()));
				i--;
			}
		}
		return maxArea;
	}
}
