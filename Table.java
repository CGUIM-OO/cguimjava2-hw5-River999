import java.util.ArrayList;

public class Table {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    private static final  int MAXPLAYER = 4;//玩家的人數是4
    private Deck getDeck;//Deck的變數(instance field)，存放所有的牌
    private Player getPlayer[];//Player[] 的變數(instance field)，存放所有的玩家
    private Dealer getDealer;//莊家
    
    private int[] pos_betArray=new int[MAXPLAYER];//放玩家的下注
    
    public Table(int nDeck){
    	this.getDeck=getDeck;//Deck class實體化，並存入上述新增的Deck變數(instance field)
    	Deck dk=new Deck(nDeck);//Deck是class，物件(object)是dk
    	getDeck=dk;
    	getPlayer=new Player[MAXPLAYER];//宣告一個長度是MAXPLAYER 的Player array
    }
    
    public void set_player(int pos, Player p){//將Player放到牌桌上 (意即放到型別為Player[]的變數(instance field)中，為setter)，pos為牌桌位置，最多MAXPLAYER人，p則為Player instance。
    	getPlayer[pos]=p;
    }
    public Player[] get_player(){//回傳所有在牌桌上的player，意即回傳型別為Player[]的變數(instance field)變數，為getter
    	
    	return getPlayer;
    }
    public void set_dealer(Dealer d){
    	getDealer=d;
    } //將Dealer放到牌桌上 (意即將Dealer放到 型別為Dealer 的變數(instance field) 中，為變數之setter)。

    public Card get_face_up_card_of_dealer(){//給莊家的
		return getDealer.getOneRoundCard().get(1);//回傳dealer打開的那張牌，也就是第二張牌
    	
    }
    
    private void ask_each_player_about_bets(){
    	for(int i=0;i<MAXPLAYER;i++){
    	
    		getPlayer[i].sayHello();//呼叫Player.java的sayHello()
    		getPlayer[i].makeBet();//呼叫Player.java的makeBet()
    		pos_betArray[i]=getPlayer[i].makeBet();}//每個玩家下的注要存在pos_betArray供之後使用
    }
    private void distribute_cards_to_dealer_and_players(){
    	for(int i=0;i<MAXPLAYER;i++){
    		ArrayList<Card> a=new ArrayList<Card>();
    		a.add(getDeck.getOneCard(true));//先發兩張打開的牌給玩家
    		a.add(getDeck.getOneCard(true));
    		getPlayer[i].setOneRoundCard(a);//再一張蓋著的牌(從Person.java的setOneRoundCard)
    	}ArrayList<Card> a=new ArrayList<Card>();
    	a.add(getDeck.getOneCard(false));
		a.add(getDeck.getOneCard(true));
    	getDealer.setOneRoundCard(a);//一張打開的牌給莊家(從Person.java的setOneRoundCard)
    	
    	System.out.println("Dealer's face up card is");
    	
    	get_face_up_card_of_dealer().printCard();
    }
    private void ask_each_player_about_hits(){//問每個玩家要不要牌
    	boolean hit=false;
    	for(int i=0;i<MAXPLAYER;i++){
    	do{
			hit=getPlayer[i].hitMe(); //從Player.java的hitMe()
			if(hit){
				getPlayer[i].getOneRoundCard().add(getDeck.getOneCard(true));
				getPlayer[i].setOneRoundCard(getPlayer[i].getOneRoundCard());
				System.out.print("Hit! ");
				System.out.println(getPlayer[i].getName()+"'s Cards now:");
				for(Card c : getPlayer[i].getOneRoundCard()){
					c.printCard();
				}
			}
			else{
				//System.out.println("Pass hit!");//System.out.println(getPlayer[i].getName()+", Pass hit!");
				System.out.println(getPlayer[i].getName()+", Card:");
				//System.out.println(getPlayer[i].getName()+"'s hit is over!");
				for(Card c : getPlayer[i].getOneRoundCard()){
					c.printCard();
				}
				System.out.println("Pass hit!");
				System.out.println(getPlayer[i].getName()+"'s hit is over!");
			}
		}while(hit);
		
		hit=false;}

    }
    private void ask_dealer_about_hits(){
    	boolean hit=false;
    	do{
			hit=getDealer.hit_me(this); //
			if(hit){
				getDealer.getOneRoundCard().add(getDeck.getOneCard(true));
			}
			else
				{System.out.println("Dealer's hit is over!");}
		}while(hit);
		hit=false;
		}
    
