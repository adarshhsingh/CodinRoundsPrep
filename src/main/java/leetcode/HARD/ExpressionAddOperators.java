package leetcode.HARD;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ExpressionAddOperators {
    Set<String> expressions;
    public List<String> addOperators(String num, int target) {
        expressions = new HashSet<>();
        Set<String> validExpressions = new HashSet<>();
        backtrack(num, 0, new StringBuilder());
        //System.out.println(expressions);
        for(String expression: expressions) {
            if(expressionResult(expression) == target) {
                //System.out.println(expression +" that is equal to" + target);
                validExpressions.add(expression);
            }
        }
        return validExpressions.stream().toList();
    }

    private long expressionResult(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+'; // Start with '+' to handle the first number

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If it's a digit, build the current number
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            // If it's an operator or end of string, process the previous operator
            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    if(currentNumber == 0) {
                        return -1;
                    }
                    stack.push(stack.pop() / currentNumber);
                }

                // Reset for the next number and set the new operation
                operation = ch;
                currentNumber = 0;
            }
        }

        // Sum up everything in the stack for the final result
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;

    }

    private void backtrack(String num, int index, StringBuilder resultString) {
        if(index == num.length()) {
            expressions.add(resultString.toString());
            return;
        }

        resultString.append(num.charAt(index));
        if(index == num.length()-1) {
            backtrack(num, index + 1, new StringBuilder(resultString));
        } else {
            // 4 cases
            resultString.append('+');
            backtrack(num, index + 1, new StringBuilder(resultString));
            resultString.deleteCharAt(resultString.length() - 1);

            resultString.append('-');
            backtrack(num, index + 1, new StringBuilder(resultString));
            resultString.deleteCharAt(resultString.length() - 1);

            resultString.append('*');
            backtrack(num, index + 1, new StringBuilder(resultString));
            resultString.deleteCharAt(resultString.length() - 1);

            //last case without any symbol
            backtrack(num, index + 1, new StringBuilder(resultString));

        }

    }

    public static void main(String[] args) {
        String num[] = {"123", "232", "3456237490","105"};
        int target[] = {6, 8, 9191,5};
        ExpressionAddOperators obj = new ExpressionAddOperators();

        for(int i = 0; i < num.length; i++) {
            System.out.println(obj.addOperators(num[i],target[i]));
        }
    }
}
