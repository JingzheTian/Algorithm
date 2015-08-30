package google;

import java.util.Arrays;

public class Zhaoqian {
	public static void main(String[] args) {
		int[] type = {23,13,7,2,1};
		int[] array = {2,0,1,2,3,2,4,2,2,5,2,2,2,7,8,2,2,2};
		int target = 26;
		System.out.println(new Zhaoqian().returnMoney(type, 26));
		
	}
	
	public int majority(int[] array){
		int major =array[0];
		int count = 0;
		for(int i:array){
			if(count == 0){
				major = i;
				count++;
			}if(i!=major){
				count--;
			}else{
				count++;
			}
		}
		return major;
	}
	
	public int returnMoney(int[] type, int target){
		int[] dp = new int[target+1];
		Arrays.fill(dp, target);
		dp[0] = 0;
		for(int i = 1;i<target+1;i++){
			for(int j = 0;j<type.length;j++){
				if(i-type[j]>=0){
					int temp = dp[i - type[j]]+1;
					dp[i] = Math.min(dp[i], temp);
				}
				
			}
		}
		return dp[target];
	}
}
