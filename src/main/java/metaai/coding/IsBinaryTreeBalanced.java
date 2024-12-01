package metaai.coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class node {
    int data;
    node left;
    node right;
}

public class IsBinaryTreeBalanced {

    public static void main(String[] args) {
        node root = new node(1, null, null);
        root.right = new node(3, null, null);
        root.left = new node(2, null, null);
        root.left.right = new node(5, null, null);
        root.left.left = new node(4, null, null);

        printPostOrderTree(root);
    }


    static void printPreOrderTree(node root) {
        if(root == null) return;
        System.out.print(root.data+" ");
        printPreOrderTree(root.left);
        printPreOrderTree(root.right);
    }

    static void printPostOrderTree(node root) {
        if(root == null) return;
        printPostOrderTree(root.left);
        printPostOrderTree(root.right);
        System.out.print(root.data+" ");
    }
}
