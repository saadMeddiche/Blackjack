package helpers.coreHelper;

public class card {

    public int getCardValue(int rank, int valueInHand, Integer[] numAces) {

        if (isNormalCard(rank)) {
            return rank;
        }

        if (isJackQueenKing(rank)) {
            return 10;
        }

        if (isAce(rank)) {
            numAces[0]++;
            return 11;
        }

        return 0;
    }

    public boolean isNormalCard(int rank) {
        return rank >= 2 && rank <= 10;
    }

    public boolean isAce(int rank) {
        return rank == 1;
    }

    public boolean isJackQueenKing(int rank) {
        return rank >= 11 && rank <= 13;
    }

}
