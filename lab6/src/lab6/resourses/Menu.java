package src.lab6.resources;

/** Все тексты программы. */
public final class Menu {

    private Menu() {}

    public static final String HEADER =
            "Лаба 6: быстрая сортировка чисел в диапазоне [N; K] \n" +
                    "Введите числа через пробел или запятую.";

    public static final String ARRAY  = "Введите числа: ";
    public static final String N = "Введите N (нижняя граница): ";
    public static final String K = "Введите K (верхняя граница): ";

    public static final String EMPTY_INPUT   = "  [!] Пусто — попробуйте снова.";
    public static final String NO_NUMBERS    = "  [!] Нет ни одного корректного числа — попробуйте снова.";
    public static final String NOT_NUMBER    = "  [!] Не число — попробуйте снова.";
    public static final String SWAPPED       = "  [i] N > K, границы поменяны местами.";

    public static String skipped(int bad) {
        return "  [i] Пропущено некорректных: " + bad;
    }

    public static String originalArray(String list, int size) {
        return "\nИсходный массив:\n  " + list + "  (" + size + " эл.)";
    }

    public static String noNumbersInRange(String n, String k) {
        return "\nВ диапазоне [" + n + "; " + k + "] чисел нет.";
    }

    public static String inRangeHeader(String n, String k) {
        return "\nЧисла в диапазоне [" + n + "; " + k + "]:";
    }

    public static String inRangeList(String list, int size) {
        return "  " + list + "  (" + size + " эл.)";
    }

    public static String resultHeader() {
        return "\nМассив после сортировки чисел в диапазоне:";
    }
}