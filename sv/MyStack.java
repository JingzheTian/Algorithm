package sv;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
	private Queue<Integer> stack;

	public MyStack() {
		this.stack = new LinkedList<Integer>();
	}

	// Push element x onto stack.
	public void push(int x) {
		stack.add(x);
	}

	// Removes the element on top of the stack.
	public void pop() {
		int size = stack.size();


		while (size > 1) {
			stack.add(stack.poll());
			size--;
		}
		// int res = stack.poll();
		stack.poll();

		//stack.addAll(temp);

		// return res;
	}

	// Get the top element.
	public int top() {
		int size = stack.size();

		//Queue<Integer> temp = new LinkedList<Integer>();

		while (size > 1) {
			stack.add(stack.poll());
			size--;
		}
		int res = stack.peek();
		stack.add(stack.poll());
		//stack.addAll(temp);

		return res;

	}

	// Return whether the stack is empty.
	public boolean empty() {
		if (stack.size() == 0)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		MyStack s = new MyStack();
		s.push(1);
		s.push(2);
		s.push(3);
		
		System.out.println(s.top());
		s.pop();
		System.out.println(s.top());
		
	}
}
