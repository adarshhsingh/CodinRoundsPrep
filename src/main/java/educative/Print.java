package educative;

public class Print {

    public static void printTree(TreeNode head, int level) {
        if(head==null)return;
        printTree(head.right, level+1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.print(head.data);
        System.out.println();
        printTree(head.left, level+1);
    }
}
