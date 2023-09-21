package views;

import java.util.Scanner;

import helpers.ViewHelper;

public class View {

    public static Integer[][] a52cardDeck;

    public static void lobby() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("======== Welcome To AnassCazino ========\n", "red");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing \n elit. Donec egestas vitae mi quis maximus.\n Nullam porta urna et ipsum aliquam, eu congue lorem tincidunt.\n Maecenas gravida nisi sed condimentum ultrices. \n";
        ViewHelper.colorText(description, "green");

        ViewHelper.stopProgramUntilButtonIsCliqued("Press Enter To Start ...");
    }

    public static void distribution_of_cards() {

        

    }
}
