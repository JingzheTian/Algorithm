package sv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode next;

	TreeNode(int x) {
		val = x;
	}
}

public class TreeAlgo {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);
		TreeNode c1 = new TreeNode(1);
		TreeNode c2 = new TreeNode(2);
		TreeNode c3 = new TreeNode(3);
		TreeNode c4 = new TreeNode(4);
		TreeNode c5 = new TreeNode(5);
		TreeNode c6 = new TreeNode(6);
		TreeNode c7 = new TreeNode(7);
		TreeNode c8 = new TreeNode(8);
		TreeNode c9 = new TreeNode(9);
		// TreeNode c10 = new TreeNode(10);
		// TreeNode c11 = new TreeNode(11);
		// TreeNode c12 = new TreeNode(12);
		// TreeNode c13 = new TreeNode(13);
		// TreeNode c14 = new TreeNode(14);

		root.left = c1;
//		root.right = c2;
//		c1.right = c4;
//		c4.right = c3;
//		c1.left = c8;
//		c8.right = c9;
//		c2.left = c6;
		// c2.left = c5;
		// c2.right = c6;
		// c3.left = c7;
		// c3.right = c8;
		// c4.left = c9;
		// c4.right = c10;
		// c5.left = c6;
		// c5.right = c7;
		// c6.left = c13;
		// c6.right = c14;

		System.out.println(new TreeAlgo().zigzagLevelOrder(root));
	}

	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null)
			return res;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int n = 1;
		int level = 1;
		while (!queue.isEmpty()) {
			List<Integer> l = new LinkedList<Integer>();
			int tempn = 0;
			while (n != 0) {
				TreeNode node = queue.poll();
				n--;
				l.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
					tempn++;
				}
				if (node.right != null) {
					queue.offer(node.right);
					tempn++;
				}
			}
			n = tempn;
			if (level == 1)
				res.add(l);
			else {
				Collections.reverse(l);
				res.add(l);
			}

			level = (level + 1) % 2;
		}

		return res;

	}

	static int maxD = 0;

	public int getMaxDiameter(TreeNode root) {
		if (root == null)
			return 0;

		if (root.left == null && root.right == null) {
			return 1;
		}

		int left = getMaxDiameter(root.left);
		int right = getMaxDiameter(root.right);

		maxD = Math.max(maxD, left + right + 1);

		return Math.max(left + 1, right + 1);
	}

	public TreeNode commonAncester(TreeNode root, TreeNode t1, TreeNode t2) {
		TreeNode res = null;
		System.out.println(res);
		isCommonAncester(root, t1, t2, res);
		System.out.println(res);
		return res;
	}

	public boolean isCommonAncester(TreeNode root, TreeNode t1, TreeNode t2,
			TreeNode res) {
		// System.out.println(res.val);
		if (root == null)
			return false;
		if (root == t1 || root == t2)
			return true;

		if (isCommonAncester(root.left, t1, t2, res)
				&& isCommonAncester(root.right, t1, t2, res)) {
			res = root;
			// System.out.println(res);
			return false;
		}
		boolean is = isCommonAncester(root.left, t1, t2, res)
				^ isCommonAncester(root.right, t1, t2, res);
		// System.out.println(is);
		return is;
	}

	public static int MAX = 0;

	public int maxlength(TreeNode root) {
		if (root != null) {
			int left = dfs(root.left, true) + 1;
			int right = dfs(root.right, false) + 1;
			System.out.println(left + " " + right);
		}
		return MAX;
	}

	public int dfs(TreeNode node, boolean isLeft) {
		if (node.left == null && node.right == null) {
			return 1;
		}
		int left = 1;
		int right = 1;
		if (node.left != null) {
			left = dfs(node.left, true) + 1;
		}

		if (node.right != null) {
			right = dfs(node.right, false) + 1;
		}

		MAX = Math.max(MAX, Math.max(left, right));
		// System.out.println(max);
		return isLeft == true ? left : right;

	}

	// public int maxPathSum(TreeNode root) {
	// int temp = recursive(root);
	// return max;
	// }

	public List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		if (root == null)
			return null;
		int cur = 1;
		int next = 0;

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		List<Integer> temp = new ArrayList<Integer>();
		q.offer(root);

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			temp.add(node.val);
			cur--;
			if (node.left != null) {
				q.offer(node.left);
				next++;
			}
			if (node.right != null) {
				q.offer(node.right);
				next++;
			}

			if (cur == 0) {
				l.add(temp);
				cur = next;
				next = 0;
				temp = new ArrayList<Integer>();
			}
		}

		return l;
	}

	// public int recursive(TreeNode node) {
	// if (node == null)
	// return 0;
	// int left = recursive(node.left);
	// int right = recursive(node.right);
	// int cur = node.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
	// max = Math.max(max, cur);
	// return node.val + Math.max(left, Math.max(0, right));
	// }

	List<Integer> l = new ArrayList<Integer>();
	List<List<Integer>> la = new ArrayList<List<Integer>>();

	public List<Integer> rightSideView(TreeNode root) {
		if (root == null)
			return l;
		dfs(root, 0);
		for (int i = 0; i < la.size(); i++) {
			l.add(la.get(i).get(la.get(i).size() - 1));
		}

		return l;

	}

	public void dfs(TreeNode root, int dept) {
		if (la.get(dept) == null) {
			List<Integer> level = new ArrayList<Integer>();
			level.add(root.val);
			la.add(level);
		} else {
			la.get(dept).add(root.val);
		}

		if (root.left == null && root.right == null)
			return;

		if (root.left != null)
			dfs(root.left, dept + 1);
		if (root.right != null)
			dfs(root.right, dept + 1);

		return;
	}

	public static int Maxdeep = 0;

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		depth(root, 1);
		return Maxdeep;
	}

	public void depth(TreeNode node, int deep) {
		if (node.left == null && node.right == null) {
			if (deep > Maxdeep)
				Maxdeep = deep;
			return;
		}

		if (node.left != null)
			depth(node.left, deep + 1);
		if (node.right != null)
			depth(node.right, deep + 1);

	}

	List<List<Integer>> l2 = new ArrayList<List<Integer>>();

	public List<List<Integer>> levelOrder1(TreeNode root) {
		if (root == null)
			return l2;
		level(root, 0);
		return l2;

	}

	public boolean result = true;

	public boolean isBalanced2(TreeNode root) {
		treeDeep2(root);
		return result;
	}

	public int treeDeep2(TreeNode root) {
		if (root == null)
			return 0;

		int left = treeDeep2(root.left);
		int right = treeDeep2(root.right);

		if (Math.abs(left - right) > 1)
			result = false;

		return (left > right) ? left + 1 : right + 1;
	}

	public void level(TreeNode root, int deep) {
		if (l2.size() <= deep) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.add(root.val);
			l2.add(temp);
		} else {
			l2.get(deep).add(root.val);
		}
		if (root.left != null) {
			level(root.left, deep + 1);
		}
		if (root.right != null) {
			level(root.right, deep + 1);
		}

		return;
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		return null;

	}

	public int sumNumbers(TreeNode root) {
		return numrec(root, root.val);
	}

	public int pathrec(TreeNode root, int rootvalue) {
		int left = 0;
		int right = 0;
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return rootvalue;
		if (root.left != null)
			left = numrec(root.left, rootvalue * 10 + root.left.val);
		if (root.right != null)
			right = numrec(root.right, rootvalue * 10 + root.right.val);

		return left + right;
	}

	public int numrec(TreeNode root, int rootvalue) {
		int left = 0;
		int right = 0;
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return rootvalue;
		if (root.left != null)
			left = numrec(root.left, rootvalue * 10 + root.left.val);
		if (root.right != null)
			right = numrec(root.right, rootvalue * 10 + root.right.val);

		return left + right;
	}

	public void buildleft(TreeNode root, ArrayList<Integer> l) {
		if (root != null) {
			buildleft(root.left, l);
			l.add(root.val);
			buildleft(root.right, l);
		} else {
			l.add(Integer.MAX_VALUE);
		}
	}

	public void buildright(TreeNode root, ArrayList<Integer> l) {
		if (root != null) {
			buildright(root.right, l);
			l.add(root.val);
			buildright(root.left, l);
		} else {
			l.add(Integer.MAX_VALUE);
		}
	}

	public boolean isSymmetric(TreeNode root) {
		ArrayList<Integer> lleft = new ArrayList<Integer>();
		ArrayList<Integer> lright = new ArrayList<Integer>();
		buildleft(root, lleft);
		buildright(root, lright);

		if (lleft.size() != lright.size())
			return false;

		for (int i = 0; i < lleft.size(); i++) {
			int m = lleft.get(i);
			int n = lright.get(i);
			if (m != n) {
				return false;
			}
		}
		return true;
	}

	public void buildl(TreeNode root, ArrayList<Integer> l) {
		if (root != null) {
			l.add(root.val);
			buildl(root.left, l);
			buildl(root.right, l);
		} else {
			l.add(Integer.MAX_VALUE);
		}
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		buildl(p, l1);
		buildl(q, l2);
		if (l1.size() != l2.size()) {
			return false;
		} else {
			for (int i = 0; i < l1.size(); i++) {
				int m = l1.get(i);
				int n = l2.get(i);
				if (m != n) {
					return false;
				}
			}
		}
		return true;
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<TreeNode> l = new ArrayList<TreeNode>();
		TreeNode t = root;

		while (t != null) {
			if (t.left != null) {
				l.add(t.left);
			} else {
				l.add(null);
			}

			if (t.right != null) {
				l.add(t.right);
			} else {
				l.add(null);
			}
		}
		return null;

	}

	public static TreeNode connect(TreeNode root) {
		if (root != null) {
			ArrayList<TreeNode> al = new ArrayList<TreeNode>();
			al.add(root);
			int level = 1;
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i).left != null && al.get(i).left != null) {
					al.add(al.get(i).left);
					al.add(al.get(i).right);
				}
				if (i == ((int) Math.pow(2.0, (double) level)) - 2) {
					al.get(i).next = null;
					level++;
				}

			}

		}

		return root;
	}

	public int minDepth(TreeNode root) {

		if (root == null)
			return 0;
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return (left == 0 || right == 0) ? left + right + 1 : Math.min(left,
				right) + 1;

	}

	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return null;
		List<Integer> l = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.push(root);
		while (!s.isEmpty()) {
			TreeNode temp = s.pop();
			l.add(temp.val);

			if (temp.right != null) {
				s.push(temp.right);
			}

			if (temp.left != null) {
				s.push(temp.left);
			}
		}

		return l;

	}

	public int treeDepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = treeDepth(root.left);
		int right = treeDepth(root.right);
		return (left > right) ? left + 1 : right + 1;
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		int left = treeDepth(root);
		int right = treeDepth(root);
		int diff = Math.abs(left - right);

		if (diff > 1) {
			return false;
		}

		return isBalanced(root.left) && isBalanced(root.right);
	}
}
