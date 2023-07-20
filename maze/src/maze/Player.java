package maze;

public class Player {
	int PlayerId;   		//������� ���������� ������
	String name;			//����� ������
	int Score;				//����������
	Board board;			//����������� BOARD ��� ����� ������ � �������
	int x,y;				// ���������---���������
	int dice;				//�� ���� ��� "�������" ��� �� ��������
	
	public Player() {		//����� CONSTRUCTOR
		PlayerId=0;
		name="Unknown";
		Score=0;
		x=0;
		y=0;
}
	public Player(int PlayerId, String name, int Score,Board board, int x, int y) {		//CONSTRUCTOR �� �������� ��� ������� �����
		this.PlayerId=PlayerId;
		this.name=name;
		this.Score=Score;
		this.board=board;
		this.x=x;
		this.y=y;		
	}
	
	
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPlayerId() {
		return PlayerId;
	}
	public void setPlayerId(int playerId) {
		PlayerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setDice(int dice) {
		this.dice=dice;
	}
	public int getDice() {
		return dice;
	}
	public void RandomizeDice() {
		setDice((int)Math.floor((Math.random()*4)+1));		//������ ���� �� ����� ������ ������ 1-4)
	}
	
	
	public int[] move(int id) {		//������ ������
		
		int N=board.getN();		//������� ��� �������� ��� BOARD 
								//�������� ���������� ��� ������ ������� �� ������� � ��������� ��� ��� ���� ��� ���� � �������
		int ID=id;				//������� ���������� TILE
		int X=getX();			//TETMHNH
		int Y=getY();			//����������
		int SUPPLYID=0;			//������� ���������� ��� ������� ��� ���� � �����
		
		
		if(getDice()==1) {		//��������� 1�  � ������� ��������� �� ������� ���� �� ����
			if(board.tiles[id].up==false) {		//�������� ��� ��� ������� ������
				ID=id+N;						//������� �� ID,�,� ��������� ���� �� ����� ������� �� ���� TILE
				X=id/N+1;						
				Y=id%N;
				setX(X);						//����� ��� ���������� ��������� ��� ������
				setY(Y);						//����� ��� ���������� ��������� ��� ������
			}
			else 
				System.out.println(getName() +": oh no! There is a wall");		//��� ������� ������ ��������� ������� ������		
			}
		if(getDice()==2) {
			if(board.tiles[id].right==false) {
				ID=id+1;
				X=id/N;
				Y=id%N+1;
				setX(X);
				setY(Y);
			}
			else 
				System.out.println(getName() + ": oh no! There is a wall");
		}
		if(getDice()==3) {
			if(board.tiles[id].down==false) {
				ID=id-N;
				X=id/N-1;
				Y=id%N; 
				setX(X);
				setY(Y);
			}
			else 
				System.out.println(getName() + ": oh no! There is a wall");
		}
		if(getDice()==4) {
			if(board.tiles[id].left==false) {
				ID=id-1;
				X=id/N;
				Y=id%N-1;
				setX(X);
				setY(Y);
			}
			else 
				System.out.println(getName() + ": oh no! There is a wall");
		}
		
			
		for(int i=0 ; i<board.getS() ; i++) {		//�������� ��� ������� ������ ��� �������� ��� ������������ � �����
			if(board.supplies[i].getSupplyTileId()==ID && getPlayerId()==0 && ID!=0) { //������� ��� �� ������ ���� ��� ������
				int k=getScore();
				setScore(k+1);								//������� �� ���� ��� ����
				SUPPLYID=board.supplies[i].getSupplyId();  	//���������� ��� ������ ���������� ��� �������
				board.supplies[i].setSupplyTileId(0);		//��������� �� ������ ���� �� ��� ������� ���� �����
				board.supplies[i].setX(0);
				board.supplies[i].setY(0);				
			}
		}
		int[] intArray=new int[] {ID,X,Y,SUPPLYID};
		return intArray;



}
}
