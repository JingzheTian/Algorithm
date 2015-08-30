package google;

public class FindMinimunGap {
	public static void main(String[] args) {
		int[] a = {11,1,1,1,1,1,1};
		System.out.println(maximumGap(a));
		}
	
	public static int maximumGap(int[] nums) {
		int left = Integer.MAX_VALUE;
		int right = 0;
		for(int i:nums){
            right = Math.max(right, i);
            left = Math.min(left, i);
        }
        int[] bucket = new int[right-left+1];
        for(int i:nums){
            bucket[i-1] = 1;
        }
        int max = 0;
        int count = 0;
        for(int i = 0;i<bucket.length;i++){
            if(bucket[i] == 0){
                count++;
            }else{
                max  = Math.max(max, count+1);
                count = 0;
            }
        }
        
        return max;
    }
}
