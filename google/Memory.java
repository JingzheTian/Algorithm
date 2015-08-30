package google;

import java.util.ArrayList;

class Node{
	private int data;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node(int data) {
		super();
		this.data = data;
	}
	
}

public class Memory {
	private static int Msize = 10;
	private ArrayList<Node> list;
	
	
	
	public Memory() {
		list = new ArrayList<Node>();
	}

	public void push(Node n)
	{
		if(list.size() < Msize){
			list.add(n);
		}else{
			list.remove(0);
			list.add(n);
		}
		
	}
	
	public Node peek()
	{
		return list.get(list.size()-1);
	}
	
	public void use(Node n)
	{
		if(list.contains(n))
		{
			int i = list.indexOf(n);
			list.remove(i);
			list.add(n);
			
		}else{
			throw new NullPointerException();
		}
	}
	
	public static void main(String[] args) {
		Memory m = new Memory();
		for(int i = 0;i<13;i++)
		{
			m.push(new Node(i));
		}
		
		System.out.println(m.peek().getData());
	}
	
}
