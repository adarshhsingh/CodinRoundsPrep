package reusables;

import java.util.*;

public class TrieNode {
        Map<Character, TrieNode> children;
        List<String> suggestions ;

        boolean isEndOfWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isEndOfWord = false;
            suggestions = new ArrayList<>();
        }

    void addSuggestion(String product) {
        suggestions.add(product);
        Collections.sort(suggestions);
        if (suggestions.size() > 3) {
            suggestions.remove(suggestions.size() - 1);
        }
    }

}
