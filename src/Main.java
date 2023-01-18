import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        int operation;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                System.out.println("Выберите операцию:\n" +
                        "0 - выйти, 1 - Добавить, 2 - показать, 3 - удалить, 4 - поиск");
                try {
                    operation = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException n) {
                    System.out.println("Для выбора операции введите от 0 до 4 включительно!\n");
                    continue;
                }

                if (operation == 0) {
                    break;
                } else if (operation == 1) {
                    Main.addPurchase(arrayList, reader);
                } else if (operation == 2) {
                    Main.showPurchase(arrayList);
                } else if (operation == 3) {
                    Main.removePurchase(arrayList, reader);
                } else if (operation == 4) {
                    Main.searchPurchase(arrayList, reader);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }

    }

    public static void addPurchase(ArrayList arrayList, BufferedReader reader) throws IOException {
        System.out.println("Какую покупку хотите добавить?");
        String purchase = reader.readLine();
        arrayList.add(purchase);
        System.out.println("Итого в списке покупок: " + arrayList.size() + "\n");
    }

    public static void showPurchase(ArrayList arrayList) throws IOException {
        System.out.println("Список покупок: ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(i + 1 + ". " + arrayList.get(i));
        }
        System.out.println();
    }

    public static void removePurchase(ArrayList arrayList, BufferedReader reader) throws IOException {
        Main.showPurchase(arrayList);
        System.out.println("Какую хотите удалить? Введите номер или название!");
        String str = reader.readLine();
        String delPursh = null;
        if (checkNumber(str)) {
            Integer i = Integer.parseInt(str);
            try {
                delPursh = (String) arrayList.get(i - 1);
                arrayList.remove(i - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Покупки под номером " + "\"" + i + "\"" + " не существует!\n");
                return;
            }
            System.out.print("Покупка " + "\"" + delPursh + "\"" + " удалена, ");
            showPurchase(arrayList);
        } else {
            if (arrayList.contains(str)) {
                arrayList.remove(str);
                System.out.print("Покупка " + "\"" + str + "\"" + " удалена, ");
                showPurchase(arrayList);
            } else {
                System.out.println("Покупки под названием " + "\"" + str + "\"" + " не существует!\n");
            }
        }
    }

    public static boolean checkNumber(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void searchPurchase(ArrayList<String> arrayList, BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("Найдено: \n");
        System.out.println("Введите текст для поиска:");
        String str = reader.readLine().toLowerCase();
        boolean isSearch = false;
        int i = 1;
        for (String s : arrayList) {
            if (s.toLowerCase().contains(str)) {
                if (isSearch == false) {
                    isSearch = true;
                }
                stringBuilder.append(i)
                        .append(". ")
                        .append(s)
                        .append("\n");
                i++;
            } else {
                i++;
            }
        }

        if (isSearch) {
            System.out.println(stringBuilder.toString());
        } else {
            System.out.println("По Вашему запросу " + "\"" + str + "\"" + " ничего найдено не было!");
        }
    }

}
