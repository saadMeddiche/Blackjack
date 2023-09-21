package views;

import java.util.Scanner;

import helpers.Helper;
import helpers.ViewHelper;
import repository.CardRepository;

public class View {

    public static Integer[][] inHandsCards;
    public static Integer[][] playedCards;
    public static Integer[][] dealerCards;
    public static Integer[][] usedCards;
    public static Integer[][] drawedCards;
    public static Integer[][] remaningCards;

    public static void lobby() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("======== Welcome To AnassCazino ========\n", "red");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing \n elit. Donec egestas vitae mi quis maximus.\n Nullam porta urna et ipsum aliquam, eu congue lorem tincidunt.\n Maecenas gravida nisi sed condimentum ultrices. \n";
        ViewHelper.colorText(description, "green");

        ViewHelper.stopProgramUntilButtonIsCliqued("Press Enter To Start ...");
    }

    public static void start() throws Exception {

        CardRepository.twoArrays results = CardRepository.get_prepared_cards();

        drawedCards = results.drawedCards;
        remaningCards = results.remainingCards;

        ViewHelper.clearConsole();

        ViewHelper.colorText("Mixing cards...", "yellow");

        Thread.sleep(1000);

        animation();

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Mixed !", "green");

        ViewHelper.stopProgramUntilButtonIsCliqued("Press Enter To Start ...");

    }

    public static void animation() throws InterruptedException {
        String[] symbols = { "|", "/", "-", "\\" };
        int symbolIndex = 0;

        long startTime = System.currentTimeMillis();
        long periodBetweenPastAndFuture = 0;

        while (periodBetweenPastAndFuture < 6000) {

            System.out.print("\r" + symbols[symbolIndex]);

            symbolIndex = (symbolIndex + 1) % symbols.length;

            Thread.sleep(250);

            periodBetweenPastAndFuture = System.currentTimeMillis() - startTime;
        }

        System.out.println();
    }

}
