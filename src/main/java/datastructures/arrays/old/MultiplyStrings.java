package datastructures.arrays.old;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        char num1Array[] = num1.toCharArray();
        char num2Array[] = num2.toCharArray();
        int product[] = new int[num1Array.length + num2Array.length];

        for (int i=num2.length()-1; i>=0; i--) {
            for (int j=num1.length()-1; j>=0; j--) {
                int mul = (num1Array[j] - '0') * (num2Array[i] - '0');
                int sum = mul + product[i+j+1];

                product[i+j+1] = sum % 10;
                product[i+j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : product) {
            if (sb.length() == 0 && i == 0) {
                continue;
            }
            sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
