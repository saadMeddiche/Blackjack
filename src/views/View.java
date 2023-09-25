package views;

import java.util.Scanner;
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

    public static void mixing_cards() throws Exception {

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

    public static void play_round() {

        while (true) {
            showCardsInHands();

            control_panel();

            ViewHelper.clearConsole();
        }

    }

    public static void control_panel() {

        ViewHelper.colorText("======================", "purple");
        ViewHelper.colorText("1. Hit", "purple");
        ViewHelper.colorText("2. Stand", "purple");
        ViewHelper.colorText("3. Double Down", "purple");
        ViewHelper.colorText("4. Split", "purple");

        Scanner scanner = new Scanner(System.in);

        ViewHelper.colorText("==> Your Move :", "purple");
        Integer choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                hit();
                break;
            case 2:

                break;
            case 3:

                break;

            case 4:

                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }

    }

    public static void hit() {
        giveCardToPlayerFromDrawedCards();
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

    public static void cardShape(int r, int s) {

        String shapeName = getNameOfShape(s);

        String spacedShapeName = calculatSpace(shapeName, 16);

        String rank = Integer.toString(r);
        String spacedRank = calculatSpace(rank, 16);

        ViewHelper.colorText("\u001b[47;1m----------------", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedRank, "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedShapeName, "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m                ", "red");
        ViewHelper.colorText("\u001b[47;1m" + spacedRank, "red");
        ViewHelper.colorText("\u001b[47;1m----------------", "red");
    }

    public static void showCardsInHands() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Dealer Hand (" + dealerCards.length + ")", "green");

        for (Integer[] card : dealerCards) {

            ViewHelper.colorText("=====Card=====", "green");
            System.out.println("-> Rank: " + card[0]);
            System.out.println("-> Suit: " + getNameOfShape(card[1]));
            ViewHelper.colorText("==============", "green");

            // cardShape(card[0], card[1]);

            // System.out.println();
        }

        ViewHelper.colorText("Player Hand (" + playedCards.length + ")", "yellow");

        for (Integer[] card : playedCards) {

            ViewHelper.colorText("=====Card=====", "yellow");
            System.out.println("-> Rank: " + card[0]);
            System.out.println("-> Suit: " + getNameOfShape(card[1]));
            ViewHelper.colorText("==============", "yellow");

            // cardShape(card[0], card[1]);

            // System.out.println();
        }

    }

    // ======================================================
    public static String getNameOfShape(int s) {
        String[] suitNames = { "Diamond", "Heart", "Club", "Spade" };

        if (s < 0 || s > suitNames.length) {
            return "Invalid Suit";
        }

        return suitNames[s];

    }

    public static String calculatSpace(String text, int totalWidth) {

        int textLength = text.length();
        int spaceLength = totalWidth - textLength;

        if (spaceLength <= 0) {
            return "";
        }

        int leftSpace = spaceLength / 2;
        int rightSpace = spaceLength - leftSpace;

        return " ".repeat(leftSpace) + text + " ".repeat(rightSpace);
    }

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
