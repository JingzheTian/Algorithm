import java.util.ArrayList;
import java.util.HashSet;


public class test {
	public static void main(String[] args) {
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		l1.add(1);l1.add(2);l1.add(3);
		l2.add(1);l2.add(2);l2.add(3);
		HashSet hs = new HashSet();
		hs.add(l1);
		hs.add(l2);
		System.out.println(hs.size());
		
	}

	public static int solution(int[] A) {
		int[] dp = new int[A.length];
		dp[0] = A[0];
		dp[1] = A[1];
		dp[2] = A[2];
		int min = Integer.MAX_VALUE;
		for(int i = 3;i<A.length-1;i++)
		{
			if(A[i] < dp[i-1])
			dp[i] = A[i];
			else
			dp[i] = dp[i-1];
			
			if(A[i] + dp[i-2] < min)
			min = A[i] + dp[i-2];
		}
		
		
		return min;
	}
}
