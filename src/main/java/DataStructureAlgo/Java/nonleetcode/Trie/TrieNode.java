package DataStructureAlgo.Java.nonleetcode.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Java.Trie Node
 * 
 * @author iitgu
 *
 */
public class TrieNode {

	private char value;
	private boolean isEnd;
	private final Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public TrieNode(char value, boolean isEnd) {
		this.value = value;
		this.isEnd = isEnd;
	}

}
