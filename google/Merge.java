package google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Merge {
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6};
//		int[] b = new Merge().findmissing(a);
//		for (int i = 0; i < b.length; i++) {
//			System.out.println(b[i]);
//		}
		System.out.println(new Merge().biggest(a));
	}
	
	public int biggest(int[] nums){
		int n1 = 0;
		int n2 = 0;
		
		Arrays.sort(nums);
		
		for(int i = 0;i<nums.length;i++){
			if(i%2 == 0)
				n1= n1*10+nums[i];
			else
				n2= n2*10+nums[i];
			
		}
		System.out.println(n1 + " " + n2);
		return n1 + n2;
 	}
	
	
	public int[] findmissing(int[] array){
		int[] res = new int[2];
		if(array.length == 1){
			res[0] = res[1] = array[0];
			return res;
		}
				
		
		int left = 0;
		int right = array.length-1;
		
		int mid = (left + right)/2;
		
		if(array[mid] != array[mid-1] && array[mid] != array[mid+1]){
			res[0] = res[1] = array[mid];
		}
		
		if(array[mid] == array[mid-1] && array[mid] == array[mid+1]){
			res[0] = findonemissing(Arrays.copyOfRange(array, 0, mid-1));
			res[1] = findonemissing(Arrays.copyOfRange(array, mid+2, array.length));
		}
		
		if(array[mid] != array[mid-1] && array[mid] == array[mid+1]){
			if(mid%3 == 0){
				res = findmissing(Arrays.copyOfRange(array, mid, array.length));
			}else if(mid%3==2){
				res[0] = findonemissing(Arrays.copyOfRange(array, 0, mid));
				res[1] = findonemissing(Arrays.copyOfRange(array, mid, array.length));
			}else{
				res = findmissing(Arrays.copyOfRange(array, 0, mid));
			}
		}
		
		if(array[mid] == array[mid-1] && array[mid] != array[mid+1]){
			if((mid+1)%3 == 0){
				res = findmissing(Arrays.copyOfRange(array, mid+1, array.length));
			}else if((mid+1)%3==2){
				res[0] = findonemissing(Arrays.copyOfRange(array, 0, mid+1));
				res[1] = findonemissing(Arrays.copyOfRange(array, mid+1, array.length));
			}else{
				res = findmissing(Arrays.copyOfRange(array, 0, mid+1));
			}
		}
		
		return res;
	}
	
	public int findonemissing(int[] array){
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		for(int i:array){
			if(hm.containsKey(i)){
				hm.put(i, hm.get(i)+1);
			}else{
				hm.put(i, 1);
			}
		}
		
		for(int i: hm.keySet()){
			if(hm.get(i) == 2)
				return i;
		}
		
		return 0;
	}
	
	
	
	

	public String convert(String s) {
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		int count = 1;
		for (int i = 1; i < c.length; i++) {
			if (c[i] != c[i - 1]) {
				sb.append(count);
				sb.append(c[i-1]);
				count = 1;
			}else{
				count++;
			}
		}
		
		sb.append(count);
		sb.append(c[c.length-1]);
		
		return sb.toString();
		
		

	}

	public int[] merge(int[] array) {
		int[] newarray = new int[array.length];
		int index = 0;

		if (array[0] != 0) {
			newarray[0] = array[0];
		}

		for (int i = 1; i < array.length; i++) {
			if (array[i] == newarray[index]) {
				newarray[index] = newarray[index] * 2;
				index++;
				i++;
				newarray[index] = array[i];
				continue;
			}
			if (array[i] != newarray[index] && array[i] != 0) {
				index++;
				newarray[index] = array[i];
			}
		}

		return newarray;

	}
}
