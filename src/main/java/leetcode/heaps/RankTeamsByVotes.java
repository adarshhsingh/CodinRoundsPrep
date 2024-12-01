package leetcode.heaps;

// https://leetcode.com/problems/rank-teams-by-votes/description/

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Voting application:  implement a function that determines
 * the winner of the hackathon. pass in a list of pallets -
 * return a list of candidates in descending order of total points
 * each candidate received. assume we extract candidates
 * names from VOTES as we process them.
 * VOTER is allowed to vote for up to 3 different candidates.
 * Order of vote is very important.
 * The first vote is 3 points.
 * Seconds vote - 2 points, 3d vote = 1 point.
 * The function should return a list of candidates in descending order of points of candidates.
 */

class pair<X,Y> {
    X _key;
    Y _value;
    pair(X x, Y y) { _key = x; _value = y; }
    X key(){return _key;}
    Y value(){return _value;}
}

public class RankTeamsByVotes {

    Map<String, AtomicInteger> candidatePoints;
    PriorityQueue<pair<String, Integer>> rankedCandidate;
    ScheduledExecutorService scheduler;


    int interval;

    public RankTeamsByVotes() {
        candidatePoints = new ConcurrentHashMap<>();
        interval = 1;
        scheduler = Executors.newScheduledThreadPool(1);
        rankedCandidate = new PriorityQueue<>(10, new Comparator<pair<String, Integer>>() {
            @Override
            public int compare(pair<String, Integer> o1, pair<String, Integer> o2) {
                if( o2.value()-o1.value()!=0) {
                    return o2.value()-o1.value();
                } else {
                    return o1.key().compareTo(o2.key());
                }
            }
        });
        scheduleTopWinnersOutput();
    }

    private void scheduleTopWinnersOutput() {
        scheduler.scheduleAtFixedRate(this::outputTopWinners, interval, interval, TimeUnit.SECONDS);
    }

    public void outputTopWinners() {
        synchronized (rankedCandidate) {
            rankedCandidate.clear();
            candidatePoints.forEach((name, points) -> {
                rankedCandidate.offer(new pair<>(name, points.get()));
            });
        }
        List<String> topWinners = new ArrayList<>();
        while (!rankedCandidate.isEmpty()) {
            topWinners.add(rankedCandidate.poll().key());
        }
        System.out.println("Top winners at interval: " + topWinners);
    }
    public void addVote(List<String> pallets) {
        int points = 3;
        for(String candidate: pallets) {
            candidatePoints.putIfAbsent(candidate, new AtomicInteger(0));
            candidatePoints.get(candidate).addAndGet(points);
            --points;
            if(points == 0) break;
        }
    }




    // return - list of candidates in descending order
    public void rankTeam(List<List<String>> votersPallet) {
        candidatePoints = new ConcurrentHashMap<>();
        // votes - is the list of each vote pallet
        // have to parse through each pallet in votersPallet
        votersPallet.parallelStream().forEach( pallet -> {
            // each pallet can have max 3 names.
            // with 1st name - 3 point, 2nd name - 2 point, 3rd name - 1 point
            int i = 3;
            for (String candidate : pallet) {
                // put candidate in list if never has before
                candidatePoints.putIfAbsent(candidate, new AtomicInteger(0));
                // increment point by (i)
                // decrement i for next candidate
                candidatePoints.get(candidate).addAndGet(i);
                --i;
                if (i == 0) break; // in case pallet has more, we don't want to decrease points
            }
        });
        // we have all votes counted now
        // we need ordererd list
        PriorityQueue<pair<String, Integer>> rankedCandidate =
                new PriorityQueue<>(new PriorityQueue<>(10,
                    new Comparator<pair<String, Integer>>() {
                        @Override
                        public int compare(pair<String, Integer> o1, pair<String, Integer> o2) {
                            if(o2.value()- o1.value() !=0) {
                                return o2.value() - o1.value();
                            } else {
                                return o1.key().compareTo(o2.key());
                            }
                        }
                }));

        candidatePoints.forEach((name, points) -> {
            rankedCandidate.offer(new pair<>(name, points.get()));
        });

        System.out.println("*** Winners List ***");
        while (!rankedCandidate.isEmpty()) {
            pair p = rankedCandidate.poll();
            System.out.println(p.key() + " - "+ p.value());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        RankTeamsByVotes obj = new RankTeamsByVotes();
        // now doing the parallel processing, where votes are being given continoulsy
        // and we need to calculate the votes in parallel

        // Simulating incoming votes
        List<List<String>> votes = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Charlie"),
                Arrays.asList("Bob", "Charlie"),
                Arrays.asList("Alice", "Charlie"),
                Arrays.asList("Charlie", "Alice")
        );

        for (List<String> vote : votes) {
            System.out.println("New vote came" + vote);
            obj.addVote(vote);
            Thread.sleep(2000);  // Simulating time delay between votes
        }
        obj.scheduler.shutdown();



        // below is the test cases for the base version
        /*List<List<String>> testCase1 = new ArrayList<>();

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

        List<List<String>> testCase7 = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Charlie"),
                Arrays.asList("Bob", "Charlie"),
                Arrays.asList("Alice", "Charlie"),
                Arrays.asList("Charlie", "Alice")
        );

        obj.rankTeams(testCase1);
        obj.rankTeams(testCase2);
        obj.rankTeams(testCase3);
        obj.rankTeams(testCase4);
        obj.rankTeams(testCase5);
        obj.rankTeams(testCase6);
        obj.rankTeams(testCase7);*/
    }
}
