package services;

import core.CardCore;
import core.CardCore.twoArrays;
import helpers.ViewHelper;

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

    public String[] stand() {
        dealerHit();
        return result();
    }

    public String[] result() {

        Integer dealerValue = calculateDealerCardsValue();
        Integer playerValue = calculatePLayerCardsValue();

        if (playerValue > 21) {
            return new String[] { "Dealer Win", "red" };
        }

        if (dealerValue > 21) {
            return new String[] { "Player Win", "green" };

        }

        switch (Integer.compare(playerValue, dealerValue)) {
            case 1:
                return new String[] { "Player Win", "green" };
            case -1:
                return new String[] { "Dealer Win", "red" };
            default:
                return new String[] { "Tie !!", "yellow" };
        }

    }

    public void playerHit() {
        giveCardToPlayerFromDrawedCards();
    }

    public void dealerHit() {
        giveCardToDealerFromDrawedCards();

        Integer dealerValue = calculateDealerCardsValue();

        while (dealerValue < 17) {
            dealerHit();
        }
    }

    public Integer calculatePLayerCardsValue() {
        return cardCore.calculate_value_in_collection(playedCards);
    }

    public Integer calculateDealerCardsValue() {
        return cardCore.calculate_value_in_collection(dealerCards);
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
