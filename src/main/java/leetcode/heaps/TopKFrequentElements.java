package leetcode.heaps;

import reusables.PrintModules;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class pairs {
    int num;
    int freq;
    pairs(int num, int freq) {
        this.num = num;
        this.freq = freq;
    }
}
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreq = new HashMap<>();
        for(int n : nums) {
            numFreq.put(n, numFreq.getOrDefault(n,0)+1);
        }

        PriorityQueue<pairs> heap = new PriorityQueue<>(new Comparator<pairs>() {
            @Override
            public int compare(pairs o1, pairs o2) {
                return o1.freq - o2.freq;
            }
        });

        for(Map.Entry entry : numFreq.entrySet()) {
            int num = (int)entry.getKey();
            int freq = (int) entry.getValue();
            heap.add(new pairs(num, freq));
            if(heap.size() > k) {
                heap.remove();
            }
        }

        int result[] = new int[k];
        for(int i = k-1; i >= 0; i--) {
            result[i] = heap.poll().num;
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] nums = {{1,1,1,2,2,3},{1}};
        int k[] = {2,1};

        TopKFrequentElements obj = new TopKFrequentElements();
        for(int i = 0; i < k.length; i++) {
            PrintModules.print(obj.topKFrequent(nums[i], k[i]));
        }
    }
}
