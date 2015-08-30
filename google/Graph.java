package google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class GNode {
	public ArrayList<GNode> nodelist;
	public int value;
	public boolean marked;

	public GNode(int value) {
		nodelist = new ArrayList<GNode>();
		this.value = value;
		this.marked = false;
	}
}

public class Graph {
	public static void main(String[] args) {
		GNode v1 = new GNode(1);
		GNode v2 = new GNode(2);
		GNode v3 = new GNode(3);
		GNode v4 = new GNode(4);

		GNode v5 = new GNode(5);
		GNode v6 = new GNode(6);
		GNode v7 = new GNode(7);
		GNode v8 = new GNode(8);
		
		v1.nodelist.add(v2);
		v1.nodelist.add(v4);
		v1.nodelist.add(v5);
		v1.nodelist.add(v8);
		
		v2.nodelist.add(v1);
		v2.nodelist.add(v3);
		
		v3.nodelist.add(v2);
		v3.nodelist.add(v4);
		
		v4.nodelist.add(v1);
		v4.nodelist.add(v3);

		v5.nodelist.add(v1);
		v5.nodelist.add(v6);
		v5.nodelist.add(v7);
		
		v6.nodelist.add(v5);
		
		v7.nodelist.add(v5);
		
		v8.nodelist.add(v1);
		
		dfs2(v1);

	}
	
	public static void dfs(GNode root){
		System.out.print(root.value + " ");
		root.marked = true;
		for(int i = 0;i<root.nodelist.size();i++){
			if(!root.nodelist.get(i).marked){
				dfs(root.nodelist.get(i));
			}
		}
	}
	
	public static void dfs2(GNode root){
		Stack<GNode> s = new Stack<GNode>();
		root.marked = true;
		s.push(root);
		System.out.println(root.value);
		while(!s.isEmpty()){
			GNode node = s.pop();
			for(int i = 0;i<node.nodelist.size();i++){
				if(!node.nodelist.get(i).marked){
					node.nodelist.get(i).marked = true;
					s.push(node.nodelist.get(i));
					System.out.println(node.nodelist.get(i).value);
				}
			}
		}
	}
	
	
	public static void bfs(GNode root){
		Queue<GNode> q =  new LinkedList<GNode>();
		q.add(root);
		root.marked = true;
		while(q.peek()!=null){
			System.out.println(q.peek().value);
			for(int i = 0;i<q.peek().nodelist.size();i++){
				if(!q.peek().nodelist.get(i).marked)
				{
					q.peek().nodelist.get(i).marked = true;
					q.add(q.peek().nodelist.get(i));
				}
			}
			q.poll();
		}
		
	}

}
