

public class Card {

	 enum Suit {Clubs,Diamonds,Hearts, Spades}//�ϥ�enum�R�WSuit�A�̭��s��Clubs,Diamonds,Hearts, Spades
		//private int suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4//private�۷��public�A�u�O���P�b�L�u��b�ۤv��class�ϥ�
		private int rank; //1~13
		
		Suit suit;
		
		public Card(Suit s,int r){//�]�@��instructor�W�� Card�A�ѼƬ�s�Mr
			suit=s;
			
			rank=r;
			
			
		}	
		
		//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
		public void printCard(){
			String []cards={"Ace","2","3","4","5","6","7","8","9","10","Jake","Queen","King"};
			//cards �̭��s�F13�i�P�A�|��String�O�]�����Y���^��r��
			System.out.println(getSuit().name()+" of "+cards[getRank()-1]);
		
		
		}
		    
		
		
		
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}

