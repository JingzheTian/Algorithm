package google;

import java.util.ArrayList;
import java.util.List;

public class Subset {
	public static void main(String[] args) {
		System.out.println(new Subset().subset(4));
	}
	
	public List<List<Integer>> subset(int num){
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		bt(num,l, new ArrayList<Integer>());
		return l;
	}
	
	public void bt(int num, List<List<Integer>> l, List<Integer> temp){
		if(num == 0){
			l.add(temp);
			return;
		}
		
		for(int i = 1;i<=num;i++){
			List<Integer> newtemp = new ArrayList<Integer>();
			newtemp.addAll(temp);
			newtemp.add(i);
			bt(num-i, l, newtemp);
		}
	}
}
