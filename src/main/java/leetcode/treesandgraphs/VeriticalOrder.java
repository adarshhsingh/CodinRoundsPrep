package leetcode.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VeriticalOrder {
    List<List<Integer>> verticalOrderList;
        int minIndex = 0;
        int maxIndex = 0;
        Map<Integer, List<Integer>> hashMapList;

        public List<List<Integer>> verticalOrder(TreeNode root) {
            verticalOrderList = new ArrayList<>();
            hashMapList = new HashMap<>();

            if(root == null) return verticalOrderList;
            parseInVerticalOrder(root, 0);

            // build the list from the hashMap
            int j = 0;
            for(int i = minIndex; i <= maxIndex; i++) {
                verticalOrderList.add(j, hashMapList.get(i));
            }

            return verticalOrderList;

        }

        public void parseInVerticalOrder(TreeNode node, int index) {
            if(!hashMapList.containsKey(index)) {
                hashMapList.put(index, new ArrayList<>());
                if(minIndex > index) minIndex = index;
                if(maxIndex < index) maxIndex = index;
            }

            hashMapList.get(index).add(node.val);

            if(node.left != null) {
                parseInVerticalOrder(node.left, index - 1);
            }

            if(node.right != null) {
                parseInVerticalOrder(node.right, index + 1);
            }

        }

    }
