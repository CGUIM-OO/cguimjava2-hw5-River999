import java.util.ArrayList;

public  class Player extends Person {
	private String name;  //玩家姓名
	
	private int chips; //玩家有的籌碼
	
	private int bet; //玩家此局下注的籌碼 
	
	
	
	public Player(String name, int chips) {
		this.name=name;//新增Player物件時，需要姓名、擁有的籌碼等兩個參數
		this.chips=chips;
	}
	
	
	public  String getName() {
		return name;// name的getter
	}
		
	public int makeBet() {
	if(chips>0){//假如有籌碼
		bet=1;//基本下注：一次1元
		//chips=chips-bet; //剩下的籌碼=原本的籌碼-一次的賭注
	    return bet;//下注，回傳預計下注的籌碼
	
	    
	}
	else{//要檢查是否還有錢，沒錢了就不能再繼續下注
		return 0;
	}
	}
	
	
	
	
	
	
	public boolean hitMe(){
		if(getTotalValue()<=16){//16點以下要牌，17點以上不要牌
			return true;//是否要牌，是回傳true
		}
		else{
			return false;//不再要牌則回傳false
		}
		
	
	} 
	
	
	
	
	public int getCurrentChips() {
		return chips;
	}//回傳玩家現有籌碼
	
	public void increaseChips (int diff){
		chips=chips+diff;
	} // 玩家籌碼變動，setter
	
	public void sayHello(){ //玩家Say Hello
	
	System.out.println("Hello, I am " + name + ".");
	System.out.println("I have " + chips + " chips.");}

	public boolean hit_me(Table tbl) {
		int total_value = getTotalValue();
		if (total_value < 17)
			return true;
		else if (total_value == 17 && hasAce()) {
			return true;
		} else {
			if (total_value >= 21)
				return false;
			else {
				Player[] players = tbl.get_player();
				int lose_count = 0;
				int v_count = 0;
				int[] betArray = tbl.get_players_bet();
				for (int i = 0; i < players.length; i++) {
					if (players[i] == null) {
						continue;
					}
					if (players[i].getTotalValue() != 0) {
						if (total_value < players[i].getTotalValue()) {
							lose_count += betArray[i];
						} else if (total_value > players[i].getTotalValue()) {
							v_count += betArray[i];
						}
					}
				}
				if (v_count < lose_count)
					return true;
				else
					return false;
			}
		}

	}

	
	
}