    private void calculate_chips(){
    	getDealer.printAllCard();
    	System.out.println("Dealer's card value is "+getDealer.getTotalValue()+" ,Cards:");
    	getDealer.printAllCard();
    	for(int i=0;i<MAXPLAYER;i++){
    		System.out.print(getPlayer[i].getName()+"'s Cards:");
    		for(Card c : getPlayer[i].getOneRoundCard()){
				c.printCard();
			}
    		System.out.print(getPlayer[i].getName()+" card value is "+getPlayer[i].getTotalValue());
    		
    	    	if(getDealer.getTotalValue()>21 && getPlayer[i].getTotalValue()>21){//如果兩位玩家的牌都大於21(透過Player的getTotalValue來算)
    				System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
    			}else if(getDealer.getTotalValue()<=21&&getPlayer[i].getTotalValue()>21){//如果玩家1的牌小於等於，玩家2的牌大於21(透過Player的getTotalValue來算)
    				getPlayer[i].increaseChips(-getPlayer[i].makeBet());
    				System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
    			}else if(getDealer.getTotalValue()>21&&getPlayer[i].getTotalValue()<=21){//如果玩家2的牌都小於等於，玩家1的牌大於21(透過Player的getTotalValue來算)
    				getPlayer[i].increaseChips(getPlayer[i].makeBet());
    				System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
    			}else if(getDealer.getTotalValue()>getPlayer[i].getTotalValue()&&getDealer.getTotalValue()<=21){//如果玩家1的牌大於玩家2的牌且玩家1的牌小於等於21(透過Player的getTotalValue來算)
    				getPlayer[i].increaseChips(-getPlayer[i].makeBet());
    				System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
    			}else if(getDealer.getTotalValue()<getPlayer[i].getTotalValue()&&getPlayer[i].getTotalValue()<=21){//如果玩家1的牌小於玩家2的牌，且玩家2的牌小於等於21(透過Player的getTotalValue來算)
    				getPlayer[i].increaseChips(getPlayer[i].makeBet());
    				System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
    			}else{
    				System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
    			}
    	   	
    	}
    	
    	/*for(int i=0;i<MAXPLAYER;i++){
    	if(getDealer.getTotalValue()>21 && getPlayer[i].getTotalValue()>21){//如果兩位玩家的牌都大於21(透過Player的getTotalValue來算)
			System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
		}else if(getDealer.getTotalValue()<=21&&getPlayer[i].getTotalValue()>21){//如果玩家1的牌小於等於，玩家2的牌大於21(透過Player的getTotalValue來算)
			getPlayer[i].increaseChips(-getPlayer[i].makeBet());
			System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
		}else if(getDealer.getTotalValue()>21&&getPlayer[i].getTotalValue()<=21){//如果玩家2的牌都小於等於，玩家1的牌大於21(透過Player的getTotalValue來算)
			getPlayer[i].increaseChips(getPlayer[i].makeBet());
			System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
		}else if(getDealer.getTotalValue()>getPlayer[i].getTotalValue()&&getDealer.getTotalValue()<=21){//如果玩家1的牌大於玩家2的牌且玩家1的牌小於等於21(透過Player的getTotalValue來算)
			getPlayer[i].increaseChips(-getPlayer[i].makeBet());
			System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
		}else if(getDealer.getTotalValue()<getPlayer[i].getTotalValue()&&getPlayer[i].getTotalValue()<=21){//如果玩家1的牌小於玩家2的牌，且玩家2的牌小於等於21(透過Player的getTotalValue來算)
			getPlayer[i].increaseChips(getPlayer[i].makeBet());
			System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(輸入值)跑至Player的increaseChips(籌碼的變動)
		}else{
			System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
		}
   	}*/
    	
    	    }
    public int[] get_players_bet(){
    	return pos_betArray;
    }
    
    public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
    













}
