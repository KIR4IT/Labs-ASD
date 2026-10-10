package src.lab5;

import src.lab5.io.Console;
import src.lab5.hash.HashTable;
import src.lab5.util.Num;

public class Lab5 {

    public static void main(String[] args) {
        System.out.println("=== Лаба 5: Хеш-таблица ===");
        System.out.println("Ключ: ФИО (строка)");
        System.out.println("Хеш-функция: полиномиальная по строке");
        System.out.println("Коллизии: квадратичное опробование");
        System.out.println("Рехеширование: при заполнении > 70%");
        System.out.println();

        HashTable table = new HashTable(7);

        while (true) {
            System.out.println();
            System.out.println("Меню:");
            System.out.println("  1 — вставить ФИО");
            System.out.println("  2 — найти ФИО");
            System.out.println("  3 — удалить ФИО");
            System.out.println("  4 — напечатать таблицу");
            System.out.println("  0 — выход");
            System.out.print("Выбор: ");

            String choice = Num.trim(Console.readLine());

            if (choice.equals("1")) {
                System.out.print("ФИО: ");
                String key = Num.trim(Console.readLine());
                if (key.length() == 0) {
                    System.out.println("  [!] Пустой ключ.");
                    continue;
                }
                if (table.insert(key)) System.out.println("  [+] Вставлено: " + key);
                else System.out.println("  [!] Уже есть в таблице: " + key);

            } else if (choice.equals("2")) {
                System.out.print("ФИО: ");
                String key = Num.trim(Console.readLine());
                if (table.contains(key)) System.out.println("  [+] Найдено: " + key);
                else System.out.println("  [-] Не найдено");

            } else if (choice.equals("3")) {
                System.out.print("ФИО: ");
                String key = Num.trim(Console.readLine());
                if (table.remove(key)) System.out.println("  [+] Удалено: " + key);
                else System.out.println("  [-] Не найдено");

            } else if (choice.equals("4")) {
                table.print();

            } else if (choice.equals("0")) {
                System.out.println("Выход.");
                return;

            } else {
                System.out.println("  [!] Неверный пункт.");
            }
        }
    }
}