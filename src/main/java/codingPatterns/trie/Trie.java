package codingPatterns.trie;

import java.util. * ;


public class Trie {

    TrieNode root;

    public Trie() {
        // Write your code here
        this.root = new TrieNode();
    }

    // inserting string in trie
    public void insert(String word) {

        TrieNode traverseTrieNode = root;

        int i = 0;
        while(i < word.length()) {
            char ch = word.charAt(i);
            if(!traverseTrieNode.children.containsKey(ch)) {
                traverseTrieNode.children.put((Character)ch, new TrieNode());
            }
            traverseTrieNode = traverseTrieNode.children.get(ch);
            ++i;
        }
        traverseTrieNode.isWord = true;
    }

    // searching for a string
    public boolean search(String word) {
        int i = 0;
        TrieNode traverseTrieNode = root;
        while(i < word.length()) {
            char ch = word.charAt(i);
            if(traverseTrieNode.children.containsKey(ch)) {
                traverseTrieNode = traverseTrieNode.children.get(ch);
                ++i;
            } else {
                return false;
            }
        }
        return traverseTrieNode.isWord;
    }

    // searching for a prefix
    public boolean searchPrefix(String prefix) {
        int i = 0;
        TrieNode traverseTrieNode = root;
        while(i < prefix.length()) {
            char ch = prefix.charAt(i);
            if(traverseTrieNode.children.containsKey(ch)) {
                traverseTrieNode = traverseTrieNode.children.get(ch);
                ++i;
            } else {
                return false;
            }
        }
        return true;
    }

    // Driver Code
    public static void main(String args[]) {
        List < String > keys = Arrays.asList("the", "a", "there", "answer");
        Trie trieOfKeys = new Trie();
        int num = 1;
        for (String x: keys) {
            System.out.println(num + ".\tInserting key: '" + x + "'");
            trieOfKeys.insert(x);
            num += 1;
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
        List < String > search = Arrays.asList("a", "answer", "xyz", "an");
        for (String y: search) {
            System.out.println(num + ".\tSearching key: '" + y + "'");
            System.out.println("\tKey found? " + trieOfKeys.search(y));
            num += 1;
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
        List < String > searchPrefix = Arrays.asList("b", "an");
        for (String z: search) {
            System.out.println(num + ".\tSearching prefix: '" + z + "'");
            System.out.println("\tPrefix found? " + trieOfKeys.searchPrefix(z));
            num += 1;
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
