package src.lab5.hash;

/**
 * Метод разрешения коллизий: КВАДРАТИЧНОЕ ОПРОБОВАНИЕ.
 * index = (h + i*i) mod size,  i = 0, 1, 2, ...
 */
public final class Collision {

    private Collision() {}

    public static int probe(int h, int i, int size) {
        return (h + i * i) % size;
    }
}