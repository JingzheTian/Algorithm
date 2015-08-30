import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculate {
	public static void main(String[] args) {
		int[] h = {6,5,4,3,2,1};
		int[] o = {0,0,0,2,2,4};
		new Calculate().line(h, o);
	}

	public void line(int[] height, int[] order) {
		int len = height.length;
		for (int i = 1; i < len; i++) {
			int time = i - order[i];
			int now = i;
			while(time != 0){
				int temp = height[now];
				height[now] = height[now-1];
				height[now-1] = temp;	
				time--;
				now--;
			}
			order[i] = i;
		}
		
		System.out.println(Arrays.toString(height));
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		int limite = (k + 1) * k / 2;
		if (limite > n || n > k * 9)
			return l;
		find(k, n, 1, l, new ArrayList<Integer>());
		return l;
	}

	public void find(int k, int n, int start, List<List<Integer>> list,
			List<Integer> temp) {
		if (k == 0 && n == 0) {
			list.add(temp);
			return;
		}

		if (k == 0 || n <= 0 || n > k * 9)
			return;

		// System.out.println(temp);

		for (int i = start; i <= n; i++) {
			List<Integer> newtemp = new ArrayList<Integer>();
			newtemp.addAll(temp);
			newtemp.add(i);
			find(k - 1, n - i, i + 1, list, newtemp);
		}
	}
}
