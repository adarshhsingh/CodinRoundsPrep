package datastructures.arrays.old;

public class StringPalin {
    public static void main(String[] args) {
        StringPalin obj = new StringPalin();
        System.out.println(obj.isPalin("   x"));
    }

    public boolean isPalin(String s) {
        int start = 0;
        int end = s.length()-1;
        boolean isPalin = true;
        while (start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                isPalin = false;
                break;
            }
        }
        return isPalin;
    }
}
