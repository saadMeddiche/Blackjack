package view;

import java.util.Scanner;

import helpers.ViewHelper;
import services.CardService;

public class View {

    public CardService cardService;

    public View(CardService cardService) {
        this.cardService = cardService;
    }

    public void lobby() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("======== Welcome To AnassCazino ========\n", "red");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing \n elit. Donec egestas vitae mi quis maximus.\n Nullam porta urna et ipsum aliquam, eu congue lorem tincidunt.\n Maecenas gravida nisi sed condimentum ultrices. \n";
        ViewHelper.colorText(description, "green");

        ViewHelper.stopProgramUntilButtonIsCliqued("Press Enter To Start ...");
    }

    public void prepare_cards(long timeOfAnimation) throws Exception {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Mixing cards...", "yellow");

        Animation.waitingAnimation(timeOfAnimation);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Mixed !", "green");

        Thread.sleep(2000);

    }

    public void show_board() {

        showCardsInHands();

        control_panel();

    }

    public void control_panel() {

        ViewHelper.colorText("======================", "purple");
        ViewHelper.colorText("Drawed Card: " + cardService.drawedCards.length, "purple");
        ViewHelper.colorText("Remaning Cards: " + cardService.remaningCards.length, "purple");
        ViewHelper.colorText("Used Cards: " + cardService.usedCards.length, "purple");
        ViewHelper.colorText("1. Hit", "purple");
        ViewHelper.colorText("2. Stand", "purple");
        ViewHelper.colorText("3. Double Down", "purple");
        ViewHelper.colorText("4. Split", "purple");

        Scanner scanner = new Scanner(System.in);

        ViewHelper.colorText("==> Your Move :", "purple");
        Integer choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                cardService.giveCardToPlayerFromDrawedCards();
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

        // scanner.close();
    }

    public void distribute_cards(long timeOfAnimation) throws Exception {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Distributing cards...", "yellow");

        Animation.waitingAnimation(timeOfAnimation);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Distributed !", "green");

        Thread.sleep(2000);

    }

    public void showCardsInHands() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Dealer Hand (" + cardService.dealerCards.length + ")", "green");

        displayCards(cardService.dealerCards, "green");

        ViewHelper.colorText("Player Hand (" + cardService.playedCards.length + ")", "yellow");

        displayCards(cardService.playedCards, "yellow");

    }

    public void displayCards(Integer[][] cards, String color) {

        for (Integer[] card : cards) {
            ViewHelper.colorText("=====Card=====", color);
            System.out.println("-> Rank: " + card[0]);
            System.out.println("-> Suit: " + CardShape.getNameOfShape(card[1]));
            ViewHelper.colorText("==============", color);
        }
    }

}
