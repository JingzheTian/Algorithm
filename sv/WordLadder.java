package sv;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	public static void main(String[] args) {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("hot");
		wordDict.add("dot");
		wordDict.add("dog");
		wordDict.add("lot");
		wordDict.add("log");
		
		System.out.println(new WordLadder().ladderLength("hit", "cog", wordDict));
		
	}
	
	
	public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        wordDict.add(endWord);
        int step = 0;
        
        while(!queue.isEmpty()){
            LinkedList<String> list = new LinkedList<String>();
            step++;
            
            while(!queue.isEmpty()){
               String s = queue.poll();             
               if(s.equals(endWord)){
                 return step;
               }
               char[] c = s.toCharArray();
               for(int i = 0;i<c.length;i++){
                   for(char j = 'a';j<='z';j++){
                       char temp = c[i];
                       c[i] = j;
                       String temps = String.copyValueOf(c);
                       c[i] = temp;
                       
                       if(wordDict.contains(temps)){
                           list.add(temps);
                           wordDict.remove(temps);
                       }
                   }
               }
            }
            queue = list;
        }
        return step;   
    }
}
