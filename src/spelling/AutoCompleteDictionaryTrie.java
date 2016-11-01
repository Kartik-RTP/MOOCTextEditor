package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		word = word.toLowerCase();//applying constraint
		TrieNode currentNode = root;
		boolean isAlreadyPresent=true;
		//run loop for the length of the word
		
		for(int i=0;i<word.length();i++){
			if( currentNode.getChild(word.charAt(i))!=null  ){
				currentNode = currentNode.getChild(word.charAt(i));
				
			}else{//this node is not present
				currentNode = currentNode.insert(word.charAt(i));//therefore we add this node
				isAlreadyPresent = false;//if we add node at any stage , that means it is not present
			}
		}
		//at the end of loop , currentNode represents the node of that word 
		//we should make sure , its isWord property is true
		
		if(!isAlreadyPresent || !currentNode.endsWord() ){
			//i.e. we added the node , we have to set its isWordProperty
			currentNode.setEndsWord(true);
			isAlreadyPresent=false;//because the node was present but the word wasn't
			size++;//we also increase size as no of words increase
		}
		
	    return !isAlreadyPresent; //if its present we return false .. as no add was done
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{	
		
		//Note a word is present if 
		//1)there is a path from root till that text 
		//2) also that node is a word and not an internal node
	    
		s=s.toLowerCase();
		TrieNode currentNode = root;
		for(int i=0;i<s.length();i++){
			if(              ( currentNode = currentNode.getChild(s.charAt(i)) )  == null   ){
				//not present 
				return false;
			}
			
		}//currentNode represents the node which has text same as that of the word
		
		
		if(!currentNode.endsWord()){ // if its an internal word and its endsWord property is false
			return false;
		}
		//if control reaches till here , it means this word exists
		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> predictions = new LinkedList<String>();
    	 prefix = prefix.toLowerCase();//applying constraint
    	 TrieNode currentNode = root;
    	 
    	//find the stem
    	 for(int i=0;i<prefix.length();i++){
    		 if(  (currentNode = currentNode.getChild(prefix.charAt(i)) )==null       ){
    			 //prefix stem not present
    			 return predictions;//as an empty list
    		 }
    		 
    	 }
    	 //reaching here means that  currentNode currently represents stem node
    	 //now apply BFS on currentNode
    	  
    	 predictions = BFS(currentNode,numCompletions);
    	 
    	 
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
         return predictions;
     }
     
     /**helper method for predictCompletions
      * 
      * */
     private List<String> BFS(TrieNode root , int numCompletions){
    	 List<String> words = new LinkedList<String>();
    	 Queue<TrieNode> queue = new LinkedList<TrieNode>();
    	 int noOfWords=0;
    	 queue.add(root);
    	 TrieNode current;
    	 while(noOfWords<numCompletions && queue.size()>0){
    		 current = queue.remove();
    		 if(current.endsWord()){
    			 words.add(current.getText());
    			 noOfWords++;
    		 }
    		 for(char c : current.getValidNextCharacters()){
    			 queue.add(current.getChild(c));
    		 }
    		 
    		
    		 
    	 }
    	 
      	 return words;
     }
     
     

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}