package sv;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] x = { 9, 2, 4, 7, 3, 7, 10 };
		System.out.println(Arrays.toString(x));
 
		int low = 0;
		int high = x.length - 1;
 
		sort(x, low, high);
		System.out.println(Arrays.toString(x));
	}
	
	public static void sort(int[] array, int low, int high){
		if(array == null || array.length == 0)return;
		if(low >= high)return;
		
		int point = low+(high-low)/2;
		int pivot = array[point];
		int i = low;
		int j = high;
		while(i<=j){
			while(array[i] < pivot){
				i++;
			}
			while(array[j] > pivot){
				j--;
			}
			
			if(i<=j){
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}
		
		if(low<j){
			sort(array, low, j);
		}
		if(high > i){
			sort(array, i, high);
		}
	}

}
