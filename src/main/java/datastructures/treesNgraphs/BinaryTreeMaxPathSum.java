package datastructures.treesNgraphs;

import reusables.PrintModules;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes
 * in the sequence has an edge connecting them. A node can only appear in the sequence at most once.
 *
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 * Input: root = {1,2,3}
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input: root = {-10,9,20,null,null,15,7}
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * root = {-10};
 * Output = -10
 *
 * root = {2,-1}
 * Output = 2
 *
 * root = {1,2}
 * Output = 3
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeMaxPathSum {
    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        if(root.left == null) {
            return max(
                    maxPathSum(root.right),
                    root.val + maxSumInSingleLinePath(root.right),
                    root.val);
        }

        if(root.right == null) {
            return max(
                    maxPathSum(root.left),
                    root.val + maxSumInSingleLinePath(root.left),
                    root.val);
        }

        return max(
                maxPathSum(root.left),
                maxPathSum(root.right),
                root.val,
                maxSumInSingleLinePath(root.left) + root.val,
                maxSumInSingleLinePath(root.right) + root.val,
                maxSumInSingleLinePath(root.left) + root.val + maxSumInSingleLinePath(root.right)
        );
    }
    private int maxSumInSingleLinePath(TreeNode node) {
        if(node.left == null && node.right == null) return node.val;
        if (node.left == null) {
            return max(node.val,node.val + maxSumInSingleLinePath(node.right));
        }
        if (node.right == null) {
            return max(node.val,node.val + maxSumInSingleLinePath(node.left));
        }
        return max( node.val, node.val + max(maxSumInSingleLinePath(node.left),
                maxSumInSingleLinePath(node.right)));
    }


    private int max(int a, int b) {
        if(a>=b) return a;
        return b;
    }
    private int max(int a, int b, int c) {
        return max(max(a,b),c);
    }
    private int max(int a, int b, int c, int d, int e, int f) {
        int x = max(a,b,c);
        int y = max(d,e,f);
        return max(x,y);
    }

    public static void main(String[] args) {
        BinaryTreeMaxPathSum binaryTreeMaxPathSum = new BinaryTreeMaxPathSum();
        Integer root[] = {1,2,3};
        TreeNode ROOT = new TreeNode(root[0], null, null);
        /*setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));
        System.out.println("\n*****************\n");


        root = new Integer[]{-10,9,20,null,null,15,7};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));
        System.out.println("\n*****************\n");


        root = new Integer[]{-10};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));
        System.out.println("\n*****************\n");


        root = new Integer[]{2,-1};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));
        System.out.println("\n*****************\n");


        root = new Integer[]{-2,1};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));

        root = new Integer[]{1,-2,3};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));

        root = new Integer[]{1,2,3,4,5,null,null,7,null,null,6,null,null,null,null,5};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));

        root = new Integer[]{2,-1,-2};
        ROOT = new TreeNode(root[0], null, null);
        setupTree(ROOT, root, 1);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));*/

        ROOT = new TreeNode(2, null, null);
        ROOT.left = new TreeNode(-4, null, null);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));


        ROOT = new TreeNode(4, null, null);
        ROOT.right = new TreeNode(2, null, null);
        ROOT.right.left = new TreeNode(-4, null, null);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));

        ROOT = new TreeNode(5, null, null);
        ROOT.left = new TreeNode(4, null, null);
        ROOT.left.right = new TreeNode(2, null, null);
        ROOT.left.right.left = new TreeNode(-4, null, null);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));

        ROOT = new TreeNode(-1, null, null);
        ROOT.left = new TreeNode(5, null, null);
        ROOT.left.left = new TreeNode(4, null, null);
        ROOT.left.left.right = new TreeNode(2, null, null);
        ROOT.left.left.right.left = new TreeNode(-4, null, null);
        TreeOperationMain.printTree(ROOT,0);
        TreeTraversal.printInorder(ROOT);
        PrintModules.print(binaryTreeMaxPathSum.maxPathSum(ROOT));
    }
    public static void setupTree(TreeNode ROOT, Integer a[], int start) {
        if (start >= a.length) return;
        if(a[start]!=null) ROOT.left = new TreeNode(a[start],null,null);
        if(start+1 < a.length && a[start+1]!=null) ROOT.right = new TreeNode(a[start+1], null,null);
        if(ROOT.left != null) setupTree(ROOT.left,a,(start*2)+1);
        if(ROOT.right != null) setupTree(ROOT.right,a,((start+1)*2)+1);
    }
}
