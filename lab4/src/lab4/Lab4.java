package src.lab4;

import src.lab4.io.Console;
import src.lab4.tree.NTree;
import src.lab4.tree.TreeNode;
import src.lab4.util.Num;

public class Lab4 {

    public static void main(String[] args) {
        System.out.println("=== Лаба 4: максимум среди внутренних вершин N-дерева ===");
        System.out.println("Ввод: значение узла, потом количество ветвей, потом сами ветвь.");
        System.out.println("Пустая строка вместо значения — нет узла (для корня = пустое дерево).");
        System.out.println();

        NTree tree = new NTree();
        tree.root = readNode("Корень");

        System.out.println();
        System.out.println("Дерево:");
        tree.printTree();

        double maxInternal = tree.maxInternal();
        System.out.println();
        System.out.println("Максимум среди внутренних вершин: " + Num.format(maxInternal));
    }

    /** Рекурсивный ввод узла N-дерева. */
    private static TreeNode readNode(String label) {
        // 1) значение
        double value;
        while (true) {
            System.out.print(label + " — значение (или пусто): ");
            String line = Num.trim(Console.readLine());

            if (line.length() == 0) return null;

            if (!Num.isDouble(line)) {
                System.out.println("  [!] Не число — попробуйте снова.");
                continue;
            }
            value = Num.parse(line);
            break;
        }

        // 2) количество ветвей
        int childCount;
        while (true) {
            System.out.print("  У узла " + Num.format(value) + " сколько ветвей? (0..100): ");
            String line = Num.trim(Console.readLine());
            try {
                childCount = Num.parseInt(line);
                if (childCount < 0 || childCount > 100) {
                    System.out.println("  [!] От 0 до 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Не целое — попробуйте снова.");
            }
        }

        TreeNode node = new TreeNode(value, childCount);

        // 3) рекурсивно вводим ветв
        for (int i = 0; i < childCount; i++) {
            TreeNode child = readNode("    Ветвь " + (i + 1) + " узла " + Num.format(value));
            node.addChild(child);
        }

        return node;
    }
}