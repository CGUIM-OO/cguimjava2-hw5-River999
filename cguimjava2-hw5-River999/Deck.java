
import java.util.ArrayList;
import java.util.Random;

 public class Deck{
	private ArrayList<Card> cards;//TODO: Please implement the constructor (30 points)
	private ArrayList<Card> usedCard;
	public int nUsed;
	private ArrayList<Card> openCard;
	
	
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int deck=1;deck<=nDeck;deck++)
		{
			for(Card.Suit s:Card.Suit.values())
			{
				for(int Rank=1;Rank<=13;Rank++)//Rank�O�P��1-13����
				{
					Card card=new Card(s,Rank);
					cards.add(card);
				}
			}
		}
		shuffle();//�o�N���O���٨S��ʪ����J�A�p�G�S�~�P���ܡA�N�|���Ӷ���
	}
	
	public void shuffle(){//���@��"�~�P"����k
		openCard=new ArrayList<Card>();
		openCard.clear();
		
		Random rnd = new Random();//�ŧi�@��Random���O�A�ܼƦW�٬�ran�A�o���O�i�H���O�u�üƲ��;��v
     	usedCard=new ArrayList<Card>();//�إߤ@�ӷs���M��A�̭��˨ϥιL���P
        for(int i=0;i<208;i++){
    	 
        	int save = 0; //save�s���d�����k0�A���s�A�@��
        	save=rnd.nextInt(208-i);//)save�ΨӦs���C�����X�s���@�i�Pi
        	usedCard.add(cards.get(save));//��save�s�iusedCard�̭�
        	cards.remove(cards.get(save));//�A��cards�̭���save�R��
     }
        cards.addAll(usedCard);//�]���w�g��cards�̪�52�i�P����F�A�B��X�Ӫ��P�����busedCard�̡A��usedCard�̪��P����i�Ū�cards
     	usedCard.removeAll(usedCard);//��usedCard�̪��P�浹cards��A�i�H��usedCard�M�ŤF	
	
	nUsed=0;//�~���P�A�ϥιL���P���nUsed�N�k0
	}
	
	public Card getOneCard (boolean isOpened) {//�o�P
		
		
		nUsed=nUsed+1;
		Card card=new Card(cards.get(nUsed-1).getSuit(),cards.get(nUsed-1).getRank());//�O���󪺦W�r��card�A��nUsed=0�ɡA�N������o�X�Ӫ��P��m�}�C��0��m
		usedCard.add(cards.get(nUsed-1));//��o�X�����i�P��iusedCard�A�]��usedCard�O��m�w�g�Q�ϥΪ��F
		if(isOpened=true){
			
				openCard.add(cards.get(nUsed-1) );
			
			
		}
		
		if(nUsed==208)//52*4�i�P���^��N�i�H�~�P�F
		{
			shuffle();//�~�P
			
		}
		
		return card;
		
	}
	

	public ArrayList getOpenedCard() {
		return openCard ;//�^�Ǧ��ƵP���Ҧ����}�L���P�A�N�YopenCard 
	}
	
	
	
	
	
	//TODO: Please implement the method to print all cards on screen (10 points)
	/*public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		
		for(int i=0;i<cards.size();i++){
			Card card=new Card(cards.get(i).getSuit(),cards.get(i).getRank());//��card����ơAcards.get(i).getSuit()�s���Acards.get(i).getRank()�s�Ʀr
			card.printCard();
			
			
		}
		
	}*/
	
	
	public ArrayList<Card> getAllCards(){
		return cards;
     } 
}