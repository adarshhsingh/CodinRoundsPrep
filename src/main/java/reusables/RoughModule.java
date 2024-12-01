package reusables;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RoughModule {

    public static void main(String[] args) {

    int i = 1;
    String c = ""+i;
    System.out.println(c);
    String s = "1";
    StringBuilder sb = new StringBuilder();

    List<Integer> list = new ArrayList<>();
    Set<Character> set = new HashSet<>();

    Collections.sort(list, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    });



    if(s.indexOf(c) == -1) {
        System.out.println("does not contaions");
    } else {
        System.out.println("contaions");
    }
/*

        Comparator<Character> customComparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return 0;
            }
        };
        Map<Integer, Integer> hashMap = new HashMap<>();

        hashMap.put(1,1);
        hashMap.put(2,1);
        hashMap.values();

        int n[] = {1,2,3};
        Set<String> set = new HashSet<>();
        set.add("abc");
        set.add("bcd");
*/


/*
        int start = 0;
        int end = s.length();

        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1,1);
        hashMap.put(2,1);
        hashMap.values();


        System.out.println(start + " - "+ end +" = "+ s.substring(start,end));
        StringBuilder str = new StringBuilder(s.substring(start,end));
        System.out.println(str.toString());
        StringBuilder str2 = new StringBuilder(s.substring(start,end));
        str2.reverse();
        System.out.println(str2.toString());
        System.out.println(str2.toString().equals(str.toString()));

        Stack<String> stack = new Stack<>();
        stack.add("aaa");
        stack.add("ssd");
        System.out.println(stack);
        for(String s: stack) {
            System.out.println(s);
        }
        List<String> ss = new ArrayList<>(Collections.singleton("ss"));
        List<Character> charList = new ArrayList<>();

        Map<Integer, String> map = new HashMap<>();
        map.values();






        List<Integer> x = new ArrayList<>();
        x.add(0,1);
        x.add(0,2);


        //Stack<Integer> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        list.add('a');


        list.add('b');
        x.removeAll(list);
        System.out.println("x empty ?"+x.isEmpty());
        HashSet<Integer> set = new HashSet<>();
        set.add(1);

        List<int[]> listofarray = new ArrayList<>();
        listofarray.add(new int[]{1,2});
        for( int i[] : listofarray) {
            System.out.println(i[0]);
            System.out.println(i[1]);
        }
        int[] mid = listofarray.get(0);

        int arr[] = null;




        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(1);
        sortedSet.add(2);
        System.out.println(sortedSet.first());
        sortedSet.remove(1);
        System.out.println(sortedSet.first());

        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap.put(2,sortedMap.getOrDefault(2,0)+1);
        sortedMap.put(1,sortedMap.getOrDefault(1,0)+1);
        sortedMap.put(1,sortedMap.getOrDefault(1,0)+1);


        System.out.println(sortedMap);
        System.out.println(sortedMap.lastEntry());

        Deque<Integer> deque = new LinkedList<>();
        deque.add(4);
        deque.add(1);
        deque.add(2);
        System.out.println(deque);
        System.out.println(deque.peekLast());
        deque.removeFirst();
        System.out.println(deque);
        deque.add(1);
        System.out.println(deque);
        deque.removeFirst();
        System.out.println(deque);
*/

    }
}
