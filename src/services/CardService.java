package services;

import core.CardCore;
import core.CardCore.twoArrays;
import helpers.ViewHelper;
import helpers.mathHelper.MyMath;

public class CardService {

    public CardCore cardCore = new CardCore();

    public Integer[][] discardedCards = {};
    public Integer[][] playedCards = {};
    public Integer[][] dealerCards = {};
    public Integer[][] usedCards = {};
    public Integer[][] drawedCards = {};
    public Integer[][] remaningCards = {};

    public twoArrays draw_cards(Integer[][] cards) {

        Integer numberOfCardsShouldDrawed = MyMath.randomNumber(30, 40);

        twoArrays results = cardCore.draw_cards(cards, numberOfCardsShouldDrawed);

        drawedCards = results.drawedCards;

        remaningCards = results.remainingCards;

        return results;
    }

    public Integer[][] mix_cards(Integer[][] cards) {
        return cardCore.mix_cards(cards);
    }

    public Integer[][] get_new_deck_of_cards() {
        return cardCore.create_52card_deck();
    }

    public void distribute_cards() {

        for (int i = 0; i < 2; i++) {
            giveCardToPlayerFromDrawedCards();
            giveCardToDealerFromDrawedCards();
        }

    }

    public void moveInHandsCardsToUsedCards() {
        usedCards = cardCore.merge_two_cards(usedCards, playedCards);
        usedCards = cardCore.merge_two_cards(usedCards, dealerCards);
        cleanHands();
    }

    public void cleanHands() {
        playedCards = new Integer[0][];
        dealerCards = new Integer[0][];
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
        if (!drawedCardsAreEmpty())
            giveCardToPlayerFromDrawedCards();
    }

    public void dealerHit() {

        Integer dealerValue = calculateDealerCardsValue();

        while (dealerValue < 17 && !drawedCardsAreEmpty()) {
            giveCardToDealerFromDrawedCards();
            dealerValue = calculateDealerCardsValue();
        }
    }

    public void mergeUsedCardsWithDrawedCards() {
        usedCards = cardCore.merge_two_cards(usedCards, drawedCards);
    }

    public void mergeUsedCardsWithRemaningCards() {
        discardedCards = cardCore.merge_two_cards(usedCards, remaningCards);
    }

    public Integer[][] discard_card() {

        mergeUsedCardsWithDrawedCards();
        mergeUsedCardsWithRemaningCards();

        return discardedCards;
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

    public Boolean drawedCardsAreEmpty() {

        if (drawedCards.length == 0) {
            return true;
        }

        return false;
    }

    public Boolean drawedCardsAreLessThen4() {

        if (drawedCards.length < 4) {
            return true;
        }

        return false;
    }

    public Boolean player_has_depassed_21() {

        if (calculatePLayerCardsValue() > 21) {
            return true;
        }

        return false;
    }

    public void resetData() {
        discardedCards = new Integer[0][];
        playedCards = new Integer[0][];
        dealerCards = new Integer[0][];
        usedCards = new Integer[0][];
        drawedCards = new Integer[0][];
        remaningCards = new Integer[0][];
    }

}
