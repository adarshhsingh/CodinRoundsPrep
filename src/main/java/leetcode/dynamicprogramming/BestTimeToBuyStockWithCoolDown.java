package leetcode.dynamicprogramming;

import java.awt.*;
import java.util.*;
import java.util.List;

// medium
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
public class BestTimeToBuyStockWithCoolDown {
    // non recursive solution
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }
    // Identify States:
        int n = prices.length;
        int hold[] = new int[n]; // maximum profit if we hold a stock on day (i)
        int sold[] = new int[n]; // maximum profit if we sold a stock on day (i)
        int cooldown[] = new int[n]; // maximum profit if we cool down on day (i)

        List<Character> vowel = List.of(new Character[]{'a', 'b'});
        Stack<Character> vowelsInString = new Stack<>();
        System.out.println(Arrays.stream(" aa  aaaa     a".trim().split(" ")).toList());
        System.out.println(vowel);



    // Initialize States:
        hold[0] = -prices[0]; // On the first day, if we buy a stock, our profit is -prices[0]
        sold[0] = 0; //We can't sell a stock on the first day, so sold[0] is 0.
        cooldown[0]=0; //No transactions have occurred, so cooldown[0] is also 0.

    // State Transitions
        for(int i=1; i<n ; i++) {
            // Calculate the maximum profit for each state on day i
            hold[i] = Math.max(hold[i - 1], cooldown[i - 1] - prices[i]);
            sold[i] = hold[i-1] + prices[i] ;
            cooldown[i] = Math.max(cooldown[i-1], sold[i-1]);
        }
        // The maximum profit will be the greater of being in sold or cooldown state on the last day
        return Math.max(sold[n - 1], cooldown[n - 1]);

    }



  /*
  // memoized recursinve solution
  Map<String, Integer> memo = new HashMap<>();

    public int maxProfit(int[] prices) {
        // if decision is made to buy, then this variable is updated with holding price
        Integer holdingPrice = null;
        // didIJustSell variable - is define to track if its cooldown period time
        Boolean didIJustSell = false;
        Integer profit = 0;
        // Initial call to the recursive function with the first day and no stock held
        return calculateProfit(0, prices, null, false);
    }

    // return profit from here
    public int calculateProfit(int day, int[] prices, Integer holdingPrice, Boolean didIJustSell) {

        // Base case: If we have processed all days, return the profit
        if(day == prices.length) {
            return 0;
        }

        // Create a unique key for the current state
        String key = day + "," + holdingPrice + "," + didIJustSell;

        // Check if the result for the current state is already computed
        if (memo.containsKey(key)) {
            return memo.get(key);
        }


        int result;

        // If no stock is held and we are in cooldown period
        if (holdingPrice == null && didIJustSell) {
            result = calculateProfit(day + 1, prices, null, false);
        }
        // If no stock is held and we are not in cooldown period
        else if (holdingPrice == null && !didIJustSell) {
            int doNothing = calculateProfit(day + 1, prices, null, false);
            int buy = calculateProfit(day + 1, prices, prices[day], false);
            result = Math.max(doNothing, buy);
        }
        // If a stock is held
        else {
            int sell = calculateProfit(day + 1, prices, null, true)+ (prices[day] - holdingPrice);
            int hold = calculateProfit(day + 1, prices, holdingPrice, false);
            result = Math.max(sell, hold);
        }

        // Store the result for the current state
        memo.put(key, result);

        return result;
    }*/

    public static void main(String[] args) {
        /*BestTimeToBuyStockWithCoolDown obj = new BestTimeToBuyStockWithCoolDown();
        int prices[] = {1,2,3,0,2};
        System.out.println(obj.maxProfit(prices));*/


        Map<String, Map<String, Double>> weightedConnection = new HashMap<>();
        weightedConnection.put("a", new HashMap<>());
        weightedConnection.get("a").put("b",1.0);
        Double []arr = new Double[2];
        double x = -1.0;
        arr[0] = x;
        Point p = new Point(1,3);

        System.out.println(arr[0]);
        System.out.println(arr[1]);

        for(Map.Entry entry : weightedConnection.get("a").entrySet()) {
            String key = (String) entry.getKey();
        }

        System.out.println(weightedConnection.get("a"));
    }
}
