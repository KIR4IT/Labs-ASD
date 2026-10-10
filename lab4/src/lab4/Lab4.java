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
    //Пример работы:
    // Корень — значение (или пусто): 50
    //  У узла 50 сколько ветвей? (0..100): 3
    //    Ветвь 1 узла 50 — значение (или пусто): 30
    //  У узла 30 сколько ветвей? (0..100): 3
    //    Ветвь 1 узла 30 — значение (или пусто): 10
    //  У узла 10 сколько ветвей? (0..100): 0
    //    Ветвь 2 узла 30 — значение (или пусто): 20
    //  У узла 20 сколько ветвей? (0..100): 0
    //    Ветвь 3 узла 30 — значение (или пусто): 25
    //  У узла 25 сколько ветвей? (0..100): 0
    //    Ветвь 2 узла 50 — значение (или пусто): 70
    //  У узла 70 сколько ветвей? (0..100): 2
    //    Ветвь 1 узла 70 — значение (или пусто): 60
    //  У узла 60 сколько ветвей? (0..100): 0
    //    Ветвь 2 узла 70 — значение (или пусто): 80
    //  У узла 80 сколько ветвей? (0..100): 0
    //    Ветвь 3 узла 50 — значение (или пусто): 90
    //  У узла 90 сколько ветвей? (0..100): 1
    //    Ветвь 1 узла 90 — значение (или пусто): 95
    //  У узла 95 сколько ветвей? (0..100): 2
    //    Ветвь 1 узла 95 — значение (или пусто): 92
    //  У узла 92 сколько ветвей? (0..100): 0
    //    Ветвь 2 узла 95 — значение (или пусто): 99
    //  У узла 99 сколько ветвей? (0..100): 0
    //
    //Дерево:
    //50
    //├── 30
    //│   ├── 10
    //│   ├── 20
    //│   └── 25
    //├── 70
    //│   ├── 60
    //│   └── 80
    //└── 90
    //    └── 95
    //        ├── 92
    //        └── 99
    //
    //Максимум среди внутренних вершин: 95
}