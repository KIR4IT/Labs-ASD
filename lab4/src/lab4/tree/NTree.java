package lab4.tree;

import lab4.util.Num;

/** рекурсивный поиск максимума среди ВНУТРЕННИХ вершин */
public class NTree {

    public TreeNode root;

    public NTree() {
        root = null;
    }

    // максимум среди ВНУТРЕННИХ вершин

    /**
     * Возвращает максимальное значение среди внутренних узлов.
     * Внутренний узел — у которого есть хотя бы один потомок.
     * Если внутренних узлов нет — возвращает Нан (или можно вернуть 0).
     */
    public double maxInternal() {
        boolean[] found = { false };
        double result = maxInternal(root, found);
        return found[0] ? result : 0;   // если внутренних нет — вернём 0
    }

    private double maxInternal(TreeNode node, boolean[] found) {
        if (node == null) return 0;

        double best = found[0] ? Double.NEGATIVE_INFINITY : 0;

        // если это внутренний узел — он участвует в сравнении
        if (!node.isLeaf()) {
            if (!found[0] || node.data > best) best = node.data;
            found[0] = true;
        }

        // обходим всех детей
        for (int i = 0; i < node.childCount; i++) {
            double childBest = maxInternal(node.children[i], found);
            if (found[0] && childBest > best) best = childBest;
        }

        return best;
    }

    // Вывод

    public void printTree() {
        if (root == null) {
            System.out.println("(пусто)");
            return;
        }
        printTree(root, 0);
    }

    private void printTree(TreeNode node, int level) {
        for (int i = 0; i < level; i++) System.out.print("    ");
        System.out.println(Num.format(node.data));
        for (int i = 0; i < node.childCount; i++) {
            printTree(node.children[i], level + 1);
        }
    }
}