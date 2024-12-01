package datastructures.arrays.meta;

import reusables.PrintModules;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring_ON(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;
        while(right < s.length()) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right-left+1);
            right++;

        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring_ON2(String s) {
        /**
         * 2 pointer - i , j
         * keep i = 0 - at start and move j till no repeated words
         *  store max (defauly max is 1)
         * move pointer i till you go to next char which is not same
         */
        int max = 0; // as -> 0 <= s.length
        int start = 0;
        int next = 0;
        char c[] = s.toCharArray(); // 1-"aaaa" , 2-"abbb", 3-"aaabc" , 4-""
        for (int i = 0; i<c.length; i++) {
            int alphabets[] = new int[1000]; // alphabet/special-char already came - resets each time
            start = (int)c[i];
            int length = 1;
            alphabets[start] = 1;
            for (int j = i+1; j<c.length; j++) {
                next = (int)c[j];
                if(alphabets[next] == 0) {
                    length++;
                    alphabets[next] = 1;
                } else {
                    break;
                }
            }
            max = Math.max(max, length);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwke";
        PrintModules.print(lengthOfLongestSubstring_ON(s));
    }
}
