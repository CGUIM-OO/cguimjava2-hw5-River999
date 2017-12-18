import java.util.ArrayList;

public  class Player extends Person {
	private String name;  //���a�m�W
	
	private int chips; //���a�����w�X
	
	private int bet; //���a�����U�`���w�X 
	
	
	
	public Player(String name, int chips) {
		this.name=name;//�s�WPlayer����ɡA�ݭn�m�W�B�֦����w�X����ӰѼ�
		this.chips=chips;
	}
	
	
	public  String getName() {
		return name;// name��getter
	}
		
	public int makeBet() {
	if(chips>0){//���p���w�X
		bet=1;//�򥻤U�`�G�@��1��
		//chips=chips-bet; //�ѤU���w�X=�쥻���w�X-�@������`
	    return bet;//�U�`�A�^�ǹw�p�U�`���w�X
	
	    
	}
	else{//�n�ˬd�O�_�٦����A�S���F�N����A�~��U�`
		return 0;
	}
	}
	
	
	
	
	
	
	public boolean hitMe(){
		if(getTotalValue()<=16){//16�I�H�U�n�P�A17�I�H�W���n�P
			return true;//�O�_�n�P�A�O�^��true
		}
		else{
			return false;//���A�n�P�h�^��false
		}
		
	
	} 
	
	
	
	
	public int getCurrentChips() {
		return chips;
	}//�^�Ǫ��a�{���w�X
	
	public void increaseChips (int diff){
		chips=chips+diff;
	} // ���a�w�X�ܰʡAsetter
	
	public void sayHello(){ //���aSay Hello
	
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
