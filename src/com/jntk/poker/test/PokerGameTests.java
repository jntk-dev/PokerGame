package com.jntk.poker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jntk.poker.domain.Card;
import com.jntk.poker.domain.Hand;
import com.jntk.poker.service.CardService;
import com.jntk.poker.service.HandService;
import com.jntk.poker.utils.AppUtils;

public class PokerGameTests {
	
	public void runTests() {
		createDeckTest();
		shuffleDeckTest();
		dealFiveCardHandTest();
		dealFourCardHandTest();
		isFlushTest();
		isThreeOfAKindTest();
		isFourOfAKindTest();
		isFullHouseTest();
		isOnePairTest();
		isTwoPairTest();
		isHighCardTest();
		isStraightTest();
		isStraightFlushTest();
	}
	
	@Test
	//Create standard deck of 52 cards
	public void createDeckTest() {
		List<Card> deck = CardService.getShuffledDeckOfCards();
		assertEquals(52, deck.size());
	}
	
	@Test
	public void shuffleDeckTest() {
		List<Card> deck  = CardService.getShuffledDeckOfCards();
		List<Card> deck2 = CardService.getShuffledDeckOfCards();
		
		assertNotEquals(deck.get(0).getName(), deck2.get(0).getName());
	}
	
	@Test
	public void dealFiveCardHandTest() {
		Hand hand = HandService.dealHand(5);
		assertEquals(5, hand.getCards().size());
	}
	
	@Test
	public void dealFourCardHandTest() {
		Hand hand = HandService.dealHand(4);
		assertEquals(4, hand.getCards().size());
	}
	
	@Test
	public void isStraightTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(3, AppUtils.CARD_SUITE_HEARTS, 3+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(4, AppUtils.CARD_SUITE_HEARTS, 4+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(5, AppUtils.CARD_SUITE_SPADES, 5+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(6, AppUtils.CARD_SUITE_SPADES, 6+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(7, AppUtils.CARD_SUITE_CLUBS,  7+AppUtils.CARD_SUITE_CLUBS));
		hand.setCards(c);
		assertTrue(HandService.isStraight(hand));
	}
	
	@Test
	public void isFlushTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(3, AppUtils.CARD_SUITE_HEARTS, 3+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(4, AppUtils.CARD_SUITE_HEARTS, 4+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(5, AppUtils.CARD_SUITE_HEARTS, 5+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(6, AppUtils.CARD_SUITE_HEARTS, 6+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(7, AppUtils.CARD_SUITE_HEARTS, 7+AppUtils.CARD_SUITE_HEARTS));
		hand.setCards(c);
		assertTrue(HandService.isFlush(hand));
	}

	@Test 
	public void isTwoPairTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(11, AppUtils.CARD_SUITE_HEARTS,  AppUtils.COURT_CARD_JACK+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(11, AppUtils.CARD_SUITE_CLUBS,   AppUtils.COURT_CARD_JACK+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(4,  AppUtils.CARD_SUITE_CLUBS,   4+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(9,  AppUtils.CARD_SUITE_HEARTS,  9+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(4,  AppUtils.CARD_SUITE_SPADES,  4+AppUtils.CARD_SUITE_SPADES));
		hand.setCards(c);
		assertTrue(HandService.isTwoPair(hand));
	}
	
	@Test
	public void isOnePairTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(4,  AppUtils.CARD_SUITE_HEARTS,  4+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(4,  AppUtils.CARD_SUITE_SPADES,  4+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(13, AppUtils.CARD_SUITE_SPADES,  AppUtils.COURT_CARD_KING+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(10, AppUtils.CARD_SUITE_DIAMONDS,10+AppUtils.CARD_SUITE_DIAMONDS));
		c.add(new Card(5,  AppUtils.CARD_SUITE_SPADES,  5+AppUtils.CARD_SUITE_SPADES));
		hand.setCards(c);
		assertTrue(HandService.isOnePair(hand));
	}
	
	@Test
	public void isThreeOfAKindTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(2,  AppUtils.CARD_SUITE_DIAMONDS,  2+AppUtils.CARD_SUITE_DIAMONDS));
		c.add(new Card(2,  AppUtils.CARD_SUITE_SPADES,    2+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(2, AppUtils.CARD_SUITE_CLUBS,  	  2+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(13, AppUtils.CARD_SUITE_SPADES,	  AppUtils.COURT_CARD_KING+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(6,  AppUtils.CARD_SUITE_SPADES,    6+AppUtils.CARD_SUITE_SPADES));
		hand.setCards(c);
		assertTrue(HandService.isThreeOfAKind(hand));
	}
	
	@Test
	public void isFourOfAKindTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(9,  AppUtils.CARD_SUITE_CLUBS,    4+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(9,  AppUtils.CARD_SUITE_SPADES,   4+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(9,  AppUtils.CARD_SUITE_DIAMONDS, 4+AppUtils.CARD_SUITE_DIAMONDS));
		c.add(new Card(9,  AppUtils.CARD_SUITE_HEARTS,	 4+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(10, AppUtils.CARD_SUITE_SPADES,   AppUtils.COURT_CARD_JACK+AppUtils.CARD_SUITE_SPADES));
		hand.setCards(c);
		assertTrue(HandService.isFourOfAKind(hand));
	}
	
	@Test
	public void isFullHouseTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(3,  AppUtils.CARD_SUITE_CLUBS,    3+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(3,  AppUtils.CARD_SUITE_SPADES,   3+AppUtils.CARD_SUITE_SPADES));
		c.add(new Card(3,  AppUtils.CARD_SUITE_DIAMONDS, 3+AppUtils.CARD_SUITE_DIAMONDS));
		c.add(new Card(6, AppUtils.CARD_SUITE_CLUBS,	 6+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(6, AppUtils.CARD_SUITE_HEARTS,    6+AppUtils.CARD_SUITE_HEARTS));
		hand.setCards(c);
		assertTrue(HandService.isFullHouse(hand));
	}
	
	@Test
	public void isStraightFlushTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(12,  AppUtils.CARD_SUITE_HEARTS,   AppUtils.COURT_CARD_Queen+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(11,  AppUtils.CARD_SUITE_HEARTS,   AppUtils.COURT_CARD_JACK+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(10,  AppUtils.CARD_SUITE_HEARTS, 10+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(9, AppUtils.CARD_SUITE_HEARTS,	 9+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(8, AppUtils.CARD_SUITE_HEARTS,    8+AppUtils.CARD_SUITE_HEARTS));
		hand.setCards(c);
		assertTrue(HandService.isStraightFlush(hand));
	}
	
	@Test
	public void isHighCardTest() {
		Hand hand = HandService.dealHand(5);
		List<Card> c = new ArrayList<Card>();
		c.add(new Card(13,  AppUtils.CARD_SUITE_HEARTS,    AppUtils.COURT_CARD_KING+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(11,  AppUtils.CARD_SUITE_HEARTS,   AppUtils.COURT_CARD_JACK+AppUtils.CARD_SUITE_HEARTS));
		c.add(new Card(8,  AppUtils.CARD_SUITE_CLUBS,	 8+AppUtils.CARD_SUITE_CLUBS));
		c.add(new Card(7, AppUtils.CARD_SUITE_DIAMONDS,	 7+AppUtils.CARD_SUITE_DIAMONDS));
		c.add(new Card(4, AppUtils.CARD_SUITE_SPADES,    4+AppUtils.CARD_SUITE_SPADES));
		hand.setCards(c);
		assertTrue(HandService.isHighCard(hand));
	}
}
