package sv;

public class WordDic {
	class TrieNode {
	    // Initialize your data structure here.
	      private static final int num = 26;
	      private TrieNode[] children;
	      public boolean exsit;
	      public TrieNode() {
			children = new TrieNode[num];
			exsit = false;
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
	}
	
	
    private TrieNode root;

    public WordDic() {
        root = new TrieNode();
    }
    

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = this.root;
        
        for(int i = 0;i<word.length();i++)
        {
            if(cur.getChild(word.charAt(i)-'a') == null){
               cur.setChild(word.charAt(i)-'a', new TrieNode());
            }
            
            cur = cur.getChild(word.charAt(i)-'a');
        }
        
        cur.exsit = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return bfs(word, root, 0);
    }
    
    private boolean bfs(String word, TrieNode root, int index){
         if(index == word.length()-1){
             if(root.exsit == true)
             return true;
             else
             return false;
         }
        
         TrieNode cur = root;
         boolean res = false;
         if(word.charAt(index) == '.')
         {
             for(TrieNode child : cur.getChildren()){
                 if(child!=null)
                 res = res || bfs(word, child, index+1);
             }
         }else{
             if(cur.getChild(word.charAt(index)-'a')==null)
             return false;
             else{
               bfs(word, cur.getChild(word.charAt(index)-'a'), index+1);  
             }
         }
         
         return res;
    }
}
