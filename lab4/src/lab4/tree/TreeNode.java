package lab4.tree;

/** Узел N-дерева: значение и массив потомков. */
public class TreeNode {

    public double data;
    public TreeNode[] children;
    public int childCount;

    public TreeNode(double data, int maxChildren) {
        this.data = data;
        this.children = new TreeNode[maxChildren];
        this.childCount = 0;
    }

    public void addChild(TreeNode child) {
        if (childCount < children.length) {
            children[childCount++] = child;
        }
    }

    public boolean isLeaf() {
        return childCount == 0;
    }
}