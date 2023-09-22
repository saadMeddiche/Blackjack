package views;

import java.util.Random;
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

        Thread.sleep(2000);

    }

    public static void distribute_cards() throws Exception {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Distributing cards...", "yellow");

        for (int i = 0; i < 2; i++) {
            giveCardToPlayerFromDrawedCards();
            giveCardToDealerFromDrawedCards();
        }

        animation();

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Distributed !", "green");

        Thread.sleep(2000);

    }

    public static void showCardsInHands() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Dealer Hand (" + dealerCards.length + ")", "green");

        for (Integer[] card : dealerCards) {

            ViewHelper.colorText("=====Card=====", "green");
            System.out.println("-> Rank:" + card[0]);
            System.out.println("-> Suit" + card[1]);
            ViewHelper.colorText("==============", "green");

        }

        ViewHelper.colorText("Player Hand (" + playedCards.length + ")", "yellow");

        for (Integer[] card : playedCards) {

            ViewHelper.colorText("=====Card=====", "yellow");
            System.out.println("-> Rank:" + card[0]);
            System.out.println("-> Suit" + card[1]);
            ViewHelper.colorText("==============", "yellow");

        }

        ViewHelper.stopProgramUntilButtonIsCliqued("test");

    }

    // ======================================================
    public static void giveCardToPlayerFromDrawedCards() {

        Integer[] card = takeCardFromDrawedCards();

        playedCards = CardRepository.add_card_to_collection(playedCards, card);
    }

    public static void giveCardToDealerFromDrawedCards() {

        Integer[] card = takeCardFromDrawedCards();

        dealerCards = CardRepository.add_card_to_collection(dealerCards, card);

    }

    public static Integer[] takeCardFromDrawedCards() {

        CardRepository.twoArrays result = CardRepository.extract_card(drawedCards, 0);

        drawedCards = result.remainingCards;

        return result.card;
    }

    public static void animation() throws InterruptedException {

        String[] symbols = { "|", "/", "-", "\\" };
        int symbolIndex = 0;

        long startTime = System.currentTimeMillis();
        long periodBetweenPastAndFuture = 0;

        while (periodBetweenPastAndFuture < 0) {

            System.out.print("\r" + symbols[symbolIndex]);

            symbolIndex = (symbolIndex + 1) % symbols.length;

            Thread.sleep(250);

            periodBetweenPastAndFuture = System.currentTimeMillis() - startTime;
        }

        System.out.println();
    }

}
