import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayQ {
	public static void main(String[] args) {

		// LinkedList<Integer> q = new LinkedList<Integer>();
		// int i = q.pollLast();
		// q.addFirst(i);
		// char[][] m = {{'B','B','B','A','B','B'},
		// {'B','A','B','B','A','A'},
		// {'B','A','B','A','A','A'},
		// {'A','A','A','A','B','A'},
		// {'A','A','B','B','B','A'},
		// {'A','A','B','B','A','A'}
		// };
		Set<String> s = new HashSet<String>();
		s.add("aa");
		s.add("ba");
		s.add("bc");
		// char[][] m = { { 'C', 'A','A' }, { 'A', 'A','A' },{'B', 'C', 'D'}};
		int[] a = { 2, 1 };
		System.out.println(new ArrayQ().candy(a));

		// System.out.println(maxArea(a));
	}

	public String minWindow(String s, String t) {
		if(t.length()>s.length())return "";
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();
		Set<Character> hs1 = new HashSet<Character>();
		for(char c : tc)hs1.add(c);
		Set<Character> hs2 = new HashSet<Character>();
		
		int f = 0,se =0,size=0,maxf=0,maxs=0;
		while(true){
			
		}
 	}

	public int candy(int[] r) {
		if (r.length == 0)
			return 0;
		int[] c = new int[r.length];
		Arrays.fill(c, 1);
		for (int i = 1; i < r.length; i++) {
			if (r[i] > r[i - 1])
				c[i] = c[i - 1] + 1;
		}

		for (int i = r.length - 2; i >= 0; i--) {
			if (r[i] > r[i + 1]) {
				if (i - 1 >= 0 && r[i] >= r[i - 1])
					c[i] = Math.max(c[i], c[i + 1] + 1);
				else
					c[i] = c[i + 1] + 1;
			}
		}
		int total = 0;
		for (int i : c) {
			total += i;
		}

		return total;
	}

	public int numTrees(int n) {
		int[] res = new int[n + 1];
		res[0] = res[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				res[i] += res[j - 1] * res[i - j];
			}
		}
		return res[n];
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		if (nums.length < 4)
			return l;
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {
			int sum3 = target - nums[i];
			for (int j = i + 1; j < nums.length - 2; j++) {
				int n3 = j + 1;
				int n4 = nums.length - 1;
				while (n3 < n4) {
					List<Integer> temp = new ArrayList<Integer>();
					int sum = nums[i] + nums[j] + nums[n3] + nums[n4];
					if (sum == target) {
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[n3]);
						temp.add(nums[n4]);
						l.add(temp);
						n4--;
						n3++;
						while (n3 < n4 && nums[n4] == nums[n4 + 1]) {
							n4--;
						}
						while (n3 < n4 && nums[n3] == nums[n3 - 1]) {
							n3++;
						}
					} else if (sum3 > target)
						n4--;
					else
						n3++;
				}
			}
		}
		Set<List<Integer>> hs = new HashSet<List<Integer>>();
		hs.addAll(l);
		l.clear();
		l.addAll(hs);
		return l;
	}

	public int longestConsecutive(int[] nums) {
		int max = 0;
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			int left = 0;
			int right = 0;
			if (!hm.containsKey(nums[i])) {
				hm.put(nums[i], 1);
				if (hm.containsKey(nums[i] + 1)) {
					int right_value = hm.get(nums[i] + 1);
					hm.put(nums[i], hm.get(nums[i]) + right_value);
					right = hm.get(nums[i] + 1);
				}

				if (hm.containsKey(nums[i] - 1)) {
					int left_value = hm.get(nums[i] - 1);
					hm.put(nums[i], hm.get(nums[i]) + left_value);
					left = hm.get(nums[i] - 1);
				}

			}

			hm.put(nums[i] - left, hm.get(nums[i]));
			hm.put(nums[i] + right, hm.get(nums[i]));
			System.out.println(hm);
			max = Math.max(max, hm.get(nums[i]));

		}

		return max;
	}

	public boolean isIsomorphic(String s, String t) {
		char[] c1 = s.toCharArray();
		char[] c2 = t.toCharArray();
		Map<Character, Character> hm = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			if (hm.containsKey(c1[i])) {
				if (hm.get(c1[i]) != c2[i])
					return false;
			} else {
				if (hm.containsKey(c2[i]))
					return false;
				hm.put(c1[i], c2[i]);
			}
		}

		return true;
	}

	public int rob(int[] nums) {
		int len = nums.length;
		if (len == 0)
			return 0;
		if (len == 1)
			return nums[0];
		if (len == 2)
			if (len == 2)
				return Math.max(nums[0], nums[1]);

		int[] a1 = Arrays.copyOfRange(nums, 0, len - 1);
		int[] a2 = Arrays.copyOfRange(nums, 1, len);

		int max1 = findMax(a1);
		int max2 = findMax(a2);

		return Math.max(max1, max2);
	}

	public int findMax(int[] num) {
		if (num.length == 0)
			return 0;
		if (num.length == 1)
			return num[0];
		int[] max = new int[num.length];

		max[0] = num[0];
		if (num[1] > max[0])
			max[1] = num[1];
		else
			max[1] = num[0];
		for (int i = 2; i < num.length; i++) {
			if (num[i] + max[i - 2] < max[i - 1])
				max[i] = max[i - 1];
			else
				max[i] = num[i] + max[i - 2];
		}

		return max[num.length - 1];
	}

	public boolean isHappy(int n) {
		if (n == 0)
			return false;
		Set<Integer> hs = new HashSet<Integer>();

		while (n != 1) {
			if (hs.contains(n))
				return false;
			hs.add(n);
			int temp = 0;
			while (n != 0) {
				temp += (n % 10) * (n % 10);
				n = n / 10;
			}
			n = temp;
		}

		return true;
	}

	public boolean exist(char[][] board, String word) {
		if (word.length() == 0)
			return true;
		char[] ca = word.toCharArray();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.println(board[i][j]);
				if (board[i][j] == 'A') {
					// System.out.println(check(board,i,j,0,ca));
					if (check(board, i, j, 0, ca))
						return true;
				}
			}
		}
		return false;

	}

	public int ladderLength(String start, String end, Set<String> dict) {
		HashMap<String, Integer> al = new HashMap<String, Integer>();
		al.put(start, 1);
		for (String key : al.keySet()) {
			for (String s : dict) {
				if (transferable(key, s) && !al.containsKey(s)) {
					if (s == end)
						return al.get(key) + 1;
					al.put(s, al.get(key) + 1);
				}
			}
		}
		return 0;
	}

	public boolean transferable(String s1, String s2) {
		int dif = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				dif++;
			}
		}
		if (dif == 1)
			return true;
		else
			return false;
	}

	public boolean check(char[][] board, int row, int col, int index, char[] ca) {
		if (row > board.length - 1 || col > board[0].length - 1 || row < 0
				|| col < 0 || board[row][col] != ca[index]
				|| board[row][col] == '#')
			return false;
		if (index == ca.length - 1)
			return true;
		char temp = board[row][col];
		board[row][col] = '#';
		index++;
		boolean exsit = check(board, row + 1, col, index, ca)
				|| check(board, row - 1, col, index, ca)
				|| check(board, row, col + 1, index, ca)
				|| check(board, row, col - 1, index, ca);
		board[row][col] = temp;
		return exsit;
	}

	public static List<Integer> spiralOrder(int[][] m) {
		List<Integer> l = new ArrayList<Integer>();
		int h = m.length;
		if (h == 0)
			return l;
		int w = m[0].length;

		int[][] n = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				n[i][j] = 0;
		}

		int time = h * w;
		int d = 0;
		int row = 0;
		int col = 0;
		while (time > 0) {
			l.add(m[row][col]);
			n[row][col] = 1;
			if (d == 0) {
				if (col + 1 > w - 1 || n[row][col + 1] == 1) {
					d = (d + 1) % 4;
					row++;
					time--;
				} else {
					col++;
					time--;
				}
			} else if (d == 1) {
				if (row + 1 > h - 1 || n[row + 1][col] == 1) {
					d = (d + 1) % 4;
					col--;
					time--;
				} else {
					row++;
					time--;
				}
			} else if (d == 2) {
				if (col - 1 < 0 || n[row][col - 1] == 1) {
					d = (d + 1) % 4;
					row--;
					time--;
				} else {
					col--;
					time--;
				}
			} else if (d == 3) {
				if (row - 1 < 0 || n[row - 1][col] == 1) {
					d = (d + 1) % 4;
					col++;
					time--;
				} else {
					row--;
					time--;
				}
			}
		}
		return l;
	}

	public static int[][] generateMatrix(int n) {
		int[][] m = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				m[i][j] = 0;
		}
		int time = 1;
		int d = 0;
		int row = 0;
		int col = 0;

		while (time <= n * n) {
			if (d == 0) {
				if (col + 1 > n - 1 || m[row][col + 1] != 0) {
					m[row][col] = time;
					d = (d + 1) % 4;
					row++;
					time++;
				} else {
					m[row][col] = time;
					col++;
					time++;
				}
			} else if (d == 1) {
				if (row + 1 > n - 1 || m[row + 1][col] != 0) {
					m[row][col] = time;
					d = (d + 1) % 4;
					col--;
					time++;
				} else {
					m[row][col] = time;
					row++;
					time++;
				}
			} else if (d == 2) {
				if (col - 1 < 0 || m[row][col - 1] != 0) {
					m[row][col] = time;
					d = (d + 1) % 4;
					row--;
					time++;
				} else {
					m[row][col] = time;
					col--;
					time++;
				}
			} else if (d == 3) {
				if (row - 1 < 0 || m[row - 1][col] != 0) {
					m[row][col] = time;
					d = (d + 1) % 4;
					col++;
					time++;
				} else {
					m[row][col] = time;
					row--;
					time++;
				}
			}
		}

		return m;
	}

	public static int maxArea(int[] height) {
		if (height.length == 0)
			return 0;

		int left = 0;
		int right = height.length - 1;
		int max = area(left, right, height);
		while (left != right) {
			if (height[left] > height[right])
				right--;
			else
				left++;
			if (max < area(left, right, height)) {
				max = area(left, right, height);
			}
		}

		return max;

	}

	public static int area(int left, int right, int[] height) {
		int len = right - left;
		int he = height[left] > height[right] ? height[left] : height[right];
		return len * he;
	}

	public static int[] plusOne(int[] digits) {
		int add = 1;
		for (int i = 0; i < digits.length; i++) {
			if (digits[i] != 9) {
				add = 0;
				break;
			}
		}

		if (add == 1) {
			int[] newd = new int[digits.length + 1];
			newd[0] = 1;
			for (int i = 1; i < digits.length + 1; i++) {
				newd[i] = 0;
			}
			return newd;
		} else {
			int addone = 1;
			for (int i = digits.length - 1; i >= 0; i--) {
				if (digits[i] == 9 && addone == 1) {
					digits[i] = 0;
				} else {
					if (addone == 1) {
						digits[i] = digits[i] + 1;
						addone = 0;
					}
				}
			}

			return digits;
		}

	}

	public static String countAndSay(int n) {
		String res = "1";
		for (int i = 1; i < n; i++) {
			String temp = "";
			int count = 1;
			for (int j = 1; j < res.length(); j++) {
				if (res.charAt(j) != res.charAt(j - 1)) {
					temp += count;
					temp += res.charAt(j - 1);
					count = 1;
				} else {
					count++;
				}
			}
			temp += count;
			temp += res.charAt(res.length() - 1);
			temp = res;
		}
		return res;
	}

	public static void cd(int[] A, int[] B) {
		HashSet<Integer> hs1 = new HashSet<Integer>();
		HashSet<Integer> hs2 = new HashSet<Integer>();
		HashSet<Integer> hs3 = new HashSet<Integer>();
		ArrayList<Integer> common = new ArrayList<Integer>();
		ArrayList<Integer> dif = new ArrayList<Integer>();

		for (int i = 0; i < A.length; i++) {
			hs1.add(A[i]);
		}

		for (int i = 0; i < B.length; i++) {
			hs2.add(B[i]);
		}

		for (int i = 0; i < A.length; i++) {
			if (hs2.contains(A[i]))
				common.add(A[i]);
			else
				dif.add(A[i]);
		}

		for (int i = 0; i < B.length; i++) {
			if (!hs1.contains(B[i]))
				dif.add(B[i]);
		}

		System.out.println(common.toString());
		System.out.println(dif.toString());

	}

	public static int reverse(int x) {
		long num = Math.abs((long) x);
		long newnum = 0;
		while (num != 0) {
			newnum = newnum * 10 + num % 10;
			num /= 10;
		}

		if (newnum > Integer.MAX_VALUE) {
			return 0;
		}

		return (int) (x < 0 ? newnum * -1 : newnum);
	}

	public static int searchInsert(int[] A, int target) {
		if (A.length == 0)
			return 0;
		if (target < A[0])
			return 0;
		if (target > A[A.length - 1])
			return A.length;

		int left = 0;
		int right = A.length - 1;
		int mid = (left + right) / 2;
		while (left < right) {
			if (A[mid] < target) {
				left = mid + 1;
			} else if (A[mid] > target) {
				right = mid;
			} else {
				return mid;
			}
			mid = (left + right) / 2;
		}
		return left;
	}

	public static int[] searchRange(int[] A, int target) {
		int[] res = new int[2];
		if (A.length == 0 && A[0] > target && A[A.length - 1] < target) {
			res[0] = -1;
			res[1] = -1;
			return res;
		}
		int left = 0;
		int right = A.length - 1;
		int mid = (left + right) / 2;
		while (left <= right) {
			if (A[mid] < target)
				left = mid + 1;
			else if (A[mid] == target) {
				break;
			} else
				right = mid - 1;

			mid = (right + left) / 2;
		}

		if (left > right) {
			res[0] = -1;
			res[1] = -1;
			return res;
		}

		left = mid;
		right = mid;

		do {
			left--;
		} while (left >= 0 && A[left] == target);
		res[0] = left + 1;

		do {
			right++;
		} while (right < A.length && A[right] == target);
		res[1] = right - 1;

		return res;
	}

	public static int romanToInt(String s) {
		int length = s.length();
		char[] ca = s.toCharArray();
		int prev;
		int next;
		int result = 0;
		result += romanCharToInt(ca[length - 1]);

		for (int i = 0; i < length - 1; i++) {
			prev = romanCharToInt(ca[i]);
			next = romanCharToInt(ca[i + 1]);
			if (next > prev) {
				result -= prev;
			} else {
				result += prev;
			}
		}

		return result;

	}

	public static int romanCharToInt(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		default:
			return 0;
		}
	}

	public static int maxProduct(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int max = A[0];
		int min = A[0];
		int result = A[0];
		for (int i = 1; i < A.length; i++) {
			int temp = max;
			max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
			min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
			if (max > result) {
				result = max;
			}
		}
		return result;
	}

	static List<List<Integer>> l = new ArrayList<List<Integer>>();

	public static List<List<Integer>> combine(int n, int k) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		getList(temp, 1, n, k);
		return l;
	}

	public static void getList(ArrayList<Integer> temp, int start, int n, int k) {
		if (k == 0) {
			l.add(temp);
			return;
		}

		for (int i = start; i <= n; i++) {
			ArrayList<Integer> d = new ArrayList<Integer>();
			for (int s = 0; s < temp.size(); s++) {
				d.add(temp.get(s));
			}
			d.add(i);
			getList(d, i + 1, n, k - 1);
		}
	}

	public static void setZeroes(int[][] matrix) {
		int row = -1;
		int col = -1;
		if (matrix == null)
			return;
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					if (i == 0)
						row = 0;
					if (j == 0)
						col = 0;
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		// col
		for (int i = 1; i < m; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < n; j++)
					matrix[i][j] = 0;
			}
		}
		// row
		for (int i = 1; i < n; i++) {
			if (matrix[0][i] == 0) {
				for (int j = 1; j < m; j++)
					matrix[j][i] = 0;
			}
		}

		if (row == 0) {
			for (int j = 0; j < n; j++)
				matrix[0][j] = 0;
		}

		if (col == 0) {
			for (int j = 0; j < m; j++)
				matrix[j][0] = 0;
		}

	}

	public static List<Integer> getRow(int rowIndex) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; i++) {
			if (i == 0 || i == 1) {
				l.add(1);
			} else {
				int temp;
				for (int j = 1; j < i; j++) {
					temp = l.get(j);
					l.set(j, temp + l.get(j - 1));

				}
				l.add(1);
			}

		}
		return l;
	}

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return l;
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(1);
		l.add(l3);

		for (int i = 1; i < numRows; i++) {
			List<Integer> l2 = new ArrayList<Integer>();
			l2.add(1);
			for (int j = 1; j < i; j++) {
				l2.add(l.get(i - 1).get(j) + l.get(i - 1).get(j - 1));
			}
			l2.add(1);
			l.add(l2);
		}
		return l;
	}

	public static List<List<Integer>> threeSum(int[] num, int target) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		if (num.length < 4)
			return l;

		Arrays.sort(num);

		int len = num.length;

		for (int j = 0; j < num.length - 3; j++) {
			int tsum3 = target - num[j];
			for (int i = 0; i < num.length - 2 - j; i++) {
				int n1 = i;
				int n2 = i + 1;
				int n3 = len - 1;
				List<Integer> l2 = new ArrayList<Integer>();
				while (n2 < n3) {
					int sum = num[n1] + num[n2] + num[n3];
					if (sum == 0) {
						l2.add(num[j]);
						l2.add(num[n1]);
						l2.add(num[n2]);
						l2.add(num[n3]);
						l.add(l2);
						n3--;
						n2++;
						while (n2 < n3 && num[n3] == num[n3 + 1]) {
							n3--;
						}
						while (n2 < n3 && num[n2] == num[n2 - 1]) {
							n2++;
						}
					} else if (sum > 0) {
						n3--;
					} else {
						n2++;

					}
				}
			}
		}

		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		hs.addAll(l);
		l.clear();
		l.addAll(hs);
		return l;
	}

	public static void rotate(int[][] m) {
		int temp;
		int box;
		for (int i = 0; i < (m.length) / 2; i++) {
			int row = i;
			int col = i;
			temp = m[i][i];
			for (int j = 0; j < m.length - 1; j++) {
				int time = 4;
				while (time-- > 0) {
					box = m[col][(row + m.length - 1) % m.length];
					m[col][(row + m.length - 1) % m.length] = temp;
					temp = box;

					int temprow = row;
					row = col;
					col = (temprow + m.length - 2) % m.length;
				}

			}
		}
		System.out.println("!!");
	}

	public static int uniquePathsWithObstacles(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		if (grid.length == 1) {
			for (int i = grid[0].length - 1; i >= 0; i--) {
				if (grid[0][i] == 1)
					return 0;
			}
			return 1;
		}

		if (grid[0].length == 1) {
			for (int i = grid.length - 1; i >= 0; i--) {
				if (grid[i][0] == 1)
					return 0;
			}
			return 1;
		}

		if (grid[grid.length - 1][grid[0].length - 1] == 1)
			return 0;
		else
			grid[grid.length - 1][grid[0].length - 1] = 1;

		boolean haveway = true;
		for (int i = grid.length - 2; i >= 0; i--) {
			if (grid[i][grid[0].length - 1] == 0 && haveway == true)
				grid[i][grid[0].length - 1] = 1;
			else {
				haveway = false;
				grid[i][grid[0].length - 1] = 0;
			}

		}
		haveway = true;
		for (int i = grid[0].length - 2; i >= 0; i--) {
			if (grid[grid.length - 1][i] == 0 && haveway == true)
				grid[grid.length - 1][i] = 1;
			else {
				haveway = false;
				grid[grid.length - 1][i] = 0;
			}

		}

		for (int i = grid.length - 2; i >= 0; i--) {
			for (int j = grid[0].length - 2; j >= 0; j--) {
				if (grid[i][j] == 1)
					grid[i][j] = 0;
				else {
					int down = grid[i][j] + grid[i + 1][j];
					int right = grid[i][j] + grid[i][j + 1];
					grid[i][j] = (down < right) ? down : right;
				}

				int down = grid[i][j] + grid[i + 1][j];
				int right = grid[i][j] + grid[i][j + 1];
				grid[i][j] = (down > right) ? down : right;

			}
		}

		return grid[0][0];
	}

	public static int maxSubArray(int[] A) {
		if (A.length == 0)
			return 0;
		int max = A[0];
		int sum = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] > sum) {
				if (sum > 0) {
					max += A[i];
				} else {
					max = A[i];
					sum = A[i];
				}
			} else {
				sum += A[i];
				if (sum > max) {
					max = sum;
				}
			}

		}
		return max;
	}

	public static int hammingWeight(int n) {
		if (n == 0)
			return 0;

		int i = 0;
		while (n / 2 != 0) {
			if (n % 2 == 1)
				i++;
			n /= 2;
		}

		return i + 1;
	}

	public static void rotate(int[] nums, int k) {
		k = k % nums.length;
		if (k == 0)
			return;
		int time = nums.length;
		int index = 0;
		int box = nums[index];
		while (time > 0) {
			if (nums[(index + k) % nums.length] != box) {
				int temp = box;
				box = nums[(index + k) % nums.length];
				nums[(index + k) % nums.length] = temp;
				index = (index + k) % nums.length;
				time--;
			} else {
				index++;
				box = nums[index % nums.length];
			}
		}
	}

	public static int maxProfit2(int[] p) {
		int max = 0;

		for (int i = 1; i < p.length; i++) {
			if (p[i] - p[i - 1] > max) {
				max = p[i] - p[i - 1];
				p[i] = p[i - 1];
			} else {
				if (p[i] - p[i - 1] > 0)
					p[i] = p[i - 1];
			}
		}

		return max;
	}

	public static int singleNumber(int[] A) {
		Arrays.sort(A);
		int j = 1;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i + 1] != A[i]) {
				if (j != 3)
					return A[i];

				j = 1;
			} else {
				j++;
			}
		}
		return A[A.length - 1];
	}

	public static int uniquePaths(int m, int n) {
		int[][] a = new int[m][n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (i == m - 1 || j == n - 1) {
					a[i][j] = 1;
				} else {
					a[i][j] = a[i + 1][j] + a[i][j + 1];
				}
			}
		}

		return a[0][0];
	}

	public static int maxProfit(int[] p) {
		int profit = 0;
		int j = 0;
		int i = 1;
		int tempp = 0;
		while (i < p.length) {
			while (i < p.length && p[i] <= p[i - 1]) {
				i++;
			}

			j = i - 1;

			while (i < p.length && p[i] >= p[i - 1]) {
				i++;
				tempp = p[i - 1] - p[j];
			}
			profit += tempp;
			tempp = 0;
		}

		return profit;

	}
}

/*
 * 
 * ArrayList<Character> list = new ArrayList<Character>(); int center_x = x/3 *
 * 3 + 1; int center_y = y/3 * 3 + 1; boolean[] engaged = new boolean[9];
 * for(int i = -1;i <= 1;i++) for(int j = -1;j <= 1;j++)
 * if(board[center_x+i][center_y+j]!='.')
 * engaged[(int)(board[center_x+i][center_y+j]-'1')] = true; for(int i = 0;i <
 * 9;i++) if(board[i][y]!='.') engaged[(int)(board[i][y] - '1')] = true; for(int
 * j = 0;j < 9;j++) if(board[x][j]!='.') engaged[(int)(board[x][j] - '1')] =
 * true; for(int i = 0;i < engaged.length;i++) { char e = (char)('0' + (i+1));
 * if(!engaged[i]) list.add(e); }
 * 
 * return list;
 */