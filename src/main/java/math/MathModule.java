package math;

/**
 * Greatest Common Divisor
 */
public class MathModule {

    /**
     * Find Greatest Common Divisor
     *
     * @param a
     * @param b
     * @return
     */
    public static int GCD (int a, int b) {

        if (a < b) return GCD(b, a);

        if (a % b == 0) return b;

        return GCD(b, a % b);
    }

}
