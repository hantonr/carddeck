package org.rhanton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardsApplicationTests {

	@Test
	public void testDeal() {
		Deck deck = new Deck();
		Card card1 = deck.getCardDeck().get(0);
		Card card2 = deck.getCardDeck().get(1);

		Assert.assertEquals(card1, deck.deal());
		Assert.assertEquals(card2, deck.getCardDeck().get(0));
	}

	@Test
	public void testTurnOver() {
		Deck deck = new Deck();
		Card card1 = deck.getCardDeck().get(0);
		Card card2 = deck.getCardDeck().get(1);

		Assert.assertEquals(card1, deck.turnOver());
		Assert.assertNotEquals(card2, deck.getCardDeck().get(0));
		Assert.assertEquals(card1, deck.getCardDeck().get(0));
	}

	@Test
	public void testShuffle() {
		Deck deck = new Deck();
		Card card1 = deck.getCardDeck().get(0);
		deck.shuffle();

		Assert.assertNotEquals(card1, deck.getCardDeck().get(0));
	}

	@Test
	public void testCut() {
		Deck deck = new Deck();
		Card card1 = deck.getCardDeck().get(0);
		Card card2 = deck.getCardDeck().get(1);
		Card card3 = deck.getCardDeck().get(2);
		Card card4 = deck.getCardDeck().get(3);

		deck.cut(2);
		Assert.assertEquals(card3, deck.getCardDeck().get(0));
		Assert.assertEquals(card4, deck.getCardDeck().get(1));
		Assert.assertEquals(card1, deck.getCardDeck().get(deck.getCardDeck().size()-2));
		Assert.assertEquals(card2, deck.getCardDeck().get(deck.getCardDeck().size()-1));
	}

	@Test
	public void testSearch() {
		Deck deck = new Deck();
		Card card5 = deck.getCardDeck().get(4);

		Assert.assertEquals(5, deck.search(card5));
	}

	@Test
	public void newOrder() {
		Deck deck = new Deck();
		deck.shuffle();

		deck.newOrder();
		List<Card> cards = deck.getCardDeck();
		List<Card> expectedCards = getExpectedList();

		// expect order to be hearts A-K, clubs A-K, diamonds K-A, spades K-A)
		Iterator<Card> iter = cards.iterator();
		Iterator<Card> expected = expectedCards.iterator();
		while (iter.hasNext()) {
			Assert.assertEquals(expected.next(), iter.next());
		}
	}

	@Test
	public void testPinochle() {
		PinochleDeck deck = new PinochleDeck();
		deck.shuffle();

		deck.newOrder();
		List<Card> cards = deck.getCardDeck();
		List<Card> expectedCards = getExpectedPinochleList();

		// expect order to be hearts A-K, clubs A-K, diamonds K-A, spades K-A)
		Iterator<Card> iter = cards.iterator();
		Iterator<Card> expected = expectedCards.iterator();
		while (iter.hasNext()) {
			Assert.assertEquals(expected.next(), iter.next());
		}
	}

	private List<Card> getExpectedList() {
		List<Card> list = new ArrayList<>();
		List<Card.Value> reversedVals = Arrays.asList(Card.Value.values());
		Collections.reverse(reversedVals);

		for(Card.Value value : Card.Value.values()) {
			list.add(new Card(Card.Suit.HEARTS, value));
		}
		for(Card.Value value : Card.Value.values()) {
			list.add(new Card(Card.Suit.CLUBS, value));
		}
		for(Card.Value value : reversedVals) {
			list.add(new Card(Card.Suit.DIAMONDS, value));
		}
		for(Card.Value value : reversedVals) {
			list.add(new Card(Card.Suit.SPADES, value));
		}

		return list;
	}

	private List<Card> getExpectedPinochleList() {
		List<Card> list = new ArrayList<>();
		List<Card.Value> reversedVals = Arrays.asList(Card.Value.values());
		Collections.reverse(reversedVals);
		List<Card.Value> excluded = Arrays.asList(PinochleDeck.excludedValues);

		for(Card.Value value : Card.Value.values()) {
			if (!excluded.contains(value)) {
				list.add(new Card(Card.Suit.HEARTS, value));
				list.add(new Card(Card.Suit.HEARTS, value));
			}
		}
		for(Card.Value value : Card.Value.values()) {
			if (!excluded.contains(value)) {
				list.add(new Card(Card.Suit.CLUBS, value));
				list.add(new Card(Card.Suit.CLUBS, value));
			}
		}
		for(Card.Value value : reversedVals) {
			if (!excluded.contains(value)) {
				list.add(new Card(Card.Suit.DIAMONDS, value));
				list.add(new Card(Card.Suit.DIAMONDS, value));
			}
		}
		for(Card.Value value : reversedVals) {
			if (!excluded.contains(value)) {
				list.add(new Card(Card.Suit.SPADES, value));
				list.add(new Card(Card.Suit.SPADES, value));
			}
		}

		return list;
	}

}
