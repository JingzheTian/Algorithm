
public class titleToNumber {
	public static void main(String[] args) {
		String s = "BB";
		titleToNumber t = new titleToNumber();
		System.out.println(t.titleToNum(s));
	}
	 public int titleToNum(String s) {
		 int total=0;
		 int length = s.length();
         for(int i=0;i<length;i++)
         {
			 total += Math.pow(26, length-i-1) * (s.charAt(i)- 'A'+1);
		 }
		 
		 return total;
	 }

}
