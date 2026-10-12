package src.lab5;

/** Все тексты программы: заголовки, меню, ответы. */
public final class Menu {

    private Menu() {}

    // заголовок
    public static final String HEADER =
            "=== Лаба 5: Хеш-таблица ===\n" +
                    "Ключ: ФИО (строка)\n" +
                    "Хеш-функция: полиномиальная по строке\n" +
                    "Коллизии: квадратичное опробование\n" +
                    "Рехеширование: при заполнении > 70%";

    // меню
    public static final String MENU =
            "\nМеню:\n" +
                    "  1 — вставить ФИО\n" +
                    "  2 — найти ФИО\n" +
                    "  3 — удалить ФИО\n" +
                    "  4 — напечатать таблицу\n" +
                    "  0 — выход";

    // подсказки
    public static final String PROMPT_CHOICE = "Выбор: ";
    public static final String PROMPT_NAME   = "ФИО: ";

    // ответы
    public static final String EMPTY_KEY   = "  [!] Пустой ключ.";
    public static final String ALREADY     = "  [!] Уже есть: ";
    public static final String INSERTED    = "  [+] Вставлено: ";
    public static final String FOUND       = "  [+] Найдено: ";
    public static final String NOT_FOUND   = "  [-] Не найдено";
    public static final String REMOVED     = "  [+] Удалено: ";
    public static final String BAD_CHOICE  = "  [!] Неверный пункт.";
    public static final String BYE         = "Выход.";

    // заголовок печати таблицы
    public static String tableHeader(int size, int count) {
        return "Хеш-таблица (размер=" + size + ", записей=" + count + ")";
    }

    public static String slotEmpty(int i)   { return "  [" + i + "] —"; }
    public static String slotValue(int i, String key) { return "  [" + i + "] " + key; }
}