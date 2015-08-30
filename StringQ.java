import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringQ {
	public static void main(String[] args) {
		// biggest k such that at least k elements of the array are >= k
		int num1[] = { 1, 2, 3, 5, 6, 3, 2, 5, 1, 32, 32, 3, 4, 4, 1 };
		int num2[] = { 10, 1, 2, 7, 6, 1, 5 };
		// System.out.println(new StringQ().print("a?b"));
		new StringQ().calculate("(1+(4+5+2)-3)+(6+8)");

	}

	public int calculate(String s) {
		return 0;

	}

	public void print(String s, int index) {
		if (index == s.length()) {
			System.out.println(s);
			return;
		}

		StringBuffer sb = new StringBuffer(s);
		while (index < s.length() && s.charAt(index) != '?') {
			index++;
		}
		if (index == s.length()) {
			System.out.println(s);
			return;
		}
		sb.setCharAt(index, '*');
		print(sb.toString(), index + 1);
		sb.setCharAt(index, '#');
		print(sb.toString(), index + 1);

		return;
	}

	public List<String> anagrams(String[] strs) {
		Set<String> hs = new HashSet<String>();
		List<String> l = new ArrayList<String>();

		for (String s : strs) {
			if (isAnagrams(s)) {
				hs.add(s);
			}
		}

		l.addAll(hs);
		return l;
	}

	public boolean isAnagrams(String s) {
		char[] c = s.toCharArray();
		int i = 0;
		int j = c.length - 1;
		while (i <= j) {
			if (c[i] != c[j])
				return false;
			i++;
			j--;
		}

		return true;
	}

	List<List<Integer>> l = new ArrayList<List<Integer>>();
	Set<List<Integer>> hs = new HashSet<List<Integer>>();

	public List<List<Integer>> combinationSum2(int[] c, int target) {
		Arrays.sort(c);
		if (c.length == 0)
			return l;
		recursive(c, target, new ArrayList<Integer>(), 0, 0);
		l.addAll(hs);
		return l;
	}

	public void recursive(int[] c, int target, List<Integer> temp, int sum,
			int index) {
		if (sum == target)
			hs.add(temp);

		else {
			for (int i = index; i < c.length; i++) {
				List<Integer> newTemp = new ArrayList<Integer>();
				newTemp.addAll(temp);
				newTemp.add(c[i]);
				if (sum + c[i] <= target)
					recursive(c, target, newTemp, sum + c[i], i + 1);
			}

		}
	}

	public int maxSubArray(int[] nums) {
		int sum = nums[0];
		int max = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (sum < 0)
				sum = nums[i];
			else
				sum += nums[i];

			max = Math.max(max, sum);
		}

		return max;

	}

	public boolean canJump(int[] nums) {
		if (nums.length == 0)
			return true;

		int step = nums[0];
		for (int i = 0; i < nums.length; i++) {
			step = Math.max(step, nums[i]);
			if (step == 0)
				return false;
			step--;
		}

		return true;
	}

	// List<List<Integer>> l = new ArrayList<List<Integer>>();
	// public List<List<Integer>> combinationSum(int[] c, int target) {
	// if(c.length == 0)return l;
	// recursive(c, target, new ArrayList<Integer>(), 0, 0);
	// return l;
	// }
	//    
	// public void recursive(int[] c, int target, List<Integer> temp, int sum,
	// int index)
	// {
	// if(sum == target)l.add(temp);
	//
	// else{
	// for(int i = index;i<c.length;i++)
	// {
	// List<Integer> newTemp = new ArrayList<Integer>();
	// newTemp.addAll(temp);
	// newTemp.add(c[i]);
	// if(sum+c[i]<=target)
	// recursive(c,target,newTemp,sum+c[i],i);
	// }
	//            
	// }
	// }

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
					left = mid + 1;
				}
			}
		}

		return 0;

	}

	public boolean exist(char[][] b, String w) {

		if (w.length() == 0)
			return true;
		char[] ca = w.toCharArray();
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if (b[i][j] == w.charAt(0)) {
					if (recursive(b, ca, 0, i, j))
						return true;
				}
			}
		}
		return false;
	}

	public boolean recursive(char[][] b, char[] ca, int index, int i, int j) {

		if (i < 0 || j < 0 || i > b.length - 1 || j > b[0].length - 1
				|| index > ca.length || b[i][j] == '#' || b[i][j] != ca[index])
			return false;
		if (index == ca.length - 1)
			return true;
		char temp = b[i][j];
		b[i][j] = '#';

		boolean b1 = recursive(b, ca, index + 1, i + 1, j);
		boolean b2 = recursive(b, ca, index + 1, i - 1, j);
		boolean b3 = recursive(b, ca, index + 1, i, j + 1);
		boolean b4 = recursive(b, ca, index + 1, i, j - 1);
		b[i][j] = temp;
		return b1 || b2 || b3 || b4;
	}

	public int search(int[] A, int target) {
		int i = 0;
		int j = A.length - 1;

		if (target < A[i] && target > A[j])
			return -1;

		while (i < j) {
			int mid = (i + j) / 2;
			if (A[mid] <= A[j]) {
				if (target > A[mid] && target <= A[j]) {
					i = mid + 1;
				} else {
					j = mid;
				}
			} else {
				if (target <= A[mid] && target >= A[i]) {
					j = mid;
				} else {
					i = mid + 1;
				}
			}
		}

		if (A[i] == target)
			return i;
		else
			return -1;

	}

	public int removeDuplicates(int[] nums) {
		if (nums.length < 3)
			return nums.length;

		int w = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i > nums.length - 3) {
				nums[w] = nums[i];
				w++;
			} else if (nums[i + 2] > nums[i]) {
				nums[w] = nums[i];
				w++;
			}
		}

		return w;
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
		while (a >= b) {
			a = a - b;
			res++;
		}

		if (neg)
			res *= -1;

		return res;

	}

	public String reverseWords2(String s) {
		char[] c = reverse(s.toCharArray(), 0, s.length() - 1);
		int i = 0;
		for (int j = 0; j < c.length; j++) {
			while (c[j] == ' ') {
				j++;
				if (j == c.length)
					break;
			}
			if (j == c.length)
				break;
			i = j;
			while (c[j] != ' ') {
				j++;
				if (j == c.length)
					break;
			}
			reverse(c, i, j - 1);
		}

		String res = "";
		for (int j = 0; j < c.length; j++) {
			res += c[j];
		}
		return res;
	}

	public char[] reverse(char[] c, int i, int j) {
		while (i < j) {
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
			i++;
			j--;
		}

		return c;
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		Set<List<Integer>> hs = new HashSet<List<Integer>>();
		hs.add(new ArrayList<Integer>());
		recursive(nums, 0, new ArrayList<Integer>(), hs);
		list.addAll(hs);
		return list;
	}

	public void recursive(int nums[], int index, List<Integer> temp,
			Set<List<Integer>> hs) {
		for (int i = index; i < nums.length; i++) {
			List<Integer> newtemp = new ArrayList<Integer>();
			newtemp.addAll(temp);
			newtemp.add(nums[i]);
			hs.add(newtemp);
			recursive(nums, i + 1, newtemp, hs);
		}
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> l = new ArrayList<String>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 3; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 2; j++) {
				for (int k = j + 1; k < j + 4 && k < len - 1; k++) {
					String s1 = s.substring(0, i);
					String s2 = s.substring(i, j);
					String s3 = s.substring(j, k);
					String s4 = s.substring(k, len);
					if (isValid(s1) && isValid(s2) && isValid(s3)
							&& isValid(s4))
						l.add(s1 + "." + s2 + "." + s3 + "." + s4);
				}
			}
		}

		return l;

	}

	public boolean isValid(String s) {
		if (s.length() > 3 || s.length() == 0 || s.charAt(0) == '0'
				|| Integer.valueOf(s) > 255)
			return false;

		return true;

	}

	public int maxProduct(int[] nums) {
		if (nums.length == 0)
			return 0;

		int max = nums[0];
		int min = nums[0];
		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int temp = max;
			max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);

			min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

			if (max > result)
				result = max;

		}

		return result;

	}

	public int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			if (nums[left] < nums[right])
				return nums[left];
			else {
				int mid = (left + right) / 2;
				if (nums[mid] < nums[right])
					right = mid;
				else
					left = mid + 1;
			}
		}
		return nums[left];
	}

	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	List<String> l1 = new ArrayList<String>();

	public List<String> generateParenthesis(int n) {
		if (n == 0)
			return l1;
		else
			dfs(n, n, "");

		return l1;
	}

	public void dfs(int left, int right, String temp) {
		if (left == 0 && right == 0) {
			l1.add(temp);
			return;
		}

		if (left == 0) {
			dfs(left, right - 1, temp + ")");
		} else if (left == right) {
			dfs(left - 1, right, temp + "(");
		} else if (left < right) {
			dfs(left - 1, right, temp + "(");
			dfs(left, right - 1, temp + ")");
		} else
			return;

	}

	public int findPeakElement(int[] nums) {
		return bs(nums, 0, nums.length - 1);
	}

	public int bs(int[] nums, int left, int right) {
		if (left == right)
			return nums[right];
		else if (right - left == 1)
			return (nums[left] > nums[right]) ? left : right;
		else {
			int mid = (left + right) / 2;

			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
				return mid;
			else if (nums[mid] < nums[mid - 1] && nums[mid] > nums[mid + 1])
				return bs(nums, left, mid);
			else
				return bs(nums, mid, right);

		}

	}

	public String longestPalindrome(String s) {
		if (s.length() == 0)
			return "";
		char[] c = s.toCharArray();
		int max_left = 0;
		int max_right = 0;

		for (int i = 0; i < s.length(); i++) {
			for (int j = s.length() - 1; j > i; j--) {
				if (j - i < max_right - max_left)
					break;
				int left = i;
				int right = j;
				while (right > left && c[right] == c[left]) {
					right--;
					left++;
				}

				if (left >= right && j - i > max_right - max_left) {
					System.out.println("0000");
					max_left = j;
					max_right = i;
				}
			}
		}
		String res = "";
		for (int i = max_left; i <= max_right; i++) {
			res += c[i];
		}
		return res;
	}

	public int maxProfit1(int[] prices) {
		int len = prices.length;
		int buy = 0;
		int sell = 0;
		int profit = 0;
		while (buy < len && sell < len) {
			while (buy + 1 < len && prices[buy] > prices[buy + 1]) {
				buy++;
			}

			sell = buy + 1;

			while (sell + 1 < len && prices[sell] < prices[sell + 1]) {
				sell++;
			}

			profit += prices[sell] - prices[buy];

			buy = sell + 1;
		}

		return profit;
	}

	public int maxProfit(int[] prices) {
		if (prices.length == 0)
			return 0;

		// int[] dp = new int[prices.length];
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - prices[i - 1] > max) {
				max = prices[i] - prices[i - 1];
			}

			if (prices[i] > prices[i - 1]) {
				prices[i] = prices[i - 1];
			}
		}

		return max;

	}

	public int threeSumClosest(int[] nums, int target) {
		if (nums.length == 0)
			return 0;
		Arrays.sort(nums);
		int minGap = Integer.MAX_VALUE;
		int res = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				int sum = nums[i] + nums[j] + nums[k];

				if (Math.abs(sum - target) < minGap) {
					minGap = Math.abs(sum - target);
					res = sum;
				}

				if (sum > target) {
					k--;
				} else {
					j++;
				}

			}
		}

		return res;
	}

	public boolean isPal(int i, int j, char[] c) {
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

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				if (nums[i] + nums[j] + nums[k] == 0) {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					temp.add(nums[k]);
					list.add(temp);
					j++;
					k--;
				} else if (nums[i] + nums[j] + nums[k] > 0)
					k--;
				else
					j++;
			}
		}

		HashSet hs = new HashSet();
		hs.addAll(list);
		list.clear();
		list.addAll(hs);
		return list;
	}

	public boolean isIsomorphic(String s, String t) {
		Map<Character, String> hs1 = new HashMap<Character, String>();
		Map<Character, String> hs2 = new HashMap<Character, String>();
		char[] c1 = s.toCharArray();
		char[] c2 = t.toCharArray();
		for (int i = 0; i < c1.length; i++) {
			if (!hs1.containsKey(c1[i]))
				hs1.put(c1[i], String.valueOf(i));
			else
				hs1.put(c1[i], hs1.get(c1[i]) + i);

			if (!hs2.containsKey(c2[i]))
				hs2.put(c2[i], String.valueOf(i));
			else
				hs2.put(c2[i], hs2.get(c2[i]) + i);

			System.out.println(hs1.get(c1[i]) + " " + hs2.get(c2[i]));
			if (!hs1.get(c1[i]).equals(hs2.get(c2[i])))
				return false;
		}
		return true;
	}

	public int findNonRepeatedInt(int[] intArray) {
		if (intArray.length == 0)
			return 0;

		int[] bucket = new int[Integer.MAX_VALUE - 1];

		for (int i = 0; i < intArray.length; i++) {
			bucket[intArray[i]]++;
		}

		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] == 1)
				return i;
		}

		IllegalStateException e = new IllegalStateException();
		throw e;

	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int i = 0;
		int j = gas.length;
		int sum = gas[0] - cost[0];

		if (j > 1) {
			do {
				if (sum >= 0) {
					i++;
					if (i == j)
						break;
					sum += gas[i] - cost[i];
				} else {
					j--;
					if (i == j)
						break;
					sum += gas[j] - cost[j];
				}
			} while (true);
		}
		return sum >= 0 ? j % gas.length : -1;
	}

	public int numOfk1(int[] num) {
		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			if (num.length - i <= num[i])
				return num.length - i;
		}
		return 0;

	}

	public int numOfk(int[] num) {
		int[] buck = new int[num.length + 1];

		for (int i = 0; i < num.length; i++) {
			if (num[i] > num.length - 1)
				buck[num.length]++;
			else {
				buck[num[i]]++;
			}
		}
		int count = 0;
		for (int i = buck.length - 1; i >= 0; i--) {
			count += buck[i];
			if (i <= count)
				return i;
		}
		return 0;
	}

	// List<List<Integer>> l = new ArrayList<List<Integer>>();
	//
	// public List<List<Integer>> combinationSum(int[] candidates, int target) {
	// Arrays.sort(candidates);
	// ArrayList<Integer> temp = new ArrayList<Integer>();
	// dfs(candidates, temp, 0, target);
	// return l;
	// }
	//
	// public void dfs(int[] candidates, ArrayList<Integer> temp, int index,
	// int target) {
	// if (target > 0) {
	// for (int i = index; i < candidates.length
	// && target >= candidates[i]; i++) {
	// temp.add(candidates[i]);
	// dfs(candidates, temp, i, target - candidates[i]);
	// temp.remove(temp.size() - 1);
	// }
	//
	// } else if (target == 0) {
	// l.add(new ArrayList<Integer>(temp));
	// }
	//
	// }

	public void sortColors(int[] nums) {
		if (nums.length == 0)
			return;
		int l = 0, i = 0;
		int r = nums.length - 1;

		while (i <= r) {
			if (nums[i] == 2) {
				nums[i] = nums[r];
				nums[r--] = 2;
			} else if (nums[i] == 0) {
				nums[i++] = nums[l];
				nums[l++] = 0;
			} else {
				i++;
			}
		}
		for (int j = 0; j < nums.length; j++) {
			System.out.println(nums[j]);
		}

	}

	public int searchInsert(int[] A, int target) {
		if (target < A[0])
			return 0;
		else if (target > A[A.length - 1])
			return A.length;

		int left = 0;
		int right = A.length - 1;
		int mid = (left + right) / 2;

		while (left <= right) {
			if (A[mid] < target)
				left = mid + 1;
			else if (A[mid] > target)
				right = mid - 1;
			else
				return mid;

			mid = (left + right) / 2;
		}
		System.out.println(mid);
		return mid + 1;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null)
			return false;

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][matrix[0].length - 1] >= target) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] == target) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public int[] searchRange(int[] A, int target) {
		int[] res = new int[2];
		if (target < A[0] || target > A[A.length - 1] || A.length == 0) {
			res[0] = -1;
			res[1] = -1;
			return res;
		}
		int i = 0;
		int j = A.length - 1;
		int point = (i + j) / 2;
		while (A[point] != target) {
			if (A[point] < target) {
				i = point - 1;
				point = (i + j) / 2;
			}
			if (A[point] > target) {
				j = point + 1;
				point = (i + j) / 2;
			}
		}

		int temp = point;
		while (temp >= 0 && A[temp] == target) {
			temp--;
		}
		res[0] = temp + 1;
		temp = point;
		while (temp <= A.length - 1 && A[temp] == target) {
			temp++;
		}
		res[1] = temp - 1;
		return res;
	}

	public void setZeroes(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				for (int i = 0; i < matrix.length; i++) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			hm.put(numbers[i], i);
		}

		int[] res = new int[2];

		for (int i = 0; i < numbers.length; i++) {
			Integer secondpart = target - numbers[i];
			if (hm.containsKey(secondpart) && numbers[i] < target - numbers[i]) {
				res[0] = i;
				res[1] = hm.get(target - numbers[i]);
				return res;
			}
		}

		return res;

	}

	public int majorityElement(int[] num) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length; i++) {
			if (hm.get(num[i]) == null) {
				hm.put(num[i], 1);
			} else {
				hm.put(num[i], hm.get(num[i]) + 1);
				if (hm.get(num[i]) > num.length / 2)
					return num[i];
			}
		}
		return 0;
	}

	// public List<String> generateParenthesis(int n) {
	// if (n == 0) {
	// l.add("");
	// }
	// if (n == 1) {
	// l.add("()");
	// } else {
	// String temp = "";
	// dfs(n, n, temp);
	// }
	//
	// return l;
	// }

	// public void dfs(int leftnum, int rightnum, String temp) {
	// if (leftnum == 0 && rightnum == 0) {
	// l.add(temp);
	// return;
	//
	// }
	// if (leftnum == 0) {
	// dfs(leftnum, rightnum - 1, temp + ")");
	// } else if (leftnum < rightnum) {
	// dfs(leftnum, rightnum - 1, temp + ")");
	// dfs(leftnum - 1, rightnum, temp + "(");
	// } else if (leftnum == rightnum) {
	// dfs(leftnum - 1, rightnum, temp + "(");
	// } else {
	// return;
	// }
	// }

	static List<List<Integer>> l2 = new ArrayList<List<Integer>>();

	public static List<List<Integer>> combine(int n, int k) {

		if (k == 0 || k > n || n == 0)
			return l2;
		List<Integer> temp = new ArrayList<Integer>();
		dfs(temp, 1, n, k);
		return l2;
	}

	public static void dfs(List<Integer> temp, int i, int n, int k) {
		if (k == 0) {
			l2.add(temp);
			return;
		}
		for (int j = i; j <= n; j++) {
			List<Integer> temp2 = new ArrayList<Integer>();
			temp2.addAll(temp);
			temp2.add(j);
			dfs(temp2, j + 1, n, k - 1);
		}

	}

	public static List<String> findRepeatedDnaSequences(String s) {
		List<String> l = new ArrayList<String>();
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		for (int i = 0; i < s.length() - 10; i++) {
			String temp = s.substring(i, i + 10);
			if (hs.containsKey(temp)) {
				hs.put(temp, hs.get(temp) + 1);
			} else {
				hs.put(temp, 1);
			}
		}

		Iterator it = hs.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (hs.get(key) > 1) {
				l.add(key);
			}
		}

		return l;

	}

	public static String longestCommonPrefix(List<String> strs) {
		int index = strs.get(0).length();
		for (int i = 1; i < strs.size(); i++) {
			if (strs.get(i).equals(""))
				return "";
			for (int j = 0; j < index; j++) {
				if (j == strs.get(i).length()
						|| strs.get(0).charAt(j) != strs.get(i).charAt(j)) {
					index = j;
					break;
				}
			}
		}

		String prefix = strs.get(0).substring(0, index);
		return prefix;
	}

	public static String reverseWords(String s) {
		if (s.equals(""))
			return "";
		s = s.trim();
		String[] sa = s.split(" ");
		if (sa.length == 0)
			return " ";
		StringBuffer res = new StringBuffer();
		for (int i = sa.length - 1; i > 0; i--) {
			if (!sa[i].equals("")) {
				res.append(sa[i]);
				res.append(" ");
			}
		}
		res.append(sa[0]);
		return res.toString();
	}

	public static int minDistance(String word1, String word2) {
		int l1 = word1.length();
		int l2 = word2.length();
		int l = (l1 > l2) ? l2 : l1;
		int count = 0;
		for (int i = 0; i < l; i++) {
			if (word1.charAt(i) != word2.charAt(i))
				count++;
		}

		count += Math.abs(l1 - l2);
		return count;

	}
}
