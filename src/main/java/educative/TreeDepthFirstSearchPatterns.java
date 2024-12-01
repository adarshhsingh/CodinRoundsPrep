package educative;
import java.util.Arrays;
import java.util.List;

class pair<T,S> {
    T x;
    S y;
    pair(T x, S y) {
        this.x = x;
        this.y = y;
    }
}

class triplet<T,S> {
    T min;
    T max;
    S valid;
    triplet(T x, T y, S z) {
        this.min = x;
        this.max = y;
        this.valid = z;
    }
}

public class TreeDepthFirstSearchPatterns {

    static int MIN_INT = Integer.MIN_VALUE;
    static int MAX_INT = Integer.MAX_VALUE;
    Integer previous = null;
    static int maxDiameter = 0;
    /**
     * Given a binary tree, you need to compute the length of the tree’s diameter.
     * The diameter of a binary tree is the length of the longest path
     * between any two nodes in a tree.
     *
     * This path may or may not pass through the root.
     */
    public int diameterOfTree(TreeNode<Integer> root) {
        // diameter = maxLeftHeight + 1 + maxRightHeight
        if(root == null) return 0;
        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);
        maxDiameter = Math.max(maxDiameter, (leftHeight+rightHeight));
        return maxDiameter;
    }

    public int maxHeight(TreeNode<Integer> root) {
        // height = 1 + max(leftHeight, rightHeight)
        if(root == null) return 0;
        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);
        maxDiameter = Math.max(maxDiameter, (leftHeight+rightHeight));
        return 1 + Math.max(leftHeight,rightHeight);
    }

    /**
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * @param root
     * @return boolean
     */
    public boolean isValidBST(TreeNode<Integer> root) {
       return isValidBSTHelper(root);
    }

    private boolean isValidBSTHelper(TreeNode<Integer> root) {
        if(root == null) return true;
        boolean isLeftValidBST = isValidBSTHelper(root.left);
        boolean isCurrentNodeValidBST =  true;
        if(previous!=null && root.data <= previous) {
            isCurrentNodeValidBST = false;
        }
        previous = root.data;
        boolean isRightValidBST = isValidBSTHelper(root.right);
        return isRightValidBST && isLeftValidBST && isCurrentNodeValidBST;
    }


    public triplet<Integer, Boolean> isValidBSTHelperComplex(TreeNode<Integer> root) {
        if(root.left == null && root.right == null) return new triplet<>(root.data, root.data, true);
        triplet currentNode = new triplet<>(root.data, root.data, true);
        if(root.left != null) {
            triplet leftTree = isValidBSTHelperComplex(root.left);
            if(!(boolean)leftTree.valid || root.data <= (int)leftTree.max) {
                currentNode.valid = false;
            }
            currentNode.min = leftTree.min;
        }

        if(root.right != null && (boolean)currentNode.valid) {
            triplet rightTree = isValidBSTHelperComplex(root.right);
            if(!(boolean)rightTree.valid || root.data >= (int)rightTree.min) {
                currentNode.valid = false;
            }
            currentNode.max = rightTree.max;
        }
        return currentNode;
    }





    // Driver code
    public static void main(String args[]) {
        TreeDepthFirstSearchPatterns obj = new TreeDepthFirstSearchPatterns();

        List<List<TreeNode<Integer>>> lists = Arrays.asList(
                Arrays.asList(new TreeNode<Integer>(2), new TreeNode<Integer>(1), new TreeNode<Integer>(3)),
                Arrays.asList(new TreeNode<Integer>(5), new TreeNode<Integer>(1), new TreeNode<Integer>(4), null, null, new TreeNode<Integer>(3), new TreeNode<Integer>(6)),
                Arrays.asList(new TreeNode<Integer>(5), new TreeNode<Integer>(4), new TreeNode<Integer>(6), null, null, new TreeNode<Integer>(3), new TreeNode<Integer>(7)),
                Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2), new TreeNode<Integer>(3), new TreeNode<Integer>(4), new TreeNode<Integer>(5), new TreeNode<Integer>(6)),
                Arrays.asList(new TreeNode<Integer>(12), new TreeNode<Integer>(7), new TreeNode<Integer>(1), new TreeNode<Integer>(9), new TreeNode<Integer>(10), new TreeNode<Integer>(15)),
                Arrays.asList(new TreeNode<Integer>(3), new TreeNode<Integer>(9), new TreeNode<Integer>(20), new TreeNode<Integer>(15), new TreeNode<Integer>(7)),
                Arrays.asList(new TreeNode<Integer>(1), new TreeNode<Integer>(2)),
                Arrays.asList(new TreeNode<Integer>(9), new TreeNode<Integer>(7), null, null, new TreeNode<Integer>(1), new TreeNode<Integer>(8), new TreeNode<Integer>(10), null, new TreeNode<Integer>(12)),
                Arrays.asList(new TreeNode<Integer>(2), new TreeNode<Integer>(2), new TreeNode<Integer>(2))
        );

        for (int i = 0; i < lists.size(); i++) {
            BinaryTree<Integer> t = new BinaryTree<Integer>(lists.get(i));
            System.out.println((i + 1) + ".\tBinary Tree");
            Print.printTree(t.root, 0);
            //System.out.println((i+1)+ "\t Diameter = "+ obj.diameterOfTree(t.root));
            maxDiameter = 0;
            System.out.println((i+1)+ "\t isValidBST = "+ obj.isValidBST(t.root));

        }
    }
}
