package src.lab5.hash;

import src.lab5.Menu;

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
        if (contains(key)) return;
        if ((double) count / size >= LIMIT) rehash();

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
        reinsertAfter(idx);
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

    /** free=true — первый пустой слот, free=false — слот с ключом или -1. */
    private int findSlot(String key, boolean free) {
        int h = StringHash.hash(key, size);
        for (int i = 0; i < size; i++) {
            int idx = Collision.probe(h, i, size);
            if (table[idx] == null) return free ? idx : -1;
            if (table[idx].key.equals(key)) return idx;
        }
        return -1;
    }

    private void rehash() {
        Entry[] old = table;
        int oldSize = size;

        size = Primes.nextPrime(oldSize * 2);
        table = new Entry[size];
        count = 0;

        for (int i = 0; i < oldSize; i++)
            if (old[i] != null) insert(old[i].key);
    }

    private void reinsertAfter(int start) {
        int i = (start + 1) % size;
        while (table[i] != null) {
            String k = table[i].key;
            table[i] = null;
            count--;
            insert(k);
            i = (i + 1) % size;
        }
    }
}