package datastructures.treesNgraphs;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapLearn {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        // Inserting key-value pairs
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(5, "Five");
        treeMap.put(2, "Two");

        // Iterating over entries
        for (var entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.println(treeMap.firstEntry().getValue());
        System.out.println(treeMap.lastEntry().getValue());
        System.out.println(treeMap.floorEntry(4));
        System.out.println(treeMap.ceilingEntry(4));
    }
}
