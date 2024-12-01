package leetcode.HARD;

import java.util.*;

public class WordBreakII {
    List<String> result;
    Set<String> dictSet;
    public List<String> wordBreak(String s, List<String> wordDict) {
        result = new ArrayList<>();
        dictSet = new HashSet<>(wordDict);
        backtrack(s,0,new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(String s, int index, List<StringBuilder> resultStringList, int listIndex) {
        //System.out.println("Backtrack called: index:"+ index + ", listIndex : " + listIndex + ", Current list : "+resultStringList);
        if(index >= s.length() ) {
            // FINAL BASE
            if(listIndex == resultStringList.size()-1 && dictSet.contains(resultStringList.get(resultStringList.size() - 1).toString())) {
                String finalString = String.join(" ", resultStringList);
                result.add(finalString);
            }
            return;
        }
        if(resultStringList.size() < listIndex + 1) {
            resultStringList.add(listIndex,new StringBuilder());
        }

        char ch = s.charAt(index);
        resultStringList.get(listIndex).append(ch);
        if(dictSet.contains(resultStringList.get(listIndex).toString())) {
            //System.out.println("Calling: index:"+ (index+1) + ", listIndex : " + (listIndex+1) + ", Current list : "+resultStringList);
            backtrack(s, index + 1, new ArrayList<>(resultStringList), listIndex + 1);


        }
        // we are resetting the resultStringList since append would have to take place
        // and call backtrack with more char
        //System.out.println("Calling: index:"+ (index+1) + ", listIndex : " + (listIndex) + ", Current list : "+resultStringList);
        backtrack(s, index + 1, new ArrayList<>(resultStringList), listIndex);
    }

    public static void main(String[] args) {
        WordBreakII obj = new WordBreakII();

        String inputString[] = {"catsanddog", "pineapplepenapple", "catsandog","bb"};
        String[][] inputDict = {{"cat", "and", "cats", "sand", "dog"},
                {"apple", "pen", "applepen", "pine", "pineapple"},
                {"cats", "dog", "sand", "and", "cat"},
                {"a","b","bbb","bbbb"}
        };

        for (int i = 0; i < inputString.length; i++) {
            System.out.println(obj.wordBreak(inputString[i], Arrays.stream(inputDict[i]).toList()));
        }
    }
}
