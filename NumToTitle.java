import java.util.Stack;

public class NumToTitle {
	public static void main(String[] args) {
		int i = 52;
		NumToTitle t = new NumToTitle();
		System.out.println(t.numToTitle(i));
	}

	public String numToTitle(int num) {
		String output = "";
		Stack<Integer> s = new Stack<Integer>();
		while (num - 26 > 0) {
			int temp = num % 26;
			if(temp == 0)
			{
				s.push(26);
				num = num / 26 - 1;
			}else{
				s.push(temp);
				num = num / 26;
			}
			
		}
		s.push(num);

		while (!s.isEmpty()) {
			output += (char) ((int) s.pop() - 1 + 'A');
		}
		System.out.println(output);

		return output;
	}
}
