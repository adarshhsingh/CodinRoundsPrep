package leetcode.HARD;

public class ValidNumber {
    public boolean isNumber(String s) {
        // we can't have 2 decimals
        // we need to have atleast one number
        // we can't have any alphabet other than e
        boolean decimalUsed = false;
        boolean numberSeen = false;
        int i = 0;
        char ch = s.charAt(i);
        if (ch == '+' || ch == '-') {
            i++;
        }
        for (;i < s.length(); i++) {
            ch = s.charAt(i);

            if (Character.isAlphabetic(ch)) {
                if (ch != 'e' && ch != 'E') {
                    return false;
                } else {
                    return numberSeen && isValidInteger(s.substring(i+1));
                }
            } else if(ch == '.') {
                if(decimalUsed) {
                    return false;
                } else {
                    decimalUsed = true;
                }
            } else if(ch == '+' || ch == '-') {
                return false;
            } else {
                numberSeen = true;
            }
        }
        return numberSeen;
    }

    private boolean isValidInteger(String substring) {
        if(substring == null || substring.isEmpty()) return false;
        boolean numberSeen = false;

        int i = 0;
        char ch = substring.charAt(i);
        if (ch == '+' || ch == '-') {
            i++;
        }
        for (;i < substring.length(); i++) {
            ch = substring.charAt(i);
            if(!Character.isDigit(ch)) {
                return false;
            } else {
                numberSeen = true;
            }
        }
        return numberSeen;
    }

    public static void main(String[] args) {
        ValidNumber obj = new ValidNumber();
        String inputTrue[] = {/*"2", "0089", "-0.1", "+3.14", "4.", "-.9",*/ "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        String inputFalse[] = {"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53" };
        System.out.println("All should be TRUE");
        for(String s : inputTrue) {
            System.out.println(s + " : " +obj.isNumber(s));
        }
        System.out.println("All should be FALSE");
        for(String s : inputFalse) {
            System.out.println(s + " : " +obj.isNumber(s));
        }
    }
}
