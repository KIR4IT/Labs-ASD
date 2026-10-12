package src.lab5.hash;

/** Квадратичное опробование: (h + i*i) mod size. */
public final class Collision {

    private Collision() {}

    public static int probe(int h, int i, int size) {
        return (h + i * i) % size;
    }
}