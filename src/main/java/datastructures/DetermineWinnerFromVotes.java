package datastructures;

import java.util.*;

class pair<X,Y> {
    X _key;
    Y _value;
    pair(X x, Y y) { _key = x; _value = y; }
    X key(){return _key;}
    Y value(){return _value;}
}

class triplet<X,Y,Z> {
    X _data;
    Y _row;
    Z _column;
    triplet(X x, Y y, Z z) { _data = x; _row = y; _column = z; }
    X data(){return _data;}
    Y row(){return _row;}
    Z column(){return _column;}
}

public class DetermineWinnerFromVotes {

    static HashMap<String,Integer> candidateAndTheirPoints = null;

    public static void main(String[] args) {

        List<List<String>> testCase1 = new ArrayList<>();

        List<List<String>> testCase2 = new ArrayList<>();
        testCase2.add(Arrays.asList("Alice"));

        List<List<String>> testCase3 = new ArrayList<>();
        testCase3.add(Arrays.asList("Alice"));
        testCase3.add(Arrays.asList("Bob"));
        testCase3.add(Arrays.asList("Charlie"));

        List<List<String>> testCase4 = new ArrayList<>();
        testCase4.add(Arrays.asList("Alice", "Bob"));
        testCase4.add(Arrays.asList("Bob", "Alice"));
        testCase4.add(Arrays.asList("Alice", "Charlie", "Bob"));

        List<List<String>> testCase5 = new ArrayList<>();
        testCase5.add(Arrays.asList("Alice", "Bob"));
        testCase5.add(Arrays.asList("Bob", "Alice"));
        testCase5.add(Arrays.asList("Charlie"));

        List<List<String>> testCase6 = new ArrayList<>();
        testCase6.add(Arrays.asList("Alice", "Bob", "Charlie"));
        testCase6.add(Arrays.asList("Alice", "Charlie", "Bob"));
        testCase6.add(Arrays.asList("Charlie", "Alice"));


        determineWinners(testCase1);
        determineWinners(testCase2);
        determineWinners(testCase3);
        determineWinners(testCase4);
        determineWinners(testCase5);
        determineWinners(testCase6);
    }

    static void determineWinners(List<List<String>> pallets) {
        candidateAndTheirPoints = HashMap.newHashMap(pallets.size()*3);
        for (int i=0;i<pallets.size();i++) {
            int point = 3;
            for (String candidate: pallets.get(i)) {
                candidateAndTheirPoints.putIfAbsent(candidate,0);
                candidateAndTheirPoints.put(candidate,candidateAndTheirPoints.get(candidate) + point);
                point--;
                if (point == 0)  break;
            }
        }

        PriorityQueue<pair<String,Integer>> rankedOrderOfCandidates =
                new PriorityQueue<>(new PriorityQueue<>(10,
                        new Comparator<pair<String, Integer>>() {
                            @Override
                            public int compare(pair<String, Integer> o1, pair<String, Integer> o2) {
                                return o2.value() - o1.value();
                            }
                        }
                ));

        candidateAndTheirPoints.forEach((s, integer) -> {
            rankedOrderOfCandidates.offer(new pair<>(s, integer));
        });

        System.out.println("** Winners **");
        while(rankedOrderOfCandidates.size() > 0) {
           pair picked = rankedOrderOfCandidates.poll();
           while (rankedOrderOfCandidates.size() > 0 &&
                   rankedOrderOfCandidates.peek().value() == picked.value()) {
               picked._key = picked.key() + ", " + rankedOrderOfCandidates.poll().key();
           }
           System.out.println(picked.key() +" : "+ picked.value());
        }
        System.out.println("** End **");
    }
}
