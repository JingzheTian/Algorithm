import java.util.HashMap;
import java.util.Iterator;

public class MajorityEle {
	public static void main(String[] args) {
		int[] num = { 1, 1, 1, 1, 1, 3, 3,4,5,5,5,5,5,5,5,5,5,5,5,5,5 };
		majorityElement(num);
	}

	public static int majorityElement(int[] num) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length; i++) {
			if (!hm.containsKey(num[i])) {
				hm.put(num[i], 1);
			} else {
				int value = hm.get(num[i]) + 1;
				hm.put(num[i], value);
			}
		}

		Iterator iter = hm.keySet().iterator();
		int max = 0;
		int maxnum = 0;
		while (iter.hasNext()) {
			int key = (Integer) iter.next();
			if (hm.get(key) > max) {
				maxnum = key;
				max = hm.get(key);
			}
		}
		System.out.println(maxnum);
		return maxnum;
	}

}
