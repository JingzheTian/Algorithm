 
public class BruteForce {
	public static int bF(String s1, String s2)
	{
		for(int i = 0; i<s1.length() - s2.length();i++)
		{
			int j = 0;
			while(j< s2.length() && s1.charAt(i+j) == s2.charAt(j))
			{
				j++;
			}
			if(j == s2.length())
			return i;
			
		}
		return 0;
	}
	
	
	
	
	public static void main(String[] args) {
		String s1 = "pasdfghjklzxcvbnm";
		
		String s2 = "asdfg";
		String s3 = "jkl";
		
		System.out.println(bF(s1,s2));
		System.out.println(bF(s1,s3));
	}
}
