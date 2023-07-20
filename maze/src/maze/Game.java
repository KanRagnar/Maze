package maze;

import java.util.ArrayList;

/*		олада 144
 		1)йАМАБОЩЯАР мИЙЭКАОР(10029, kanavourn@ece.auth.gr, 6940984968)
		2)йОУТАКИЭР цЕЧЯЦИОР(9964, koutalios@ece.auth.gr, 6975568687)
		*/
public class Game {
	int round; //йаталетягтгс цуяым тоу паивмидиоу
	
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
		Game Maze=new Game();				//дглиоуяцеи амтийеилемо тупоу GAME
		int n=0;							//йаталетяегтгс жояым поу паивтгйе то фаяи
		Board map=new Board(15, 4, (int)Math.floor((Math.random()*200)+150));  		//дглиоуяцеи амтийеилемо тупоу BOARD
																			//ME диастасеис 15в15, 4 ежодиа, аяихло теивым 150-200
		map.createBoard();				//дилиоуяцеи циа то BOARD MAP
		
		ArrayList<Integer[]> path=new ArrayList<Integer[]> ();
		MinMaxPlayer Theseus=new MinMaxPlayer(0, "Theseus", 0, map, 0, 0,path, 0,0,0);   //дглиоуяцеи том хгсеа йаи том топохетеи сто (0,0)
																	
		Player Minotaur=new Player(1, "Minotaur", 0, map, 7, 7);	//дглиоуяцеи том лимотауяо йаи том топохетеи стг лесг (7,7)
		
	
				//яивмеи то фаяи левяи ма кгнеи то паивмиди ле опоиомдгпоте тяопо
		while(n<200 && Theseus.getScore() < 4 && Theseus.getX()*map.getN()+Theseus.getY()!=Minotaur.getX()*map.getN()+Minotaur.getY() ){
			Maze.setRound(Maze.getRound()+1);					//аунамеи том цуяо тоу паивмидоу
			System.out.println(("Round: ")+Maze.getRound());		//тупымеи том цуяо
			
			Theseus.setMinAwareness(Minotaur.getX()*map.getN()+ Minotaur.getY());  			//димеи тгм хесг тоу лимотауяоу стом хгсеа
			Theseus.getNextMove(Theseus.getX()*map.getN()+Theseus.getY(),Minotaur.getX()*map.getN()+ Minotaur.getY());					//йоумаеи том хгсеа
			n++;
			
			Minotaur.RandomizeDice();
			int[] b=Minotaur.move(Minotaur.getX()*map.getN()+ Minotaur.getY());		//йоумаеи том лимотауяо йаи епистяежеи емам пимайа ле тгм йимгсг тоу
			System.out.println("Minotaur moved to: ID:" + b[0] + " X:" + b[1]+ " Y:"+b[2]);
			n++;  //тупымеи тгм йимгсг тоу лимауяоу, летяаеи тгм яинг тоу фаяиоу
			
			String[][] c=map.getStringRepresantation(Theseus.getX()*map.getN()+Theseus.getY(),Minotaur.getX()*map.getN()+ Minotaur.getY());
			for(int i=0; i<31; i++) {					//тупымеи том ваятг циа том суцйейяилемо цуяо
				for(int j=0; j<map.getN(); j++)
					System.out.print(c[i][j]);
				System.out.println();
		}
	
	

	
	

	}
		Theseus.statistics();  
		
		
		if(Theseus.getScore()==4)   		//тупымеи тгм мийг тоу хгсеа
			System.out.println("Theseus won the game!!!");
		
		else if(Theseus.getX()*map.getN()+Theseus.getY()==Minotaur.getX()*map.getN()+Minotaur.getY())  		//тгм мийг тоу лимотауяоу
			System.out.println("Theseus has been slain.\nMinotaur won the game.");
		
		else if(n==200)			//тгм исопакиа
			System.out.println("It's a draw...nobody won.");
}
}
