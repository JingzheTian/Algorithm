package google;

public class WildCard {
	public static void main(String[] args) {
		System.out.println(new WildCard().isMatch("ac", "ab*"));
	}

	public boolean isMatch(String s, String p) {
		int sl = s.length();
		int pl = p.length();
		if (p.equals("") && s.equals(""))
			return true;
		if (p.replace("*", "").length() > s.length())
			return false;


		int[][] dp = new int[pl + 1][sl + 1];
		dp[0][0] = 1;
		for (int i = 1; i < sl + 1; i++) {
			dp[0][i] = 0;
		}

		for (int i = 1; i < pl + 1; i++) {
			dp[i][0] = 0;
		}

		for (int i = 1; i < pl + 1; i++) {
			if(p.charAt(i-1) == '*' && dp[i-1][0] == 1)dp[i][0]=1;
			for (int j = 1; j < sl + 1; j++) {
				
				
				
				if (p.charAt(i - 1) == '?') {
					if (dp[i - 1][j - 1] == 1)
						dp[i][j] = 1;
				} else if (p.charAt(i - 1) == '*') {
					if (dp[i - 1][j - 1] == 1 || dp[i][j - 1] == 1
							|| dp[i - 1][j] == 1)
						dp[i][j] = 1;
				} else if (p.charAt(i - 1) == s.charAt(j - 1)) {
					if (dp[i - 1][j - 1] == 1)
						dp[i][j] = 1;
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return dp[pl][sl] == 1 ? true : false;
	}
}
