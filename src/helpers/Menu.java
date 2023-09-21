package helpers;

import java.util.Scanner;
import helpers.ViewHelper;

public abstract class Menu {

    protected Menu() {
    }

    public void start() {

        try {
            Integer selectedOption = 0;

            while (true) {
                ViewHelper.clearConsole();

                displayMenu(selectedOption);

                Scanner input = new Scanner(System.in);
                String key = input.next();

                if (key.equals("w") && selectedOption < options().length - 1) {
                    selectedOption = selectedOption + 1;

                } else if (key.equals("s") && selectedOption >= 1) {
                    selectedOption = selectedOption - 1;

                } else if (key.equals("c")) {
                    ViewHelper.clearConsole();

                    if (selectedOption.equals(options().length)) {
                        break;
                    }

                    excuteChoice(selectedOption);

                }

                ViewHelper.clearConsole();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayMenu(int selectedOption) throws Exception {
        System.out.println("\u001B[32m======Menu======\u001B[0m");
        String[] options = options();
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                System.out.println("\u001B[32m" + "-> " + "\u001B[0m" + options[i]);
            } else {
                System.out.println("   " + options[i]);
            }
        }
    }

    protected abstract String[] options();

    public abstract void excuteChoice(int choice);

}
