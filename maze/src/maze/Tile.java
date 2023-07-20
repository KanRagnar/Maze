package maze;

public class Tile {
	int tileId;   			//аяихлос таутотгтас пкайидиоу
	int x;					//тетлглемг
	int y;					//тетацлемг
	int k;  				//летяаеи посоус теивоус евеи цуяы то пкайидио
	boolean up;				//тсейаяеи еам упаявеи теивос памы
	boolean down;			//йаты
	boolean left;			//аяистеяа
	boolean right; 			//дениа
	
	public Tile()		//йемос CONSTRUCTOR
	{
		tileId = 0;
		x = 0;
		y = 0;
		k=0;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	

		//CONSTRUCTOR ле ояислата циа аявийес тилес
	public Tile(int tileId, int x, int y,int k, boolean up, boolean down, boolean left, boolean right) {
		this.tileId = tileId;
		this.x = x;
		this.y = y;
		this.k=k;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	




	public void setTileId(int til) {
		this.tileId = til;
	}

	public int getTileId() {
		return tileId;
	}




	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}





	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	public void setK(int k) {
		this.k=k;
	}
	public int getK() {
		return k;
	}





	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isUp() {
		return up;
	}





	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isDown() {
		return down;
	}




	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isLeft() {
		return left;
	}


	



	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isRight() {
		return right;
	}


}




