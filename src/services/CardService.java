package services;

import core.CardCore;
import core.CardCore.twoArrays;

import helpers.Helper;

public class CardService {

    public CardCore cardCore = new CardCore();

    public Integer[][] inHandsCards = {};
    public Integer[][] playedCards = {};
    public Integer[][] dealerCards = {};
    public Integer[][] usedCards = {};
    public Integer[][] drawedCards = {};
    public Integer[][] remaningCards = {};

    public void prepare_cards() {

        twoArrays results = cardCore.prepare_cards();

        drawedCards = results.drawedCards;

        remaningCards = results.remainingCards;
    }

    public void distribute_cards() {

        for (int i = 0; i < 2; i++) {
            giveCardToPlayerFromDrawedCards();
            giveCardToDealerFromDrawedCards();
        }

    }

    public void giveCardToPlayerFromDrawedCards() {

        Integer[] card = takeCardFromDrawedCards();

        playedCards = cardCore.add_card_to_collection(playedCards, card);
    }

    public void giveCardToDealerFromDrawedCards() {

        Integer[] card = takeCardFromDrawedCards();

        dealerCards = cardCore.add_card_to_collection(dealerCards, card);
    }

    public Integer[] takeCardFromDrawedCards() {

        twoArrays result = cardCore.extract_card(drawedCards, 0);

        drawedCards = result.remainingCards;

        return result.card;
    }
}
