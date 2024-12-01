package leetcode.strings;

public class IsPalinByRemoval {

    public static void main(String[] args) {
        String s1 = "abca";
        String s2 = "aba";

        boolean canBeMadeIntoPalin = true;

        if(!isPalin(s1)) {

            char c[] = s1.toCharArray();
            int first = 0;
            int last = c.length - 1;

            while (first < last) {
                if (c[first] == c[last]) {
                    first++;
                    last--;
                } else {
                    String one = new StringBuilder(s1).deleteCharAt(first).toString();
                    String two = new StringBuilder(s1).deleteCharAt(last).toString();
                    if (isPalin(one) || isPalin(two)) {
                        canBeMadeIntoPalin = true;
                    } else {
                        canBeMadeIntoPalin = false;
                    }

                }
            }
        }

        System.out.println(canBeMadeIntoPalin);
    }



    public static boolean isPalin(String s) {
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();

        return (s2.equals(s));

    }
}
