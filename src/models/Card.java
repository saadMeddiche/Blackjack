package models;

import java.util.List;

public class Card {
    private Integer rank;
    private Integer suit;

    public Card() {
    }

    public Card(Integer rank, Integer suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSuit() {
        return this.suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }


    @Override
    public String toString() {
        return "{" +
                " rank='" + getRank() + "'" +
                ", suit='" + getSuit() + "'" +
                "}";
    }

}
