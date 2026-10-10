package src.lab5.hash;

/** Запись хеш-таблицы ключ (ФИО) */
public class Entry {

    public String key;       // ФИО
    public boolean deleted;  // маркер ленивого удаления

    public Entry(String key) {
        this.key = key;
        this.deleted = false;
    }

    public boolean isDeleted() { return deleted; }
    public boolean isAlive() { return key != null && !deleted; }
}