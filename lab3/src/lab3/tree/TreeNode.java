package src.lab3.tree;

/** Узел бинарного дерева. */
public class TreeNode {
    public double data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(double data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}