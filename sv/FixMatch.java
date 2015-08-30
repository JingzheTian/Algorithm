package sv;

public class FixMatch {
	public static void main(String[] args) {
		System.out.println(new FixMatch().solution("abadabaa"));
	}

	public int solution(String s) {
		int[] lsp = new int[s.length()];
		lsp[0] = 0; // Base case
		for (int i = 1; i < s.length(); i++) {
			// Start by assuming we're extending the previous LSP
			int j = lsp[i - 1];
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = lsp[j - 1];
			if (s.charAt(i) == s.charAt(j))
				j++;
			lsp[i] = j;
		}
		return lsp[lsp.length - 1];
	}
}
