package educative;

/**
 * This is exactly what the knowing what to track pattern does.
 * It involves counting the occurrences of elements in a given data structure,
 * mostly an array or a string,
 * and using this frequency information to solve the problem efficiently.
 *
 * The pattern can be divided into two main phases:
 * Counting phase: This is to iterate through the elements of the data structure
 * and count the frequency of each element. We can use different data
 * structures to achieve this, such as hash maps, dictionaries, arrays, or even simple variables.
 *
 * Utilization phase: Once the frequencies are calculated, we can use this
 * information to solve the specific problem at hand.
 * This could involve any task that benefits from knowing the frequency of elements, such as:
 * - Find the most frequent element
 * - Identify elements that occur only once.
 * - Check if two arrays are permutations of each other
 * - Check if the player wins the game.
 */
public class KnowingWhatToTrackPattern {

    public static void main(String[] args) {
        System.out.println(permutePalindrome("abb"));
    }
    /**
     * For a given string, st, find whether a permutation of this string
     * is a palindrome. You should return TRUE if such a permutation
     * is possible and FALSE if it isn’t possible.
     *
     * Constraints:
     * 1 ≤  st.length  ≤1000
     * The string will contain lowercase English letters.
     */

    public static boolean permutePalindrome(String st) {
        int chars[] = new int[26];
        for (int i=0; i< st.length(); i++) {
            int charInt = st.charAt(i)-'a';
            chars[charInt]++;
        }
        boolean isEven = st.length()%2 == 0;
        boolean possiblePalin = true;
        if(isEven) {
            // none of the alphabest should be odd occurring
            for(int i=0;i<26; i++) {
                if(chars[i]%2==1) possiblePalin = false;
            }
        } else {
            // only one of the alphabet should be odd occuring
            boolean oneOddFound = false;
            for(int i=0;i<26; i++) {
                if (chars[i] % 2 == 1) {
                    if(!oneOddFound) {
                        oneOddFound = true;
                        continue;
                    }
                    possiblePalin = false;
                }
            }
        }
        return possiblePalin;
    }

}
