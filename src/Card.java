
public class Card {
	private int value;
	private String suit;
	private String faceValue;

	Card(){}
	
	Card(String s, int v){
		suit = s;
		value =v;
		switch(v){
			case(1):
				faceValue="Ace";
				break;
			case(11):
				faceValue="Jack";
				break;
			case(12):
				faceValue="Queen";
				break;
			case(13):
				faceValue="King";
				break;
			default:
				faceValue=Integer.toString(v);
				break;
			}
	}
	
	public String getSuit(){
		return suit;
	}
	
	public int getValue(){
		return value;
	}
	
	
	public String toString(){
		return(faceValue + " of " +suit);
	}
	
	public boolean equals(Card c){
		if (this.value == c.getValue()){return true;}
		else if (this.suit.equals(c.getSuit())){return true;}
		else if (c.getValue() == 8){return true;}
		else if (this.getValue() ==8) {return true;} //added this so that if top card is 8, any card matches it
		else return false;
		
	}
}


