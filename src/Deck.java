import java.util.Random;

public class Deck extends ArrayStack <Card>{
	
	Deck(){
		stack = new Card[52]; //instantiates arraystack called stack with 52 Cards
	}


	public void fillDeck() {
		//pop all cards onto deck, 13 of each suit
		
		 
		for (int i=0; i<stack.length; i++) { 
			if (i<13) {
				this.push(new Card("Hearts", i+1));
			}
			if(12<i & i<26) {
				this.push(new Card("Diamonds", (i%13)+1));
			}
			if(25<i & i<39) {
				this.push(new Card("Spades", (i%13)+1));
			}
			if(38<i & i<52) {
				this.push(new Card("Clubs", (i%13)+1));
			}
			
		}
		
		
	}
	

	public void shuffleDeck() {
		Random rgen = new Random();  // Random number generator			
		 
		for (int i=0; i<size(); i++) {
		    int randomPosition = rgen.nextInt(size()); //assigns a random position within the size of the stack
		    Card temp = stack[i]; //assigns element from stack to temporary Card object
		    stack[i] = stack[randomPosition]; //changes position of element from original to random position
		    stack[randomPosition] = temp; //assigns original card to new, random position
		}

		
	}
	
	public String toString() { //print the deck in a single line separated by commas
		String str = " ";
		
			for (int i=stack.length-1; i>=0; i--) { //assigns every element in the stack to temporary Card object
				Card temp = stack[i];
				
				if (temp!=null) {
					str += String.valueOf(temp) +  ", "; //prints every element in the stack
				}
			}
		
		return str;
	}
	

}
