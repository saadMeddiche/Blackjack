package view;

import java.util.Scanner;

import helpers.Helper;
import helpers.ViewHelper;
import helpers.viewHelper.Animation;
import helpers.viewHelper.CardShape;
import core.CardCore.twoArrays;
import services.CardService;

public class View {

    public CardService cardService;
    public Long animationTime = 0L; // Mounir L

    public View(CardService cardService) {
        this.cardService = cardService;
    }

    public void open_application() throws Exception {

        main_menu();

        // If Player want to exit from application
        if (!wanna_play())
            System.exit(0);

        // If Player Want To Play

        start_game();
    }

    public void main_menu() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("======== Welcome To AnassCazino ========\n", "red");

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\nDonec egestas vitae mi quis maximus.\nNullam porta urna et ipsum aliquam, eu congue lorem tincidunt.\nMaecenas gravida nisi sed condimentum ultrices.\n";
        ViewHelper.colorText(description, "green");

    }

    public Boolean wanna_play() {

        Scanner scanner = new Scanner(System.in);
        Object choice = null;

        while (true) {

            ViewHelper.colorText("Press 'y' to start the game Or 'n' to exist", "white");
            choice = Helper.getInput(scanner, "String");

            switch (choice.toString()) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    public void start_game() throws Exception {

        Integer[][] newDeck = get_new_deck_of_cards();

        while (true) {

            Integer[][] mixedCards = mix_cards(newDeck);

            draw_cards(mixedCards);

            distribute_cards();

            while (true) {

                show_cards_in_hands();

                display_choices();

                Object choice = choose_a_choice();

                excute_choice(choice);

                if (cardService.drawedCardsAreLessThen4()) {

                    ViewHelper.clearConsole();

                    ViewHelper.colorText("No Enought cards to play", "red");

                    ViewHelper.stopProgramUntilButtonIsCliqued("Click enter to prepare new prepared cards");

                    move_cards_in_hand_to_used_cards();

                    newDeck = discard_card();

                    cardService.resetData();

                    break;
                }

                if (!choice.equals(1)) {
                    next_round();
                }

            }
        }

    }

    public Integer[][] get_new_deck_of_cards() throws Exception {

        ViewHelper.clearConsole();

        Integer[][] newDeck = cardService.get_new_deck_of_cards();

        ViewHelper.colorText("Getting a new deck of cards...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Deck has been getted !", "green");

        Thread.sleep(2000);

        return newDeck;
    }

    public Integer[][] mix_cards(Integer[][] cards) throws Exception {

        ViewHelper.clearConsole();

        Integer[][] mixedCards = cardService.mix_cards(cards);

        ViewHelper.colorText("Mixing cards...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Mixed !", "green");

        Thread.sleep(2000);

        return mixedCards;

    }

    public Integer[][] draw_cards(Integer[][] cards) throws Exception {

        ViewHelper.clearConsole();

        twoArrays results = cardService.draw_cards(cards);

        ViewHelper.colorText("Drawing cards...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Drawed !", "green");

        Thread.sleep(2000);

        return results.drawedCards;
    }

    public void show_cards_in_hands() {

        ViewHelper.clearConsole();

        ViewHelper.colorText("Dealer Hand (" + cardService.calculateDealerCardsValue() + ")", "green");

        display_cards(cardService.dealerCards, "green");

        ViewHelper.colorText("==========", "green");

        System.out.println();

        ViewHelper.colorText("Player Hand (" + cardService.calculatePLayerCardsValue() + ")", "yellow");

        display_cards(cardService.playedCards, "yellow");

        ViewHelper.colorText("==========", "yellow");

        System.out.println();

    }

    public void display_cards(Integer[][] cards, String color) {

        CardShape.print(cards);

        // for (Integer[] card : cards) {

        // ViewHelper.colorText("=====Card=====", color);
        // System.out.println("-> Rank: " + card[0]);
        // System.out.println("-> Suit: " + CardShape.getNameOfShape(card[1]));
        // ViewHelper.colorText("==============", color);
        // }
    }

    public Object choose_a_choice() {

        Scanner scanner = new Scanner(System.in);

        ViewHelper.colorText("==> Your Move :", "purple");

        Object choice = Helper.getInput(scanner, "Integer");

        return choice;
    }

    public void excute_choice(Object choice) throws Exception {
        switch (choice.toString()) {
            case "1":
                hit();
                break;
            case "2":
                stand();
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public void display_choices() throws Exception {

        ViewHelper.colorText("======================", "purple");
        ViewHelper.colorText("Drawed Card: " + cardService.drawedCards.length, "purple");
        ViewHelper.colorText("Remaning Cards: " + cardService.remaningCards.length, "purple");
        ViewHelper.colorText("Used Cards: " + cardService.usedCards.length, "purple");
        ViewHelper.colorText("1. Hit", "purple");
        ViewHelper.colorText("2. Stand", "purple");
        ViewHelper.colorText("3. Double Down", "purple");
        ViewHelper.colorText("4. Split", "purple");

    }

    public void hit() throws Exception {

        if (cardService.drawedCardsAreEmpty()) {

            ViewHelper.clearConsole();

            show_cards_in_hands();

            display_winner();

            ViewHelper.stopProgramUntilButtonIsCliqued("Press Button To Continue");

            return;
        }

        cardService.playerHit();

        if (cardService.player_has_depassed_21()) {

            ViewHelper.clearConsole();

            show_cards_in_hands();

            ViewHelper.colorText("Dealer Win", "red");

            ViewHelper.stopProgramUntilButtonIsCliqued("Press Button To Continue");

            next_round();

        }

    }

    public void stand() throws Exception {

        ViewHelper.clearConsole();

        cardService.dealerHit();

        show_cards_in_hands();

        display_winner();

        ViewHelper.stopProgramUntilButtonIsCliqued("Press Button To Continue");

    }

    public Integer[][] discard_card() throws Exception {

        ViewHelper.clearConsole();

        Integer[][] discardedCards = cardService.discard_card();

        ViewHelper.colorText("Discarding cards...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Discarded !", "green");

        Thread.sleep(2000);

        return discardedCards;

    }

    public void display_winner() {

        String[] result = cardService.result();

        ViewHelper.colorText(result[0], result[1]);

    }

    public void next_round() throws Exception {

        move_cards_in_hand_to_used_cards();

        distribute_cards();
    }

    public void move_cards_in_hand_to_used_cards() throws Exception {

        ViewHelper.clearConsole();

        cardService.moveInHandsCardsToUsedCards();

        ViewHelper.colorText("Removing cards From Hands ...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Removed !", "green");

        Thread.sleep(2000);

    }

    public void distribute_cards() throws Exception {

        ViewHelper.clearConsole();

        cardService.distribute_cards();

        ViewHelper.colorText("Distributing cards...", "yellow");

        Animation.waitingAnimation(animationTime);

        ViewHelper.clearConsole();

        ViewHelper.colorText("Cards Has Been Distributed !", "green");

        Thread.sleep(2000);

    }

}
