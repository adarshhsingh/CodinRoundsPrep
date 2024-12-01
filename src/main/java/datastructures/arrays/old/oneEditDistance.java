package datastructures.arrays.old;

public class oneEditDistance {

    public static void main(String[] args) {
        oneEditDistance oneEditDistance = new oneEditDistance();
        String inputS[] ={"ab","","a","abc","ab","a","a"};
        String inputT[] ={"acb","","aa","adc","a","ab","aaa"};

        for(int i = 0; i<inputT.length; i++) {
            System.out.println(oneEditDistance.isOneEditDistance(inputS[i],inputT[i]));
        }
    }

    public boolean isOneEditDistance(String s, String t) {
        if(s.isEmpty() && t.isEmpty()) return false;
        if(s.equals(t)) return false;

        if(s.length() == t.length()) {
            int replace=0;
            for(int i=0; i<s.length();i++) {
                if(s.charAt(i)!=t.charAt(i)) {
                    replace++;
                }
            }
            return (replace==1)?true:false;
        }

        if( s.length() == t.length()+1) {
            int delete=0;
            for(int i=0, j=0; i<s.length() && j<t.length();) {
                if(s.charAt(i)!=t.charAt(j)) {
                    delete++;
                    i++;
                } else {
                    j++;
                    i++;
                }
            }
            return (delete<=1)?true:false;
        }

        if(s.length() == t.length()-1) {
            int insert=0;
            for(int i=0, j=0; i<s.length() && j<t.length();) {
                if(s.charAt(i)!=t.charAt(j)) {
                    insert++;
                    j++;
                } else {
                    j++;
                    i++;
                }
            }
            return (insert<=1)?true:false;

        }
        return false;
    }
}
