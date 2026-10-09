package src.lab2.matrix;

/** Операции над CCS-матрицей. */
public final class MatrixOps {

    private MatrixOps() {}

    /**
     * Циклический сдвиг каждого столбца вниз на n разрядов.
     * rowIndex[i] = (rowIndex[i] + n) mod rows
     * Затем внутри каждого столбца сортируем по rowIndex (требование CCS).
     */
    public static void cyclicShiftColumns(CCSMatrix m, int n) {
        int rows = m.rows;
        // нормализуем n в диапазон [0, rows)
        n = ((n % rows) + rows) % rows;
        if (n == 0) return;

        // 1) сдвигаем индексы строк
        for (int i = 0; i < m.rowIndex.length; i++) {
            m.rowIndex[i] = (m.rowIndex[i] + n) % rows;
        }

        // 2) сортируем элементы внутри каждого столбца по rowIndex
        for (int j = 0; j < m.cols; j++) {
            int from = m.colPtr[j];
            int to = m.colPtr[j + 1];
            sortByRow(m.values, m.rowIndex, from, to);
        }
    }

    /** Сортировка вставками участка [from; to) по rowIndex. */
    private static void sortByRow(double[] vals, int[] rows, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            double v = vals[i];
            int r = rows[i];
            int j = i - 1;
            while (j >= from && rows[j] > r) {
                vals[j + 1] = vals[j];
                rows[j + 1] = rows[j];
                j--;
            }
            vals[j + 1] = v;
            rows[j + 1] = r;
        }
    }
}