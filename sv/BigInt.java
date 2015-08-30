package sv;

public class BigInt {
	boolean negtive;
	String num;
	int length;



	public BigInt(String num) {
		if (num.charAt(0) == '-') {
			this.negtive = false;
			this.num = new StringBuffer(num.substring(1, num.length()))
					.reverse().toString();
			this.length = num.length() - 1;
		} else {
			this.negtive = true;
			this.num = new StringBuffer(num).reverse().toString();
			this.length = num.length();
		}

	}

	public BigInt(int num) {
		if (num < 0) {
			this.negtive = false;
			this.num = new StringBuffer(String.valueOf(-num)).reverse()
					.toString();
		} else {
			this.negtive = true;
			this.num = new StringBuffer(String.valueOf(num)).reverse()
					.toString();
		}
	}

	public String toString() {
		if (this.negtive == false) {
			return "-" + new StringBuffer(this.num).toString();
		} else {
			return new StringBuffer(this.num).toString();
		}
	}

	public BigInt add(BigInt b2) {
		if (this.negtive != b2.negtive) {

		}

		int add = 0;
		StringBuffer sb = new StringBuffer();

		int i = 0;
		while (i < length) {
			if (i < this.num.length() && i < b2.num.length()) {
				int num = Integer.valueOf(String.valueOf(this.num.charAt(i)))
						+ Integer.valueOf(String.valueOf(b2.num.charAt(i)));
				if (add == 1) {
					num++;
				}
				if (num > 9) {
					sb.append(num % 10);
					add = 1;
				} else {
					add = 0;
					sb.append(num);
				}
			} else if (i < this.num.length()) {
				int num = Integer.valueOf(String.valueOf(this.num.charAt(i)));
				if (add == 1) {
					num++;
				}
				if (num > 9) {
					sb.append(num % 10);
					add = 1;
				} else {
					add = 0;
					sb.append(num);
				}
			} else if (i < b2.num.length()) {
				int num = Integer.valueOf(String.valueOf(b2.num.charAt(i)));
				if (add == 1) {
					num++;
				}
				if (num > 9) {
					sb.append(num % 10);
					add = 1;
				} else {
					add = 0;
					sb.append(num);
				}
			}
			i++;
		}
		if (add == 1) {
			sb.append(1);
		}
		BigInt res = new BigInt(sb.toString());
		if (this.negtive == false) {
			res.negtive = false;
		}

		return res;
	}
	
//	public BigInt minus(BigInt b2){
//		
//	}

	public BigInt mutiply(BigInt b2) {
		boolean negtive = !(this.negtive ^ b2.negtive);
		System.out.println(negtive);
		int[] number = new int[this.length * b2.length];

		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < b2.length; j++) {
				int num1 = this.num.charAt(i) - '0';
				int num2 = b2.num.charAt(j) - '0';
				number[i + j] += num1 * num2;
			}
		}
		int i = number.length - 1;
		while (i >= 0 && number[i] == 0) {
			i--;
		}

		StringBuffer sb = new StringBuffer();
		int carry = 0;
		for (int j = 0; j <= i; j++) {
			sb.append(number[j] % 10 + carry);
			carry = number[j] / 10;
		}

		if (carry != 0) {
			sb.append(new StringBuffer(String.valueOf(carry)).reverse());
		}

		BigInt res = new BigInt(sb.toString());
		if (negtive == false) {
			res.negtive = false;
		}

		return res;

	}

	public static void main(String[] args) {
		BigInt b1 = new BigInt("164");
		BigInt b2 = new BigInt("33");

		System.out.println(b1.num);
		System.out.println(b2.num);
		System.out.println(b1.mutiply(b2).toString());
	}
}
