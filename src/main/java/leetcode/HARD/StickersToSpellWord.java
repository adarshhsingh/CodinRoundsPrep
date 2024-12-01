package leetcode.HARD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickersToSpellWord {
    HashMap<Character, List<Integer>> charStickerMap;
    HashMap<Character, Integer> targetCharCountMap;
    int minStickersUsed;
    Map<String, Integer> memo = new HashMap<>(); // Memoization map

    public int minStickers(String[] stickers, String target) {
        // re-initialize each time
        charStickerMap = new HashMap<>();
        minStickersUsed = Integer.MAX_VALUE;
        targetCharCountMap = new HashMap<>();

        // populate the charStickerMap with chars in each sticker and the indices of stickers containing them
        for(int i = 0; i < stickers.length; i++) {
            String sticker = stickers[i];
            for(char c : sticker.toCharArray()) {
                charStickerMap.computeIfAbsent(c, k -> new ArrayList<>());
                if (!charStickerMap.get(c).contains(i)) {  // Prevent duplicates
                    charStickerMap.get(c).add(i);
                }
            }
        }

       // populate the targetCharCountMap to count characters occurrences in the target string
        for(char c : target.toCharArray()) {
            targetCharCountMap.put(c,targetCharCountMap.getOrDefault(c,0)+1);
        }

        // Start the recursive function to find minimum stickers needed
        int result = findMinCombinationOfStickersForTarget(stickers, targetCharCountMap, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private String getTargetKey(Map<Character, Integer> targetMap) {
        StringBuilder sb = new StringBuilder();
        for(char c = 'a'; c <= 'z'; c++) {
            sb.append(targetMap.getOrDefault(c,0)).append("#");
        }
        return sb.toString();
    }
    private int findMinCombinationOfStickersForTarget(String[] stickers, Map<Character, Integer> targetCharCountMap, int stickersCount) {
        // Generate a memo key from the targetCharCountMap
        String targetKey = getTargetKey(targetCharCountMap);

        if(memo.containsKey(targetKey)) {
            return memo.get(targetKey);
        }

        // Base condition: if all characters in target have been covered
        if(targetCharCountMap.values().stream().allMatch(count -> count <=0)) {
           return 0; // no additional stickers needed
        }

        int minStickers = Integer.MAX_VALUE;
        for (int stickerIndex = 0; stickerIndex < stickers.length; stickerIndex++) {
            String sticker = stickers[stickerIndex];

            // Check if the sticker contributes to the remaining target
            if (!stickerContributes(sticker, targetCharCountMap)) {
                continue;
            }

            // Create a copy of targetCharCountMap to apply sticker
            Map<Character, Integer> newTargetMap = new HashMap<>(targetCharCountMap);
            for(char ch : sticker.toCharArray()) {
                if(newTargetMap.containsKey(ch)) {
                    int remainingNeed = newTargetMap.get(ch);
                    newTargetMap.put(ch, Math.max(0, remainingNeed - 1));
                }
            }
            // recursive call with updated map and increment stickers count
            int stickersNeeded = findMinCombinationOfStickersForTarget(stickers, newTargetMap, stickersCount + 1);
            if(stickersNeeded != Integer.MAX_VALUE) {
                minStickers = Math.min(minStickers, 1 + stickersNeeded);
            }
        }
        // Memorize the result for this state
        memo.put(targetKey, minStickers);
        return minStickers;
    }

    private boolean stickerContributes(String sticker, Map<Character, Integer> targetMap) {
        for(char ch : sticker.toCharArray()) {
            if(targetMap.getOrDefault(ch,0) > 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StickersToSpellWord obj = new StickersToSpellWord();
        String[] stickers = {"with","example","science"};
        String target = "thehat";
        System.out.println(obj.minStickers(stickers, target));
        stickers = new String[]{"notice","possible"};
        target = "basicbasic";
        System.out.println(obj.minStickers(stickers, target));
        stickers = new String[]{"these","guess","about","garden","him"};
        target = "atomher";
        System.out.println(obj.minStickers(stickers, target));
        stickers = new String[]{"fly","me","charge","mind","bottom"};
        target = "centorder";
        System.out.println(obj.minStickers(stickers, target));
    }
}
