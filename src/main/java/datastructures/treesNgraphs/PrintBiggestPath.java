package datastructures.treesNgraphs;

/**
 * Given a binary tree, the task is to print the longest path from the root
 * node to the leaf node.
 *
 * If there are multiple answers print any one of them.
 *
 * Examples:
 *
 * Input:
 *       4
 *      / \
 *     3   6
 *        / \
 *       5   7
 * Output:
 * 4 -> 6 -> 7
 * Explanation:
 * Longest paths from root to leaf
 * are (4 -> 6 -> 5)
 * and (4 -> 6 -> 7).
 * Print any of them.
 *
 * Input:
 *          1
 *         / \
 *        2   3
 *      /  \
 *     4    5
 *    /      \
 *   7        6
 *  /
 * 5
 * Output:
 * 5 -> 7 -> 4 -> 2 -> 5 -> 6
 */
public class PrintBiggestPath {
    public static void main(String[] args) {
        Integer a[] = {4,3,6,null,null,5,7};
        Integer b[] = {1,2,3,4,5,null,null,7,null,null,6,null,null,null,null,5};
        TreeNode ROOT_A = new TreeNode(a[0], null, null);
        TreeOperationMain.setupTree(ROOT_A, a, 1);
        TreeNode ROOT_B = new TreeNode(b[0], null, null);
        TreeOperationMain.setupTree(ROOT_B, b, 1);
        TreeOperationMain.printTree(ROOT_A, 0);
        TreeOperationMain.printTree(ROOT_B, 0);
    }

    public void printLongestPath(TreeNode node) {

    }
}
