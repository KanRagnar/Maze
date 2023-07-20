package maze;
import java.util.Random;

public class Board {
	Random rand=new Random();  			//��� ��� �������� ������� ��������
	int N;								//���������� ��� BOARD
	int S;								//A������ �������
	int W;								//������� ������
	Tile[] tiles;						//������� ����� TILE ��� �� ������������ � �����
	Supply[] supplies;					//������� ����� SUPPLY ��� ��� �� ������
	
	public Board() {			//����� CONSTRUCTOR
		N=0;
		S=0;
		W=0;
		tiles=new Tile[0];
		supplies=new Supply[0];
	}
	
	public Board(int N,int S,int W) {		//CONSTRUCTOR �� �������� ��� ������� �����
		this.N=N;
		this.S=S;
		this.W=W;
		tiles=new Tile[N*N];
		supplies=new Supply[W];
	}
	
	public Board(Board B) {					//CONSTRUCTOR ��� ������� �� ������ BOARD ��� �������� ��� ����������
		this.N=B.N;
		this.S=B.S;
		this.W=B.W;
		this.tiles=B.tiles;
		this.supplies=B.supplies;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getS() {
		return S;
	}

	public void setS(int s) {
		S = s;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public Supply[] getSupplies() {
		return supplies;
	}

	public void setSupplies(Supply[] supplies) {
		this.supplies = supplies;
	}
	
	void createTile() {				//���������� �� TILES ��� ��������� ���� ������� �� ��� ����� BOARD
		int Wallcounter=2*getN();								//KATAMETRHTH� ������
		for(int i=0; i<getN()*getN() ; i++) {					//A���������� �� ������� ��� ������
			tiles[i]=new Tile(i, i/getN(), i%getN(), 0 , false , false, false, false);
		}
			for(int c=0; c<getN() ; c++) {  			//���������� �� ��������� �����
				
				tiles[c].setDown(true);						//���� ���������
				tiles[c].setK(tiles[c].getK()+1);			//������� ��� ����������� ������ ��� TILES
				
				tiles[c*getN()].setLeft(true);					//A������� ���������
				tiles[c*getN()].setK(tiles[c*getN()].getK()+1);			
				
				tiles[c*getN()+getN()-1].setRight(true);			//����� ���������
				tiles[c*getN()+getN()-1].setK( tiles[c*getN()+getN()-1].getK() + 1);
				
				tiles[getN()*getN()-c-1].setUp(true);				//��� ���������
				tiles[getN()*getN()-c-1].setK(tiles[getN()*getN()-c-1].getK() + 1);
			}
			for(int i=0; i<(getN()-1)*getN() ; i++){
				
				
						//�������� ��� �� ������������ TILE ������ �� ��������� ������ � ������
				if(tiles[i].getK() < 2    &&    tiles[i+getN()].getK() < 2 && Wallcounter<getW() ) { 
						tiles[i].setUp(rand.nextBoolean());				//������ ����������� ���������� �������
						tiles[i+getN()].setDown(tiles[i].isUp());		//����������� ��� �� ��������� TILE
						
						if(tiles[i].isUp()== true) {			//������� ���� ������������ ������ TILES ��� BOARD
							tiles[i].setK(tiles[i].getK() + 1);
							tiles[i+getN()].setK(tiles[i+getN()].getK() + 1);
							Wallcounter+=1;
						}
				}	
			}	

			for(int i=0; i<getN()*getN()-1 ; i++) {    //�� ���� ��� ���������� �����
				if(tiles[i].getK() < 2   &&   tiles[i+1].getK() < 2 && Wallcounter<getW()) {
					if(i%getN()!=getN()-1) {
						tiles[i].setRight(rand.nextBoolean());
						tiles[i+1].setLeft(tiles[i].isRight());
					}
					if(tiles[i].isRight()== true) {
						tiles[i].setK(tiles[i].getK() + 1);
						tiles[i+1].setK(tiles[i+1].getK() + 1);
						Wallcounter+=1;
					}
				}
			}
		}
	
			
			
			
		
	void createSupply() {			//���������� �� SUPPLIES ��� �� ����������� ������ ���� �����
		for(int s=0; s<getS() ; s++) {
			supplies[s]=new Supply(0,0,0,0);
		}
		int[] a=new int[getS()];
			for(int i=0; i<getS() ; i++) {
				a[i]=(int)Math.floor((Math.random()*getN()*getN())+1);
				while(i>0 && a[i]==a[i-1])
					a[i]=(int)Math.floor((Math.random()*getN())+0); 		//��������� ��� ���������� ������� �� ��� �������
				supplies[i].setSupplyId(i+1);						//�������� �� ���� SUPPLY
				supplies[i].setSupplyTileId(a[i]);					//���������� ��� ���� ���
				supplies[i].setX(a[i] / getN()  );					//��� ��������� ���
				supplies[i].setY(a[i] % getN()  );					//��� ��������� ���
			}
	}
	
	void createBoard() { //����� ��� 2 �������� ����������� ��� ��� ���������� ��� BOARD
		createTile();
		createSupply();
	}
	
	
	String[][] getStringRepresantation(int TheseusTile,int MinotaurTile) { //E��������� ���� ����� ��� ��� ������ ����
		String[][] Screenshot=new String[getN()*2 + 1][getN()];   //���������� ��� ������ �� �������� ���������� 
		for(int i=0 ; i<getN()*2+1 ; i++) {			//�������� ��� �� TILES
			for(int j =0; j<getN(); j++) {
				Screenshot[i][j]="";				//����������� ���� STRING ��� ������
				if(i==0 && j!=0)					//��������� �� ���� ��������� �����
					Screenshot[i][j]="---+";
				if(i==0 && j==0)
					Screenshot[i][j]="+---+";
				if(j==0 && i!=0) {			//��������� ���������� ����� ���� ����� ����� 
					if(i%2==1) 
						Screenshot[i][j]="|";
					else
						Screenshot[i][j]="+";
				
				}
				if(i>0 && j>=0 && i%2!=1) {        //��������� ������� ��� ������� ���� ��� ���� TILE �����
					if(tiles[(2 * getN() - i ) / 2 * getN() +j].down)
						Screenshot[i][j]=Screenshot[i][j]+"---+";
					else
						Screenshot[i][j]=Screenshot[i][j]+"   +";
				}
				if(i>=0 && j>=0 && i%2==1) {
					int b=0;  //MIA META����� ��� �������
						for(int a=0; a<getS() ; a++) {
							if(supplies[a].SupplyTileId==(2 * getN() - i - 1) / 2 * getN() +j && supplies[a].SupplyTileId!=0 && supplies[a].getSupplyTileId()!=MinotaurTile){
								Screenshot[i][j]=Screenshot[i][j]+" S"+(a+1);
								b++;				//��������� �� ������ ���� ����� ����� ��� �� ������ � ���������� ��� ���� � �� ����� ��� ������ ��� ��� �����
							}
		}
						if((2 * getN() - i -1) / 2 * getN() +j== TheseusTile && TheseusTile!=MinotaurTile) {
							Screenshot[i][j]=Screenshot[i][j]+" T ";  //��������� ��� ����� ���� ���� ��� ����� �� ��� ����� � ����������
							b++;
						}
						if((2 * getN() - i - 1) / 2 * getN() +j== MinotaurTile) {
							Screenshot[i][j]=Screenshot[i][j]+" M "; //��������� ��� ��������� ���� ���� ���
							b++;
						}
						if(b==0)
							Screenshot[i][j]=Screenshot[i][j]+"   ";   //����������� ��� ����� ���������
						if(tiles[(2 * getN() - i - 1) / 2 * getN() +j].right) 
							Screenshot[i][j]=Screenshot[i][j]+"|";		//��������� ����� ���� ����������
						else 
							Screenshot[i][j]=Screenshot[i][j]+" ";
					}
				}
			}
		
		
	
		
			return Screenshot; //��������� ��� ������
	}
}
