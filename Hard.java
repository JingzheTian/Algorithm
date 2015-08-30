public class Hard {
	public static void main(String[] args) {
		Hard h  = new Hard();
		int[] d = {1,2,3,1,5};
		System.out.println(h.candy(d));
	}
	
	 public int candy(int[] r) {
	        int number = 0;
	        int i = 1;
	        while(i+1<r.length)
	        {
	            if(r[i] > r[i-1])
	            {
	                int t = 1;
	               while(i+1<r.length && r[i+1] > r[i])
	               {
	                   t++;
	                   i++;
	               }
	               number += (t*(t+1))/2;
	               i++;
	            }else if(r[i] < r[i-1])
	            {
	               int t = 1;
	               while(i!=r.length-1 && r[i] < r[i-1])
	               {
	                   t++;
	                   i++;
	               }
	               number += (t*(t+1))/2-1;
	               i++;
	            }else
	            {
	                number++;
	                i++;
	            }
	        }
	        return number+1;
	    }
	
	public int calculateMinimumHP(int[][] d) {
		int i = d.length - 1;
		int j = d[0].length - 1;
		if (d[i][j] > 0) {
			d[i][j] = 1;
		} else {
			d[i][j] = (d[i][j] * -1) + 1;
		}

		for (int m = i; m >= 0; m--) {
			for (int n = j; n >= 0; n--) {
				int right = Integer.MAX_VALUE;
				int down = Integer.MAX_VALUE;
				if (m + 1 <= i) {
					down = d[m + 1][n] - d[m][n];
					if (down <= 0) {
						down = 1;
					}
				}

				if (n + 1 <= j) {
					right = d[m][n + 1] - d[m][n];
					if (right <= 0) {
						right = 1;
					}
				}

				if(!(m == i && n == j))
				d[m][n] = Math.min(down, right);
			}
		}

		
		return d[0][0];

	}
}
