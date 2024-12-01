package leetcode.HARD;

import machinecoding.parkinglot.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

class triplet {
    int x;
    int y;
    int val;
    triplet(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public String toString() {
        return "x = "+ x+", y = "+y+", val = "+val;
    }
}

public class VerticalOrderTraversalOfABinaryTree {
    List<triplet> tripletList;
    int minX = 0;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        tripletList = new ArrayList<>();
        traverseTreeAndMaintainCoordinates(root, 0, 0);
        tripletList.sort(new Comparator<triplet>() {
            @Override
            public int compare(triplet o1, triplet o2) {
                if(o1.x != o2.x) {
                    return o1.x - o2.x;
                } else if(o1.y != o2.y) {
                    return o2.y - o1.y;
                } else {
                    return o1.val - o2.val;
                }
            }
        });
        int offset = 0;
        if(minX < 0) {
            offset = -(minX);
        }
        for(triplet tuple: tripletList) {
            int x = tuple.x + offset;
            int y = tuple.y;
            int val = tuple.val;

            if(answer.size() < x+1) {
                answer.add(x, new ArrayList<>());
            }
            answer.get(x).add(val);
        }
        return answer;
    }

    private void traverseTreeAndMaintainCoordinates(TreeNode node, int x, int y) {
        if(node == null) return;
        minX = Math.min(minX, x);
        tripletList.add(new triplet(x, y, node.val));
        traverseTreeAndMaintainCoordinates(node.left, x - 1, y - 1);
        traverseTreeAndMaintainCoordinates(node.right, x + 1, y - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        VerticalOrderTraversalOfABinaryTree obj = new VerticalOrderTraversalOfABinaryTree();
        System.out.println(obj.verticalTraversal(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(obj.verticalTraversal(root));

    }
}
