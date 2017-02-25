package org.rhanton;

import java.util.*;

public class Deck {
    // deck of cards where card at 0 is the top of the deck
    protected List<Card> cardDeck = new LinkedList<>();

    /**
     * Generate a normal deck of 52 cards (no jokers)
     */
    public Deck() {
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                cardDeck.add(new Card(suit, value));
            }
        }
    }

    public Deck(List<Card> cardMap) {
        this.cardDeck = cardMap;
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    /**
     * Retrieves the top card and removes it from the deck.
     * @return the top Card from the deck
     */
    public Card deal() {
        return cardDeck.remove(0);
    }

    /**
     * Retrieves the top card but does not remove it from the deck.
     * @return the top Card from the deck
     */
    public Card turnOver() {
        return cardDeck.get(0);
    }

    /**
     * Randomizes the order of the cards.
     */
    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    /**
     * Splits the deck at a point chosen by the player
     * @param splitBefore the card position directly after the split, which will become the first card in the deck
     */
    public void cut(int splitBefore) {
        if (splitBefore < 0 || splitBefore >= cardDeck.size()) {
            throw new IllegalArgumentException("The split point cannot be less than 0 or greater than the number of cards - 1");
        }

        for (int i = 0; i < splitBefore; i++) {
            cardDeck.add(cardDeck.remove(0));
        }
    }

    /**
     * Finds the 1-based position of the given card in the desk (first found)
     * @param card the Card to search for
     * @return the 1-based position where the top card is 1
     */
    public int search(Card card) {
        return cardDeck.indexOf(card) + 1;
    }

    /**
     * Places the remaining cards in the deck in the order of a new deck of cards
     * (top to bottom:  hearts A-K, clubs A-K, diamonds K-A, spades K-A)
     */
    public void newOrder() {
        Collections.sort(cardDeck);
    }
}
