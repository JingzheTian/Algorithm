package sv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class ArrayQ {
	public static void main(String[] args) {
		int[][] a = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 },
				{ 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };

		int[] b = {1,1};
		// int[] b = new ArrayQ().sortColors(a);
		// for (int j = 0; j < a.length; j++) {
		// System.out.print(b[j] + " ");
		// }
		new ArrayQ().productExceptSelf2(b);

	}
	
	public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        if(nums.length == 0)return res;
        int n = nums[0];
        
        for(int i = 1; i<nums.length-1;i++){
            res[i] = n;
            n*=nums[i];
        }
        res[nums.length-1] = n;
        
        n = nums[nums.length-1];
        
        for(int i = nums.length-2;i>0;i--){
            res[i]*=n;
            n*=nums[i];
        }
        
        res[0] = n;
        
        return res;
    }

	public List<String> summaryRanges2(int[] nums) {
		List<String> res = new ArrayList<String>();
		if(nums.length == 0)return res;
		int n = 0;

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] + 1 == nums[i + 1]) {
				n++;
			} else {
				if (n > 0) {
					String s = nums[i - n] + "->" + nums[i];
					res.add(s);
					n = 0;
				} else {
					String s = nums[i] + "";
					res.add(s);
				}
			}
		}

			if (n > 0) {
				String s = nums[nums.length - n - 1] + "->"
						+ nums[nums.length - 1];
				res.add(s);
			} else {
				String s = nums[nums.length - 1] + "";
				res.add(s);
			}

		return res;
	}

	public int threeSumClosest(int[] nums, int target) {
		int res = 0;
		int dif = Integer.MAX_VALUE;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (Math.abs(target - sum) < dif) {
					dif = Math.abs(target - sum);
					res = sum;
				}
				if (sum > target) {
					right--;
				} else {
					left++;
				}
			}
		}

		return res;
	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int[] res = new int[2];

		for (int i = 0; i < nums.length; i++) {
			if (hm.containsKey(target - nums[i])) {
				res[0] = hm.get(target - nums[i]) + 1;
				res[1] = i + 1;
				return res;
			}
			hm.put(target - nums[i], i);
		}

		return res;
	}

	public boolean searchMatrix3(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int m = 0;
		int n = matrix[0].length - 1;

		while (m < matrix.length && n >= 0) {
			if (target == matrix[m][n])
				return true;
			else if (target < matrix[m][n])
				n--;
			else
				m++;
		}

		return false;

	}

	public int[] productExceptSelf(int[] nums) {
		int pro = nums[0];
		int[] res = new int[nums.length];
		res[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res[i] = pro;
			pro *= nums[i];
		}

		System.out.println(Arrays.toString(res));
		pro = nums[nums.length - 1];

		for (int i = nums.length - 2; i > 0; i--) {
			res[i] *= pro;
			pro *= nums[i];
		}

		res[0] = pro;
		System.out.println(Arrays.toString(res));
		return res;
	}

	public int divide(int a, int b) {
		if (b == 0)
			return Integer.MAX_VALUE;
		if (a == 0)
			return 0;

		boolean neg = ((a > 0 && b < 0) || (a < 0 && b > 0));

		int res = 0;
		a = Math.abs(a);
		b = Math.abs(b);
		int time = 1;

		while (a >= b) {
			if (a <= b * time) {
				res += divide(a, b);
				break;
			}
			a -= b * time;
			res += time;
		}

		if (neg)
			res *= -1;

		return res;

	}

	public int countDigitOne(int n) {
		if (n < 10)
			return 1;
		int temp = n % 10;// prrvious
		n /= 10;
		int sum = 1; // sum

		int pre = n % 10;
		int x = 1; // pow

		while (n != 0) {

			if (n % 10 == 1) {
				sum += Math.pow(10, x) + sum;
			}
			pre = (int) (Math.pow(10, x) + 10 * pre);
			temp = n % 10;
			n = n / 10;
			x++;
		}
		return sum;

	}

	public int largestRectangleArea(int[] h) {
		Stack<Integer> s = new Stack<Integer>();
		int max = 0;
		for (int i = 0; i <= h.length; i++) {
			int height = (i == h.length ? 0 : h[i]);
			if (s.isEmpty() || height > h[s.peek()]) {
				s.push(i);
			} else {
				int th = s.pop();
				int length = (s.isEmpty() ? i : i - 1 - s.peek());
				int temp = h[th] * length;
				max = Math.max(max, temp);
				i--;
			}
		}

		return max;
	}

	public int jump2(int[] nums) {
		if (nums.length == 0)
			return 0;
		int could = nums[0];
		int real = nums[0];
		int step = 0;

		for (int i = 0; i < nums.length; i++) {
			if (could < 0)
				return -1;
			if (i < real) {
				could = Math.max(could, nums[i]);
				could--;
			} else if (i == real) {
				step++;
				real = could;
			}
		}
		if (could > 0)
			return step + 1;
		else
			return -1;
	}

	public int removeDuplicates2(int[] nums) {
		if (nums.length == 0)
			return 0;
		int point = 0;
		int i = 0;

		while (point < nums.length) {
			if (point > nums.length - 2 || nums[point + 1] > nums[point]) {
				nums[i] = nums[point];
				i++;
				point++;
			} else
				point++;
		}
		for (int n : nums) {
			System.out.print(n + " ");
		}

		return i;
	}

	public void backTracking(int k, int n, int start, List<Integer> temp,
			List<List<Integer>> res) {
		if (k == 0 && n == 0) {
			res.add(temp);
			return;
		}

		if (n < start)
			return;

		for (int i = start; i <= 9; i++) {
			List<Integer> newTemp = new ArrayList<Integer>();
			newTemp.addAll(temp);
			newTemp.add(i);
			if (n >= i) {
				backTracking(k - 1, n - i, i + 1, newTemp, res);
			}
		}
	}

	public int calculateMinimumHP(int[][] dungeon) {
		int col = dungeon[0].length - 1;
		int row = dungeon.length - 1;

		if (dungeon[row][col] <= 0) {
			dungeon[row][col] = 1 - dungeon[row][col];
		} else {
			dungeon[row][col] = 1;
		}

		for (int i = col - 1; i >= 0; i--) {
			if (dungeon[row][i + 1] - dungeon[row][i] <= 0) {
				dungeon[row][i] = 1;
			} else {
				dungeon[row][i] = dungeon[row][i + 1] - dungeon[row][i];
			}
		}

		for (int i = row - 1; i >= 0; i--) {
			if (dungeon[i + 1][col] - dungeon[i][col] <= 0) {
				dungeon[i][col] = 1;
			} else {
				dungeon[i][col] = dungeon[i + 1][col] - dungeon[i][col];
			}
		}

		for (int i = row - 1; i >= 0; i--) {
			for (int j = col - 1; j >= 0; j--) {
				int right = 0;
				int bottom = 0;
				if (dungeon[i][j + 1] - dungeon[i][j] <= 0) {
					right = 1;
				} else {
					right = dungeon[i][j + 1] - dungeon[i][j];
				}

				if (dungeon[i + 1][j] - dungeon[i][j] <= 0) {
					bottom = 1;
				} else {
					bottom = dungeon[i + 1][j] - dungeon[i][j];
				}

				dungeon[i][j] = Math.min(right, bottom);

			}
		}
		System.out.println(dungeon[0][0]);
		return dungeon[0][0];

	}

	public List<List<Integer>> combinationSum2(int[] c, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(c);
		if (c.length == 0)
			return res;
		recursive(c, target, 0, new ArrayList<Integer>(), res);
		return res;

	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if ((k + 1) * k / 2 > n || n > k * 9)
			return res;
		backTracking(k, n, 1, new ArrayList<Integer>(), res);
		return res;

	}

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n == 0 || k == 0 || k > n)
			return res;
		recursive(n, 1, k, res, new ArrayList<Integer>());
		return res;
	}

	public List<Integer> convert(int[] nums) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i : nums) {
			l.add(i);
		}
		return l;
	}

	public String convertToTitle(int num) {
		String output = "";
		Stack<Integer> s = new Stack<Integer>();
		while (num - 26 > 0) {
			int temp = num % 26;
			if (temp == 0) {
				s.push(26);
				num = num / 26 - 1;
			} else {
				s.push(temp);
				num = num / 26;
			}

		}
		s.push(num);

		while (!s.isEmpty()) {
			output += (char) ((int) s.pop() - 1 + 'A');
		}
		System.out.println(output);

		return output;
	}

	public int countPrimes(int n) {
		int[] res = new int[n];
		Arrays.fill(res, -1);
		res[0] = res[1] = 0;

		int length = (int) Math.sqrt(n);

		for (int i = 2; i <= length; i++) {
			int temp = i * 2;
			while (temp < n) {
				res[temp] = 0;
				temp += i;
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (res[i] == -1)
				count++;
		}

		return count;
	}

	public boolean dfs(char[][] board, int i, int j, int index, char[] c) {
		if (index == c.length)
			return true;

		char temp = board[i][j];
		board[i][j] = '#';

		boolean res = false;

		if (i - 1 >= 0 && board[i - 1][j] == c[index]) {
			res = res | dfs(board, i - 1, j, index + 1, c);
		}
		if (i + 1 < board.length && board[i + 1][j] == c[index]) {
			res = res | dfs(board, i + 1, j, index + 1, c);
		}
		if (j - 1 >= 0 && board[i][j - 1] == c[index]) {
			res = res | dfs(board, i, j - 1, index + 1, c);
		}
		if (j + 1 < board[0].length && board[i][j + 1] == c[index]) {
			res = res | dfs(board, i, j + 1, index + 1, c);
		}

		board[i][j] = temp;

		return res;
	}

	public boolean exist(char[][] board, String word) {
		if (word.length() == 0)
			return true;
		char[] c = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == c[0]) {
					if (dfs(board, i, j, 1, c))
						return true;
				}
			}
		}

		return false;

	}

	public int findFirstOne(int[] nums) {
		int i = 0;
		int j = nums.length - 1;

		while (i < j) {
			int mid = (i + j) / 2;

			if (nums[mid] == 0) {
				i = mid + 1;
			} else {
				j = mid;
			}
		}

		return i;
	}

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (i < k) {
				pq.offer(nums[i]);
			} else {
				if (nums[i] > pq.peek()) {
					pq.poll();
					pq.offer(nums[i]);
				}
			}
		}

		return pq.peek();
	}

	public int findMin(int[] nums) {
		int l = 0;
		int r = nums.length - 1;

		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] > nums[0]) {
				l = mid + 1;
			}
			if (nums[mid] < nums[nums.length - 1]) {
				r = mid - 1;
			}
		}

		return nums[l];
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return res;

		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		res.add(l);
		for (int i = 1; i < numRows; i++) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.add(1);
			for (int j = 1; j < i; j++) {
				temp.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
			}
			temp.add(1);
			res.add(temp);
		}

		return res;
	}

	public int[][] generateMatrix(int n) {
		int steps = n * n;
		int[][] res = new int[n][n];
		int d = 0;
		int row = 0;
		int col = 0;
		for (int i = 1; i < steps + 1; i++) {
			res[row][col] = i;
			if (d == 0) {
				if (col + 1 < n && res[row][col + 1] == 0)
					col++;
				else {
					d = 1;
					row++;
					continue;
				}
			}

			if (d == 1) {
				if (row + 1 < n && res[row + 1][col] == 0)
					row++;
				else {
					d = 2;
					col--;
					continue;
				}
			}

			if (d == 2) {
				if (col - 1 >= 0 && res[row][col - 1] == 0)
					col--;
				else {
					d = 3;
					row--;
					continue;
				}
			}

			if (d == 3) {
				if (row - 1 >= 0 && res[row - 1][col] == 0)
					row--;
				else {
					d = 0;
					col++;
					continue;
				}
			}
		}

		return res;
	}

	public String getPermutation(int n, int k) {
		if (n == 1)
			return "1";
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = i + 1;
		}

		while (k - 1 > 0) {
			nextPermutation(nums);
			k--;
		}

		StringBuffer res = new StringBuffer();
		for (int i = 0; i < n; i++) {
			res.append(nums[i]);
		}
		return res.toString();
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<Integer>();
		if (rowIndex == 0)
			return res;
		res.add(1);
		for (int i = 0; i < rowIndex; i++) {
			res.add(1);
			int box = res.get(0);
			for (int j = 1; j < res.size() - 1; j++) {
				int temp = res.get(j);
				res.set(j, res.get(j) + box);
				box = temp;
			}
		}

		return res;

	}

	public boolean isHappy(int n) {
		Set<Integer> hs = new HashSet<Integer>();
		return recursive(n, hs);
	}

	public int jump(int[] nums) {
		int could = 0;
		int step = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (could < i + nums[i] && could < nums.length - 1) {
				step++;
				could = i + nums[i];
			}

		}

		if (could >= nums.length - 1)
			return step;
		else
			return -1;
	}

	public String largestNumber(int[] nums) {
		if (nums.length == 0)
			return "";

		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i : nums)
			al.add(i);
		Comparator<Integer> comp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				long l1 = Long.valueOf(String.valueOf(o1) + String.valueOf(o2));
				long l2 = Long.valueOf(String.valueOf(o2) + String.valueOf(o1));
				if (l1 > l2)
					return -1;
				else
					return 1;
			}
		};

		String res = "";
		Collections.sort(al, comp);
		System.out.println(al);

		for (Integer i : al) {
			res += i;
		}
		return res;
	}

	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		int max = 0;

		for (int i = 0, j = 0; i < s.length(); i++) {
			if (hm.containsKey(s.charAt(i))) {
				max = Math.max(max, i - j);
				j = hm.get(s.charAt(i)) + 1;
			}

			hm.put(s.charAt(i), i);
		}
		return max;
	}

	public List<Integer> majorityElement(int[] nums) {
		int num1 = 0;
		int num2 = 1;

		int v1 = 0;
		int v2 = 0;

		for (int i : nums) {
			if (i == num1)
				v1++;
			else if (i == num2)
				v2++;
			else if (v1 == 0) {
				num1 = i;
				v1 = 1;
			} else if (v2 == 0) {
				num2 = i;
				v2 = 1;
			} else {
				v1--;
				v2--;
			}
		}

		v1 = v2 = 0;

		for (int i : nums) {
			if (i == num1)
				v1++;
			else if (i == num2)
				v2++;
		}
		List<Integer> l = new ArrayList<Integer>();
		if (v1 > nums.length / 3)
			l.add(num1);
		if (v2 > nums.length / 3)
			l.add(num2);
		return l;
	}

	public int majorityElement2(int[] nums) {
		int num = 0;
		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			if (count == 0) {
				num = nums[i];
				count = 1;
				continue;
			}
			if (nums[i] == num) {
				count++;
			} else {
				count--;
			}

		}
		return num;
	}

	public int maxArea(int[] height) {
		int max = 0;
		int left = 0;
		int right = height.length - 1;

		while (left < right) {
			max = Math.min(height[left], height[right]) * (right - left);

			if (height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}
		}

		return max;

	}

	public int maxProduct(int[] nums) {
		if (nums.length == 0)
			return 0;
		int max = nums[0];
		int min = nums[0];
		int res = 0;
		for (int i = 1; i < nums.length; i++) {
			int temp = max;
			max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
			min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
			res = Math.max(max, res);
		}

		return res;
	}

	public int maxProfit(int[] prices) {
		int min = prices[0];
		int max = 0;

		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			max = Math.max(max, prices[i] - min);
		}

		return max;

	}

	public String multiply(String num1, String num2) {
		int[] res = new int[num1.length() * num2.length()];

		for (int i = num1.length() - 1; i >= 0; i--) {
			for (int j = num2.length() - 1; j >= 0; j--) {
				res[i + j] += Integer.valueOf(num2.charAt(j) - '0')
						* Integer.valueOf(num1.charAt(i) - '0');
			}
		}

		int carry = 0;
		int i = res.length - 1;
		while (true) {
			if (res[i] != 0)
				break;
			i--;
		}

		for (int j = 0; j < res.length; i++) {
			res[i] = res[i] % 10 + carry;
			carry = res[i] / 10;
		}

		StringBuffer strB = new StringBuffer();
		for (int j = 0; j <= i; j++) {
			strB.append(res[j]);
		}

		return strB.reverse().toString();

	}

	public int mySqrt(int x) {
		if (x > 0) {
			int left = 1;
			int right = 46341;
			while (true) {
				int mid = (left + right) / 2;
				if (mid * mid == x)
					return mid;
				else if (mid * mid > x) {
					right = mid;
				} else {
					if (right - left == 1)
						return left;
					left = mid;
				}
			}
		}

		return 0;

	}

	public void nextPermutation(int[] nums) {
		System.out.println(Arrays.toString(nums));
		int point = nums.length - 2;

		while (point >= 0) {
			if (nums[point + 1] > nums[point])
				break;
			point--;
		}

		if (point == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}
		int n = nums.length - 1;
		while (nums[point] > nums[n])
			n--;
		int temp = nums[point];
		nums[point] = nums[n];
		nums[n] = temp;
		System.out.println(Arrays.toString(nums));
		reverse(nums, point + 1, nums.length - 1);
		System.out.println(Arrays.toString(nums));
	}

	public int numDecodings(String s) {
		if (s.length() == 0)
			return 0;

		int n = s.length();
		int[] dp = new int[s.length() + 1];
		dp[n] = 1;
		dp[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

		for (int i = n - 2; i >= 0; i--) {
			if (s.charAt(i) == 0)
				continue;
			else {
				int num = Integer.parseInt(s.substring(i, i + 2));

				if (num > 26 || num % 10 == 0)
					dp[i] = dp[i + 1];
				else
					dp[i] = dp[i + 1] + dp[i + 2];
			}
		}

		return dp[0];
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int time = nums.length * (nums.length - 1);
		System.out.println(time);
		while (time > 0) {
			res.add(convert(nums));
			nextPermutation(nums);
			time--;
		}
		return res;
	}

	public void recursive(int n, int index, int k, List<List<Integer>> res,
			List<Integer> temp) {
		if (k == 0) {
			res.add(temp);
			return;
		}

		for (int i = index; i <= n; i++) {
			List<Integer> l = new ArrayList<Integer>();
			l.addAll(temp);
			l.add(i);
			recursive(n, i + 1, k - 1, res, l);
		}
	}

	public boolean recursive(int n, Set<Integer> hs) {
		if (n == 1)
			return true;
		if (!hs.add(n))
			return false;

		int newn = 0;
		while (n != 0) {
			newn += (n % 10) * (n % 10);
			n /= 10;
		}

		return recursive(newn, hs);
	}

	public void recursive(int[] c, int target, int index, List<Integer> list,
			List<List<Integer>> res) {
		if (target < 0)
			return;
		if (target == 0) {
			res.add(list);
			return;
		}

		for (int i = index; i < c.length; i++) {
			if (i > index && c[i] == c[i - 1])
				continue;
			List<Integer> newlist = new ArrayList<Integer>();
			newlist.addAll(list);
			newlist.add(c[i]);
			recursive(c, target - c[i], i + 1, newlist, res);
		}

	}

	public void removeDuplicate(char[] str) {
		Arrays.sort(str);

		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;
		int left = 0;
		for (int i = 1; i < str.length; i++) {
			if (str[left] != str[i]) {
				left++;
				str[left] = str[i];
			}
		}
		str[left] = 0;
	}

	public int removeDuplicates(int[] nums) {
		if (nums.length < 3)
			return nums.length;

		int w = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i > nums.length - 4 || nums[i + 3] > nums[i]) {
				nums[w] = nums[i];
				w++;
			}
		}

		for (int i : nums) {
			System.out.print(i + " ");
		}

		return w;

	}

	// public int removeDuplicates(int[] nums) {
	// if (nums.length == 0)
	// return 0;
	// int left = 0;
	// for (int i = 1; i < nums.length; i++) {
	// if (nums[left] != nums[i]) {
	// left++;
	// nums[left] = nums[i];
	// }
	// }
	//
	// return left + 1;
	// }

	public int removeDuplicates3(int[] nums) {
		if (nums.length == 0)
			return 0;
		int left = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[left] != nums[i]) {
				left++;
				nums[left] = nums[i];
			}
		}

		return left + 1;
	}

	public int removeElement(int[] nums, int val) {
		if (nums.length == 0)
			return 0;

		int l = 0;
		int r = nums.length - 1;

		while (l <= r) {
			if (nums[r] == val) {
				r--;
				continue;
			}

			if (nums[l] == val) {
				int temp = nums[l];
				nums[l] = nums[r];
				nums[r] = temp;
			}
			l++;
		}
		return l;
	}

	public int reverse(int x) {
		boolean negative = (x < 0 ? true : false);
		int res = 0;
		x = Math.abs(x);

		while (x != 0) {
			res = res * 10 + x % 10;
			x /= 10;

			if (res == (Integer.MAX_VALUE / 10)
					&& x >= (Integer.MAX_VALUE % 10)) {
				return negative ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
		}

		return negative ? res * -1 : res;
	}

	public void reverse(int[] nums, int i, int j) {
		while (i < j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			i++;
			j--;
		}
		return;
	}

	public int rob(int[] nums) {
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];

		int max = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (i < 2) {
				max = Math.max(nums[i], max);
			} else {
				max = Math.max(nums[i] + nums[i - 2], max);
			}
			nums[i] = max;

		}
		return max;
	}

	// public int[] sortColors(int[] nums) {
	// if (nums.length == 0)
	// return nums;
	// int l = 0;
	// int i = 0;
	// int r = nums.length - 1;
	// while (i < r) {
	// if (nums[i] == 0) {
	// nums[i] = nums[l];
	// nums[l] = 0;
	// l++;
	// } else if (nums[i] == 2) {
	// nums[i] = nums[r];
	// nums[r] = 2;
	// r--;
	// } else {
	// i++;
	// }
	// }
	// return nums;
	// }

	public void rotate(int[] nums, int k) {
		int box = nums[0];
		int index = 0;
		int mod = 0;

		for (int i = 0; i < nums.length; i++) {
			mod += k;
			int temp = nums[(index + k) % nums.length];
			nums[(index + k) % nums.length] = box;
			box = temp;
			index = (index + k) % nums.length;
			if (mod % nums.length == 0) {
				index = (index + 1) % nums.length;
				box = nums[index % nums.length];
			}

		}

		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

	public void rotate(int[][] matrix) {
		int length = matrix.length;

		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		int l = 0;
		int r = length - 1;

		while (l < r) {
			for (int i = 0; i < length; i++) {
				int temp = matrix[i][l];
				matrix[i][l] = matrix[i][r];
				matrix[i][r] = temp;
			}
			l++;
			r--;
		}
	}

	public int search(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;

		if (target < nums[l] && target > nums[r])
			return -1;

		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] == target)
				return mid;
			if (target >= nums[0]) {
				if (nums[mid] > target || nums[mid] <= nums[nums.length - 1])
					r = mid - 1;
				else
					l = mid;
			}

			if (target <= nums[nums.length - 1]) {
				if (nums[mid] < target || nums[mid] >= nums[0])
					l = mid + 1;
				else
					r = mid;
			}
		}
		return (nums[l] == target) ? l : -1;
	}

	public int searchInsert(int[] nums, int target) {
		if (nums.length == 0)
			return 0;
		int i = 0;
		int j = nums.length - 1;

		while (i < j) {
			int mid = (i + j) / 2;
			if (nums[mid] > target) {
				j = mid;
			} else if (nums[mid] < target) {
				i = mid + 1;
			} else {
				return mid;
			}
		}

		if (nums[i] < target) {
			return i + 1;
		} else {
			return i;
		}
	}

	public boolean searchMatrix(int[][] m, int target) {
		int i = m.length;
		if (i == 0)
			return false;
		int j = m[0].length;
		if (target < m[0][0] || target > m[i - 1][j - 1])
			return false;

		int l = 0;
		int r = i * j - 1;

		int mid = (l + r) / 2;

		while (l <= r) {
			int row = mid / j;
			int col = mid % j;

			if (m[row][col] > target) {
				r = mid - 1;
			} else if (m[row][col] < target) {
				l = mid + 1;
			} else {
				return true;
			}
			mid = (l + r) / 2;
		}
		return false;
	}

	public boolean searchMatrix2(int[][] matrix, int target) {
		int row = matrix.length - 1;
		int col = matrix[0].length - 1;

		if (target < matrix[0][0] || target > matrix[row][col])
			return false;

		while (row >= 0) {
			if (matrix[row][0] <= target) {
				break;
			}
			row--;
		}

		while (col >= 0) {
			if (matrix[row][col] == target) {
				return true;
			}
			col--;
		}

		return false;
	}

	public void sortColors(int[] nums) {
		if (nums.length == 0)
			return;
		int i = 0;
		int j = nums.length - 1;
		int point = 0;
		while (point < j) {
			if (nums[point] == 2) {
				nums[point] = nums[j];
				nums[j] = 2;
				j--;
			} else if (nums[point] == 0) {
				nums[point] = nums[i];
				nums[i] = 0;
				i++;
				point++;
			} else {
				point++;
			}
		}
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return res;

		int count = matrix.length * matrix[0].length;
		int direction = 0;
		int row = 0;
		int col = 0;

		int[][] n = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++)
				n[i][j] = 0;
		}

		while (count > 0) {
			res.add(matrix[row][col]);
			n[row][col] = -1;
			if (direction == 0) {
				col++;
				if (col == matrix[0].length || n[row][col] == -1) {
					col--;
					row++;
					direction = 1;
				}
			} else if (direction == 1) {
				row++;
				if (row == matrix.length || n[row][col] == -1) {
					row--;
					col--;
					direction = 2;
				}
			} else if (direction == 2) {
				col--;
				if (col < 0 || n[row][col] == -1) {
					row--;
					col++;
					direction = 3;
				}
			} else {
				row--;
				if (row < 0 || n[row][col] == -1) {
					row++;
					col++;
					direction = 0;
				}
			}
			count--;

		}

		return res;
	}

	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<String>();

		if (nums.length == 0)
			return res;

		int l = 0;
		int r = 1;
		while (r < nums.length) {
			if (nums[r] - 1 != nums[r - 1]) {
				if (r - 1 == l) {
					String s = String.valueOf(nums[l]);
					res.add(s);
				} else {
					String s = String.valueOf(nums[l]) + "->"
							+ String.valueOf(nums[r - 1]);
					res.add(s);
				}
				l = r;
			}
			r++;
		}

		if (r - 1 == l) {
			String s = String.valueOf(nums[l]);
			res.add(s);
		} else {
			String s = String.valueOf(nums[l]) + "->"
					+ String.valueOf(nums[r - 1]);
			res.add(s);
		}

		return res;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums.length < 3)
			return res;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			// if(i>0 && nums[i] == nums[i-1])
			// continue;

			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				if (nums[i] + nums[l] + nums[r] == 0) {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(nums[i]);
					temp.add(nums[l]);
					temp.add(nums[r]);
					res.add(temp);
					r--;
					l++;
					while (l < r && nums[r] == nums[r + 1])
						r--;
					while (l < r && nums[l] == nums[l - 1])
						l++;

				} else if (nums[i] + nums[l] + nums[r] > 0) {
					r--;
				} else {
					l++;
				}
			}
		}

		return res;
	}

	public int trap(int[] height) {
		if (height.length == 0)
			return 0;
		int sum = 0;
		int max = height[0];
		int index = 0;
		int max_index = 0;
		while (index < height.length) {
			if (max <= height[index]) {
				while (max_index < index) {
					sum += max - height[max_index];
					max_index++;
				}
				max = height[index];
				max_index = index;
			}
			index++;
		}
		if (max_index == index - 1)
			return sum;

		int end = max_index;
		index--;
		max_index = index;
		max = height[index];
		while (index >= end) {
			if (max <= height[index]) {
				while (max_index > index) {
					sum += max - height[max_index];
					max_index--;
				}
				max = height[index];
				max_index = index;
			}
			index--;
		}

		return sum;

	}

	// public int[] twoSum(int[] nums, int target) {
	// Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
	// int[] res = new int[2];
	// for (int i = 0; i < nums.length; i++) {
	// if (hm.containsKey(target - nums[i])) {
	// res[0] = hm.get(target - nums[i]) + 1;
	// res[1] = i + 1;
	// return res;
	// }
	//
	// hm.put(nums[i], i);
	// }
	//
	// return res;
	// }

}
