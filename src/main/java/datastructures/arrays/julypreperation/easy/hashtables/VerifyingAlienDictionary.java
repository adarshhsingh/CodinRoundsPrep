package datastructures.arrays.julypreperation.easy.hashtables;

import java.util.Hashtable;

public class VerifyingAlienDictionary {

    public static void main(String[] args) {
        VerifyingAlienDictionary obj = new VerifyingAlienDictionary();
        String words[] = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(obj.isAlienSorted(words, order));
        words = new String[]{"word","world","row"};
        order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(obj.isAlienSorted(words, order));
        words = new String[]{"apple","app"};
        order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(obj.isAlienSorted(words, order));
        words = new String[]{"kuvp","q"};
        order = "ngxlkthsjuoqcpavbfdermiywz";
        System.out.println(obj.isAlienSorted(words, order));
    }


    public boolean isAlienSorted(String[] words, String order) {
        Hashtable<Character, Integer> alienOrder = new Hashtable<>();
        for (int i = 0; i < order.length(); i++) {
            alienOrder.put(order.charAt(i), i);
        }

        for (int i = 1; i < words.length; i++) {
            if (!isOrdered(words[i - 1], words[i], alienOrder)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrdered(String word1, String word2, Hashtable<Character, Integer> alienOrder) {
        int len1 = word1.length();
        int len2 = word2.length();

        int minLen = Math.min(len1, len2);

        for(int i=0; i<minLen; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if(c1 != c2) {
                return alienOrder.get(c1) < alienOrder.get(c2);
            }
        }
        return (len1 < len2);
    }
}
