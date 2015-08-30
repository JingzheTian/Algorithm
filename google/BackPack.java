package google;

public class BackPack {
	public static void main(String[] args) {
		int[] value = { 1, 2, 3, 4, 5, 20 };
		int[] weight = { 2, 3, 2, 1, 5, 4 };

		int total = 8;
		System.out.println(new BackPack().dp(value, weight, total));
	}

	public int dp(int[] value, int[] weight, int total) {
		int[] dp = new int[total+1];

		for (int i = 0; i <weight.length; i++) {
			System.out.println(weight[i]);
			for (int j = total; j > 0;  j--) {     
				if (j - weight[i] >= 0)
					dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
				System.out.print(dp[j] + " ");
			}
			System.out.println();
		}

		return dp[total];

	}
}
