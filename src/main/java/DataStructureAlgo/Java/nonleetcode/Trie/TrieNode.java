package DataStructureAlgo.Java.nonleetcode.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Trie Node
 * Link: https://leetcode.com/problems/trie-node/
 * Description:
 * Java.Trie Node
 * @author iitgu
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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
