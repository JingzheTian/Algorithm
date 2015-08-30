package sv;

import java.util.Stack;

public class MyQueue2 {
	private Stack<Integer> queue;
    private Stack<Integer> tempS;
    
    public MyQueue2(){
        queue = new Stack<Integer>();
        tempS = new Stack<Integer>();
    }
    
    // Push element x to the back of queue.
    public void push(int x) {
        queue.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(!queue.isEmpty()){
            tempS.push(queue.pop());
        }
        tempS.pop();
        while(tempS.isEmpty()){
            queue.push(queue.pop());
        }
        
    }

    // Get the front element.
    public int peek() {
         while(queue.isEmpty()){
            tempS.push(queue.pop());
        }
        int peek = tempS.peek();
        while(tempS.isEmpty()){
            queue.push(queue.pop());
        }
        return peek;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
