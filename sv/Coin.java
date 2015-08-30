package sv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coin {
	public static void main(String[] args) {
		int[] coins = {50,25,10,5};
		List<List<Integer>> hs = new ArrayList<List<Integer>>();
		recursive(coins, 0, 50, hs, new ArrayList<Integer>());
		
		System.out.println(hs.size());
	}

	
	public static void recursive(int[] coins, int index, int sum, List<List<Integer>> res, List<Integer> temp){
		if(sum < 0)return;
		if(sum == 0)res.add(temp);
		
		for(int i = index;i<coins.length;i++){
			List<Integer> newTemp = new ArrayList<Integer>();
			newTemp.addAll(temp);
			newTemp.add(coins[i]);
			recursive(coins, i, sum-coins[i], res, newTemp);
		}
		
	}
}
