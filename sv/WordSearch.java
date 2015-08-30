package sv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
		public static void main(String[] args) {
			char[][] board = {{'a'}};
			String[] s = {"a"};
			
			System.out.println(new WordSearch().findWords(board, s));
		}
	 	public List<String> findWords(char[][] board, String[] words) {
	        Trie trie = new Trie();
	        List<String> res = new ArrayList<String>();
	        Set<String> hs = new HashSet<String>();
	        for(String word:words){
	           trie.insert(word);
	        }
	        for (int i = 0; i < board.length; i++) {
	            for (int j = 0; j < board[0].length; j++) {
	            	dfs(board, i,j,"",hs,trie);;
	            }
	        }
	        
	        res.addAll(hs);
	        return res;
	    }
	    
	    public void dfs(char[][] board, int i, int j, String temp, Set<String> hs, Trie trie){
	        if(i<0 || j<0 || i>board.length-1 || j > board[0].length-1 || board[i][j] == '#')
	        return;
	        temp+=board[i][j];
	        if(!trie.startsWith(temp))return;
	        if(trie.search(temp))
	        {
	            hs.add(temp);
	            return;
	        }
	        char c = board[i][j];
	        board[i][j] = '#';
	        dfs(board,i+1,j,temp,hs,trie);
	        dfs(board,i-1,j,temp,hs,trie);
	        dfs(board,i,j+1,temp,hs,trie);
	        dfs(board,i,j-1,temp,hs,trie);
	        board[i][j] = c;
	    }
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

	    class Trie {
	        private TrieNode root;

	        public Trie() {
	            root = new TrieNode();
	        }

	        // Inserts a word into the trie.
	        public void insert(String word) {
	            TrieNode newRoot = root;
	            for(int i = 0;i<word.length();i++)
	            {
	                if(newRoot.getChild(word.charAt(i) - 'a') == null){
	                   newRoot.setChild(word.charAt(i) - 'a', new TrieNode());
	                }
	                newRoot = newRoot.getChild(word.charAt(i)-'a');
	            }
	            newRoot.exsit = true;
	            
	        }

	        // Returns if the word is in the trie.
	        public boolean search(String word) {
	            TrieNode newRoot = root;
	            for(int i = 0;i<word.length();i++)
	            {
	                if(newRoot.getChild(word.charAt(i) - 'a') == null){
	                  return false; 
	                }
	                newRoot = newRoot.getChild(word.charAt(i)-'a');
	            }
	            
	            return newRoot.exsit;
	        }

	        // Returns if there is any word in the trie
	        // that starts with the given prefix.
	        public boolean startsWith(String prefix) {
	            TrieNode newRoot = root;
	            for(int i = 0;i<prefix.length();i++)
	            {
	                if(newRoot.getChild(prefix.charAt(i) - 'a') == null){
	                   return false;
	                }
	                newRoot = newRoot.getChild(prefix.charAt(i)-'a');
	            }
	            
	            return true;
	        }
	    }
}
