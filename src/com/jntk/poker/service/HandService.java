package com.jntk.poker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jntk.poker.domain.Card;
import com.jntk.poker.domain.Hand;

/* The repo below assisted in development of the Hand Evaluator logic in this class:
 * https://github.com/rachelvansciver/PokerHandAnalyzer
 */

public class HandService {
	
	public static Hand dealHand(int numCards) {
		List<Card> deck = CardService.getShuffledDeckOfCards();
		
		List<Card> cards = new ArrayList<>();
		cards.addAll(deck.subList(0, numCards));
		
		return new Hand(cards);
	}
	
	//Return hand strength as String
	 /*  Listed in decreasing rank, these hands are: 
	 *  1. Straight Flush - contains five cards of sequential rank, all of the same suit, such as Q♥ J♥ 10♥ 9♥ 8♥ (a "queen-high straight flush").
		2. Four of a Kind - contains four cards of one rank and one card of another rank (the kicker), such as 9♣ 9♠ 9♦ 9♥ J♥ ("four of a kind, nines").
		3. Full House - contains three cards of one rank and two cards of another rank, such as 3♣ 3♠ 3♦ 6♣ 6♥ (a "full house, threes over sixes")
		4. Flush - A flush is a hand that contains five cards all of the same suit, not all of sequential rank, such as K♣ 10♣ 7♣ 6♣ 4♣ (a "king-high flush"
		5. Straight - A straight is a hand that contains five cards of sequential rank, not all of the same suit, such as 7♣ 6♠ 5♠ 4♥ 3♥ (a "seven-high straight"). 
		
		6. Three of a Kind - contains three cards of one rank and two cards of two other ranks (the kickers), 
		   such as 2♦ 2♠ 2♣ K♠ 6♥ ("three of a kind, twos" or "trip twos" or a "set of twos")
		   
		7. Two Pair - contains two cards of one rank, two cards of another rank and one card of a third rank (the kicker),
						 such as J♥ J♣ 4♣ 4♠ 9♥ ("two pair, jacks and fours" or "two pair, jacks over fours" or "jacks up")
						 
		8. One Pair - contains two cards of one rank and three cards of three other ranks (the kickers), 
					   such as 4♥ 4♠ K♠ 10♦ 5♠ ("one pair, fours" or a "pair of fours")
					   
		9. High Cards - also known as no pair or simply nothing, is a hand that does not fall into any other category, 
				such as K♥ J♥ 8♣ 7♦ 4♠ ("high card, king" or "king-jack-high" or "king-high").
	 * */
	public static String evaluateHand(Hand hand) {
		String result = null;
		
		boolean straightFlush = isStraightFlush(hand);
		boolean fourKind 	  = isFourOfAKind(hand);
		boolean fullHouse	  = isFullHouse(hand);
		boolean flush 		  = isFlush(hand);
		boolean straight 	  = isStraight(hand);
		boolean threeKind 	  = isThreeOfAKind(hand);
		boolean twoPair   	  = isTwoPair(hand);
		boolean onePair   	  = isOnePair(hand);
		boolean isHighCard 	  = isHighCard(hand);
		
		if(straightFlush) {
			result = "Straight Flush";
			
		}else if(fourKind) {
			result = "Four of a Kind";
			
		}else if(fullHouse) {
			result = "Full House";
			
		}else if(flush) {
			result = "Flush";
			
		}else if(straight) {
			result = "Straight";
			
		}else if(threeKind) {
			result = "Three of a Kind";
			
		}else if(twoPair) {
			result = "Two Pair";
			
		}else if(onePair) {
			result = "One Pair";
			
		}else if(isHighCard) {
			result = "High Cards";
		}
		
		return "You Have: " + result;
	}

	
	//5. hand that contains five cards of sequential rank, not all of the same suit, such as 7♣ 6♠ 5♠ 4♥ 3♥ (a "seven-high straight").
	public static boolean isStraight(Hand hand) {
		hand = sortHand(hand);
		
		boolean straight = true;
	
		for(int i = 0; i < hand.getCards().size() && i+1 < hand.getCards().size() ; i++) {
			//looks at next and current card value
			int current = hand.getCards().get(i).getRank();
			int next    = hand.getCards().get(i+1).getRank();
			
			//if next card is more than one greater it is not a straight
			if (next > current+1) {
				straight = false;
				break;
			}
		}
		
		return straight;
	}

	// A flush is a hand that contains five cards all of the same suit, not all of sequential rank, such as K♣ 10♣ 7♣ 6♣ 4♣ (a "king-high flush")
	public static boolean isFlush(Hand hand) {
		boolean flush = true;
		
		String suite = hand.getCards().get(0).getSuite();
		for(Card card : hand.getCards()) {
			if(!card.getSuite().equals(suite)) {
				flush = false;
				break;
			}
		}
	
		return flush;
	}
	
	/*Two Pair - contains two cards of one rank, two cards of another rank and one card of a third rank (the kicker),
	such as J♥ J♣ 4♣ 4♠ 9♥ ("two pair, jacks and fours" or "two pair, jacks over fours" or "jacks up") */
	public static boolean isTwoPair(Hand hand) {
		//[Rank][List<Card>]
		Map<Integer, List<Card>> map = new HashMap<Integer, List<Card>>();
		
		for(Card card : hand.getCards()) {
			if(map.containsKey(card.getRank())) {
				map.get(card.getRank()).add(card);
			}else {
				List<Card> cList = new ArrayList<>();
						   cList.add(card);
				map.put(card.getRank(), cList);
			}
		}
		
		int oneCard		 = 0; 
		int twoPairCount = 0;
		
		for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
			Integer key    = entry.getKey();
			List<Card> val = entry.getValue();
			
			if(val.size() == 2) {
				twoPairCount++;
			}else {
				oneCard++;
			}
		}
		
