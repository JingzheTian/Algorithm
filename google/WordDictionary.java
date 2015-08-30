package google;

class TrieNode {
	// Initialize your data structure here.
	private final int R = 26;
	private final TrieNode[] children;
	private boolean has;

	public TrieNode() {
		children = new TrieNode[R];
		this.setHas(false);
	}

	public TrieNode[] getChildren() {
		return children;
	}

	public TrieNode getChild(int i) {
		if (i >= 26 || i < 0)
			throw new IllegalArgumentException();
		return children[i];
	}

	public void setChild(int i, TrieNode node) {
		children[i] = node;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	public boolean isHas() {
		return has;
	}

}

public class WordDictionary {
	public TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode curr = root;
		for (char c : word.toCharArray()) {
			if (curr.getChild(c - 'a') == null)
				curr.setChild(c - 'a', new TrieNode());
			curr = curr.getChild(c - 'a');
		}
		curr.setHas(true);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return bfs(root, word);

	}

	public boolean bfs(TrieNode node, String word) {
		if (word.length() == 0){
			if(node.isHas())return true;
			else return false;
		}
			
		boolean res = false;
		if (word.charAt(0) == '.') {
			for (TrieNode child : node.getChildren()) {
				if(child != null)
				res = res || bfs(child, word.substring(1, word.length()));
			}
		} else {
			if (node.getChild(word.charAt(0) - 'a') == null)
				return false;
			else {
				res = bfs(node.getChild(word.charAt(0) - 'a'), word.substring(
						1, word.length()));
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		WordDictionary wd = new WordDictionary();
		wd.addWord("ad");
		wd.addWord("dad");
		wd.addWord("mad");
		System.out.println(wd.search("a"));
		System.out.println(wd.search("bad"));
		System.out.println(wd.search(".ad"));
		System.out.println(wd.search("b.."));
	}

}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
