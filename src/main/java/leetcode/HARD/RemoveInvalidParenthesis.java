package leetcode.HARD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesis {
    String longestString;
    Set<String> answerSet;

    public List<String> removeInvalidParentheses(String s) {
        // we need to keep track of longest string
        longestString = "";
        answerSet = new HashSet<>();

        // we need to do a backtracking DFS
        buildValidParenthesisOfMaxLength(s, 0, new StringBuilder(), 0, 0);
        return answerSet.stream().toList();
    }

    private void buildValidParenthesisOfMaxLength(String s, int currentIndex, StringBuilder currentResult, int leftCount, int rightCount) {
        if( currentIndex >= s.length()) {
            if(leftCount == rightCount) {
                if(currentResult.length() > longestString.length()) {
                    longestString = currentResult.toString();
                    answerSet.clear();
                    answerSet.add(currentResult.toString());
                } else if (currentResult.length() == longestString.length()) {
                    answerSet.add(currentResult.toString());
                }
            }
        } else {
            char currentChar = s.charAt(currentIndex);
            if(currentChar == '(') {
                currentResult.append(currentChar);
                buildValidParenthesisOfMaxLength(s, currentIndex + 1, new StringBuilder(currentResult), leftCount + 1, rightCount);
                currentResult.deleteCharAt(currentResult.length() - 1);
                buildValidParenthesisOfMaxLength(s, currentIndex + 1, new StringBuilder(currentResult), leftCount, rightCount);
            } else if(currentChar == ')') {
                buildValidParenthesisOfMaxLength(s, currentIndex + 1, new StringBuilder(currentResult), leftCount, rightCount);
                if(leftCount > rightCount) {
                    currentResult.append(currentChar);
                    buildValidParenthesisOfMaxLength(s, currentIndex + 1, new StringBuilder(currentResult), leftCount, rightCount + 1);
                    currentResult.deleteCharAt(currentResult.length() - 1);
                }
            } else if(Character.isAlphabetic(currentChar)) {
                currentResult.append(currentChar);
                buildValidParenthesisOfMaxLength(s, currentIndex + 1, new StringBuilder(currentResult), leftCount, rightCount);
            }
        }
    }

    public static void main(String[] args) {
        String input[] = {"()())()", "(a)())()", ")(",")(f"};
        RemoveInvalidParenthesis obj = new RemoveInvalidParenthesis();

        for(String s : input) {
            System.out.println(s + " :- "+obj.removeInvalidParentheses(s));
        }
    }
}
