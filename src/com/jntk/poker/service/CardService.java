package com.jntk.poker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jntk.poker.domain.Card;
import com.jntk.poker.utils.AppUtils;

public class CardService {
	//Returns standard deck of 52 cards
	private static List<Card> createDeck(){
		List<Card> deck = new ArrayList<>();
		
		//Create Hearts
		List<Card> hearts = createCardSuite(AppUtils.CARD_SUITE_HEARTS);
		deck.addAll(hearts);
		
		//Create Diamonds
		List<Card> diamonds = createCardSuite(AppUtils.CARD_SUITE_DIAMONDS);
		deck.addAll(diamonds);
		
		//Create Clubs
		List<Card> clubs = createCardSuite(AppUtils.CARD_SUITE_CLUBS);
		deck.addAll(clubs);
				
		//Create Spades
		List<Card> spades = createCardSuite(AppUtils.CARD_SUITE_SPADES);
		deck.addAll(spades);
		
		return deck;
	}
	
	private static List<Card> createCardSuite(String suiteName){
		List<Card> cards = new ArrayList<>();
		
		for(int i = 1; i <=13; i++) {
			int rank = i;
			StringBuilder sb = new StringBuilder();
			
			if(rank > 1 && rank < 11) {
				sb.append(rank);
			}else {
				if(rank == 1){
					sb.append(AppUtils.HONOUR_CARD_ACE);
					
				}else if(rank == 11) {
					sb.append(AppUtils.COURT_CARD_JACK);
					
				}else if(rank == 12) {
					sb.append(AppUtils.COURT_CARD_Queen);
					
				}else if(rank == 13) {
					sb.append(AppUtils.COURT_CARD_KING);
				}
			}
			sb.append(suiteName);
			
			cards.add(new Card(rank, suiteName, sb.toString()));
		}
		
		return cards;
	}
	
	public static List<Card> getShuffledDeckOfCards(){
		List<Card> cards = createDeck();
		Collections.shuffle(cards);
		return cards;
	}
}
