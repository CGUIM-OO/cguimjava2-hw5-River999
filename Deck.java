
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
				for(int Rank=1;Rank<=13;Rank++)//Rank是牌的1-13順序
				{
					Card card=new Card(s,Rank);
					cards.add(card);
				}
			}
		}
		shuffle();//這就像是剛還沒拆封的撲克，如果沒洗牌的話，就會按照順序
	}
	
	public void shuffle(){//做一個"洗牌"的方法
		openCard=new ArrayList<Card>();
		openCard.clear();
		
		Random rnd = new Random();//宣告一個Random類別，變數名稱為ran，這類別可以說是「亂數產生器」
     	usedCard=new ArrayList<Card>();//建立一個新的清單，裡面裝使用過的牌
        for(int i=0;i<208;i++){
    	 
        	int save = 0; //save存完卡之後歸0，重新再一次
        	save=rnd.nextInt(208-i);//)save用來存取每次取出新的一張牌i
        	usedCard.add(cards.get(save));//把save存進usedCard裡面
        	cards.remove(cards.get(save));//再把cards裡面的save刪除
     }
        cards.addAll(usedCard);//因為已經把cards裡的52張牌抽光了，且抽出來的牌全都在usedCard裡，把usedCard裡的牌全放進空的cards
     	usedCard.removeAll(usedCard);//把usedCard裡的牌交給cards後，可以把usedCard清空了	
	
	nUsed=0;//洗完牌，使用過的牌堆裡nUsed就歸0
	}
	
	public Card getOneCard (boolean isOpened) {//發牌
		
		
		nUsed=nUsed+1;
		Card card=new Card(cards.get(nUsed-1).getSuit(),cards.get(nUsed-1).getRank());//令物件的名字為card，當nUsed=0時，就直接把發出來的牌放置陣列的0位置
		usedCard.add(cards.get(nUsed-1));//把發出的那張牌放進usedCard，因為usedCard是放置已經被使用的了
		if(isOpened=true){
			
				openCard.add(cards.get(nUsed-1) );
			
			
		}
		
		if(nUsed==208)//52*4張牌收回後就可以洗牌了
		{
			shuffle();//洗牌
			
		}
		
		return card;
		
	}
	

	public ArrayList getOpenedCard() {
		return openCard ;//回傳此副牌中所有打開過的牌，意即openCard 
	}
	
	
	
	
	
	//TODO: Please implement the method to print all cards on screen (10 points)
	/*public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		
		for(int i=0;i<cards.size();i++){
			Card card=new Card(cards.get(i).getSuit(),cards.get(i).getRank());//把card實體化，cards.get(i).getSuit()存花色，cards.get(i).getRank()存數字
			card.printCard();
			
			
		}
		
	}*/
	
	
	public ArrayList<Card> getAllCards(){
		return cards;
     } 
}