package org.rhanton;

import java.util.Arrays;
import java.util.List;
import org.rhanton.Card.Value;

public class PinochleDeck extends Deck {

    protected static Card.Value[] excludedValues = {Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT};

    public PinochleDeck() {
        cardDeck.clear();
        List<Value> excluded = Arrays.asList(excludedValues);

        for (Card.Suit suit: Card.Suit.values()) {
            for (Value value : Value.values()) {
                if (!excluded.contains(value)) {
                    cardDeck.add(new Card(suit, value));
                    cardDeck.add(new Card(suit, value));
                }
            }
        }
    }
}
