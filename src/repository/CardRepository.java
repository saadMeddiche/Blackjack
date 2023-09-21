package repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import helpers.Helper;
import models.Card;

public class CardRepository {

    public static void main(String[] args) throws Exception {
        Integer[][] cards = create_52card_deck();
        display(cards);

        twoArrays results = pull_card(cards);
        System.out.println("=======Random Card=======");
        System.out.println("Rank" + results.randomCard[0]);
        System.out.println("Suit" + results.randomCard[1]);

        System.out.println("=======Remaining Card=======");
        display(results.remainingCards);

        System.out.println("=======Mixed Card=======");
        Integer[][] mixedCards = mix_cards(cards);
        display(mixedCards);

        twoArrays results1 = draw_card(mixedCards, 2);

        System.out.println("=======Drawed Card=======");
        display(results1.drawedCards);

        System.out.println("=======Remaining Card=======");
        display(results1.remainingCards);

    }

    public static Integer[][] create_52card_deck() {

        Integer[][] a52cardDeck = new Integer[52][2];

        int index = 0;

        for (int numSuits = 1; numSuits <= 4; numSuits++) {

            for (int numCardsOfSuit = 1; numCardsOfSuit <= 13; numCardsOfSuit++) {
                a52cardDeck[index][0] = numCardsOfSuit;
                a52cardDeck[index][1] = numSuits;
                index++;
            }
        }

        return a52cardDeck;
    }

    public static void discard_card() {

    }

    public static twoArrays draw_card(Integer[][] cards, Integer numberOfCardsShouldDrawed) {

        Integer[][] drawedCards = new Integer[numberOfCardsShouldDrawed][2];

        Integer[][] remainingCards = new Integer[52 - numberOfCardsShouldDrawed][2];

        int indexOfCard = 0;
        for (int i = 0; i < drawedCards.length; i++) {
            drawedCards[i] = cards[indexOfCard];
            indexOfCard++;
        }

        for (int i = 0; i < remainingCards.length; i++) {
            remainingCards[i] = cards[indexOfCard];
            indexOfCard++;
        }

        return new twoArrays(drawedCards, remainingCards);

    }

    public static Integer[][] mix_cards(Integer[][] cards) {

        Integer[][] mixedCard = new Integer[52][2];

        twoArrays results = pull_card(cards);

        for (int i = 0; i < cards.length; i++) {

            mixedCard[i] = results.randomCard;

            results = pull_card(results.remainingCards);
        }

        return mixedCard;
    }

    public static twoArrays pull_card(Integer[][] cards) {

        int randomIndex = Helper.randomNumber(0, cards.length - 1);

        twoArrays results = extract_card(cards, randomIndex);

        return results;
    }

    public static twoArrays extract_card(Integer[][] cards, int randomIndex) {

        // i didn't do this from first , untile an error appeared
        if (cards.length == 0) {
            return null;
        }

        Integer[] randomCard = cards[randomIndex];

        Integer[][] remainingCards = new Integer[cards.length - 1][];

        for (int i = 0; i < randomIndex; i++) {
            remainingCards[i] = cards[i];
        }

        for (int i = randomIndex + 1; i < cards.length; i++) {
            remainingCards[i - 1] = cards[i];
        }

        return new twoArrays(randomCard, remainingCards);
    }

    static class twoArrays {
        public Integer[] randomCard;
        public Integer[][] remainingCards;
        public Integer[][] drawedCards;

        public twoArrays(Integer[] arr1, Integer[][] arr2) {
            this.randomCard = arr1;
            this.remainingCards = arr2;
        }

        public twoArrays(Integer[][] arr1, Integer[][] arr2) {
            this.drawedCards = arr1;
            this.remainingCards = arr2;
        }
    }

    public static void display(Integer[][] cards) {
        for (int i = 0; i < cards.length; i++) {
            System.out.println("======Card" + i + "======");
            System.out.println("Rank:" + cards[i][0]);
            System.out.println("Suit:" + cards[i][1]);
            System.out.println("================");
            System.out.println();
        }
    }

}
