package src.lab3.tree;

import src.lab3.util.Num;

/** Бинарное дерево (содержит корень, рекурсивный метод подсчёта суммы листьев) */
public class BinaryTree {
    public TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /** Сумма значений всех листьев. */
    public double sumLeaves() {
        return sumLeaves(root);
    }

    private double sumLeaves(TreeNode node) {
        if (node== null) return 0;

        // лист — нет ни левого, ни правого потомка
        if (node.left == null && node.right == null) {
            return node.data;
        }

        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    //  Пример вывода Корень (число, или пусто): 5
    //  левый потомок 5 (число, или пусто): 3
    //  левый потомок 3 (число, или пусто): 1
    //  левый потомок 1 (число, или пусто):
    //  правый потомок 1 (число, или пусто):
    //  правый потомок 3 (число, или пусто): 4
    //  левый потомок 4 (число, или пусто):
    //  правый потомок 4 (число, или пусто):
    //  правый потомок 5 (число, или пусто): 8
    //  левый потомок 8 (число, или пусто): 3
    //  левый потомок 3 (число, или пусто):
    //  правый потомок 3 (число, или пусто):
    //  правый потомок 8 (число, или пусто): 9
    //  левый потомок 9 (число, или пусто):
    //  правый потомок 9 (число, или пусто):
    //
    //Дерево (повёрнуто на 90°, корень слева):
    //        5
    //      /   \
    //    3       8
    //   / \     / \
    //  1   4   3   9
    //
    //Сумма значений всех листьев: 17

    public void printTree() {
        if (root == null) {
            System.out.println("(пусто)");
            return;
        }

        int height = height(root);
        int width = (1 << height) * 2;      // 2^height * 2
        char[][] canvas = new char[height * 2][width];

        for (int i = 0; i < canvas.length; i++)
            for (int j = 0; j < canvas[i].length; j++)
                canvas[i][j] = ' ';

        fill(root, 0, width / 2, width / 4, canvas);

        for (int i = 0; i < canvas.length; i++) {
            String line = new String(canvas[i]);
            int end = line.length();
            while (end > 0 && line.charAt(end - 1) == ' ') end--;
            if (end > 0) System.out.println(line.substring(0, end));
        }
    }

    private int height(TreeNode n) {
        if (n == null) return 0;
        int l = height(n.left);
        int r = height(n.right);
        return 1 + (l > r ? l : r);
    }

    /** (Вывод узлов и веток) row — текущая строка , col — столб, где стоит узел , offset — сдвиг для следующих */
    private void fill(TreeNode n, int row, int col, int offset, char[][] c) {
        if (n == null) return;

        String s =Num.format(n.data);
        for (int k = 0; k < s.length(); k++)
            c[row][col + k] = s.charAt(k);

        if (n.left != null) {
            c[row + 1][col - offset / 2] = '/';
            fill(n.left, row + 2, col - offset, offset / 2, c);
        }
        if (n.right != null) {
            c[row + 1][col + offset / 2] = '\\';
            fill(n.right, row + 2, col + offset, offset / 2, c);
        }
    }
}