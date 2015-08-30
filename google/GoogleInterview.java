package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GoogleInterview {
	public static void main(String[] args) {
		int[] a = { 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5 };
		// int[] num = new GoogleInterview().swapNum(a, 4);
		// for (int i = 0; i < num.length; i++) {
		// System.out.println(num[i]);
		// }

		String s = "abc?bcd";
		StringBuffer sb= new StringBuffer(s);
		sb.replace(3, 4, "*");
		System.out.println(sb.toString());
		//System.out.println(new GoogleInterview().subset(4));
		// a.length-1));
		//new GoogleInterview().subset(4,"");
	}
	
	public List<String> subset(int num)
	{
		List<String> l = new ArrayList<String>();
		if(num == 0)return l;
		print(num,l,"");
		return l;
	}
	
	public void print(int num, List<String> l, String s) {
		if (num == 0)
		{
			l.add(s);
		}
		
		for(int i = 1;i<=num;i++){
			print(num-i, l, s + " " + String.valueOf(i));
		}
	}

	public int minCut(String s) {
		List<List<String>> res = new ArrayList<List<String>>();
		if (s.length() == 0)
			return 0;
		bt(s, new ArrayList<String>(), res);

		int min = s.length();

		for (int i = 0; i < res.size(); i++) {
			min = Math.min(min, res.get(i).size() - 1);
		}

		return min;

	}

	public void bt(String s, List<String> temp, List<List<String>> res) {
		if (s.length() == 0) {
			res.add(temp);
			return;
		}
		for (int i = 0; i <= s.length(); i++) {
			if (s.substring(0, i).length() != 0 && isPal(s.substring(0, i))) {
				List<String> newTemp = new ArrayList<String>();
				newTemp.addAll(temp);
				newTemp.add(s.substring(0, i));
				bt(s.substring(i, s.length()), newTemp, res);
			}

		}
	}

	public boolean isPal(String s) {
		char[] c = s.toCharArray();
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (c[i] == c[j]) {
				i++;
				j--;
			} else {
				return false;
			}
		}

		return true;
	}

	public int largestRectangleArea(int[] height) {
		Stack<Integer> s = new Stack<Integer>();

		int max = 0;
		for (int i = 0; i < height.length; i++) {
			if (s.size() == 0)
				max = Math.max(max, height[i]);
			else if (height[i] < s.peek())
				max = Math.max(max, height[i] * (s.size() + 1));
			else if (height[i] >= s.peek()) {
				if (s.peek() * (s.size() + 1) <= height[i]) {
					s.clear();
					max = height[i];
				} else {
					max = Math.max(max, s.peek() * (s.size() + 1));
				}
			}

			s.add(height[i]);
		}

		return max;

	}

	public int firstMissingPositive(int[] nums) {
		int[] a = new int[nums.length + 2];
		Arrays.fill(a, -1);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0 && nums[i] <= nums.length) {
				a[nums[i]] = nums[i];
			}
		}

		for (int i = 1; i < a.length; i++) {
			if (a[i] < 0) {
				return i;
			}
		}

		return 1;
	}

	public int longestValidParentheses(String s) {
		int[] len = new int[s.length()];
		int max = 0;
		int open = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				open++;
			} else if (s.charAt(i) == ')' && open > 0) {
				open--;
				len[i] = len[i - 1] + 2;
				if (i - len[i] >= 0) {
					len[i] = len[i] + len[i - len[i]];
				}
			}

			max = Math.max(len[i], max);

		}

		return max;

	}

	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		if (m == 0)
			return n;
		if (n == 0)
			return m;

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}

		for (int i = 0; i <= n; i++) {
			dp[0][i] = i;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else {
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(
							dp[i - 1][j], dp[i][j - 1]));
					dp[i][j]++;
				}

			}
		}
		return dp[m][n];
	}

	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0)
			return 0;

		int first = 0;
		int second = 0;
		int min = nums.length + 1;
		int sum = nums[0];
		while (first < nums.length && second <= first) {

			if (sum < s) {
				first++;
				if (first < nums.length)
					sum += nums[first];
			} else {
				min = Math.min(first - second + 1, min);
				second++;
				sum -= nums[second];
			}
		}

		if (min == nums.length + 1)
			return 0;
		return min;
	}

	public int numDistinct(String s, String t) {
		if (s.length() == 0)
			return t.length() == 0 ? 1 : 0;

		if (t.length() == 0)
			return 1;
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == t.charAt(0)
					&& s.substring(i + 1).length() >= t.substring(1).length()) {
				res += numDistinct(s.substring(i + 1), t.substring(1));
			}
		}

		return res;

	}

	public int jump(int[] nums) {
		int real = 0;
		int could = 0;
		int step = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			could = Math.max(could, i + nums[i]);
			if (i == real) {
				real = could;
				step++;
			}
		}

		if (real >= nums.length - 1)
			return step;
		else
			return -1;
	}

	public int candy(int[] r) {
		if (r.length == 0)
			return 0;
		int res = 0;

		int[] c = new int[r.length];

		for (int i = 0; i < r.length; i++) {
			c[i] = 1;
		}
		for (int i = 1; i < r.length; i++) {
			if (r[i] > r[i - 1])
				c[i] = c[i - 1] + 1;

		}

		for (int i = r.length - 2; i >= 0; i--) {
			if (r[i] > r[i + 1]) {
				if (i - 1 >= 0 && c[i - 1] <= c[i])
					c[i] = Math.max(c[i], c[i + 1] + 1);
				else if (i == 0)
					c[i] = c[i + 1] + 1;
			}

		}

		for (int i = 0; i < r.length; i++) {
			res += c[i];
		}

		return res;
	}

	public int[] swapNum(int num[], int step) {
		for (int i = 0; i < num.length && step > 0; i++) {
			int index = num.length - 1;
			int max = num[num.length - 1];
			for (int j = num.length - 2; j > i; j--) {
				if (num[j] > max) {
					max = num[j];
					index = j;
				}
			}

			if (max > num[i]) {
				int temp = num[i];
				num[i] = num[index];
				num[index] = temp;
				step--;
			}
		}

		return num;

	}

	public static void Outputpairs(int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		arr[1] = 1;
		int i = 2;

		while (i <= n) {
			arr[i] = arr[i - 1] + arr[i - 2];
			i++;
		}

		for (int k = 0; k <= n - 1; k++) {
			System.out.print("[" + arr[k] % 10 + " , " + arr[k + 1] % 10 + "]");
			System.out.println("\n");
		}

	}

	public boolean couldSqurt(int i) {
		int temp = (int) Math.sqrt(i);
		if (i == temp * temp)
			return true;
		else
			return false;
	}

	public int[] find(int[] a) {
		int point = 0;
		int maxl = 0;
		int templ = 1;

		for (int i = 0; i < a.length; i++) {
			templ = 1;
			while (i + 1 < a.length && a[i] + 1 == a[i + 1]) {
				i++;
				templ++;
			}

			if (templ > maxl) {
				maxl = templ;
				point = i - maxl + 1;
			}
		}
		int[] res = new int[maxl];
		for (int i = point; i < point + maxl; i++) {
			res[i - point] = a[i];
			System.out.println(a[i]);
		}

		return res;
	}

	public List<String> missingPart(int[] a) {
		List<String> l = new ArrayList<String>();
		if (a.length == 0) {
			l.add("0-99");
			return l;
		}

		if (a[0] - 0 == 1) {
			l.add("0");
		} else if (a[0] - 0 > 1) {
			String s = 0 + "-" + (a[0] - 1);
			l.add(s);
		}

		for (int i = 1; i < a.length; i++) {
			if (a[i] - a[i - 1] == 2) {
				l.add(String.valueOf(a[i - 1]));
			} else if (a[i] - a[i - 1] > 1) {
				String s = (a[i - 1] + 1) + "-" + (a[i] - 1);
				l.add(s);
			}
		}
		if (99 - a[a.length - 1] == 1) {
			l.add("99");
		} else if (99 - a[a.length - 1] > 1) {
			String s = (a[a.length - 1] + 1) + "-99";
			l.add(s);
		}

		return l;
	}
}
