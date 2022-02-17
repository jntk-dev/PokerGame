#Technical Assessment

#– Poker Test -

#Problem Statement
Five-card draw is a variant of poker where each player receives a hand of 5 cards, is then allowed to swap
any number of those cards for new ones, and then competes against each other based on the standard 5
card poker hand strength. You need to design and develop a console based application which will deal
and evaluate poker hands for a simplified version of the five-card draw variant. Like the five-card draw
variant, the application will have to deal a hand of 5 cards, but unlike the five-card draw variant, no
swapping will be allowed.

The application will be required to provide the following functionality:
▪ Simulate shuffling a standard deck of 52 cards.
▪ Deal a single hand of 5 cards to the player.
▪ Evaluate the player’s hand, informing them of the highest ranked poker hand that matches their
hand of 5 cards.

The standard 5 card poker hand strength applies. Listed in decreasing rank, these hands are:
1. Straight Flush
2. Four of a Kind
3. Full House
4. Flush
5. Straight
6. Three of a Kind
7. Two Pair
8. One Pair
9. High Cards

Technical Requirements
▪ The application is to be developed as a console application Java.
▪ You should include a build script which can compile, package and run the application.
▪ The application code should also be properly unit tested, and the build script should be able to run
the unit tests.

Example output of running the application:
$ ./run.sh Shuffling ... Shuffling ... Shuffling ...
Your hand: 3♣ 7♦ 7♥ 7♠ K♥
You have: Three of a Kind
$
