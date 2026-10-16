package src.lab6;

import src.lab6.io.Console;
import src.lab6.sort.DoubleArray;
import src.lab6.sort.QuickSort;
import src.lab6.util.Num;

public class Lab6 {

    public static void main(String[] args) {
        System.out.println("=== Лаба 6: быстрая сортировка чисел в диапазоне [N; K] ===");
        System.out.println("Введите числа через пробел или запятую.");
        System.out.println();

        // 1. Ввод массива
        DoubleArray arr = readArray();

        System.out.println();
        System.out.println("Исходный массив:");
        System.out.println("  " + toString(arr) + "  (" + arr.size() + " эл.)");

        // 2. Ввод N и K
        double n = readDouble("Введите N (нижняя граница): ");
        double k = readDouble("Введите K (верхняя граница): ");

        if (n > k) {
            double t = n;
            n = k;
            k = t;
            System.out.println("  [i] N > K, границы поменяны местами.");
        }

        // 3. Собираем индексы и значения чисел в диапазоне
        int[] positions = new int[arr.size()];
        int m = 0;
        for (int i = 0; i < arr.size(); i++) {
            double v = arr.get(i);
            if (v >= n && v <= k) positions[m++] = i;
        }

        if (m == 0) {
            System.out.println();
            System.out.println("В диапазоне [" + Num.format(n) + "; " + Num.format(k) + "] чисел нет.");
            return;
        }

        double[] inRange = new double[m];
        for (int i = 0; i < m; i++) inRange[i] = arr.get(positions[i]);

        System.out.println();
        System.out.println("Числа в диапазоне [" + Num.format(n) + "; " + Num.format(k) + "]:");
        System.out.println("  " + toString(inRange) + "  (" + m + " эл.)");

        // 4. Сортируем их
        QuickSort.sort(inRange);

        // 5. Раскладываем отсортированные значения обратно по исходным позициям
        for (int i = 0; i < m; i++) arr.set(positions[i], inRange[i]);

        System.out.println();
        System.out.println("Массив после сортировки чисел в диапазоне:");
        System.out.println("  " + toString(arr) + "  (" + arr.size() + " эл.)");
    }

    // ---------- ввод ----------

    private static DoubleArray readArray() {
        while (true) {
            System.out.print("Введите числа: ");
            String line = Num.trim(Console.readLine());

            if (line.length() == 0) {
                System.out.println("  [!] Пусто — попробуйте снова.");
                continue;
            }

            DoubleArray result = new DoubleArray();
            String[] parts = Num.split(line);
            int bad = 0;

            for (int i = 0; i < parts.length; i++) {
                if (Num.isDouble(parts[i])) result.add(Num.parse(parts[i]));
                else bad++;
            }

            if (result.isEmpty()) {
                System.out.println("  [!] Нет ни одного корректного числа — попробуйте снова.");
                continue;
            }
            if (bad > 0) System.out.println("  [i] Пропущено некорректных: " + bad);
            return result;
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = Num.trim(Console.readLine());
            if (line.length() == 0) {
                System.out.println("  [!] Пусто — попробуйте снова.");
                continue;
            }
            if (Num.isDouble(line)) return Num.parse(line);
            System.out.println("  [!] Не число — попробуйте снова.");
        }
    }

    // ---------- вывод ----------

    private static String toString(DoubleArray arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(Num.format(arr.get(i)));
        }
        return sb.append("]").toString();
    }

    private static String toString(double[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(Num.format(a[i]));
        }
        return sb.append("]").toString();
    }
}