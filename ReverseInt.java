import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ReverseInt {
	public static void main(String[] args) {
		ReverseInt r = new ReverseInt();
		// // System.out.println(r.reverse(1000000003));
		// // System.out.println(r.atoi("+-2"));
		// String[] strs = new String[3];
		// strs[0] = "aaa";
		// strs[1] = "aa";
		// strs[2] = "aaa";
		//
		// System.out.println(r.isPalindrome("ab"));
		// int[] A = {2};
		// System.out.println(r.removeElement(A, 3));

		// int i = 11;
		// System.out.println(Integer.toBinaryString(i));

		int[] num = { 1, 2, 3, 3 };
		String s = "aba";
		StringBuffer sb = new StringBuffer(s);
		System.out.println(sb.reverse().toString().equals(s));
//		String s = "   a   b ";
//		System.out.println(r.reverseWords(s));
				
		// int[] a = { 1,2,1,2,0,2, 0 };
		// int[] b = { 2, 1 };
		// 
		// r.sortColors(a);
	}

	// public String longestPalindrome(String s) {
	// int len = s.length();
	// char[] c = s.toCharArray();
	//        
	// for(int i = 0;i<len;i++)
	// {
	//           
	//            
	// while(len-i-1>i)
	// {
	// int left = i;
	// int right = len-i-1;
	// while(left < right && c[left] == c[right])
	// {
	// left++;
	// right--;
	// }
	//            
	// if(left>= right)
	// {
	// return s.substring(i,len-i);
	// }
	//                
	// len--;
	// }
	//            
	// }
	// return "";
	//        
	// }
	//	

	public String reverseWords(String s) {

		String[] sarray = s.trim().split(" +");

		if(sarray.length == 0)
        {
            return "";
        }else if(sarray.length == 1)
        {
        	return sarray[0];
        }
		
		int i = 0;
		int j = sarray.length - 1;

		while (i < j) {
			String temp = sarray[i];
			sarray[i] = sarray[j];
			sarray[j] = temp;
			i++;
			j--;
		}
		String result = "";
		int len = sarray.length - 1;
		if (sarray[1].equals("")) {
			len--;
		}
		for (int m = 0; m < len + 1; m++) {
			result += sarray[m];
			if (m != len) {
				result += " ";
			}
		}
		return result;
	}

	public List<List<Integer>> subsetsWithDup(int[] num) {
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		List<Integer> l = new ArrayList<Integer>();
	
		hs.add(l);
		int len = num.length;
		while (len > 0) {
			System.out.println(hs.size());

			for (int i = 0; i + len <= num.length; i++) {
				List<Integer> templ = new ArrayList<Integer>();
				int j = 0;
				while (j < len) {
					templ.add(num[i + j]);
					j++;
				}
				hs.add(templ);
			}

			len--;
		}

		List<List<Integer>> finalL = new ArrayList<List<Integer>>();

		for (List<Integer> a : hs) {
			finalL.add(a);
		}

		return finalL;
	}

	public String longestPalindrome(String s) {
		int len = s.length();
		char[] chars = s.toCharArray();

		while (len >= 0) {
			for (int i = 0; i + len - 1 < s.length(); i++) {
				int left = i;
				int right = i + len - 1;

				while (left < right && chars[left] == chars[right]) {
					left++;
					right--;
				}

				if (left >= right) {
					return s.substring(i, i + len);
				}
			}
			len--;
		}

		return "";
	}

	public int solution6(String S) {
		char[] c = S.toCharArray();
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < c.length; i++) {
			if (c[i] >= '0' && c[i] <= '9') {
				s.push((int) c[i] - '0');
			} else {
				if (s.size() < 2) {
					System.out.println("error");
					return -1;
				}
				int m = s.pop();
				int n = s.pop();
				switch (c[i]) {
				case '+': {
					s.push(n + m);
					break;
				}
				case '-': {
					s.push(n - m);
					break;
				}
				case '*': {
					s.push(n * m);
					break;
				}
				case '/': {
					s.push(n / m);
					break;
				}
				}
			}
		}
		if (s.size() > 1) {
			System.out.println("error");
			return -1;
		} else {
			return s.pop();
		}

	}

	public void sortColors(int[] A) {
		if (A == null || A.length <= 1) {
			return;
		}

		int len = A.length;
		int low = 0, high = len - 1;
		for (int i = low; i <= high;) {
			if (A[i] == 2) {
				A[i] = A[high];
				A[high--] = 2;
			} else if (A[i] == 0) {
				A[i++] = A[low];
				A[low++] = 0;
			} else {
				++i;
			}
		}
	}

	public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			hm.put(numbers[i], i);
		}
		for (int i = 0; i < numbers.length; i++) {
			int keyValue = target - numbers[i];
			if (hm.containsKey(keyValue) && hm.get(keyValue) != i) {
				result[0] = i;
				result[1] = hm.get(keyValue);
				return result;
			}
		}

		return null;

	}

	public int minimumTotal(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				if (triangle.get(i).get(j) + triangle.get(i + 1).get(j) > triangle
						.get(i).get(j)
						+ triangle.get(i + 1).get(j + 1)) {
					triangle.get(i)
							.set(
									j,
									triangle.get(i).get(j)
											+ triangle.get(i + 1).get(j));
				} else {
					triangle.get(i).set(
							j,
							triangle.get(i).get(j)
									+ triangle.get(i + 1).get(j + 1));
				}
			}
		}
		return triangle.get(0).get(0);
	}

	public String solution3(String A) {
		if (A.length() == 0) {
			return "";
		}
		Stack<Character> s = new Stack<Character>();
		s.push(A.charAt(0));
		for (int i = 1; i < A.length(); i++) {
			s.push(A.charAt(i));
			boolean stop = true;
			while (stop) {
				if (s.size() == 1) {
					stop = false;
					break;
				}
				int temp = 0;
				char m = s.pop();
				char n = s.pop();
				temp += m;
				temp += n;
				switch (temp) {
				case 130: {
					s.push('A');
					break;
				}
				case 131: {
					s.push('A');
					break;
				}
				case 133: {
					s.push('C');
					break;
				}
				case 134: {
					s.push('C');
					break;
				}
				default: {
					s.push(m);
					s.push(n);
					stop = false;
					break;
				}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		while (!s.isEmpty()) {
			sb.append(s.pop());

		}
		return sb.reverse().toString();
	}

	public int solution2(int[] A) {
		// write your code in Java SE 8
		int[] result = merge_Sort(A);
		int maxGap = -1;
		for (int i = 1; i < result.length; i++) {
			if (maxGap < result[i] - result[i - 1]) {
				maxGap = result[i] - result[i - 1];
			}
		}
		return maxGap;
	}

	public static int[] merge_Sort(int[] array) {
		if (array.length == 1) {
			return array;
		}
		int half = array.length / 2;
		int[] a1 = new int[half];

		int[] a2 = new int[array.length - half];
		for (int i = 0; i < half; i++) {
			a1[i] = array[i];
		}

		for (int i = 0; i < array.length - half; i++) {
			a2[i] = array[i + half];
		}

		a1 = merge_Sort(a1);
		a2 = merge_Sort(a2);
		return mergeSortSub(a1, a2);
	}

	public static int[] mergeSortSub(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (true) {
			if (arr1[i] < arr2[j]) {
				result[k] = arr1[i];
				if (++i > arr1.length - 1)
					break;
			} else {
				result[k] = arr2[j];
				if (++j > arr2.length - 1)
					break;
			}
			k++;
		}
		for (; i < arr1.length; i++) {
			result[++k] = arr1[i];
		}
		for (; j < arr2.length; j++) {
			result[++k] = arr2[j];
		}
		return result;

	}

	public int solution(int[] A) {
		// write your code in Java SE 8

		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			if (!hm.containsKey(A[i])) {
				hm.put(A[i], 1);
			} else {
				hm.put(A[i], hm.get(A[i]) + 1);
			}
		}

		for (int i : hm.keySet()) {
			int value = hm.get(i);
			if (value >= 2) {
				sum += (value * (value - 1)) / 2;
			}
		}

		return (sum > 1000000000) ? 1000000000 : sum;

	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int i = gas.length;
		int j = 0;
		int sum = gas[0] - cost[0];

		if (i > 1) {
			do {
				if (sum >= 0) {
					j = (++j) % (gas.length);
					if ((i % gas.length) == j)
						break;
					sum += (gas[j] - cost[j]);
				} else {
					i--;
					if ((i % gas.length) == j)
						break;
					sum += (gas[i] - cost[i]);
				}

			} while (true);

		}
		return (sum < 0) ? -1 : j;
	}

	// public boolean isSameTree(TreeNode p, TreeNode q) {
	// ArrayList a1 = new ArrayList();
	// ArrayList a2 = new ArrayList();
	// int i = 0;
	// while(a1.get(i) == null)
	// {
	// if(a1.get(i).left!=null)
	// {
	// a1.add(a1.get(i).left);
	// }
	//	            
	// if(a1.get(i).right!=null)
	// {
	// a1.add(a1.get(i).right);
	// }
	// }
	//	        
	// i = 0;
	// while(a2.get(i) == null)
	// {
	// if(a2.get(i).left ^= null)
	// {
	// a2.add(a2.get(i).left);
	// }
	//	            
	// if(a2.get(i).right!=null)
	// {
	// a2.add(a2.get(i).right);
	// }
	// }
	//	        
	// if(a1.size() != a2.size())
	// {
	// return false;
	// }else{
	// for(int j = 0;j<a1.size();j++)
	// {
	// if(a1.get(j).val != a2.get(j).val)
	// {
	// return false;
	// }
	// }
	// }
	//	        
	// return true;
	// }

	public void merge(int A[], int m, int B[], int n) {
		int length = m + n - 1;
		m--;
		n--;
		while (length >= 0) {
			if (m >= 0 && n >= 0) {
				if (A[m] >= B[n]) {
					A[length] = A[m];
					m--;
					length--;
				} else {
					A[length] = B[n];
					n--;
					length--;
				}
			} else if (m < 0 && n >= 0) {
				A[length] = B[n];
				n--;
				length--;
			} else if (m >= 0 && n < 0) {
				A[length] = A[m];
				m--;
				length--;
			}

		}
	}

	public String addBinary(String a, String b) {
		StringBuffer sb1 = new StringBuffer(a);
		StringBuffer sb2 = new StringBuffer(b);
		StringBuffer sb3 = new StringBuffer(b);

		sb1 = sb1.reverse();
		sb2 = sb2.reverse();

		return a;
	}

	public int lengthOfLastWord(String s) {
		String[] sa = s.split(" ");
		if (sa.length == 0) {
			return 0;
		} else {
			return sa[sa.length - 1].length();
		}
	}

	public int[] plusOne(int[] digits) {
		boolean add = true;
		for (int i = 0; i < digits.length; i++) {
			if (digits[i] != 9) {
				add = false;
				break;
			}
		}

		if (add) {
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			for (int i = digits.length - 1; i > 1; i--) {
				newDigits[i] = 0;
			}
			return newDigits;
		} else {
			for (int i = digits.length - 1; i >= 0; i--) {
				if (i == digits.length - 1 && digits[i] < 9) {
					digits[i] += 1;
				} else if (i == digits.length - 1 && digits[i] == 9) {
					digits[i] = 0;
					digits[i - 1]++;
				} else if (digits[i] == 10) {
					digits[i] = 0;
					digits[i - 1]++;
				}
			}
			return digits;
		}
	}

	public int climbStairs(int n) {
		if (n == 0 || n < 0)
			return 0;
		if (n == 1) {
			return 1;

		} else if (n == 2) {
			return 2;
		}

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		a.add(1);
		a.add(2);
		for (int i = 3; i <= n; i++) {
			a.add(a.get(i - 1) + a.get(i - 2));
		}

		return a.get(n);
	}

	public int removeElement(int[] A, int elem) {
		int length = 0;
		int tail = A.length - 1;
		for (int i = 0; i < tail + 1; i++) {
			if (A[i] != elem) {
				length++;
			} else {
				while (A[tail] == elem) {

					if (tail == i) {
						return length;
					}
					tail--;
				}
				A[i] = A[tail];
				length++;
				tail--;

			}

		}

		return length;

	}

	public int strStr(String haystack, String needle) {
		if (needle.length() == 0)
			return 0;
		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			int j = 0;
			while (haystack.charAt(i + j) == needle.charAt(j)) {
				j++;
				if (j == needle.length()) {
					return i;
				}

			}
		}
		return -1;
	}

	public boolean isPalindrome(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
					|| (c >= '0' && c <= '9')) {
				sb.append(c);
			}
		}
		s = sb.toString();
		s = s.toLowerCase();
		sb = new StringBuffer(s);
		// System.out.println(sb + " " + sb.reverse());
		if (sb.toString().equals(sb.reverse().toString())) {
			return true;
		}
		return false;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		String pre = strs[0];

		for (int i = 1; i < strs.length; i++) {
			int j = 0;

			while (j < pre.length() && j < strs[i].length()
					&& pre.charAt(j) == strs[i].charAt(j)) {
				j++;
			}
			pre = pre.substring(0, j);
		}

		return pre;
	}

	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int d = 1;
		while (x / 10 / d != 0) {
			d *= 10;
		}
		while (x != 0) {
			if (x % 10 != x / d) {
				return false;
			}
			x = x % d / 10;
			d = d / 100;

		}

		return true;
	}

	public int atoi(String str) {
		try {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != ' ') {
					str = str.substring(i, str.length());
					break;
				}
			}

			for (int i = 0; i < str.length(); i++) {
				if (i != 0 && (str.charAt(i) > '9' || str.charAt(i) < '0')) {
					str = str.substring(0, i);
					break;
				}
			}
			if (str.length() == 1) {
				return 0;
			}
			System.out.println(str);
			int output = Integer.valueOf(str);
			return output;

		} catch (Exception e) {
			if (str.length() != 0) {
				if (str.charAt(0) == '-') {
					return Integer.MIN_VALUE;
				} else {
					return Integer.MAX_VALUE;
				}
			} else {
				return 0;
			}
		}
	}

	public int reverse(int x) {
		int negtive = 1;
		if (x < 0) {
			negtive = -1;
			x = x * -1;
		}

		String news = String.valueOf(x);
		StringBuffer sb = new StringBuffer(news);
		sb.reverse();
		news = sb.toString();
		try {
			int out = Integer.valueOf(news);
			return out * negtive;
		} catch (Exception e) {
			return 0;
		}

	}
}
