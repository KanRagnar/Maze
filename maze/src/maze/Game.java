package maze;

import java.util.ArrayList;

/*		����� 144
 		1)���������� ��������(10029, kanavourn@ece.auth.gr, 6940984968)
		2)��������� ��������(9964, koutalios@ece.auth.gr, 6975568687)
		*/
public class Game {
	int round; //������������ ����� ��� ����������
	
	public Game() {
		round=0;
	}
	public Game(int round) {
		this.round=round; 
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}



	public static void main(String[] args) {	// TODO Auto-generated method stub
		Game Maze=new Game();				//���������� ����������� ����� GAME
		int n=0;							//������������� ����� ��� �������� �� ����
		Board map=new Board(15, 4, (int)Math.floor((Math.random()*200)+150));  		//���������� ����������� ����� BOARD
																			//ME ���������� 15�15, 4 ������, ������ ������ 150-200
		map.createBoard();				//���������� ��� �� BOARD MAP
		
		ArrayList<Integer[]> path=new ArrayList<Integer[]> ();
		MinMaxPlayer Theseus=new MinMaxPlayer(0, "Theseus", 0, map, 0, 0,path, 0,0,0);   //���������� ��� ����� ��� ��� ��������� ��� (0,0)
																	
		Player Minotaur=new Player(1, "Minotaur", 0, map, 7, 7);	//���������� ��� ��������� ��� ��� ��������� ��� ���� (7,7)
		
	
				//������ �� ���� ����� �� ����� �� �������� �� ������������ �����
		while(n<200 && Theseus.getScore() < 4 && Theseus.getX()*map.getN()+Theseus.getY()!=Minotaur.getX()*map.getN()+Minotaur.getY() ){
			Maze.setRound(Maze.getRound()+1);					//������� ��� ���� ��� ���������
			System.out.println(("Round: ")+Maze.getRound());		//������� ��� ����
			
			Theseus.setMinAwareness(Minotaur.getX()*map.getN()+ Minotaur.getY());  			//����� ��� ���� ��� ���������� ���� �����
			Theseus.getNextMove(Theseus.getX()*map.getN()+Theseus.getY(),Minotaur.getX()*map.getN()+ Minotaur.getY());					//������� ��� �����
			n++;
			
			Minotaur.RandomizeDice();
			int[] b=Minotaur.move(Minotaur.getX()*map.getN()+ Minotaur.getY());		//������� ��� ��������� ��� ���������� ���� ������ �� ��� ������ ���
			System.out.println("Minotaur moved to: ID:" + b[0] + " X:" + b[1]+ " Y:"+b[2]);
			n++;  //������� ��� ������ ��� ��������, ������� ��� ���� ��� ������
			
			String[][] c=map.getStringRepresantation(Theseus.getX()*map.getN()+Theseus.getY(),Minotaur.getX()*map.getN()+ Minotaur.getY());
			for(int i=0; i<31; i++) {					//������� ��� ����� ��� ��� ������������ ����
				for(int j=0; j<map.getN(); j++)
					System.out.print(c[i][j]);
				System.out.println();
		}
	
	

	
	

	}
		Theseus.statistics();  
		
		
		if(Theseus.getScore()==4)   		//������� ��� ���� ��� �����
			System.out.println("Theseus won the game!!!");
		
		else if(Theseus.getX()*map.getN()+Theseus.getY()==Minotaur.getX()*map.getN()+Minotaur.getY())  		//��� ���� ��� ����������
			System.out.println("Theseus has been slain.\nMinotaur won the game.");
		
		else if(n==200)			//��� ��������
			System.out.println("It's a draw...nobody won.");
}
}
