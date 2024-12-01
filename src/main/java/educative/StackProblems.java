package educative;

import java.util.Stack;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class StackProblems {

    public static void main(String[] args) {
            StackProblems stackProblems = new StackProblems();
            stackProblems.reverse("abc");

            String inp[] = {"2","3","*","5","+"};
            stackProblems.evaluatePostfixExpression(inp);

            String input = "azxxzy";
            System.out.println(stackProblems.removeAdjacentDuplicates(input));
    }

    // remove adjacent duplicates in strings
    public String removeAdjacentDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        String finalString = "";
        for (int i=0; i<s.length(); i++) {
            if(stack.isEmpty()) {
                stack.add(s.charAt(i));
                continue;
            }
            char c = stack.peek();
            if(c == s.charAt(i)) {
                stack.pop();
            } else {
                stack.add(s.charAt(i));
            }
        }
        while (!stack.isEmpty()) {
            finalString = stack.pop()+finalString;
        }
        return finalString;
    }

    // Reverse a string using stack: Given an input string as
    // an array of characters, reverse all the characters in this string.
    public void reverse(String s) {
        char stack[] = new char[s.length()];
        for(int i=s.length()-1, j=0; i>=0; i--,j++) {
            stack[i] = s.charAt(j);
        }
        System.out.println(stack);
    }

    // Evaluate postfix expression: Given an array of tokens
    // that represents an arithmetic expression in a postfix notation,
    // evaluate the expression and return an integer that represents the value of the expression.
    public void evaluatePostfixExpression(String input[]) {
        // push digit in stack until a operand comes up
        String operands = "*+-/";
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<input.length;i++) {
            if(operands.contains(input[i])) {
                int a = stack.pop();
                int b = stack.pop();
                int c = 0;
                switch (input[i]) {
                    case "*":
                        c = b * a; break;
                    case "*/":
                        c = b / a; break;
                    case "+":
                        c = b + a; break;
                    case "-":
                        c = b - a; break;
                }
                stack.add(c);
            } else {
                stack.add(Integer.parseInt(input[i]));
            }
        }
        System.out.println(stack.peek());
    }
}