		return twoPairCount == 2 && oneCard == 1;
	}
	
	/*
	8. One Pair - contains two cards of one rank and three cards of three other ranks (the kickers), 
	   such as 4♥ 4♠ K♠ 10♦ 5♠ ("one pair, fours" or a "pair of fours") */
	public static boolean isOnePair(Hand hand) {
		//[Rank][List<Card>]
		Map<Integer, List<Card>> map = new HashMap<Integer, List<Card>>();
		
		for(Card card : hand.getCards()) {
			if(map.containsKey(card.getRank())) {
				map.get(card.getRank()).add(card);
			}else {
				List<Card> cList = new ArrayList<>();
						   cList.add(card);
				map.put(card.getRank(), cList);
			}
		}
		
		int oneCard		 = 0; 
		int onePairCount = 0;
		
		for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
			Integer key    = entry.getKey();
			List<Card> val = entry.getValue();
			
			if(val.size() == 2) {
				onePairCount++;
			}else {
				oneCard++;
			}
		}
		
		return onePairCount == 1 && oneCard == 3;
	}
	
	/*
	 * contains three cards of one rank and two cards of two other ranks (the kickers), 
		   such as 2♦ 2♠ 2♣ K♠ 6♥ ("three of a kind, twos" or "trip twos" or a "set of twos")
	 * */
	public static boolean isThreeOfAKind(Hand hand) {
		//[Rank][List<Card>]
		Map<Integer, List<Card>> map = new HashMap<Integer, List<Card>>();
		
		for(Card card : hand.getCards()) {
			if(map.containsKey(card.getRank())) {
				map.get(card.getRank()).add(card);
			}else {
				List<Card> cList = new ArrayList<>();
						   cList.add(card);
				map.put(card.getRank(), cList);
			}
		}
		
		boolean threeKind = false;
		
		for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
			Integer key    = entry.getKey();
			List<Card> val = entry.getValue();
			
			if(val.size() == 3) {
				threeKind = true;
			}
		}
		
		return threeKind;
	}
	

	//ontains four cards of one rank and one card of another rank (the kicker), such as 9♣ 9♠ 9♦ 9♥ J♥ ("four of a kind, nines").
	public static boolean isFourOfAKind(Hand hand) { 
		//[Rank][List<Card>]
		Map<Integer, List<Card>> map = new HashMap<Integer, List<Card>>();
		
		for(Card card : hand.getCards()) {
			if(map.containsKey(card.getRank())) {
				map.get(card.getRank()).add(card);
			}else {
				List<Card> cList = new ArrayList<>();
						   cList.add(card);
				map.put(card.getRank(), cList);
			}
		}
		
		boolean fourKind = false;
		
		for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
			Integer key    = entry.getKey();
			List<Card> val = entry.getValue();
			
			if(val.size() == 4) {
				fourKind = true;
			}
		}
		
		return fourKind;
	}
	
	//contains three cards of one rank and two cards of another rank, such as 3♣ 3♠ 3♦ 6♣ 6♥ (a "full house, threes over sixes")
	public static boolean isFullHouse(Hand hand) { 
			//[Rank][List<Card>]
			Map<Integer, List<Card>> map = new HashMap<Integer, List<Card>>();
			
			for(Card card : hand.getCards()) {
				if(map.containsKey(card.getRank())) {
					map.get(card.getRank()).add(card);
				}else {
					List<Card> cList = new ArrayList<>();
							   cList.add(card);
					map.put(card.getRank(), cList);
				}
			}
			
			int threeRanks = 0; 
			int twoRanks   = 0;
			
			for (Map.Entry<Integer, List<Card>> entry : map.entrySet()) {
				Integer key    = entry.getKey();
				List<Card> val = entry.getValue();
				
				if(val.size() == 3) {
					threeRanks++;
				}else if(val.size() == 2){
					twoRanks++;
				}
			}
			
			return threeRanks == 1 && twoRanks == 1;
	}
	
	//contains five cards of sequential rank, all of the same suit, such as Q♥ J♥ 10♥ 9♥ 8♥ (a "queen-high straight flush").
	public static boolean isStraightFlush(Hand hand) {
		return isStraight(hand) && isFlush(hand);
	}
	
	/*
	 * High Cards - also known as no pair or simply nothing, is a hand that does not fall into any other category, 
		such as K♥ J♥ 8♣ 7♦ 4♠ ("high card, king" or "king-jack-high" or "king-high").
	 * */
	public static boolean isHighCard(Hand hand) {
		boolean straightFlush = isStraightFlush(hand);
		boolean fourKind 	  = isFourOfAKind(hand);
		boolean fullHouse	  = isFullHouse(hand);
		boolean flush 		  = isFlush(hand);
		boolean straight 	  = isStraight(hand);
		boolean threeKind 	  = isThreeOfAKind(hand);
		boolean twoPair   	  = isTwoPair(hand);
		boolean onePair   	  = isOnePair(hand);
		
		
		return straightFlush == false && fourKind == false && fullHouse == false
			&& flush == false && straight == false && threeKind == false
			&& twoPair == false && onePair == false;
	}
	
	public static Hand sortHand(Hand hand) {
		List<Card> cards = hand.getCards();
		
		Collections.sort(cards, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				Integer rank1 = c1.getRank();
				Integer rank2 = c2.getRank();
				
				return rank1.compareTo(rank2);
			}
		});
		
		hand.setCards(cards);
		
		return hand;
	}
}
