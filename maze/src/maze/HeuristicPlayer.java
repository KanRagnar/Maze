package maze;
import java.util.*;

public class HeuristicPlayer extends Player{
	ArrayList<Integer[]> path=new ArrayList<Integer[]> ();   //����� �� ������� ����� INTEGER[]
	int minAwareness=0;										//�������� �� ��LEID ��� ���������
	int NearSupplies;										//�������� ��� ������� ������
	int OpponentDist;										//�������� ��� �����
	
	
	public HeuristicPlayer() {
		super();
		path=null;
		minAwareness=0;
		NearSupplies=0;
		OpponentDist=0;
	}
	public HeuristicPlayer(ArrayList<Integer[]> path,int minAwareness,int NearSupplies, int OpponentDist){
		super();
		this.path=path;
		this.minAwareness=minAwareness;
		this.NearSupplies=NearSupplies;
		this.OpponentDist=OpponentDist;
		
	}
	public HeuristicPlayer(int PlayerId, String name, int Score,Board board, int x, int y,ArrayList<Integer[]> path,int minAwareness, int NearSupplies, int OpponentDist) {
		super(PlayerId, name, Score, board, x,y);
		this.path=path;
		this.minAwareness=minAwareness;
		this.NearSupplies=NearSupplies;
		this.OpponentDist=OpponentDist;

	}
	public ArrayList<Integer[]> getPath() {
		return path;
	}
	public void setPath(ArrayList<Integer[]> path) {
		this.path = path;
	}
	
	public int getMinAwareness() {
		return minAwareness;
	}
	public void setMinAwareness(int minAwareness) {
		this.minAwareness = minAwareness;
	}
	public int getNearSupplies() {
		return NearSupplies;
	}
	public void setNearSupplies(int nearSupplies) {
		NearSupplies = nearSupplies;
	}
	public int getOpponentDist() {
		return OpponentDist;
	}
	public void setOpponentDist(int opponentDist) {
		OpponentDist = opponentDist;
	}
	
	double f(int NearOpponent, int NearSupplies) {			//��������� ��� ����������� �������� �� ���� ���������� ��� �� ������ ��� ��� �����
		double goal=0;		//��� �� ������
		double enemy=0;		//��� ��� ��������
		if(NearSupplies==0)			//��� ��� ����� �� ������ ���� ��� ���� � ������, 0 ����� ������ ��� -10 ���������
			goal=0;
		if(NearSupplies==1)
			goal=1;
		if(NearSupplies==2)
			goal=0.5;
		if(NearSupplies==3)
			goal=0.3;
		if(NearSupplies==-10)
			goal=-0.1;
		if(NearOpponent==1)					//��� ��� ����� � ������ ���� ����������� � ��������� �����������, 4 ����� ������
			enemy=1;
		if(NearOpponent==2)
			enemy=0.5;
		if(NearOpponent==3)
			enemy=0.3;
		if(NearOpponent==4)
			enemy=0;
		return goal*0.46 -enemy*0.54;
	}

