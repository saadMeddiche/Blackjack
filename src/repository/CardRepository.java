package repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import models.Card;

public class CardRepository {

    public static void main(String[] args) throws Exception {
        Integer[][] cards = create_52card_deck();
        display(cards);
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
