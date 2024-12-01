package datastructures.arrays.julypreperation.easy;

public class AddBinary {
        static final String ZERO = "0";
        public String addBinary(String a, String b) {
            if(a==null || a.isEmpty() || a.equals(ZERO)) return b;
            if(b==null || b.isEmpty() || b.equals(ZERO)) return a;
            StringBuilder sb = new StringBuilder();
            int i = a.length()-1;
            int j = b.length()-1;
            int carry = 0;

            while ( i>=0 || j>=0 || carry == 1 ) {
                int sum = carry;
                if(i>=0) {
                    sum += a.charAt(i--)-'0';
                }
                if(j>=0) {
                    sum += b.charAt(j--)-'0';
                }
                sb.append(sum%2);
                carry = sum/2;
            }

            return sb.reverse().toString();
        }

    public static void main(String[] args) {
        AddBinary obj = new AddBinary();
        System.out.println(obj.addBinary("11", "1"));
        System.out.println(obj.addBinary("1010", "1011"));
    }
}
