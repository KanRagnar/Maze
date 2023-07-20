package maze;

public class Player {
	int PlayerId;   		//аяихлос таутотгтас паийтг
	String name;			//омола паийтг
	int Score;				//бахлокоциа
	Board board;			//амтийеилемо BOARD сто опоио паифеи о паийтгс
	int x,y;				// тетлглемг---тетацлемг
	int dice;				//то фаяи поу "яивмоум" циа ма йимгхоум
	
	public Player() {		//йемос CONSTRUCTOR
		PlayerId=0;
		name="Unknown";
		Score=0;
		x=0;
		y=0;
}
	public Player(int PlayerId, String name, int Score,Board board, int x, int y) {		//CONSTRUCTOR ле ояислата циа аявийес тилес
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
		setDice((int)Math.floor((Math.random()*4)+1));		//яивмеи фаяи то опоио жеямеи туваиа 1-4)
	}
	
	
	public int[] move(int id) {		//йимгсг паийтг
		
		int N=board.getN();		//паиямеи тгм диастасг тоу BOARD 
								//жтиавмеи летабкгтес тис опоиес хекоуле ма цуяисеи г сумаятгсг циа тгм хесг поу пгце о паийтгс
		int ID=id;				//аяихлос таутотгтас TILE
		int X=getX();			//TETMHNH
		int Y=getY();			//тетацлемгг
		int SUPPLYID=0;			//аяихлос таутотгтас тоу ежодиоу поу пгяе о гяыас
		
		
		if(getDice()==1) {		//пеяиптысг 1г  о паийтгс пяоспахеи ма йимгхеи пяос та памы
			if(board.tiles[id].up==false) {		//тсейаяеи еам дем упаявеи теивос
				ID=id+N;						//аунамеи то ID,в,у йатаккгка ысте ма еимаи айяибыс то памы TILE
				X=id/N+1;						
				Y=id%N;
				setX(X);						//хетеи тгм йаимоуяциа тетлглемг тоу паийтг
				setY(Y);						//хетеи тгм йаимоуяциа тетацлемг тоу паийтг
			}
			else 
				System.out.println(getName() +": oh no! There is a wall");		//еам упаявеи теивос елжамифеи амакоцо лгмула		
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
		
			
		for(int i=0 ; i<board.getS() ; i++) {		//тсейаяеи еам упаявеи ежодио сто пкайидио поу летайимгхгйе о гяыас
			if(board.supplies[i].getSupplyTileId()==ID && getPlayerId()==0 && ID!=0) { //екецвеи еам то ежодио евеи гдг паяхеи
				int k=getScore();
				setScore(k+1);								//аунамеи то сйоя тоу гяыа
				SUPPLYID=board.supplies[i].getSupplyId();  	//епистяежеи том аяихло таутотгтас тоу ежодиоу
				board.supplies[i].setSupplyTileId(0);		//лгдемифеи то ежодио ысте ма лгм упаявеи стом ваятг
				board.supplies[i].setX(0);
				board.supplies[i].setY(0);				
			}
		}
		int[] intArray=new int[] {ID,X,Y,SUPPLYID};
		return intArray;



}
}
