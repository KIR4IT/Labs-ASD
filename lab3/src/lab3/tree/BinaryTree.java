package src.lab3.tree;

/**
 * Бинарное дерево.
 * Содержит корень и рекурсивные методы обхода.
 */
public class BinaryTree {

    public TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Сумма значений всех ЛИСТЬЕВ.
     * Лист — узел без левого и правого потомка.
     * Рекурсивный обход.
     */
    public double sumLeaves() {
        return sumLeaves(root);
    }

    private double sumLeaves(TreeNode node) {
        if (node == null) return 0;

        // лист — нет ни левого, ни правого
        if (node.left == null && node.right == null) {
            return node.data;
        }

        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    /** Красивый вывод дерева (повёрнутое на 90°, рекурсивно). */
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(TreeNode node, int level) {
        if (node == null) return;
        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) System.out.print("    ");
        System.out.println(src.lab3.util.Num.format(node.data));
        printTree(node.left, level + 1);
    }
}