package sv;

import java.util.Stack;

public class StackWithMin {
	Stack<Integer> s1 = new Stack<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();
    
    public void push(int x) {
        s1.push(x);
        if(s2.isEmpty() || x <= s2.peek())
        s2.push(x);
    }

    public void pop() {
        int i = s1.pop();
        if(s2.isEmpty())
        return;
        else{
            if(i == s2.peek())
            s2.pop();
        }
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }

}
