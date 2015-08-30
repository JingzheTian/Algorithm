package sv;

public class RemoveVowel {
	public static void main(String[] args) {
		System.out.println(new RemoveVowel()
				.solution("abcdefghigklmnopqrstuvwxyz"));
	}

	public String solution(String input) {
		char[] c = input.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			if (!isVowel(c[i])) {
				sb.append(c[i]);
			}
		}

		return sb.toString();

	}

	public boolean isVowel(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
				|| c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
			return true;
		}
		return false;
	}
}
