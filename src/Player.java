
public class Player {
	
	ArrayStack <Card> hand = new ArrayStack <Card>(); //instantiate new hand
	
	private String name; 
	//TESTING: keeps track of number of matches
	private int matchCount;
	
	Player(String name, int matchCount){ //constructor
		this.name = name;
		this.matchCount = matchCount;
	}
	
	public Deck deck; //access deck class

	
	//function that takes in a card & adds it to the player's hand
	public ArrayStack<Card> addCard(Deck deck){ 
		hand.push(deck.pop());
		return hand;
	}
	
	
	ArrayStack<Card> match = new ArrayStack<Card>(); //instantiate stack for cards that match in hasMatch and CompareCard	
		

	public Boolean hasMatch(Card card) {
		ArrayStack<Card> temp = new ArrayStack<Card>(); //instantiate stack for hasMatch function
		Card top = card;
		int i = 0;
		
		while (i<hand.size()) {
			
			if (top.equals(hand.peek())) { //if the cards match, move card from hand into match stack
				match.push(hand.pop());
			}
			
			else  //if the cards don't match, move card to temp, (remember to move back to hand after)
				temp.push(hand.pop());
		}
		
		while (temp.isEmpty() != true) {
			hand.push(temp.pop()); //return cards that didn't match back to player's hand
		}
		
		
		if (match.isEmpty() != true) {
			while (match.isEmpty()!=true) {
				hand.push(match.pop()); //return cards from match to hand
			}
			return true;
		}
		else
			return false; 
	}
		
		
	//function that takes in a card and compares that card to the cards in the player's hand,
	//and returns an ArrayStack of cards that are of equal value 
	
	public Card compareCard(Card card) {
		Card top = card;
		
		ArrayStack<Card> temp = new ArrayStack<Card>(); //instantiate stack for compareCard function non matches
		ArrayStack<Card> eights = new ArrayStack<Card>(); //instantiate stack for compareCard function crazy eights
		ArrayStack<Card> nonEights = new ArrayStack<Card>(); //instantiate stack for compareCard function non crazy eight matches

		
		while (hand.isEmpty()==false) {
			
			if (top.equals(hand.peek())) { //if the cards match, move card from hand into match stack
				match.push(hand.pop());
			}
			
			else  //if the cards don't match, move card to temp, (remember to move back to hand after)
				temp.push(hand.pop());
		}
		
		while (temp.isEmpty() != true) {
			hand.push(temp.pop()); //return cards that didn't match back to player's hand
		}
		
		
		if (match.isEmpty() != true) {
			this.matchCount++; //add 1 to match count 
			//run through match stack, if card value does not equal 8, return that card
			//if there are no other options except 8, return 8
			int l = 0;
			while (l<match.size()) {
				for (int i = 0; i<match.size(); i++) { //first grabs Eight card so they are taken out of regular match stack
					if (match.peek().getValue() == 8) {
						eights.push(match.pop());
					}
				}
				for (int i = 0; i<match.size(); i++) { //adds non-eight matches to different stack
					if (match.peek().getValue() != 8) {
						nonEights.push(match.pop());
					}
				}
			}
			
			Card matchCard = null;
			Card crazyEightCard = null;
			
			if (nonEights.isEmpty() == false) {
				matchCard = nonEights.pop();
				while (nonEights.isEmpty()==false) {
					hand.push(nonEights.pop());
				}
				while (eights.isEmpty()==false) {
						hand.push(eights.pop());
				}
			}
			else if (eights.isEmpty() == false) {
				crazyEightCard = eights.pop();
				while (eights.isEmpty()==false) {
					hand.push(eights.pop());
				 }
			 }
			if(matchCard != null) {
				return matchCard;
			}
			else
				return crazyEightCard;
	     }
		
		else
			return null;
		
	}
						
			
			
	public Card drawCard(Deck deck) {
		if (deck.isEmpty() != true) {
			hand.push(deck.pop()); //adds card from deck to hand
			Card handTop = hand.peek(); 
			return handTop; //returns top card in hand (card that was just drawn from deck)
		}
		else
			return null;
	}
	
	
	public Boolean isTurn(int turnCount) {
		if(turnCount % 2 == 1) {
			return true; //player 1's turn
		}
		else if (turnCount % 2 == 0) {
			return false; //player 2's turn
		}
		else
			return null;
	}
	
	public int isWinner(Player b) { 
		if (this.hand.size() < b.hand.size()) {
			//TESTING: shows how many matching cards each player had at end of game
			//System.out.println(this.name + " had " + String.valueOf(this.matchCount));
			//System.out.println(b.name + " had " + String.valueOf(b.matchCount));
			return 1;
		}
		else if (b.hand.size() < this.hand.size()) {
			//System.out.println(this.name + " had " + String.valueOf(this.matchCount));
			//System.out.println(b.name + " had " + String.valueOf(b.matchCount));			
			return -1;
		}
		else //(this.hand.size() == b.hand.size())  //if hand size is equal, then it must be the case that player 1 won and emptied hand first
			//System.out.println(this.name + " had " + String.valueOf(this.matchCount));
			//System.out.println(b.name + " had " + String.valueOf(b.matchCount));
			return 0;
 	} 
		
	public String getName() { 
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	
	//TESTING: variable used to count how many matching cards each player had
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int m) {
		matchCount = m;
	}
	
	

}
