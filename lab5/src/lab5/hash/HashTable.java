package src.lab5.hash;

import src.lab5.resources.Menu;

/**
 * Хеш-таблица, открытая адресация.
 * Ключ: ФИО. Хеш: StringHash. Коллизии: Collision.
 */
public class HashTable {

    private static final double LIMIT = 0.7;

    private Entry[] table;
    private int size;
    private int count;

    public HashTable(int initialCapacity) {
        size = Primes.nextPrime(initialCapacity);
        table = new Entry[size];
    }

    // ---------- API ----------

    public void insert(String key) {
        if (key == null || key.length() == 0) return;
        if (findSlot(key, false) != -1) return;              // уже есть
        if ((double) count / size >= LIMIT) resize();

        table[findSlot(key, true)] = new Entry(key);
        count++;
    }

    public boolean contains(String key) {
        return findSlot(key, false) != -1;
    }

    public boolean remove(String key) {
        int idx = findSlot(key, false);
        if (idx == -1) return false;

        table[idx] = null;
        count--;
        rebuild();
        return true;
    }

    public void print() {
        System.out.println(Menu.tableHeader(size, count));
        for (int i = 0; i < size; i++) {
            if (table[i] == null) System.out.println(Menu.slotEmpty(i));
            else System.out.println(Menu.slotValue(i, table[i].key));
        }
    }

    // ---------- внутреннее ----------

    /**
     * free = true  — вернуть первый пустой слот (для вставки).
     * free = false — вернуть слот с ключом или -1.
     */
    private int findSlot(String key, boolean free) {
        int h = StringHash.hash(key, size);
        for (int i = 0; i < size; i++) {
            int idx = Collision.probe(h, i, size);
            if (table[idx] == null) return free ? idx : -1;
            if (table[idx].key.equals(key)) return idx;
        }
        return -1;
    }

    private void resize() {
        size = Primes.nextPrime(size * 2);
        rebuild();
    }

    /** Пересборка без вызова insert — иначе на середине перевставки снова сработает resize. */
    private void rebuild() {
        Entry[] old = table;
        int oldSize = old.length;

        table = new Entry[size];
        count = 0;

        for (int i = 0; i < oldSize; i++) {
            if (old[i] != null) {
                table[findSlot(old[i].key, true)] = old[i];
                count++;
            }
        }
    }
}