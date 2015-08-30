package sv;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MTreeNode {
	int val;
	List<MTreeNode> children;

	public MTreeNode(int val) {
		this.val = val;
		this.children = new ArrayList<MTreeNode>();
	}
}

public class MutiNodeTree {

	public static void main(String[] args) {
		MTreeNode t1 = new MTreeNode(1);
		MTreeNode t2 = new MTreeNode(2);
		MTreeNode t3 = new MTreeNode(3);
		MTreeNode t4 = new MTreeNode(4);
		MTreeNode t5 = new MTreeNode(5);
		MTreeNode t6 = new MTreeNode(6);

		MTreeNode t7 = new MTreeNode(7);
		MTreeNode t8 = new MTreeNode(8);
		MTreeNode t9 = new MTreeNode(9);
		MTreeNode t0 = new MTreeNode(0);
		t0.children.add(t1);
		t0.children.add(t2);
		t0.children.add(t3);

		t1.children.add(t4);
		t1.children.add(t5);

		t2.children.add(t6);
		t2.children.add(t7);

		t3.children.add(t8);
		t3.children.add(t9);

		
		new MutiNodeTree().dfs(t0);
	}
	
	public void dfs(MTreeNode root){
		if(root == null)return;
		
		Stack<MTreeNode> s = new Stack<MTreeNode>();
		s.push(root);
		
		while(!s.isEmpty()){
			MTreeNode temp = s.pop();
			if(temp == null)continue;
			System.out.println(temp.val);
			for(int i = temp.children.size()-1;i>=0;i--){
				s.push(temp.children.get(i));
			}
		}
		
		return;
	}

}
