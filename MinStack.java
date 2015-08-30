import java.util.LinkedList;


class MinStack {
	LinkedList<Integer> list = new LinkedList<Integer>();
	LinkedList<Integer> minList = new LinkedList<Integer>();

	public void push(int x) {
	    list.add(x);
	    if(minList.size()==0){
	        minList.add(x);
	    }else{
	        if(x<=minList.getLast()){
	            minList.add(x);
	        }
	    }
	}

	public void pop() {
	    int x = list.getLast();
	 if(x == minList.getLast() ){
	    minList.removeLast();
	 }
	 list.removeLast();
	}

	public int top() {
	   return list.get(list.size()-1);
	}

	public int getMin() {
	    return minList.getLast();

	}
	
	
	
	public static void main(String[] args) {
		MinStack m = new MinStack();
		m.push(0);
		m.push(7);
		m.push(8);
		m.push(0);
		
		System.out.println(m.getMin());
		m.pop();
		System.out.println(m.getMin());
	}
}