import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountAndSay {
	public static void main(String[] args) {
		CountAndSay ca = new CountAndSay();
		// System.out.println(6 ^ 7);
		int[] A = { 1, 2, 3 };
		String s = "asdfghgfdsui";
		System.out.println(new CountAndSay().longestPalindrome(s));
	}

	public List<List<Integer>> subsets(int[] S) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (S.length == 0)
			return res;
		Arrays.sort(S);
		dfs(S, 0, new ArrayList<Integer>(), res);
		return res;
	}

	public String longestPalindrome(String s) {
	        int len = s.length();
			char[] c = s.toCharArray();
			int maxl = 0;
			int maxr = 0;

			for (int i = 0; i < len; i++) {
				for (int j = len - 1; j >= i; j--) {
					int left = i;
					int right = j;
					while (left < right && c[left] == c[right]) {
						left++;
						right--;
					}

					if (left >= right) {
						if (j - i > maxr - maxl) {
							maxr = j;
							maxl = i;
						}
					}
				}
			}
	        String res = "";
	        for (int i = maxl; i <= maxr; i++)
	        {
	            res+=c[i];
	        }

			return res;
	}

	public void dfs(int[] s, int index, List<Integer> temp,
			List<List<Integer>> res) {
		res.add(new ArrayList<Integer>(temp));
		if (index == s.length)
			return;

		for (int i = index; i < s.length; i++) {
			temp.add(s[i]);
			dfs(s, i + 1, temp, res);
			temp.remove(temp.size() - 1);
		}
	}

	public boolean wordBreak(String s, Set<String> wordDict) {
		if (s.length() == 0)
			return true;

		for (int i = 1; i < s.length(); i++) {
			if (wordDict.contains(s.substring(0, i))
					&& wordBreak(s.substring(i, s.length()), wordDict))
				return true;
		}

		return false;
	}

	public static int countPrimes(int n) {
		if (n < 2)
			return 0;
		int[] a = new int[n];
		a[0] = 1;
		a[1] = 1;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (a[i] != 1) {
				for (int j = i * 2; j < n; j += i) {
					a[j] = 1;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] == 0)
				count++;
		}
		return count;

	}

	public static boolean isHappy(int n) {
		HashSet<Integer> hs = new HashSet<Integer>();

		while (true) {
			if (n == 1)
				return true;
			if (hs.contains(n))
				return false;
			else
				hs.add(n);
			String s = String.valueOf(n);
			n = 0;
			char[] c = s.toCharArray();
			for (char num : c) {
				n += (num - '0') * (num - '0');
			}
		}

	}

	public int sqrt(int x) {
		if (x > 0) {
			int left = 1;
			int right = 46341;
			while (true) {
				int half = (left + right) / 2;
				if (half * half == x) {
					return half;
				} else if (half * half > x) {
					if (right - left == 1)
						return right;
					right = half;
				} else if (half * half < x) {
					if (right - left == 1)
						return left;
					left = half;
				}
			}

		}
		return 0;
	}

	public int singleNumber(int[] A) {
		int result = 0;
		for (int i : A) {
			result ^= i;
			System.out.println(i + " " + result);
		}
		return result;
	}

	public String countAndSay(int n) {
		String output = "1";

		for (int i = 0; i < n; i++) {
			String temp = "";
			int time = 0;
			for (int j = 0; j < output.length(); j++) {
				if (j == 0 || output.charAt(j) == output.charAt(j - 1)) {
					time++;
				} else {
					temp += time;
					temp += output.charAt(j - 1);
					time = 1;
				}

			}
			temp += time;
			temp += output.charAt(output.length() - 1);
			output = temp;
		}

		return output;

	}

	public static String countAndSay2(int n) {
		if (n == 0)
			return "0";
		String s = "1";
		for (int i = 1; i < n; i++) {

			char[] c = s.toCharArray();
			s = "";
			for (int j = 0; j < c.length;) {
				int count = 0;
				do {
					count++;
					j++;
				} while (j < c.length && c[j] == c[j - 1]);
				s += count + "" + (c[j - 1] - '0');
			}
		}
		return s;
	}
}
