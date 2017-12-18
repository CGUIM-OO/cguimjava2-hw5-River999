

public class Card {

	 enum Suit {Clubs,Diamonds,Hearts, Spades}//使用enum命名Suit，裡面存取Clubs,Diamonds,Hearts, Spades
		//private int suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4//private相當於public，只是不同在他只能在自己的class使用
		private int rank; //1~13
		
		Suit suit;
		
		public Card(Suit s,int r){//設一個instructor名為 Card，參數為s和r
			suit=s;
			
			rank=r;
			
			
		}	
		
		//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
		public void printCard(){
			String []cards={"Ace","2","3","4","5","6","7","8","9","10","Jake","Queen","King"};
			//cards 裡面存了13張牌，會用String是因為裡頭有英文字母
			System.out.println(getSuit().name()+" of "+cards[getRank()-1]);
		
		
		}
		    
		
		
		
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}

