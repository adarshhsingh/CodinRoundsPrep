package introToAlgorithm.searching;

public class BinarySearch {

    public static void main(String[] args) {
        int a[] = {1,2,2,5,5,6,8};
        long s = 11;
        int y = 2;
        //Math.ceilDiv(s,)
        System.out.println("Finding exact number with minimum index in ");
        for(int i: a) System.out.print(i+",");
        System.out.println();
        for(int i = 0; i<a.length; i++) System.out.print(i+",");
        System.out.println();
        /*System.out.println("3 at : "+binarySearchExactNumberWithMinIndex(3, a));
        System.out.println("8 at : "+binarySearchExactNumberWithMinIndex(8, a));
        System.out.println("2 at : "+binarySearchExactNumberWithMinIndex(2, a));
        System.out.println("5 at : "+binarySearchExactNumberWithMinIndex(5, a));
        System.out.println("1 at : "+binarySearchExactNumberWithMinIndex(1, a));
        System.out.println("-1 at : "+binarySearchExactNumberWithMinIndex(-1, a));
        System.out.println("10 at : "+binarySearchExactNumberWithMinIndex(10, a))*/;

        System.out.println("Finding exact number or greater number with minimum index");
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(3, a));
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(2, a));
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(9, a));
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(5, a));
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(4, a));
        System.out.println(binarySearchGreaterThanNumberIfNumberNotPresent(0, a));

        /*System.out.println("Finding exact number or less number with minimum index");
        System.out.println(binarySearchLessThanNumberIfNumberNotPresent(3, a));
        System.out.println(binarySearchLessThanNumberIfNumberNotPresent(2, a));
        System.out.println(binarySearchLessThanNumberIfNumberNotPresent(9, a));
        System.out.println(binarySearchLessThanNumberIfNumberNotPresent(4, a));
        System.out.println(binarySearchLessThanNumberIfNumberNotPresent(0, a));**/
    }

    private static int binarySearchExactNumberWithMinIndex(int i, int[] a) {
        int n = a.length;
        int indexStart = 0;
        int indexEnd = n-1;
        int left = indexStart;
        int right = indexEnd;

        while(left <= right) {
            int mid = left + (right - left)/2;
            // base exit condition
            if(a[mid] == i && (mid == indexStart || a[mid-1] < i)) {
                return mid;
            }

            if(a[mid] >= i) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;


    }

    public static int binarySearchGreaterThanNumberIfNumberNotPresent(int i, int[] a) {
        int start = 0;
        int end = a.length-1;

        int left = start;
        int right = end;

        while (left <= right) {
            int mid = left + (right - left)/2;
            // exit condition
            if(a[mid] >= i && (mid == start || a[mid-1] < i)) {
                return mid;
            }

            if(a[mid] > i) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*public static int binarySearchLessThanNumberIfNumberNotPresent(int i, int[] a) {
        int start = 0;
        int end = a.length - 1;

        int left = start;
        int right = end;

        while(left <= right) {
            int mid = left + (right - left)/2;

            // exit condition
            if(a[mid] <= i && (mid == end || a[mid+1] > i)) {

            }

        }
    }*/
}
