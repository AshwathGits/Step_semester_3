package abstraction.class_problems;

public class WarehousePrinter {

    public static void printAll(Printable[] items) {

        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }
}