import java.util.ArrayList;

public class Table {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    private static final  int MAXPLAYER = 4;//���a���H�ƬO4
    private Deck getDeck;//Deck���ܼ�(instance field)�A�s��Ҧ����P
    private Player getPlayer[];//Player[] ���ܼ�(instance field)�A�s��Ҧ������a
    private Dealer getDealer;//���a
    
    private int[] pos_betArray=new int[MAXPLAYER];//�񪱮a���U�`
    
    public Table(int nDeck){
    	this.getDeck=getDeck;//Deck class����ơA�æs�J�W�z�s�W��Deck�ܼ�(instance field)
    	Deck dk=new Deck(nDeck);//Deck�Oclass�A����(object)�Odk
    	getDeck=dk;
    	getPlayer=new Player[MAXPLAYER];//�ŧi�@�Ӫ��׬OMAXPLAYER ��Player array
    }
    
    public void set_player(int pos, Player p){//�NPlayer���P��W (�N�Y��쫬�O��Player[]���ܼ�(instance field)���A��setter)�Apos���P���m�A�̦hMAXPLAYER�H�Ap�h��Player instance�C
    	getPlayer[pos]=p;
    }
    public Player[] get_player(){//�^�ǩҦ��b�P��W��player�A�N�Y�^�ǫ��O��Player[]���ܼ�(instance field)�ܼơA��getter
    	
    	return getPlayer;
    }
    public void set_dealer(Dealer d){
    	getDealer=d;
    } //�NDealer���P��W (�N�Y�NDealer��� ���O��Dealer ���ܼ�(instance field) ���A���ܼƤ�setter)�C

    public Card get_face_up_card_of_dealer(){//�����a��
		return getDealer.getOneRoundCard().get(1);//�^��dealer���}�����i�P�A�]�N�O�ĤG�i�P
    	
    }
    
    private void ask_each_player_about_bets(){
    	for(int i=0;i<MAXPLAYER;i++){
    	
    		getPlayer[i].sayHello();//�I�sPlayer.java��sayHello()
    		getPlayer[i].makeBet();//�I�sPlayer.java��makeBet()
    		pos_betArray[i]=getPlayer[i].makeBet();}//�C�Ӫ��a�U���`�n�s�bpos_betArray�Ѥ���ϥ�
    }
    private void distribute_cards_to_dealer_and_players(){
    	for(int i=0;i<MAXPLAYER;i++){
    		ArrayList<Card> a=new ArrayList<Card>();
    		a.add(getDeck.getOneCard(true));//���o��i���}���P�����a
    		a.add(getDeck.getOneCard(true));
    		getPlayer[i].setOneRoundCard(a);//�A�@�i�\�۪��P(�qPerson.java��setOneRoundCard)
    	}ArrayList<Card> a=new ArrayList<Card>();
    	a.add(getDeck.getOneCard(false));
		a.add(getDeck.getOneCard(true));
    	getDealer.setOneRoundCard(a);//�@�i���}���P�����a(�qPerson.java��setOneRoundCard)
    	
    	System.out.println("Dealer's face up card is");
    	
    	get_face_up_card_of_dealer().printCard();
    }
    private void ask_each_player_about_hits(){//�ݨC�Ӫ��a�n���n�P
    	boolean hit=false;
    	for(int i=0;i<MAXPLAYER;i++){
    	do{
			hit=getPlayer[i].hitMe(); //�qPlayer.java��hitMe()
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
    		
    	    	if(getDealer.getTotalValue()>21 && getPlayer[i].getTotalValue()>21){//�p�G��쪱�a���P���j��21(�z�LPlayer��getTotalValue�Ӻ�)
    				System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
    			}else if(getDealer.getTotalValue()<=21&&getPlayer[i].getTotalValue()>21){//�p�G���a1���P�p�󵥩�A���a2���P�j��21(�z�LPlayer��getTotalValue�Ӻ�)
    				getPlayer[i].increaseChips(-getPlayer[i].makeBet());
    				System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
    			}else if(getDealer.getTotalValue()>21&&getPlayer[i].getTotalValue()<=21){//�p�G���a2���P���p�󵥩�A���a1���P�j��21(�z�LPlayer��getTotalValue�Ӻ�)
    				getPlayer[i].increaseChips(getPlayer[i].makeBet());
    				System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
    			}else if(getDealer.getTotalValue()>getPlayer[i].getTotalValue()&&getDealer.getTotalValue()<=21){//�p�G���a1���P�j�󪱮a2���P�B���a1���P�p�󵥩�21(�z�LPlayer��getTotalValue�Ӻ�)
    				getPlayer[i].increaseChips(-getPlayer[i].makeBet());
    				System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
    			}else if(getDealer.getTotalValue()<getPlayer[i].getTotalValue()&&getPlayer[i].getTotalValue()<=21){//�p�G���a1���P�p�󪱮a2���P�A�B���a2���P�p�󵥩�21(�z�LPlayer��getTotalValue�Ӻ�)
    				getPlayer[i].increaseChips(getPlayer[i].makeBet());
    				System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
    				
    				//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
    			}else{
    				System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
    			}
    	   	
    	}
    	
    	/*for(int i=0;i<MAXPLAYER;i++){
    	if(getDealer.getTotalValue()>21 && getPlayer[i].getTotalValue()>21){//�p�G��쪱�a���P���j��21(�z�LPlayer��getTotalValue�Ӻ�)
			System.out.println(",chips have no change! The Chips now is: "+getPlayer[i].getCurrentChips());
		}else if(getDealer.getTotalValue()<=21&&getPlayer[i].getTotalValue()>21){//�p�G���a1���P�p�󵥩�A���a2���P�j��21(�z�LPlayer��getTotalValue�Ӻ�)
			getPlayer[i].increaseChips(-getPlayer[i].makeBet());
			System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
		}else if(getDealer.getTotalValue()>21&&getPlayer[i].getTotalValue()<=21){//�p�G���a2���P���p�󵥩�A���a1���P�j��21(�z�LPlayer��getTotalValue�Ӻ�)
			getPlayer[i].increaseChips(getPlayer[i].makeBet());
			System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
		}else if(getDealer.getTotalValue()>getPlayer[i].getTotalValue()&&getDealer.getTotalValue()<=21){//�p�G���a1���P�j�󪱮a2���P�B���a1���P�p�󵥩�21(�z�LPlayer��getTotalValue�Ӻ�)
			getPlayer[i].increaseChips(-getPlayer[i].makeBet());
			System.out.println("Loss"+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(-getPlayer[i].makeBet());//-p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
		}else if(getDealer.getTotalValue()<getPlayer[i].getTotalValue()&&getPlayer[i].getTotalValue()<=21){//�p�G���a1���P�p�󪱮a2���P�A�B���a2���P�p�󵥩�21(�z�LPlayer��getTotalValue�Ӻ�)
			getPlayer[i].increaseChips(getPlayer[i].makeBet());
			System.out.println(",Get "+getPlayer[i].makeBet()+" Chips, the Chips now is: "+getPlayer[i].getCurrentChips());
			
			//getPlayer[i].increaseChips(getPlayer[i].makeBet());//p1Bet(��J��)�]��Player��increaseChips(�w�X���ܰ�)
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
