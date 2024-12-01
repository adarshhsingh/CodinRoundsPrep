package datastructures.arrays.backtrack;

public class PrintAllPermutationOfGivenString {

        public static void main(String[] args) {

            permute("abc".toCharArray(),3);
        }

        private static void permute(char a[], int i) {

            if( i == a.length) System.out.println(a);

            for(int j=i; j<a.length; j++) {

                swap(a, i, j);
                permute(a, i+1);
                // Backtrack
                swap(a, i, j);
            }
        }

        private static void swap(char[] a, int i, int j) {
            char t =a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
