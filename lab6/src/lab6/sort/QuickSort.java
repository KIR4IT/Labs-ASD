package src.lab6.sort;

/**
 * Быстрая сортировка (QuickSort).
 * 3-way partition:
 * разбивает массив на три части — < pivot, = pivot, > pivot.
 * Безопасно работает на массивах с дубликатами — без StackOverflowError.
 * Средняя сложность O(n log n), худшая O(n^2).
 */
public final class QuickSort {

    private QuickSort() {}

    /** Сортирует весь массив. */
    public static void sort(double[] a) {
        if (a == null || a.length < 2) return;
        sort(a, 0, a.length - 1);
    }

    private static void sort(double[] a, int left, int right) {
        if (left >= right) return;

        double pivot = a[left + (right - left) / 2];

        int lt = left;      // граница "меньше pivot"
        int gt = right;     // граница "больше pivot"
        int i = left;       // текущий элемент

        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else if (a[i] > pivot) {
                swap(a, i, gt--);
            } else {
                i++;    // равен pivot — пропускаем
            }
        }

        sort(a, left, lt - 1);
        sort(a, gt + 1, right);
    }

    private static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}