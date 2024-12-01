package datastructures.arrays.julypreperation.medium;


import java.util.Stack;


public class BasicCalculatorII {

    public static void main(String[] args) {
        BasicCalculatorII obj = new BasicCalculatorII();
        String s = " 7 - 2*2";
        System.out.println(obj.calculate(s));
        s = " 3+5 / 2 ";
        System.out.println(obj.calculate(s));
        s = "0- 0 ";
        System.out.println(obj.calculate(s));
        s= "1- 1+ 1";
        System.out.println(obj.calculate(s));
        s= "1- 2147483647";
        System.out.println(obj.calculate(s));
        s="2-3+4";
        System.out.println(obj.calculate(s));
        s="2-3-4";
        System.out.println(obj.calculate(s));
        s="1+2*5/3+6/4*2";
        System.out.println(obj.calculate(s));
        s="1-2*5/3-6/4*2";
        System.out.println(obj.calculate(s));
        s=" 30";
        System.out.println(obj.calculate(s));
    }


    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        s = s.replaceAll(" ","");
        //System.out.println(s);
        int num1 = 0;
        int num2 = 0;
        int i = 0;
        char previousOp = '+';
        boolean numCreated = false;
        while(i<s.length()) {
            num1 = 0;
            num2 = 0;
            while(i<s.length() && Character.isDigit(s.charAt(i))) {
                num1 = num1*10 + s.charAt(i)-'0';
                numCreated = true;
                ++i;
            }
            if(numCreated){
                if(previousOp == '-') {
                    num1 = num1*(-1);
                }
                stack.push(num1);
                numCreated = false;
            }
            //System.out.println(stack);
            if(i>=s.length()) break;
            switch (s.charAt(i)) {
                case '+':
                    previousOp = '+';
                    ++i;
                    break;
                case '-':
                    previousOp = '-';
                    ++i;
                    break;
                case '*':
                    num1 = stack.pop();
                    //System.out.println("Got multiple. Popped num1 = "+num1);
                    ++i;
                    num2 = 0;
                    while(i<s.length() && Character.isDigit(s.charAt(i))) {
                        num2 = num2*10 + s.charAt(i)-'0';
                        ++i;
                    }
                    //System.out.println("num2 = "+num2);
                    stack.push(num1*num2);
                    //System.out.println("Pushed "+num1*num2);
                    previousOp = '*';
                    break;
                case '/':
                    //System.out.println("Got divide");
                    num1 = stack.pop();
                    //System.out.println("Got divide. Popped num1="+num1);
                    ++i;
                    num2 = 0;
                    while(i<s.length() && Character.isDigit(s.charAt(i))) {
                        num2 = num2*10 + s.charAt(i)-'0';
                        ++i;
                    }
                    //System.out.println("num2 = "+num2);
                    stack.push(num1/num2);
                    //System.out.println("Pushed "+(num1/num2));
                    previousOp = '/';
                    break;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
           ans += stack.pop();
        }
        return ans;
    }
}
