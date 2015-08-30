package google;

import java.util.Hashtable;

public class LRUCache {
	class DLNode {
		int key;
		int value;
		DLNode pre;
		DLNode next;
	}

	public void addNode(DLNode node) {
		node.pre = head;
		node.next = head.next;
		head.next.pre = node;
		head.next = node;
	}

	public void removeNode(DLNode node) {
		DLNode pre = node.pre;
		DLNode next = node.next;
		pre.next = next;
		next.pre = pre;
	}

	public void moveToHead(DLNode node) {
		this.removeNode(node);
		this.addNode(node);
	}

	public DLNode deleteLast() {
		DLNode node = tail.pre;
		this.removeNode(node);
		return node;

	}

	private Hashtable<Integer, DLNode> cache = new Hashtable<Integer, DLNode>();
	private int capacity;
	public int size;
	private DLNode head;
	private DLNode tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		head = new DLNode();
		tail = new DLNode();
		head.next = tail;
		head.pre = null;
		tail.pre = head;
		tail.next = null;
	}

	public int get(int key) {
		DLNode node = cache.get(key);
		if (node == null)
			return -1;
		else
			this.moveToHead(node);

		return node.value;
	}

	public void set(int key, int value) {
		DLNode node = cache.get(key);
		if (node == null) {
			DLNode newNode = new DLNode();
			newNode.key = key;
			newNode.value = value;
			size++;

			if (size > capacity) {
				DLNode tail = this.deleteLast();
				this.cache.remove(tail.key);
				size--;
			}
			this.addNode(newNode);
			this.cache.put(key, newNode);
		} else {
			node.value = value;
			this.moveToHead(node);
		}

	}
}
