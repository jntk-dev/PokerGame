package com.jntk.poker.domain;

import java.util.List;

public class Hand {
	private List<Card> cards;
	private String value;
	
	public Hand() {
		super();
	}
	
	public Hand(List<Card> cards) {
		super();
		this.cards = cards;
		setValue(cards);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
		setValue(cards);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private void setValue(List<Card> cards) {
		StringBuilder sb = new StringBuilder();
		for(Card card : getCards()) {
			sb.append(card.getName()).append(" ");
		}
		this.value = sb.toString();
	}
}
