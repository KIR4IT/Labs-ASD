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

            String choice = Num.trim(Console.readLine());

            if (choice.equals("0")) {
                System.out.println(Menu.BYE);
                return;
            } else if (choice.equals("1")) {
                insert(table);
            } else if (choice.equals("2")) {
                find(table);
            } else if (choice.equals("3")) {
                delete(table);
            } else if (choice.equals("4")) {
                table.print();
            } else {
                System.out.println(Menu.BAD_CHOICE);
            }
        }
    }

    private static void insert(HashTable table) {
        System.out.print(Menu.PROMPT_NAME);
        String key = Num.trim(Console.readLine());
        if (key.length() == 0) { System.out.println(Menu.EMPTY_KEY); return; }
        if (table.contains(key)) { System.out.println(Menu.ALREADY + key); return; }

        table.insert(key);
        System.out.println(Menu.INSERTED + key);
    }

    private static void find(HashTable table) {
        System.out.print(Menu.PROMPT_NAME);
        String key = Num.trim(Console.readLine());
        if (table.contains(key)) System.out.println(Menu.FOUND + key);
        else System.out.println(Menu.NOT_FOUND);
    }

    private static void delete(HashTable table) {
        System.out.print(Menu.PROMPT_NAME);
        String key = Num.trim(Console.readLine());
        if (table.remove(key)) System.out.println(Menu.REMOVED + key);
        else System.out.println(Menu.NOT_FOUND);
    }
}