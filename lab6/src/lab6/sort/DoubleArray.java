package src.lab6.sort;

/**
 * Свой динамический массив double.
 * Без java.util.ArrayList — расширяется вручную.
 */
public class DoubleArray {

    private double[] data;
    private int size;

    public DoubleArray() {
        data = new double[8];
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public double get(int i) { return data[i]; }
    public void set(int i, double v) { data[i] = v; }

    public void add(double v) {
        if (size == data.length) grow();
        data[size++] = v;
    }

    private void grow() {
        double[] bigger = new double[data.length * 2];
        for (int i = 0; i < data.length; i++) bigger[i] = data[i];
        data = bigger;
    }

    /** Возвращает копию внутреннего массива длиной size. */
    public double[] toArray() {
        double[] out = new double[size];
        for (int i = 0; i < size; i++) out[i] = data[i];
        return out;
    }
}