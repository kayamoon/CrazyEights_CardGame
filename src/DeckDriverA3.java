

public class DeckDriverA3 {


	public static Card matchingCard;
	public static Card matchingCard2;
	public static Card topCard;
	
	
	public static void main(String[] args) {
				 
		//instantiate deck
		Deck deck1 = new Deck();

		deck1.fillDeck();
		
		deck1.shuffleDeck();
				
		//TESTING print deck before dealing player's hands 
		System.out.println("52 card deck: \n");
		System.out.println(deck1.toString());
		
		System.out.println("------------------------------");
		System.out.println("\n");
		

		//instantiate players
		Player player1 = new Player("Player 1", 0);
		Player player2 = new Player("Player 2", 0);
		
		//instantiate and print players' hands (add 5 cards to each player's hand from the draw pile)
		for (int i=0; i<5; i++) {
			player1.addCard(deck1);
			player2.addCard(deck1);
		}
		
		System.out.println(String.valueOf(player1.getName()) + "'s Hand: \n" + (player1.hand).toString());
		System.out.println(String.valueOf(player2.getName()) + "'s Hand: \n" + (player2.hand).toString());
		
		System.out.println("------------------------------");
		System.out.println("\n");
		
		//instantiate starter pile
		Deck starterPile = new Deck();
		starterPile.push(deck1.pop());
				
		//TESTING print deck after dealing player's hands and putting one card on starter pile, also print starter pile
		System.out.println("Draw Pile: ");
		System.out.println(deck1.toString());
		System.out.println("\nStarter Pile: ");
		System.out.println(starterPile.toString());

		System.out.println("\n");
		System.out.println("------------------------------");
		System.out.println("\n");
		
		int infiniteLoop = 0;
		int turnCt = 1; //turn count, allows players to switch turns
		
		while (infiniteLoop == 0){ //infinite while loop for the game, I use breaks when one of the player's hands is empty to exit the loop and end the game

			//compare players' hands to the top card of the deck
			topCard = starterPile.peek();
			System.out.println("\nTop card: " + topCard.toString() + "\n");
		
			
			if (player1.hand.isEmpty() == false) {
				if (player1.isTurn(turnCt) == true) {
					//find matching cards for player 1
					if (player1.hasMatch(topCard) == true) {
						matchingCard = player1.compareCard(topCard);
						System.out.println(String.valueOf(player1.getName()) + "'s Matching Card: \n" + matchingCard.toString());						
						starterPile.push(matchingCard);
						topCard = starterPile.peek();
					}
					else if (player1.hasMatch(topCard) == false) {
						System.out.println(String.valueOf(player1.getName()) + " has no matching cards.");
							if (deck1.isEmpty() == false) {
								drawTilMatch(player1, deck1, starterPile);
							}
							else if (deck1.isEmpty()){ //if draw pile is empty, discard pile gets shuffled and added back to the draw pile
								starterPile.shuffleDeck();
									while (starterPile.isEmpty() == false) { 
										deck1.push(starterPile.pop());
									}
										starterPile.push(deck1.pop()); //add one card from draw deck to new starter pile (new top card is selected)
										topCard = starterPile.peek();
										drawTilMatch(player1, deck1, starterPile);
							}
					}
					turnCt++;
				}
				//breaks loop to end game if player's hand is empty
				if (player1.hand.isEmpty()) {
					System.out.println(String.valueOf(player1.getName()) + "'s hand is empty!");
					break;
				}
			}
			//breaks loop to end game if player's hand is empty
			else if (player1.hand.isEmpty()) { 
				System.out.println(String.valueOf(player1.getName()) + "'s hand is empty!");
				break;
			}
			
			if (player2.hand.isEmpty() == false) {
				if (player2.isTurn(turnCt) == false) {
					//find matching cards for player 2
					if (player2.hasMatch(topCard) == true) {
						matchingCard2 = player2.compareCard(topCard);
						System.out.println(String.valueOf(player2.getName()) + "'s Matching Card: \n" + matchingCard2.toString());
						starterPile.push(matchingCard2);
						topCard = starterPile.peek();
					}
					else if (player2.hasMatch(topCard) == false) {
						System.out.println(String.valueOf(player2.getName()) + " has no matching cards.");
							if (deck1.isEmpty() == false) {
								drawTilMatch(player2, deck1, starterPile);
							}
							else if (deck1.isEmpty()){ //if draw pile is empty, discard pile gets shuffled and added back to the draw pile
								starterPile.shuffleDeck();
									while (starterPile.isEmpty() == false) { 
										deck1.push(starterPile.pop());
									}
										starterPile.push(deck1.pop()); //add one card from draw deck to new starter pile (new top card is selected)
										topCard = starterPile.peek();
										drawTilMatch(player1, deck1, starterPile);
							}
					}
					turnCt++;
				}
				//breaks loop to end game if player's hand is empty
				if (player2.hand.isEmpty()) {
					System.out.println(String.valueOf(player2.getName()) + "'s hand is empty!");
					break;
				}
			}
			//breaks loop to end game if player's hand is empty
			else if (player2.hand.isEmpty()) {
				System.out.println(String.valueOf(player2.getName()) + "'s hand is empty!");
				break;
			}
			
			//TESTING prints players' hands at end of each round
			//System.out.println(String.valueOf("\n" + player1.getName()) + "'s Hand: \n" + (player1.hand).toString());
			//System.out.println(String.valueOf(player2.getName()) + "'s Hand: \n" + (player2.hand).toString());
			//TESTING prints draw pile at end of each round
			//System.out.println("Draw Pile: " + deck1.toString() + "\n");
			
		} 

		
		
		System.out.println("------------------------------");
		System.out.println("\n");
		
		//TESTING Prints players' hand sizes at the end of the game
		System.out.println("Players' Final Hand Sizes:\n");
		System.out.println("Player 1: " + String.valueOf(player1.hand.size()));
		System.out.println("Player 2: " + String.valueOf(player2.hand.size()));
		System.out.println(" ");

		System.out.println("------------------------------");
		System.out.println("\n");
		
	    //Declares winner
		if(player1.isWinner(player2) == 1) {
			System.out.println(String.valueOf(player1.getName()) + " wins!");
		}
		else if(player1.isWinner(player2) == -1) {
			System.out.println(String.valueOf(player2.getName()) + " wins!");
		}
		else if(player1.isWinner(player2) == 0) {
			System.out.println(String.valueOf(player1.getName()) + " wins!"); //player 1 wins, because if both hands are empty after one round, player 1 emptied hand first
		} 
	  
	    	
	}

	
	//keeps drawing cards until player has a match 
	public static void drawTilMatch(Player p, Deck deck1, Deck starterPile) {

		while (p.hasMatch(topCard) == false) { //KEEPS DRAWING TIL FINDS MATCH
			if (deck1.isEmpty()) {
				starterPile.shuffleDeck();
				while (starterPile.isEmpty() == false) { 
					deck1.push(starterPile.pop());
				}
					starterPile.push(deck1.pop()); //add one card from draw deck to new starter pile (new top card is selected)
					topCard = starterPile.peek();
			}
			System.out.println(p.getName() + " is drawing a card");
			p.drawCard(deck1);	
		}
		matchingCard = p.compareCard(topCard);
		System.out.println(String.valueOf(p.getName()) + "'s Matching Card: \n" + matchingCard.toString());						
		starterPile.push(matchingCard);
		topCard = starterPile.peek();
		
	}

	

}
