package src.lab2.matrix;

import src.lab2.util.Num;

/** матрица CCS */
public class CCSMatrix {

    public int rows;
    public int cols;
    public double[] values;   // значения ненулевых элементов 1 массив (ненулевые элементы)
    public int[] rowIndex;    // индексы строк для values 2 массив (для каждого элемента хранит номер строки)
    public int[] colPtr;      // colPtr[j] — начало столбца j 3 массив (длина, индекс с которого начинаются элементы столбца)

    public CCSMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.values = new double[0];
        this.rowIndex = new int[0];
        this.colPtr = new int[cols + 1];
    }

    /** CCS, nnz - это не нулевой */
    public static CCSMatrix fromDense(double[][] a, int rows, int cols) {
        int nnz = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (a[i][j] != 0) nnz++;

        CCSMatrix m = new CCSMatrix(rows, cols);
        m.values = new double[nnz];
        m.rowIndex = new int[nnz];
        m.colPtr = new int[cols + 1];

        int k = 0;
        for (int j = 0; j < cols; j++) {
            m.colPtr[j] = k;
            for (int i = 0; i < rows; i++) {
                if (a[i][j] != 0) {
                    m.values[k] = a[i][j];
                    m.rowIndex[k] = i;
                    k++;
                }
            }
        }
        m.colPtr[cols] =k;
        return m;
    }

    /** CCS для вывода */
    public double[][] toDense() {
        double[][] a = new double[rows][cols];
        for (int j = 0; j < cols; j++) {
            for (int k = colPtr[j]; k < colPtr[j + 1]; k++) {
                a[rowIndex[k]][j] = values[k];
            }
        }
        return a;
    }

    @Override
    public String toString() {
        double[][] a = toDense();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[");
            for (int j = 0; j < cols; j++) {
                if (j > 0) sb.append(", ");
                sb.append(Num.format(a[i][j]));
            }
            sb.append("]");
            if (i < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }
}