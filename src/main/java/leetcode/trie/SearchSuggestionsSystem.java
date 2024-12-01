package leetcode.trie;


import reusables.PrintModules;
import reusables.Trie;

import java.util.Arrays;

public class SearchSuggestionsSystem {
    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        Trie trie = new Trie();
        Arrays.sort(products);
        for(String s : products) {
            trie.insert(s);
        }
       trie.printTrie();
       String search = "mouse";

       System.out.println(trie.getWordsStartingWith(search));
    }
}
