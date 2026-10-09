package src.lab3;

import src.lab3.io.Console;
import src.lab3.tree.BinaryTree;
import src.lab3.tree.TreeNode;
import src.lab3.util.Num;

public class Lab3 {

    public static void main(String[] args) {
        System.out.println("=== Лаба 3: сумма листьев бинарного дерева ===");
        System.out.println("Ввод дерева рекурсивно.");
        System.out.println("Для каждого узла вводится его значение.");
        System.out.println("Пустая строка — нет потомка.");
        System.out.println();

        BinaryTree tree = new BinaryTree();
        tree.root = readNode("Корень");

        System.out.println();
        System.out.println("Дерево (повёрнуто на 90°, корень слева):");
        tree.printTree();

        double sum = tree.sumLeaves();
        System.out.println();
        System.out.println("Сумма значений всех листьев: " + Num.format(sum));
    }

    /** Рекурсивный ввод узла. */
    private static TreeNode readNode(String label) {
        while (true) {
            System.out.print(label + " (число, или пусто): ");
            String line = Num.trim(Console.readLine());

            if (line.length() == 0) return null;

            if (!Num.isDouble(line)) {
                System.out.println("  [!] Не число — попробуйте снова.");
                continue;
            }

            double v = Num.parse(line);
            TreeNode node = new TreeNode(v);
            node.left  = readNode("  левый потомок "  + Num.format(v));
            node.right = readNode("  правый потомок " + Num.format(v));
            return node;
        }
    }
}