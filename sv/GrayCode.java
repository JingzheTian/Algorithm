package sv;

public class GrayCode {
	public static int checkGrayCode(byte term1, byte term2) {
		int x = (term1 ^ term2);
		System.out.println(x);

		System.out.println(Integer.toBinaryString(10) + " "
				+ Integer.toBinaryString(-10));

		return ((x > 0) && ((x & -x) == x)) ? 1 : 0;
	}

	

	public static void main(String[] args) {
		byte term1 = (byte) 1001, term2 = (byte) 1011;

		System.out.println(Integer.toBinaryString(5 & -5));
		System.out.println(term1 + " " + term2);
		int out = checkGrayCode(term1, term2);

		System.out.println(out);
	}

}