	public double evaluate(int currentPos, int dice) { 		//���������� ��� ������
		int i=0;
		setNearSupplies(0);				//����������� ��� ��������
		int nearOpponent=4;
		if(dice==1) {						//���������� ���� ������ ����� ��� ���� �������� ��� ��������������� 3 ����� ������ ��� ������� ������
											//��������� ����������� ��� �������� ��� ������ ������ � ��� ���� ��� ��������
			while(i<3) {
				if(board.tiles[currentPos].up==false) {
					currentPos+=board.getN();
					for(int j=0 ; j<board.getS() ; j++ ) {
						if(currentPos==board.supplies[j].SupplyTileId)
							setNearSupplies(i+1);
						if(currentPos==minAwareness) {
							setOpponentDist(i+1);
							nearOpponent=i;
						}
				}
				}
				else if(i==0)						//�� ��������� ��� � ������ ������ ���������� �� ����� ������������ �� ������� ���� �������� ���������
					setNearSupplies(-10);

				i++;
			}
	}
		if(dice==2) {
			while(i<3) {
				if(board.tiles[currentPos].right==false) {
					currentPos+=1;
					for(int j=0 ; j<board.getS() ; j++ ) {
						if(currentPos==board.supplies[j].SupplyTileId)
							setNearSupplies(i+1);
						if(currentPos==minAwareness) {
							setOpponentDist(i+1);
							nearOpponent=i;
						}
				}
				}
				else if(i==0)
					setNearSupplies(-10);
				i++;
			}
		}
		if(dice==3) {
			while(i<3) {
				if(board.tiles[currentPos].down==false) {
					currentPos-=board.getN();
					for(int j=0 ; j<board.getS() ; j++ ) {
						if(currentPos==board.supplies[j].SupplyTileId)
							setNearSupplies(i+1);
						if(currentPos==minAwareness) {
							setOpponentDist(i+1);
							nearOpponent=i;
						}
				}
				}
				else if(i==0)
					setNearSupplies(-10);
				i++;
			}
		}
		if(dice==4) {
			while(i<3) {
				if(board.tiles[currentPos].left==false)
					if(board.tiles[currentPos].left==false) {
					currentPos-=1;
					for(int j=0 ; j<board.getS() ; j++ ) {
						if(currentPos==board.supplies[j].SupplyTileId)
							setNearSupplies(i+1);
						if(currentPos==minAwareness) {
							setOpponentDist(i+1);
							nearOpponent=i;
						}
					}
					}
					else if(i==0)
						setNearSupplies(-10);
				i++;
			}
		}
		if((getDice()==dice+2 || getDice()==dice-2) && getNearSupplies()==0) //��� ������ ��� ������ �� ����� ���� ���� ������ ��� ������� ������ ���� ������ ��� ����������
			setNearSupplies(-10);
		return f(nearOpponent,NearSupplies);   			//���������� ��� ����������
}
	public int getNextMove(int currentPos) {
		setOpponentDist(4);				//����������� ��� �������� 
		double ranking;					//��������� ��� ����������� ��� ��������
		int k=1;
		int ScoreCheck=getScore();									//��������� �� ���� ��� �� �������� ��� ����� ��� ��������
		int bestMove=(int)Math.floor((Math.random()*4)+1);			//�������� ������ ��� ������ ������ �� �������� ����� �� ���������� �� ��������
		int move=0;
		ArrayList <Double> posMoves=new ArrayList<Double>();		//����� ��� ��������� ��� �������� ���� �� ��� ����������� ����
		
		for(int i=1; i<5 ; i++) {									//������� ��� ����� �� ��������
			ranking=evaluate(currentPos, i);						
			posMoves.add((double)i);
			posMoves.add(ranking);
		}
		double topRanking=posMoves.get((bestMove-1)*2+1);			//��������� ��� ���������� ��� ��� ������ �������� ������
		for(int j=1 ; j<8 ; j+=2 ) {
			move++;
			if(topRanking<posMoves.get(j)) {						//��������� ��� �������� ��� ����� �������� ������ ������ � ������ ��� ���������� ����� ���������
				topRanking=posMoves.get(j);
				bestMove=move;
			}
		}
		setDice(bestMove);									//����� �� ���� ��� ��� �������� ������
		evaluate(currentPos, getDice());					//��������������� ��� ��� ����� ���� ��� ��������� ��� �� ������
		int[] c=move(currentPos);									//����� ��� ������
		System.out.println(getName()+" moved to: ID:" + c[0] + " X:" + c[1]+ " Y:"+c[2]); //������� ��� ������
		if(ScoreCheck==getScore())							//������� ��� �������� �� ����
			k=0;
		Integer[] a={bestMove, k, getNearSupplies(), getOpponentDist()};				//������� ��� ����� PATH
		path.add(a);																//�������� ��� ��� ������ 
		return getX()*board.getN()+getY();

	}
	
	
	public void statistics() {								//��������� �� �������� ��� ������������� ��� ���� ��� �������� ���� ��� ������ �������� ����������
		
		int up=0,down=0,left=0,right=0,i=1, k=0;
		for(Integer[] stats : path) {		//������� ���� ������
			
			if(stats[0]==1)					//������� ���������� ��� �� �� ����� ��� ���� � �������
				up++;
			if(stats[0]==2)
				right++;
			if(stats[0]==3)
				down++;
			if(stats[0]==4)
				left++;
			if(stats[1]==1)
				k++;
			
																										//PRINTS
			System.out.println("Round "+ i);													//ROUND
			i++;
			System.out.println(getName()+ " rolled "+ stats[0] + " this turn" );				//DICE
			System.out.print("He had collected "+ k);											//SCORE
			if(k==1)
				System.out.println(" supply.");			//����������� �����-����������
			else 
				System.out.println(" supplies.");
			
																								//�������� ��� ������
			if(stats[2]==0)
				System.out.println("He couldn't see any supplies.");							
			else if(stats[2]==1)
				System.out.println("He got a Supply");
			else
				System.out.println("He was "+ (stats[2]-1)+ " tiles away from a supply.");
			
			
			if(stats[3]==4)																		//�������� ��� �����
				System.out.println("He didn't sense the enemy near.");
			else
				System.out.println("He was "+ stats[3]+ " tiles away from the enemy.");
			
			
	}
		System.out.println(getName()+ " rolled 1 on the dice: "+ up+ " times");					//��������� ����� ��� �������� ���� ������
		System.out.println(getName()+ " rolled 2 on the dice: "+ right+ " times");
		System.out.println(getName()+ " rolled 3 on the dice: "+ down+ " times");
		System.out.println(getName()+ " rolled 4 on the dice: "+ left+ " times");
		
	}
}