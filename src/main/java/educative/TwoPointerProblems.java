package educative;

public class TwoPointerProblems {

    public static void main(String[] args) {
        TwoPointerProblems obj = new TwoPointerProblems();
        System.out.println(obj.isStringPalindrome("aaabvx"));
    }

    public boolean isStringPalindrome(String s) {
        int start = 0;
        int end = s.length()-1;
        while (start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public void reverseArray(int arr[]) {
        int start = 0;
        int end = arr.length-1;
        while (start<end) {
           int temp = arr[start];
           arr[start] = arr[end];
           arr[end] = temp;
           start++;
           end--;
        }
    }

}
