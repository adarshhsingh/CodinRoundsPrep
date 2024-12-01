package codingPatterns.fastAndSlowPointer;

public class HappyNumber {

    public static boolean isHappyNumber(int n) {
        if(n == 1) return true;
        // n = 23
        int s = 0;
        int x = n;// x = 23
        while(s != 1 && s != n) { // s = 0 (T)
            s = 0;
            while(x != 0) {
                s += (x % 10) * (x % 10); // s += 3*3 ; s = 9 // s += 2*2 // s = 13
                x = x / 10;// x = 2  // x = 0
            }
            x = s; // x = 13
        }

        if(s == n) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isHappyNumber(23));
        System.out.println(isHappyNumber(2));
    }
}
