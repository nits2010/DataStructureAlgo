package DataStructureAlgo.Java.nonleetcode.Trie;

import java.util.List;
import java.util.Map;

public class Trie {

	private TrieNode root;
	// dictionary
	private final List<String> dictionary;

	public Trie(List<String> dictionary) {
		this.root = new TrieNode((char) 0, false);
		this.dictionary = dictionary;
	}

	public List<String> getDictionary() {
		return dictionary;
	}

	public TrieNode getRoot() {
		return root;
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}

	public void buildTrie() {

		for (String string : dictionary) {
			TrieNode crawl = root;
			if (null != string) {
				for (int i = 0; i < string.length(); i++) {
					char ch = string.charAt(i);
					Map<Character, TrieNode> child = crawl.getChildren();

					if (child.containsKey(ch)) {
						crawl = child.get(ch);
					} else {
						final TrieNode temp = new TrieNode(ch, false);
						child.put(ch, temp);
						crawl = temp;
					}
				}
				crawl.setEnd(true);
			}
		}

	}

	public void printTrie() {
		printTrie(root, "");

	}

	public void printTrie(TrieNode node, String str) {
		if (null == node)
			return;
		if (node.isEnd()) {
			System.out.println(str);
		}

		for (Character ch : node.getChildren().keySet()) {

			printTrie(node.getChildren().get(ch), str + ch);
		}
	}

	public String prefixMatch(final String input) {
		if (null == input || input.isEmpty())
			return null;
		TrieNode crawl = root;
		StringBuilder result = new StringBuilder();
		int prevMatch = 0;
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			Map<Character, TrieNode> child = crawl.getChildren();

			if (child.containsKey(ch)) {
				result.append(ch);
				crawl = child.get(ch);

				if (crawl.isEnd())
					prevMatch = 1 + i;

			} else
				break;

		}
		if (!crawl.isEnd())
			return result.substring(0, prevMatch);
		else
			return result.toString();
	}

}
