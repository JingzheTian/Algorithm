package sv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StringQ {
	public static void main(String[] args) {
		// String[] strs = { "cat", "cats", "and", "sand", "dog" };
		Set<String> wordDict = new HashSet<String>();
		// wordDict.add("cat");
		// wordDict.add("cats");
		// wordDict.add("and");
		// wordDict.add("sand");
		// wordDict.add("dog");

		wordDict.add("leet");
		wordDict.add("code");
		// wordDict.add("aaaa");

		String s = "aaaaa";

		System.out.println(new StringQ()
				.longestPalindrome2("asdfgfdsawertyuiopoiuytrew"));
	}
	
	String[][] keyBoard = { { " " }, { "" }, { "a", "b", "c" },
			{ "d", "e", "f" }, { "g", "h", "i" }, { "j", "k", "l" },
			{ "m", "n", "o" }, { "p", "q", "r", "s" }, { "t", "u", "v" },
			{ "w", "x", "y", "z" } };
    
    List<String> result = new ArrayList<String>();
	
	Set<String> unmatch = new HashSet<String>();
	
	public String add(String s1, String s2) {

		s1 = new StringBuffer(s1).reverse().toString();
		s2 = new StringBuffer(s2).reverse().toString();
		int add = 0;
		StringBuffer sb = new StringBuffer();

		int i = 0;
		while (i < Math.max(s1.length(), s2.length())) {
			int num = 0;
			if (i < s1.length() && i < s2.length()) {
				num = Integer.valueOf(String.valueOf(s1.charAt(i)))
						+ Integer.valueOf(String.valueOf(s2.charAt(i)));

			} else if (i < s1.length()) {
				num = Integer.valueOf(String.valueOf(s1.charAt(i)));

			} else if (i < s2.length()) {
				num = Integer.valueOf(String.valueOf(s2.charAt(i)));
			}
			if (add == 1) {
				num++;
			}
			if (num > 1) {
				sb.append(num % 2);
				add = 1;
			} else {
				add = 0;
				sb.append(num);
			}
			i++;
		}
		if (add == 1) {
			sb.append(1);
		}
		return sb.reverse().toString();
	}

	public List<String> anagrams(String[] strs) {
		Map<String, List<String>> hm = new HashMap<String, List<String>>();
		List<String> res = new ArrayList<String>();

		for (int i = 0; i < strs.length; i++) {
			char[] temp = strs[i].toCharArray();
			Arrays.sort(temp);
			String s = new String(temp);
			if (!hm.containsKey(s)) {
				List<String> l = new ArrayList<String>();
				l.add(strs[i]);
				hm.put(s, l);
			} else {
				List<String> l = hm.get(s);
				l.add(strs[i]);
				hm.put(s, l);
			}
		}
		for (String key : hm.keySet()) {
			if (hm.get(key).size() > 1) {
				res.addAll(hm.get(key));
			}
		}
		return res;

	}

	public void backTracking(List<String> res, String s, int l, int r) {
		if (l == 0 && r == 0) {
			res.add(s);
			return;
		}

		if (l > r)
			return;
		String temp = s;
		if (l != 0) {

			temp += "(";
			backTracking(res, temp, l - 1, r);
		}

		if (r != 0) {
			temp = s;
			temp += ")";

			backTracking(res, temp, l, r - 1);
		}

	}

	public void bt(String s, List<String> res, int l, int r) {
		if (l == 0 && r == 0) {
			res.add(s);
			return;
		}

		if (l > r || l < 0 || r < 0)
			return;

		String temp = s;
		while (l > 0) {
			temp += "(";
			bt(temp, res, l - 1, r);
		}

		while (r > 0) {
			temp += ")";
			bt(temp, res, l, r - 1);
		}
	}

	public void bt(String s, String digits, int index, List<String> res) {
		if (index == digits.length()) {
			res.add(s);
			return;
		}

		for (int i = 0; i < keyBoard[digits.charAt(index) - '0'].length; i++) {
			String temp = s + keyBoard[digits.charAt(index) - '0'][i];
			bt(temp, digits, index + 1, res);
		}
	}

	public String check(String s, int i, int j){
        while(i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        
        return s.substring(i+1, j);
    }

	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (k < 1 || t < 0)
			return false;
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			if ((ts.floor(n) != null && n - ts.floor(n) <= t)
					|| (ts.ceiling(n) != null && ts.ceiling(n) - n <= t))
				return true;
			ts.add(n);
			if (i >= k)
				ts.remove(nums[i - k]);
		}

		return false;
	}

	public int convert(String sub) {
		int res = 0;
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
		dict.put('A', 0);
		dict.put('C', 1);
		dict.put('G', 2);
		dict.put('T', 3);
		for (int i = 0; i < 10; ++i) {
			res += dict.get(sub.charAt(i));
			res *= 4;
		}
		return res;
	}

	public String convert(String s, int numRows) {
		if (numRows <= 1)
			return s;
		if (s.length() == 0)
			return s;
		char[] c = s.toCharArray();
		String[] sa = new String[numRows];
		sa[0] = new String();
		sa[0] += c[0];
		int time = s.length() - 1;
		int n = 1;

		while (time > 0) {
			while (time > 0 && n < numRows) {
				if (sa[n] == null)
					sa[n] = new String();
				sa[n] += c[s.length() - time];
				n++;
				time--;
			}
			n = n - 2;

			while (time > 0 && n >= 0) {
				// if(sa[n]==null)sa[n] = new String();
				sa[n] += c[s.length() - time];
				n--;
				time--;
			}
			n = n + 2;

		}

		String res = "";
		for (int i = 0; i < numRows; i++) {
			res += sa[i];
		}

		return res;

	}

	public String convert2(String s, int numRows) {
		if (numRows <= 1)
			return s;
		if (s.length() == 0)
			return s;

		char[] c = s.toCharArray();
		String[] sa = new String[numRows];
		sa[0] = new String();
		sa[0] += c[0];
		int i = 1;
		int n = 1;
		while (i < s.length()) {
			while (i < s.length() && n < numRows) {
				if (sa[n] == null)
					sa[n] = new String();
				sa[n] += c[i];
				i++;
				n++;
			}

			n = n - 2;

			while (i < s.length() && n >= 0) {
				sa[n] += c[i];
				i++;
				n--;
			}
			n = n + 2;
		}

		String res = "";
		for (String temp : sa) {
			res += temp;
		}

		return res;

	}

	public String countAndSay(int n) {
		if (n == 0)
			return "";
		String s = "1";

		while (n > 1) {
			char[] c = s.toCharArray();
			s = "";
			int j = 0;
			while (j < c.length) {
				int count = 0;
				do {
					count++;
					j++;
				} while (j < c.length && c[j] == c[j - 1]);
				s += count + "" + c[j - 1];
			}
			n--;
		}

		return s;
	}

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' || input.charAt(i) == '*'
					|| input.charAt(i) == '-') {
				String left = input.substring(0, i);
				String right = input.substring(i + 1);

				List<Integer> leftL = diffWaysToCompute(left);
				List<Integer> rightL = diffWaysToCompute(right);
				for (Integer l : leftL) {
					for (Integer r : rightL) {
						if (input.charAt(i) == '+') {
							res.add(l + r);
						} else if (input.charAt(i) == '-') {
							res.add(l - r);
						} else {
							res.add(l * r);
						}
					}
				}
			}
		}
		if(res.size() == 0){
			res.add(Integer.valueOf(input));
		}

		return res;

	}

	public String find(String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}

		return s.substring(i + 1, j);
	}

	public List<String> findRepeatedDnaSequences(String s) {
		List<String> l = new ArrayList<String>();
		Set<Integer> hs = new HashSet<Integer>();
		Set<String> res = new HashSet<String>();
		for (int i = 0; i <= s.length() - 10; i++) {
			int temp = convert(s.substring(i, i + 10));
			String temps = s.substring(i, i + 10);
			if (hs.contains(temp)) {
				res.add(temps);
			} else {
				hs.add(temp);
			}
		}

		l.addAll(res);

		return l;
	}

	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		backTracking(res, "", n, n);
		return res;
	}

	public List<String> generateParenthesis2(int n) {
		List<String> res = new ArrayList<String>();
		if (n == 0)
			return res;
		bt("", res, n, n);

		return res;
	}

	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 1) {
				count++;
			}
			n /= 2;
		}

		return count;

	}

	public boolean isAlphanumeric(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9')) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAnagram(String s, String t) {
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        s = String.valueOf(sa);
        t = String.valueOf(ta);
        
        
        
        return s.equals(t);
    }

	public boolean isInterleave(String s1, String s2, String s3) {
		int l1 = s1.length();
		int l2 = s2.length();
		if (s3.length() != (l1 + l2))
			return false;

		boolean[][] matrix = new boolean[l1 + 1][l2 + 1];
		matrix[0][0] = true;

		for (int i = 1; i < l1 + 1; i++) {
			matrix[i][0] = matrix[i - 1][0]
					&& s3.charAt(i - 1) == s1.charAt(i - 1);
		}

		for (int i = 1; i < l2 + 1; i++) {
			matrix[0][i] = matrix[0][i - 1]
					&& s3.charAt(i - 1) == s2.charAt(i - 1);
		}

		for (int i = 1; i < l1 + 1; i++) {
			for (int j = 1; j < l2 + 1; j++) {
				matrix[i][j] = s3.charAt(i + j - 1) == s2.charAt(j - 1)
						&& matrix[i][j - 1]
						|| s3.charAt(i + j - 1) == s1.charAt(i - 1)
						&& matrix[i - 1][j];

			}
		}

		return matrix[l1][l2];

	}

	public boolean isPalindrome(String s) {
		s = s.toLowerCase();
		int i = 0;
		int j = s.length() - 1;

		while (i <= j) {
			char l = s.charAt(i);
			char r = s.charAt(j);
			if (!isAlphanumeric(l)) {
				i++;
				continue;
			}

			if (!isAlphanumeric(r)) {
				j--;
				continue;
			}

			if (s.charAt(i) != s.charAt(j))
				return false;
			i++;
			j--;
		}

		return true;
	}

	public int lengthOfLastWord(String s) {
		s = s.trim();

		int count = 0;
		int l = s.length() - 1;
		while (l > 0 && s.charAt(l) != ' ') {
			l--;
			count++;
		}

		return count;
	}

	public int lengthOfLongestSubstring(String s) {
	       if(s.length() == 0)return 0;
	       
	       Map<Character, Integer> hm = new HashMap<Character, Integer>();
	       int max = 0;
	       int point = 0;
	       
	       for(int i=0;i<s.length();i++){
	           if(hm.containsKey(s.charAt(i))){                
	                point = Math.max(point, hm.get(s.charAt(i))+1);
	           }
	           max = Math.max(max, i - point+1);
	           
	           hm.put(s.charAt(i), i);
	       }
	       
	       return max;
	    }
	public int lengthOfLongestSubstring2(String s) {
		if (s.length() == 0)
			return 0;
		int length = 0;

		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		int j = 0;

		for (int i = 0; i < s.length(); i++) {
			if (hm.containsKey(s.charAt(i))) {
				length = Math.max(length, i - j);

				j = hm.get(s.charAt(i)) + 1;

			}
			hm.put(s.charAt(i), i);
		}

		return length;
	}

	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<String>();
		if (digits.length() == 0)
			return res;
		bt("", digits, 0, res);
		return res;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";

		String res = strs[0];

		for (int i = 1; i < strs.length; i++) {
			for (int j = 0; j < res.length(); j++) {
				if (j > strs[i].length() - 1
						|| res.charAt(j) != strs[i].charAt(j)) {
					res = res.substring(0, j);
				}
			}
		}

		return res;
	}

	public String longestPalindrome(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			String s1 = find(s, i, i);
			if (s1.length() > res.length())
				res = s1;
			s1 = find(s, i - 1, i);

			if (s1.length() > res.length())
				res = s1;
		}

		return res;

	}

	public String longestPalindrome2(String s) {
        if(s == null || s.length() == 0)return "";
        String res = "";
        for(int i = 1;i<s.length();i++){
            String str = check(s,i,i);
            if(str.length() > res.length())res = str;
            str = check(s,i-1,i);
            if(str.length() > res.length())res = str;
        }
        
        return res;
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

	public String minWindow(String s, String t) {
		String res = "";
		if (t.length() > s.length())
			return "";
		int sum = 0;
		Map<Character, Integer> hs = new HashMap<Character, Integer>();
		for (char c : t.toCharArray()) {
			if (hs.containsKey(c)) {
				hs.put(c, hs.get(c) + 1);
			} else {
				hs.put(c, 1);
			}
			sum++;
		}

		System.out.println(sum);
		System.out.println(hs);

		int l = 0;
		int r = 0;

		while (r <= s.length()) {
			if (sum == 0) {

				if (res.equals("")) {
					res = s.substring(l, r);
				}
				if (!res.equals("") && r - l + 1 < res.length()) {
					res = s.substring(l, r);
					System.out.println(res);
				}

				if (hs.containsKey(s.charAt(l))) {
					if (hs.get(s.charAt(l)) < 0) {
						hs.put(s.charAt(l), hs.get(s.charAt(l)) + 1);
					} else {
						hs.put(s.charAt(l), hs.get(s.charAt(l)) + 1);
						sum++;
					}
				}
				l++;

			} else {
				if (r == s.length())
					break;
				if (hs.containsKey(s.charAt(r))) {
					if (hs.get(s.charAt(r)) > 0) {
						hs.put(s.charAt(r), hs.get(s.charAt(r)) - 1);
						sum--;
					} else {
						hs.put(s.charAt(r), hs.get(s.charAt(r)) - 1);
					}

				}
				r++;
			}

		}

		return res;

	}

	public String multiply(String n1, String n2) {
		boolean neg = false;
		if (n1.charAt(0) == '-') {
			n1 = new StringBuffer(n1.substring(1, n1.length())).reverse()
					.toString();
			neg = !neg;
		} else {
			n1 = new StringBuffer(n1).reverse().toString();
		}

		if (n2.charAt(0) == '-') {
			n2 = new StringBuffer(n2.substring(1, n2.length())).reverse()
					.toString();
			neg = !neg;
		} else {
			n2 = new StringBuffer(n2).reverse().toString();
		}

		int[] number = new int[(n1.length()) + (n2.length())];

		for (int i = 0; i < n1.length(); i++) {
			for (int j = 0; j < n2.length(); j++) {
				int num1 = n1.charAt(i) - '0';
				int num2 = n2.charAt(j) - '0';
				number[i + j] += num1 * num2;
			}
		}
		int i = number.length - 1;
		while (i >= 0 && number[i] == 0) {
			i--;
		}
		if (i == -1)
			return "0";
		StringBuffer sb = new StringBuffer();
		int carry = 0;
		for (int j = 0; j <= i; j++) {
			sb.append((number[j] + carry) % 10);
			carry = (number[j] + carry) / 10;
		}

		if (carry != 0) {
			sb.append(new StringBuffer(String.valueOf(carry)).reverse());
		}

		return sb.reverse().toString();
	}

	public void recursive(String s, Set<String> wordDict, String res) {
		if (s.length() == 0) {
			result.add(res.substring(0, res.length() - 1));
			return;
		}

		for (int i = 1; i <= s.length(); i++) {
			if (wordDict.contains(s.substring(0, i))) {
				String ns = res + s.substring(0, i) + " ";
				recursive(s.substring(i, s.length()), wordDict, ns);
			}
		}
		return;
	}

	public int reverseBits(int n) {
		System.out.println(Integer.toBinaryString(n));
		int res = 0;
		int time = 32;
		while (time > 0) {
			res <<= 1;
			res += (n & 1);
			n >>>= 1;
			time--;
		}
		System.out.println(Integer.toBinaryString(res));
		return res;
	}

	public List<String> wordBreak(String s, Set<String> wordDict) {
		recursive(s, wordDict, "");
		return result;
	}

	public boolean wordBreak2(String s, Set<String> wordDict) {
		boolean[] dp = new boolean[s.length() + 1];
		Arrays.fill(dp, false);
		dp[0] = true;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] == true && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}

			}
		}
		return dp[dp.length - 1];
	}
}
