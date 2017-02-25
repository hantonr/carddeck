package org.rhanton;

public class Card implements Comparable<Card> {
    public enum Suit {
        HEARTS, CLUBS, DIAMONDS, SPADES
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (suit != null ? !suit.equals(card.suit) : card.suit != null) return false;
        return value != null ? value.equals(card.value) : card.value == null;
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    /**
     * Used to create new deck order: hearts A-K, clubs A-K, diamonds K-A, spades K-A
     * @param other card to compare with
     * @return int 0 if equal, -1 other is greater, 1 this is greater
     */
    public int compareTo(Card other) {
        int suit = this.getSuit().compareTo(other.getSuit());
        if (suit != 0) {
            return suit;
        } else {
            switch (this.getSuit()) {
                case HEARTS:
                case CLUBS:
                    return this.getValue().compareTo(other.getValue());
                case DIAMONDS:
                case SPADES:
                    return other.getValue().compareTo(this.getValue());
            }
        }
        // this should never happen
        return 0;
    }
}
