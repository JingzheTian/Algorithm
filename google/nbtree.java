package google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class TreeNode{
	String name;
	TreeNode superNode;
	ArrayList<TreeNode> subNode;
	public TreeNode(String name, TreeNode superNode) {
		super();
		this.name = name;
		this.superNode = superNode;
		subNode = new ArrayList<TreeNode>();
	}
	
	public void addSubNode(TreeNode node, HashSet<TreeNode> hs){
		
		this.subNode.add(node);
		node.superNode = this;
		hs.add(node);
	}
	
	

	
}
public class nbtree{
	TreeNode root;
	
	HashSet<TreeNode> hs = new HashSet<TreeNode>();

	public nbtree(TreeNode root) {
		this.root = root;
		root.superNode = null;
	}
	
	public void nodeInfo(String name)
	{
		
	}
	
	
	
	

}
