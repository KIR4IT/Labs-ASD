package src.lab5.hash;

/**
 * Своя хеш-функция для строковых ключей.
 * Полиномиальная: h = h * 31 + код_символа.
 */
public final class StringHash {

    private static final int MULTIPLIER = 31;

    private StringHash() {}

    /** Возвращает хеш-значение в диапазоне [0; size). */
    public static int hash(String key, int size) {
        int h = 0;
        for (int i = 0; i < key.length(); i++) {
            h = h * MULTIPLIER + key.charAt(i);
        }
        // отбрасываем знак — оставляем только положительное
        h = h & 0x7FFFFFFF;
        return h % size;
    }
}