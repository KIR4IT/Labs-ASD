package src.lab5.hash;

/**
 * Хеш-таблица с открытой адресацией.
 * Ключ: ФИО (строка).
 * Хеш-функция: StringHash.
 * Коллизии: Collision (квадратичное опробование).
 * Рехеширование при loadFactor > 0.7.
 */
public class HashTable {

    private static final double LOAD_FACTOR_LIMIT = 0.7;

    private Entry[] table;
    private int size;
    private int count;

    public HashTable(int initialCapacity) {
        size = Primes.nextPrime(initialCapacity);
        table = new Entry[size];
        count = 0;
    }

    // =========================================================
    //  ВСТАВКА
    // =========================================================
    public boolean insert(String key) {
        if (key == null || key.length() == 0) return false;

        // если ключ уже есть — ничего не делаем
        if (findIndex(key) != -1) {
            return false;
        }

        if ((double) (count + 1) / size > LOAD_FACTOR_LIMIT) {
            rehash();
        }

        int h = StringHash.hash(key, size);
        int firstDeleted = -1;
        for (int i = 0; i < size; i++) {
            int idx = Collision.probe(h, i, size);
            Entry e = table[idx];
            if (e == null) {
                int target = (firstDeleted != -1) ? firstDeleted : idx;
                table[target] = new Entry(key);
                count++;
                return true;
            }
            if (e.isDeleted()) {
                if (firstDeleted == -1) firstDeleted = idx;
            } else if (e.key.equals(key)) {
                return false;   // уже есть
            }
        }
        if (firstDeleted != -1) {
            table[firstDeleted] = new Entry(key);
            count++;
            return true;
        }
        rehash();
        return insert(key);
    }

    // =========================================================
    //  ПОИСК (есть / нет)
    // =========================================================
    public boolean contains(String key) {
        return findIndex(key) != -1;
    }

    private int findIndex(String key) {
        int h = StringHash.hash(key, size);
        for (int i = 0; i < size; i++) {
            int idx = Collision.probe(h, i, size);
            Entry e = table[idx];
            if (e == null) return -1;
            if (e.isDeleted()) continue;
            if (e.key.equals(key)) return idx;
        }
        return -1;
    }

    // =========================================================
    //  УДАЛЕНИЕ
    // =========================================================
    public boolean remove(String key) {
        int idx = findIndex(key);
        if (idx == -1) return false;
        table[idx].deleted = true;
        count--;
        return true;
    }

    // =========================================================
    //  РЕХЕШИРОВАНИЕ
    // =========================================================
    private void rehash() {
        int newSize = Primes.nextPrime(size * 2);
        Entry[] old = table;
        int oldSize = size;
        table = new Entry[newSize];
        size = newSize;
        count = 0;

        for (int i = 0; i < oldSize; i++) {
            Entry e = old[i];
            if (e != null && e.isAlive()) {
                insert(e.key);
            }
        }
    }

    // =========================================================
    //  ПЕЧАТЬ
    // =========================================================
    public void print() {
        System.out.println("Хеш-таблица (размер=" + size + ", записей=" + count + "):");
        for (int i = 0; i < size; i++) {
            Entry e = table[i];
            if (e == null) {
                System.out.println("  [" + i + "] —");
            } else if (e.isDeleted()) {
                System.out.println("  [" + i + "] <удалено>");
            } else {
                System.out.println("  [" + i + "] " + e.key);
            }
        }
    }
}