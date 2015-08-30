package google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph {
	public static void main(String[] args) {
		int[][] graph = { { 0, 1, 0, 1, 1, 0, 0, 1 },
						  { 1, 0, 1, 0, 0, 0, 0, 0 }, 
						  { 0, 1, 0, 1, 0, 0, 0, 0 },
						  { 1, 0, 1, 0, 0, 0, 0, 0 }, 
						  { 1, 0, 0, 0, 0, 1, 1, 0 },
						  { 0, 0, 0, 1, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 1, 0, 0, 0, 0 },
						  { 1, 0, 0, 0, 0, 0, 0, 0 } };

		boolean[] marked = new boolean[8];
		Arrays.fill(marked, false);
		bfs(graph, 0, marked);
	}

	public static void dfs(int[][] graph, int start, boolean[] market) {
		System.out.println(start + " ");
		market[start] = true;
		for (int i = 0; i < 8; i++) {
			if (graph[start][i] == 1 && !market[i]) {
				dfs(graph, i, market);
			}
		}
	}

	public static void bfs(int[][] graph, int start, boolean[] market) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.offer(start);
		market[start] = true;
		while (!queue.isEmpty()) {
			//System.out.println(queue);
			int i = queue.poll();			
			System.out.println(i);
			for (int j = 0; j < 8; j++) {
				if (graph[i][j] == 1 && !market[j]) {
					queue.offer(j);
					market[j] = true;
				}
			}
		}
	}
}
