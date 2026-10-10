package src.lab2;

import src.lab2.io.Console;
import src.lab2.matrix.CCSMatrix;
import src.lab2.matrix.MatrixOps;
import src.lab2.util.Num;

public class Lab2 {

    public static void main(String[] args) {
        System.out.println("=== Лаба 2: CCS, сдвиг столбцов ===");
        System.out.println("Введите матрицу построчно.");
        System.out.println("Строка — числа через пробел. Пустая строка — конец ввода.");
        System.out.println();

        double[][] a = null;
        int rows = 0, cols = 0;

        while (a == null) {
            double[][] temp = new double[64][];
            int r =0;
            int expectedCols = -1;
            boolean bad = false;

            while (true) {
                System.out.print("Строка " + (r + 1) + ": ");
                String line = Num.trim(Console.readLine());

                if (line.length() == 0) break;

                String[] parts = Num.split(line);
                double[] row = new double[parts.length];
                int k = 0;
                for (int i = 0; i < parts.length; i++) {
                    if (Num.isDouble(parts[i])) row[k++] = Num.parse(parts[i]);
                    else System.out.println("  [!] Пропущен токен: '" + parts[i] + "'");
                }
                double[] trimmed = new double[k];
                for (int i = 0; i < k; i++) trimmed[i] = row[i];
                if (k == 0) continue;

                if (expectedCols == -1) expectedCols = k;
                else if (k != expectedCols) {
                    System.out.println("  [!] В строке " + (r + 1) +
                            " должно быть " + expectedCols + " чисел, а не " + k);
                    bad = true;
                    break;
                }
                temp[r++] = trimmed;
                if (r >= temp.length) {
                    System.out.println("  [!] Слишком много строк ( 64 макс).");
                    break;
                }
            }

            if (bad) continue;
            if (r == 0) {
                System.out.println("  [!] Матрица пустая — попробуйте снова.");
                continue;
            }

            rows = r;
            cols = expectedCols;
            a = new double[rows][];
            for (int i = 0; i < rows; i++) a[i] = temp[i];
        }

        CCSMatrix m = CCSMatrix.fromDense(a, rows, cols);

        System.out.println();
        System.out.println("Исходная матрица (" + rows + "x" + cols + "):");
        System.out.println(m);

        int n = 0;
        while (true) {
            System.out.print("Введите n (сдвиг вниз, любое целое): ");
            String s = Num.trim(Console.readLine());
            try {
                n = parseInt(s);
                break;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Не целое число — попробуйте снова.");
            }
        }

        MatrixOps.cyclicShiftColumns(m, n);

        System.out.println();
        System.out.println("После сдвига на " + n + ":");
        System.out.println(m);
    }

    private static int parseInt(String s) {
        s = Num.trim(s);
        if (s.length() == 0) throw new NumberFormatException("empty");
        int i = 0;
        boolean neg = false;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            neg = s.charAt(0) == '-';
            i = 1;
        }
        if (i >= s.length()) throw new NumberFormatException("no digits");
        long v = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') throw new NumberFormatException("not digit");
            v = v * 10 + (c - '0');
            if (v > Integer.MAX_VALUE) throw new NumberFormatException("overflow");
        }
        return neg ? (int) -v : (int) v;
    }
}