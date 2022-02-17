package com.jntk.poker.main;

import com.jntk.poker.domain.Hand;
import com.jntk.poker.service.HandService;
import com.jntk.poker.test.PokerGameTests;

public class PokerGame {

	public static void main(String[] args) {
		//Run Unit Tests
		new PokerGameTests().runTests();
		
		//Deal and evaluate Hand
		Hand hand = HandService.dealHand(5);
		System.out.println("Your hand: " + hand.getValue());
		System.out.println(HandService.evaluateHand(hand));
	}

}
