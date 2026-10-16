package src.lab6;

import src.lab6.io.Console;
import src.lab6.resources.Menu;
import src.lab6.sort.DoubleArray;
import src.lab6.sort.QuickSort;
import src.lab6.util.Num;

public class Lab6 {

    public static void main(String[] args) {
        System.out.println(Menu.HEADER);
        System.out.println();

        DoubleArray arr = readArray();
        System.out.println(Menu.originalArray(toString(arr), arr.size()));

        double n = readDouble(Menu.N);
        double k = readDouble(Menu.K);

        if (n > k) {
            double t = n;
            n = k;
            k = t;
            System.out.println(Menu.SWAPPED);
        }

        // 1. собираем индексы и значения чисел в диапазоне
        int[] positions = new int[arr.size()];
        int m = 0;
        for (int i = 0; i < arr.size(); i++) {
            double v = arr.get(i);
            if (v >= n && v <= k) positions[m++] = i;
        }

        if (m == 0) {
            System.out.println(Menu.noNumbersInRange(Num.format(n), Num.format(k)));
            return;
        }

        double[] inRange = new double[m];
        for (int i = 0; i < m; i++) inRange[i] = arr.get(positions[i]);

        System.out.println(Menu.inRangeHeader(Num.format(n), Num.format(k)));
        System.out.println(Menu.inRangeList(toString(inRange), m));

        // 2. сортируем
        QuickSort.sort(inRange);

        // 3. раскладываем обратно
        for (int i = 0; i < m; i++) arr.set(positions[i], inRange[i]);

        System.out.println(Menu.resultHeader());
        System.out.println(Menu.inRangeList(toString(arr), arr.size()));
    }

    // ---------- ввод ----------

    private static DoubleArray readArray() {
        while (true) {
            System.out.print(Menu.ARRAY);
            String line = Num.trim(Console.readLine());

            if (line.length() == 0) {
                System.out.println(Menu.EMPTY_INPUT);
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
                System.out.println(Menu.NO_NUMBERS);
                continue;
            }
            if (bad > 0) System.out.println(Menu.skipped(bad));
            return result;
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = Num.trim(Console.readLine());
            if (line.length() == 0) {
                System.out.println(Menu.EMPTY_INPUT);
                continue;
            }
            if (Num.isDouble(line)) return Num.parse(line);
            System.out.println(Menu.NOT_NUMBER);
        }
    }

    // ---------- вывод ----------

    /** Делегируем второму методу: превращаем DoubleArray в double[] и форматируем. */
    private static String toString(DoubleArray arr) {
        return toString(arr.toArray());
    }

    /** Основной метод форматирования массива в строку вида [1, 2, 3]. */
    private static String toString(double[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(Num.format(a[i]));
        }
        return sb.append("]").toString();
    }
}