package src.lab4.tree;

import src.lab4.util.Num;

public class NTree {

    public TreeNode root;

    public NTree() {
        root = null;
    }

    // ЗАДАНИЕ

    public double maxInternal() {
        boolean[] found = { false };
        double result = maxInternal(root, found);
        return found[0] ? result : 0;
    }

    private double maxInternal(TreeNode node, boolean[] found) {
        if (node == null) return 0;

        double best = found[0] ? -1.0/0.0 : 0;

        if (!node.isLeaf()) {
            if (!found[0] || node.data > best) best = node.data;
            found[0] = true;
        }

        for (int i = 0; i < node.childCount; i++) {
            double childBest = maxInternal(node.children[i], found);
            if (found[0] && childBest > best) best = childBest;
        }

        return best;
    }

    //ВЫВОД
    public void printTree() {
        if (root == null) {
            System.out.println("(пусто)");
            return;
        }
        System.out.println(Num.format(root.data));
        printChildren(root, "");
    }

    private void printChildren(TreeNode node, String prefix) {
        for (int i = 0; i < node.childCount; i++) {
            boolean isLast = (i == node.childCount - 1);
            TreeNode child = node.children[i];

            System.out.print(prefix);
            System.out.print(isLast ? "└── " : "├── ");
            System.out.println(Num.format(child.data));

            String nextPrefix = prefix + (isLast ? "    " : "│   ");
            printChildren(child, nextPrefix);
        }
    }
}