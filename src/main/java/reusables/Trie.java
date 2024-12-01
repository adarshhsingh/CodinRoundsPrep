package reusables;

import java.util.*;

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }


    public void insert(String s) {
        TrieNode current = root;
        for (char c : s.toCharArray()) {
            if(current.children.get(c) == null) {
                System.out.println("add "+ c +" in node"+ current.children);
                current.children.put(c, new TrieNode());
                current.addSuggestion(s);
            }
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public void printTrie() {
        printTrie(root, "", true, "");
    }

    public List<List<String>> getWordsStartingWith(String prefix) {
        TrieNode node = root;
        List<List<String>> result = new ArrayList<>();
        boolean valid = true;

        for (char ch : prefix.toCharArray()) {
            if (valid && node != null && node.children.containsKey(ch)) {
                node = node.children.get(ch);
                result.add(new ArrayList<>(node.suggestions));
            } else {
                valid = false;  // No more valid nodes, stop processing further characters
                result.add(new ArrayList<>());
            }
        }

        return result;
    }

    private void printTrie(TrieNode current, String indent, boolean last, String word) {
        if (current != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "|   ";
            }
            System.out.println(word + (current.isEndOfWord ? "*" : ""));

            List<Character> childrenKeys = new ArrayList<>(current.children.keySet());
            for (int i = 0; i < childrenKeys.size(); i++) {
                char childKey = childrenKeys.get(i);
                printTrie(current.children.get(childKey), indent, i == childrenKeys.size() - 1, word + childKey);
            }
        }
    }
}
