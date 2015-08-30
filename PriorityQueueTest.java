import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static int findKthLargest(int[] nums, int k) {
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				// TODO Auto-generated method stub
				return n2 - n1;
			}
		};

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, comp);
		for (int i = 0; i < nums.length; i++) {
			if(pq.size()<k)pq.add(nums[i]);
			else{
				if(nums[i]<pq.peek()){
					pq.poll();
					pq.add(nums[i]);
				}
			}
		}
		
		return pq.peek();

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

		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length,
				comp);

		for (int i = 0; i < lists.length; i++) {
			ListNode temp = lists[i];
			while (temp != null) {
				pq.add(temp);
				temp = temp.next;
			}
		}

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;

		while (pq.peek() != null) {
			dummy.next = pq.poll();
			dummy = dummy.next;
		}

		return head.next;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 6, 3, 6, 11, 3, 10, 192 };
		System.out.println(findKthLargest(a, 3));

//		Comparator<Integer> comp = new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				if (o1 < o2)
//					return 1;
//				else if (o1 > o2)
//					return -1;
//				else
//					return 0;
//			}
//		};
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2, comp);
//		for (int i = 0; i < a.length; i++) {
//			pq.add(a[i]);
//		}
//
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(pq.poll() + " ");
//		}

	}
}
