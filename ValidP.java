import java.util.Stack;

public class ValidP {
	public static void main(String[] args) {
		String s = "((";
		ValidP v = new ValidP();
		System.out.println(v.isValid(s));
	}
	
	public boolean isValid(String s) {
		if(s.length() == 0 || s.length() == 1 || s.length() % 2 != 0)
		{
			return false;
		}
		System.out.println(s);
		Stack<Character> st = new Stack<Character>();
		
		char[] carray = s.toCharArray();
		
		for(int i = 0; i< carray.length; i++)
		{
			if(carray[i] == '{' || carray[i] == '[' || carray[i] == '(')
			{
				st.push(carray[i]);
			}else if(carray[i] == '}' || carray[i] == ']' || carray[i] == ')')
			{
				if(i==0)
				{
					return false;
				}
				char a1 = st.pop();
				if(a1 == '{' && carray[i] != '}')
				{
					return false;
				}else if(a1 == '[' && carray[i] != ']')
				{
					return false;
				}else if(a1 == '(' && carray[i] != ')')
				{
					return false;
				}
			}
		}
		
		if(!st.isEmpty())
		{
			return false;
		}
		return true;
    }
}
