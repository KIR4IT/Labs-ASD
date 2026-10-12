package src.lab5;

import src.lab5.io.Console;
import src.lab5.hash.HashTable;
import src.lab5.util.Num;

public class Lab5 {

    public static void main(String[] args) {
        System.out.println(Menu.HEADER);
        HashTable table = new HashTable(7);

        while (true) {
            System.out.println(Menu.MENU);
            System.out.print(Menu.PROMPT_CHOICE);

            switch (Num.trim(Console.readLine())) {
                case "0" -> { System.out.println(Menu.BYE); return; }
                case "1" -> insert(table);
                case "2" -> find(table);
                case "3" -> delete(table);
                case "4" -> table.print();
                default  -> System.out.println(Menu.BAD_CHOICE);
            }
        }
    }

    private static void insert(HashTable table) {
        String key = askKey();
        if (key.length() == 0)     { System.out.println(Menu.EMPTY_KEY); return; }
        if (table.contains(key))   { System.out.println(Menu.ALREADY + key); return; }
        table.insert(key);
        System.out.println(Menu.INSERTED + key);
    }

    private static void find(HashTable table) {
        String key = askKey();
        if (table.contains(key)) System.out.println(Menu.FOUND + key);
        else                     System.out.println(Menu.NOT_FOUND);
    }

    private static void delete(HashTable table) {
        String key = askKey();
        if (table.remove(key)) System.out.println(Menu.REMOVED + key);
        else                   System.out.println(Menu.NOT_FOUND);
    }

    private static String askKey() {
        System.out.print(Menu.PROMPT_NAME);
        return Num.trim(Console.readLine());
    }
}