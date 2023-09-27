package helpers;

import java.util.Scanner;

public class Helper {
    public static Object getInput(Scanner scanner, String Type) {
        while (true) {

            switch (Type) {
                case "String":
                    String stringValue = scanner.nextLine();
                    if (stringValue.equals("") || stringValue.equals(" ")) {
                        System.out.println("Please do not enter an empty value");
                        break;
                    }
                    return stringValue;
                case "Integer":
                    try {
                        Integer integerValue = Integer.parseInt(scanner.nextLine());
                        return integerValue;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid Integer value");
                    }
                    break;
                case "Long":
                    try {
                        Long longValue = Long.parseLong(scanner.nextLine());
                        return longValue;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid Long value");
                    }
                    break;
                default:
                    System.out.println("Had Type ga3 makayn");
                    break;
            }
        }
    }
}
