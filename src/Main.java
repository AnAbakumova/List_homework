import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<String> shoppingList = new ArrayList<>();
    public static String choice, textForSearch;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите одну из операций:\n1. Добавить\n2. Показать\n3. Удалить\n4. Найти");
            choice = scanner.nextLine();
            if (choice.toLowerCase().equals("end")) {
                break;
            }
            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    shoppingList();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    findProduct();
                    break;
                default:
                    System.out.println("Нет такой операции. Попробуйте ещё раз.\n");
            }
        }
    }

    public static void shoppingList() {
        if (shoppingList.isEmpty()) {
            System.out.println("Список покупок пуст.\n");
        } else {
            System.out.println("Список покупок:");
            for (int i = 0; i < shoppingList.size(); i++) {
                System.out.println((i + 1) + ". " + shoppingList.get(i));
            }
            System.out.print("\n");
        }
    }

    public static void addProduct() {
        System.out.println("Какую покупку хотите добавить?");
        shoppingList.add(scanner.nextLine());
        System.out.println("Итого в списке покупок: " + shoppingList.size() + "\n");
    }

    public static void deleteProduct() {
        if (shoppingList.isEmpty()) {
            System.out.println("Список покупок пуст.\n");
        } else {
            shoppingList();
            System.out.println("Какой товар хотите удалить? Введите номер или название");
            String product = scanner.nextLine();
            try {
                int isNumber = Integer.parseInt(product);//проверка, пришло ли число, если нет - выдаст исключение NumberFormatException
                System.out.println("Покупка \"" + shoppingList.get(isNumber - 1) + "\" удалена.");
                shoppingList.remove(isNumber - 1);
                shoppingList();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Нет такого товара. Попробуйте снова.\n");
                deleteProduct();
            } catch (NumberFormatException e) {
                if (shoppingList.contains(product)) {
                    shoppingList.remove(product);
                    System.out.println("Покупка \"" + product + "\" удалена.");
                    shoppingList();
                } else {
                    System.out.println("Нет такого товара. Попробуйте снова.\n");
                    deleteProduct();
                }
            }
        }
    }

    public static void findProduct() {
        if (shoppingList.isEmpty()) {
            System.out.println("Список покупок пуст.\n");
        } else {
            System.out.println("Введите текст для поиска:");
            StringBuilder stringBuilder = new StringBuilder();
            textForSearch = scanner.nextLine().toLowerCase();
            for (int i = 0; i < shoppingList.size(); i++) {
                if (shoppingList.get(i).toLowerCase().contains(textForSearch)) {
                    stringBuilder.append(i + 1).append(". ").append(shoppingList.get(i)).append("\n");
                }
            }
            if (stringBuilder.isEmpty()) {
                System.out.println("Ничего не найдено.\n");
            } else {
                stringBuilder.insert(0, "Найдено:\n");
                System.out.println(stringBuilder);
            }
        }
    }
}