package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DirectGraphy {
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		   Map<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
	       int[] nums = new int[numCourses];
	       Queue<Integer> queue = new LinkedList<Integer>();
	       for (int i = 0; i < numCourses; i++) {
	            map.put(i, new HashSet<Integer>());
	       }
	       for (int i = 0; i < prerequisites.length; i++) {
	            map.get(prerequisites[i][0]).add(prerequisites[i][1]);
	            nums[prerequisites[i][0]]++;
	       }
	        for (int i = 0; i < numCourses; i++) {
	            if (nums[i] == 0) {
	            	queue.offer(i);
	            }
	        }
	        
	        while(!queue.isEmpty()){
	           int cur = queue.poll();
	           map.remove(cur);
	           for(int a : map.keySet()){
	        	   if(map.get(a).contains(cur))
	        		map.get(a).remove(cur);
	        	   
	        	   if(map.get(a).size() == 0)
	        		queue.offer(a);
	           }
	           
	        }
	        
	        
	        return map.size()==0;
	}
	
	public static void main(String[] args) {
		int[][] r= {{4,5},{5,2},{2,1},{6,2},{3,0},{0,1}};
		System.out.println(canFinish(7,r));
		
		
	}
}
