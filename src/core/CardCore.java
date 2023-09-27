package core;

import helpers.coreHelper.card;
import helpers.mathHelper.MyMath;

public class CardCore {

    card cardH = new card();

    public twoArrays prepare_cards() {

        Integer[][] a52cardDeck = create_52card_deck();

        Integer[][] mixedCards = mix_cards(a52cardDeck);

        Integer numberOfCardsShouldDrawed = MyMath.randomNumber(30, 40);

        twoArrays results = draw_card(mixedCards, numberOfCardsShouldDrawed);

        return results;
    }

    public Integer[][] move_in_hand_cards_to_used_cards(Integer[][] inHandCards, Integer[][] usedCards) {

        Integer[][] newUsedCards = new Integer[inHandCards.length + usedCards.length][];

        Integer[][][] allCards = { inHandCards, usedCards };

        int indexOFNewUsedCards = 0;

        for (Integer[][] cards : allCards) {

            for (int i = 0; i < cards.length; i++) {
                newUsedCards[indexOFNewUsedCards] = cards[i];
                indexOFNewUsedCards++;
            }
        }

        return newUsedCards;

    }

    public Integer calculate_value_in_collection(Integer[][] cards) {

        Integer valueInHand = 0;
        Integer[] numberOfAces = { 0 };

        if (cards == null) {
            return valueInHand;
        }

        for (Integer[] card : cards) {

            int cardValue = cardH.getCardValue(card[0], valueInHand, numberOfAces);

            valueInHand += cardValue;
        }

        while (numberOfAces[0] > 0 && valueInHand > 21) {
            valueInHand -= 10;
            numberOfAces[0]--;
        }

        return valueInHand;

    }

    public Integer[][] create_52card_deck() {

        Integer[][] a52cardDeck = new Integer[52][2];

        int index = 0;

        for (int numSuits = 0; numSuits < 4; numSuits++) {

            for (int numCardsOfSuit = 1; numCardsOfSuit <= 13; numCardsOfSuit++) {
                a52cardDeck[index][0] = numCardsOfSuit;
                a52cardDeck[index][1] = numSuits;
                index++;
            }
        }

        return a52cardDeck;
    }

    public Integer[][] discard_card(Integer[][] usedCards, Integer[][] remaningCards) {

        int length = usedCards.length + remaningCards.length;

        Integer[][] discradedCards = new Integer[length][2];

        Integer[][][] allCards = { usedCards, remaningCards };

        int indexOfDeck = 0;

        for (Integer[][] cards : allCards) {

            for (int i = 0; i < cards.length; i++) {
                discradedCards[indexOfDeck] = cards[i];
                indexOfDeck++;
            }
        }

        return discradedCards;
    }

    public twoArrays draw_card(Integer[][] cards, Integer numberOfCardsShouldDrawed) {

        Integer[][] drawedCards = new Integer[numberOfCardsShouldDrawed][2];

        Integer[][] remainingCards = new Integer[cards.length - numberOfCardsShouldDrawed][2];

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

    public Integer[][] mix_cards(Integer[][] cards) {

        Integer[][] mixedCard = new Integer[cards.length][2];

        twoArrays results = pull_card(cards);

        for (int i = 0; i < cards.length; i++) {

            mixedCard[i] = results.card;

            results = pull_card(results.remainingCards);
        }

        return mixedCard;
    }

    public twoArrays pull_card(Integer[][] cards) {

        int randomIndex = MyMath.randomNumber(0, cards.length - 1);

        twoArrays results = extract_card(cards, randomIndex);

        return results;
    }

    public twoArrays extract_card(Integer[][] cards, int index) {

        // i didn't do this from first , untile an error appeared
        if (cards.length == 0) {
            return null;
        }

        Integer[] card = cards[index];

        Integer[][] remainingCards = new Integer[cards.length - 1][];

        for (int i = 0; i < index; i++) {
            remainingCards[i] = cards[i];
        }

        for (int i = index + 1; i < cards.length; i++) {
            remainingCards[i - 1] = cards[i];
        }

        return new twoArrays(card, remainingCards);
    }

    public Integer[][] add_card_to_collection(Integer[][] collection, Integer[] card) {

        int collectionLenght = collection != null ? collection.length : 0;

        Integer[][] newCollection = new Integer[collectionLenght + 1][2];

        for (int i = 0; i < collectionLenght; i++) {
            newCollection[i] = collection[i];
        }

        newCollection[collectionLenght] = card;

        return newCollection;
    }

    public class twoArrays {
        public Integer[] card;
        public Integer[][] remainingCards;
        public Integer[][] drawedCards;

        public twoArrays(Integer[] card, Integer[][] remainingCards) {
            this.card = card;
            this.remainingCards = remainingCards;
        }

        public twoArrays(Integer[][] drawedCards, Integer[][] remainingCards) {
            this.drawedCards = drawedCards;
            this.remainingCards = remainingCards;
        }
    }

}
