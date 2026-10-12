package src.lab5.hash;

/** Полиномиальная хеш-функция для строк: h = h*31 + код_символа. */
public final class StringHash {

    private StringHash() {}

    public static int hash(String key, int size) {
        int h = 0;
        for (int i = 0; i < key.length(); i++) h = h * 31 + key.charAt(i);
        return (h & 0x7FFFFFFF) % size;
    }
}