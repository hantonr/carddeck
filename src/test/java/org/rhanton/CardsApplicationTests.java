package org.rhanton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
