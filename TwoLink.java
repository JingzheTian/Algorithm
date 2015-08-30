import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class TwoLink {
	public static void main(String[] args) {
		ListNode head1 = new ListNode(1);
		ListNode a1 = new ListNode(2);
		// ListNode a2 = new ListNode(5);
		// ListNode a3 = new ListNode(7);

		// head1.next = a1;
		// // a1.next = a2;
		//
		// ListNode head2 = new ListNode(2);
		// ListNode b1 = new ListNode(4);
		// ListNode b2 = new ListNode(6);
		// ListNode b3 = new ListNode(8);
		//
		head1.next = a1;
		// // a1.next = a2;
		// // a2.next = a3;
		//
		// head2.next = b1;
		// b1.next = b2;
		// b2.next = b3;
		System.out.println(isPalindrome(head1));
	}

	

	public static boolean isPalindrome(ListNode head) {
		if (head == null)
			return true;

		ListNode temp = head;
		int len = 0;
		while (temp != null) {
			len++;
			temp = temp.next;
		}

		if (len < 2)
			return true;

		temp = head.next;
		ListNode pre = head;
		head.next = null;

		for (int i = 0; i < len / 2 - 1; i++) {
			ListNode next = temp.next;
			temp.next = pre;
			pre = temp;
			temp = next;
		}

		if (len % 2 == 1)
			temp = temp.next;

		while (temp != null) {
			if (temp.val != pre.val)
				return false;
			temp = temp.next;
			pre = pre.next;
		}

		return true;
	}

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null)
			return null;

		int size = 0;
		ListNode temp = head;
		while (temp != null) {
			size++;
			temp = temp.next;
		}

		k = k % size;

		ListNode first = head;
		temp = head;

		while (k > 0) {
			first = first.next;
		}

		while (first.next != null) {
			first = first.next;
			temp = temp.next;
		}

		first.next = head;

		ListNode newHead = temp.next;
		temp.next = null;

		return newHead;

	}

	public static ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		recursive(head, newHead);
		return newHead;
	}

	public static void recursive(ListNode head, ListNode newHead) {
		if (head == null)
			return;
		ListNode temp = head;
		head = head.next;
		temp.next = newHead;
		newHead = temp;
		recursive(head, newHead);
	}

	public static void reorderList2(ListNode head) {
		if (head == null)
			return;

		Stack<ListNode> s = new Stack<ListNode>();

		ListNode temp = head;
		int size = 0;
		while (temp != null) {
			s.push(temp);
			temp = temp.next;
			size++;
		}

		int time = (size - 1) / 2;
		System.out.println(time);
		temp = head;
		while (time-- > 0) {
			ListNode top = s.pop();
			top.next = temp.next;
			temp.next = top;

			temp = top.next;

		}
		s.pop().next = null;

	}

	public ListNode mergeKLists(ListNode[] lists) {
		Comparator<ListNode> comp = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val > o2.val)
					return -1;
				else
					return 0;
			}
		};

		if (lists.length == 0)
			return null;
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(100, comp);

		for (int i = 0; i < lists.length; i++) {
			ListNode temp = lists[i];
			while (temp != null) {
				pq.add(temp);
				temp = temp.next;
			}
		}

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;

		while (pq.size() != 0) {
			dummy.next = pq.poll();
			dummy = dummy.next;
		}
		dummy.next = null;

		return head.next;
	}

	public static ListNode removeElements(ListNode head, int val) {
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode temp = newHead;
		while (temp != null) {
			ListNode next = temp.next;
			while (next != null && next.val == val) {
				next = next.next;
			}

			temp.next = next;
			temp = temp.next;
		}

		return newHead.next;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode head;
		if (l1.val < l2.val) {
			head = new ListNode(l1.val);
			l1 = l1.next;
		} else {
			head = new ListNode(l2.val);
			l2 = l2.next;
		}
		ListNode temp = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				temp.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				temp.next = new ListNode(l2.val);
				l2 = l2.next;
			}

			temp = temp.next;

		}

		while (l1 != null) {
			temp.next = new ListNode(l1.val);
			l1 = l1.next;
			temp = temp.next;
		}

		while (l2 != null) {
			temp.next = new ListNode(l2.val);
			l2 = l2.next;
			temp = temp.next;
		}

		return head;
	}

	public static ListNode numNode(ListNode l1, ListNode l2, int add) {
		if (l1 == null && l2 == null && add == 0)
			return null;

		int sum = 0;
		if (l1 != null) {
			sum += l1.val;
			l1 = l1.next;
		}

		if (l2 != null) {
			sum += l2.val;
			l2 = l2.next;
		}

		sum += add;

		if (sum > 9) {
			sum -= 10;
			add = 1;
		} else {
			add = 0;
		}

		ListNode node = new ListNode(sum);

		node.next = numNode(l1, l2, add);

		return node;

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		return numNode(l1, l2, 0);
	}

	public static void reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null)
			return;

		ListNode move = head;

		while (move.next != null) {
			ListNode temp = move;
			if (temp.next.next == null)
				break;

			while (temp.next.next != null) {
				temp = temp.next;
			}
			temp.next.next = move.next;
			move.next = temp.next;
			temp.next = null;

			move = move.next.next;
		}

	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode head_next = head.next;
		head.next = head_next.next;
		head_next.next = head;
		ListNode point_last = head_next.next;
		ListNode point = head_next.next.next;
		while (point != null && point.next != null) {
			ListNode point_next = point.next;
			point.next = point_next.next;
			point_next.next = point;
			point_last.next = point_next;
			point_last = point_next.next;
			point = point_next.next.next;
		}

		return head_next;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode first = head;
		ListNode second = head;
		while (first != null && n > 0) {
			first = first.next;
			n--;
		}

		if (first == null)
			return head.next;

		while (first.next != null) {
			first = first.next;
			second = second.next;
		}
		// Remove slower.next which is the nth form the end
		second.next = second.next.next;
		return head;

	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		HashSet<ListNode> hs = new HashSet<ListNode>();
		if (headA == null || headB == null) {
			return null;
		}

		while (headA.next != null) {
			hs.add(headA);
			headA = headA.next;
		}

		while (headB.next != null) {
			if (hs.contains(headB)) {
				return headB;
			} else {
				headB = headB.next;
			}
		}

		return null;

	}
}
