package leetcode.HARD;

import java.util.HashMap;

public class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {
    String str1;
    String str2;
    int n;
    int m;
    HashMap<String, Boolean> memo;
    public boolean possiblyEquals(String s1, String s2) {
        str1 = s1;
        str2 = s2;
        n = s1.length();
        m = s2.length();
        memo = new HashMap<>();
        /**
         *                              l123e
         *             (l)(123e)     (l1)(123e)     (l12)(3e)
         *
         *
         *
         *
         */
        return backtrack(0, 0, 0);
    }


    private boolean backtrack(int i, int j, int diff) {
        System.out.printf("Back track called i = %d , j = %d and diff = %d \n", i, j, diff);
        if( i == n && j == m) {
            return  (diff == 0);
        }

        if(memo.containsKey(getKey(i, j, diff))) {
                return memo.get(getKey(i, j, diff));
        }

        if( i < n && Character.isDigit(str1.charAt(i))) {
            System.out.printf("  i < n && ith index in str1 is digit");
            int num = 0;
            int k = i;
            while(k < n && Character.isDigit(str1.charAt(k))) {
                num = num * 10 + str1.charAt(k) - '0';
                k = k + 1;
                if (backtrack(k, j, diff - num)) {
                    memo.put(getKey(k, j, diff), true);
                    return true;
                }
            }
        } else if(j < m && Character.isDigit(str2.charAt(j))) {
            System.out.printf("  j < m && Jth index in str2 is digit");
            int num = 0;
            int k = j;
            while(k < m && Character.isDigit(str2.charAt(k))) {
                num = num * 10 + str2.charAt(k) - '0';
                k = k + 1;
                if (backtrack(i, k, diff + num)) {
                    memo.put(getKey(i, k, diff), true);
                    return true;
                }
            }
        } else if(diff == 0) {
            if( i < n && j < m && str1.charAt(i) == str2.charAt(j)) {
                return backtrack(i + 1, j + 1, diff);
            }
        } else if(diff < 0) {
            if(j < m) {
                return backtrack(i, j + 1, diff + 1);
            }
        } else if(diff > 0) {
            if(i < n) {
                return backtrack(i + 1, j, diff - 1);
            }
        }

        memo.put(getKey(i, j, diff), false);
        return false;
    }

    private String getKey(int i, int j, int diff) {
        return (i+"#"+j+"#"+diff+"#");
    }

    public static void main(String[] args) {
        CheckIfAnOriginalStringExistsGivenTwoEncodedStrings obj = new CheckIfAnOriginalStringExistsGivenTwoEncodedStrings();

        String input[][] = {{"internationalization", "i18n"} /*,{"l123e","44"},{"a5b","c5b"}, {"1112s", "g841"}*/};

        for(String[] inp : input) {
            System.out.println(obj.possiblyEquals(inp[0], inp[1]));
        }
    }
}
