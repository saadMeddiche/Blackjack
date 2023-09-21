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

        twoArrays results = extract_card(cards);
        System.out.println("=======Random Card=======");
        System.out.println("Rank" + results.randomCard[0]);
        System.out.println("Suit" + results.randomCard[1]);

        System.out.println("=======Remaining Card=======");
        display(results.remainingCards);

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

    public static twoArrays extract_card(Integer[][] cards) {

        int randomIndex = Helper.myRandomNumber(0, cards.length - 1);

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

        public twoArrays(Integer[] arr1, Integer[][] arr2) {
            this.randomCard = arr1;
            this.remainingCards = arr2;
        }
    }

    public static void display(Integer[][] cards) {
        for (int i = 0; i < cards.length; i++) {
            System.out.println("======Card======");
            System.out.println("Rank:" + cards[i][0]);
            System.out.println("Suit:" + cards[i][1]);
            System.out.println("================");
            System.out.println();
        }
    }

}
