package reusables;

import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import datastructures.treesNgraphs.TreeNode;

import java.util.*;

public class PrintModules {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        //queue.peek()
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(8);
        treeSet.add(7);
        treeSet.add(4);
        treeSet.add(1);

        System.out.println(treeSet);
        System.out.println(treeSet.tailSet(5,false));
        System.out.println(treeSet.floor(6));
        System.out.println(treeSet.ceiling(6));

        TreeMap<Integer,String> stringTreeMap = new TreeMap<>();
        stringTreeMap.put(1,"a");
        stringTreeMap.put(4,"b");
        stringTreeMap.put(6,"c");

        System.out.println(stringTreeMap);
        System.out.println(stringTreeMap.floorEntry(3));


        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(1, treeMap.getOrDefault(1,0)+1);
        treeMap.put(8, treeMap.getOrDefault(8,0)+1);
        treeMap.put(1, treeMap.getOrDefault(1,0)+1);
        treeMap.put(4, treeMap.getOrDefault(4,0)+1);
        treeMap.put(8, treeMap.getOrDefault(8,0)+1);
        treeMap.put(1, treeMap.getOrDefault(1,0)+1);
        treeMap.put(4, treeMap.getOrDefault(4,0)+1);

        System.out.println(treeMap);
        System.out.println(treeMap.pollLastEntry());
        System.out.println(treeMap.lastEntry());
        System.out.println(treeMap);
        System.out.println(treeMap.lastEntry().getValue());
        Map.Entry entry = treeMap.lastEntry();
        entry.setValue((int)entry.getValue()-1);
        System.out.println(treeMap);





    }

    public static void print(List<Integer> occ) {
        for(int i:occ) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void print(Set<Integer> occ) {
        for(int i:occ.stream().toList()) {
            System.out.println(i);
        }
    }

    public static void print(TrieNode trie) {

    }

    public static void print(int a[]) {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void print(Integer a[]) {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void print(double a[]) {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void print(String a[]) {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void print(char a[]) {
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void print(Map<Integer, Integer> mapN){
        for (Map.Entry<Integer, Integer> entry : mapN.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

    }

    public static void print(int a[], char open, char end) {
        System.out.print(open);
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]);
            if(i!=a.length-1) System.out.print(",");
        }
        System.out.print(end);
        System.out.println();
    }

    public static void print(int a[][]) {
        if(a == null) return;
        int rows = a.length;
        int columns = a[0].length;
        for(int i=0;i<rows;i++) {
            for (int j=0; j<columns; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.print("\n");

        }
    }

    public static void print(int a) {
        System.out.println(a);
    }

    public static void print(String a) {
        System.out.println(a);
    }

    public static void printf(String a, String[]args) {
        System.out.printf(a, args);
    }
}
