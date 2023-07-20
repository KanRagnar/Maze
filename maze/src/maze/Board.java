package maze;
import java.util.Random;

public class Board {
	Random rand=new Random();  			//циа тгм ежаялоцг туваиым епикоцым
	int N;								//диастасеис тоу BOARD
	int S;								//Aяихлос ежодиым
	int W;								//аяихлос теивым
	Tile[] tiles;						//пимайас тупоу TILE циа ма дглиоуяцгхеи г писта
	Supply[] supplies;					//пимайас тупоу SUPPLY циа ока та ежодиа
	
	public Board() {			//йемос CONSTRUCTOR
		N=0;
		S=0;
		W=0;
		tiles=new Tile[0];
		supplies=new Supply[0];
	}
	
	public Board(int N,int S,int W) {		//CONSTRUCTOR ле ояислата циа аявийес тилес
		this.N=N;
		this.S=S;
		this.W=W;
		tiles=new Tile[N*N];
		supplies=new Supply[W];
	}
	
	public Board(Board B) {					//CONSTRUCTOR поу деветаи ыс ояисла BOARD йаи таутифеи тис летабкгтес
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
	
	void createTile() {				//дглиоуяцеи та TILES йаи пяосхетеи тоус тоивоус се лиа йкасг BOARD
		int Wallcounter=2*getN();								//KATAMETRHTHс теивым
		for(int i=0; i<getN()*getN() ; i++) {					//Aявийопоиеи та стоивеи тоу пимайа
			tiles[i]=new Tile(i, i/getN(), i%getN(), 0 , false , false, false, false);
		}
			for(int c=0; c<getN() ; c++) {  			//дглиоуяцеи та енытеяийа теивг
				
				tiles[c].setDown(true);						//йаты пеяижяанг
				tiles[c].setK(tiles[c].getK()+1);			//аунамеи том йаталетягтг теивым тым TILES
				
				tiles[c*getN()].setLeft(true);					//Aяистеяг пеяижяанг
				tiles[c*getN()].setK(tiles[c*getN()].getK()+1);			
				
				tiles[c*getN()+getN()-1].setRight(true);			//дениа пеяижяанг
				tiles[c*getN()+getN()-1].setK( tiles[c*getN()+getN()-1].getK() + 1);
				
				tiles[getN()*getN()-c-1].setUp(true);				//амы пеяижяанг
				tiles[getN()*getN()-c-1].setK(tiles[getN()*getN()-c-1].getK() + 1);
			}
			for(int i=0; i<(getN()-1)*getN() ; i++){
				
				
						//тсейаяеи еам се суцйейяилемо TILE лпояеи ма пяосхехеи теивос ╧ цемийа
				if(tiles[i].getK() < 2    &&    tiles[i+getN()].getK() < 2 && Wallcounter<getW() ) { 
						tiles[i].setUp(rand.nextBoolean());				//туваиа топоххетгсг ояифомтиоу теивоус
						tiles[i+getN()].setDown(tiles[i].isUp());		//ежаялофетаи циа то цеитомийо TILE
						
						if(tiles[i].isUp()== true) {			//аунамеи тоус йаталетяетес теивым TILES йаи BOARD
							tiles[i].setK(tiles[i].getK() + 1);
							tiles[i+getN()].setK(tiles[i+getN()].getK() + 1);
							Wallcounter+=1;
						}
				}	
			}	

			for(int i=0; i<getN()*getN()-1 ; i++) {    //то идио циа йатайояужа теивг
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
	
			
			
			
		
	void createSupply() {			//дглиоуяцеи та SUPPLIES йаи та емсылатымеи туваиа стгм писта
		for(int s=0; s<getS() ; s++) {
			supplies[s]=new Supply(0,0,0,0);
		}
		int[] a=new int[getS()];
			for(int i=0; i<getS() ; i++) {
				a[i]=(int)Math.floor((Math.random()*getN()*getN())+1);
				while(i>0 && a[i]==a[i-1])
					a[i]=(int)Math.floor((Math.random()*getN())+0); 		//апожеуцеи тгм топохетгсг ежодиоу се гдг упаявым
				supplies[i].setSupplyId(i+1);						//омолафеи то йахе SUPPLY
				supplies[i].setSupplyTileId(a[i]);					//емглеяымеи тгм хесг тоу
				supplies[i].setX(a[i] / getN()  );					//тгм тетлглемг тоу
				supplies[i].setY(a[i] % getN()  );					//тгм тетацлемг тоу
			}
	}
	
	void createBoard() { //йакеи тис 2 паяапамы сумаятгсеис циа тгм окойкгяысг тоу BOARD
		createTile();
		createSupply();
	}
	
	
	String[][] getStringRepresantation(int TheseusTile,int MinotaurTile) { //Eпистяежеи стом ваятг йаи тис хесеис окым
		String[][] Screenshot=new String[getN()*2 + 1][getN()];   //дилиоуяцеи том пимайа ле амакоцес диастасеис 
		for(int i=0 ; i<getN()*2+1 ; i++) {			//екецвоум ока та TILES
			for(int j =0; j<getN(); j++) {
				Screenshot[i][j]="";				//аявийопоиеи йахе STRING тоу пимайа
				if(i==0 && j!=0)					//топохетеи та памы ояифомтиа теивг
					Screenshot[i][j]="---+";
				if(i==0 && j==0)
					Screenshot[i][j]="+---+";
				if(j==0 && i!=0) {			//топохетеи йатайояужа теивг стгм пяытг стгкг 
					if(i%2==1) 
						Screenshot[i][j]="|";
					else
						Screenshot[i][j]="+";
				
				}
				if(i>0 && j>=0 && i%2!=1) {        //топохетеи амакоца еам упаявеи йаты апо йахе TILE теивг
					if(tiles[(2 * getN() - i ) / 2 * getN() +j].down)
						Screenshot[i][j]=Screenshot[i][j]+"---+";
					else
						Screenshot[i][j]=Screenshot[i][j]+"   +";
				}
				if(i>=0 && j>=0 && i%2==1) {
					int b=0;  //MIA METAбкгтг циа богхеиа
						for(int a=0; a<getS() ; a++) {
							if(supplies[a].SupplyTileId==(2 * getN() - i - 1) / 2 * getN() +j && supplies[a].SupplyTileId!=0 && supplies[a].getSupplyTileId()!=MinotaurTile){
								Screenshot[i][j]=Screenshot[i][j]+" S"+(a+1);
								b++;				//топохетеи та ежодиа стом ваятг ейтос еам та йяубеи о лимотауяос апо писы г ам евоум гдг кгжхеи апо том хгсеа
							}
		}
						if((2 * getN() - i -1) / 2 * getN() +j== TheseusTile && TheseusTile!=MinotaurTile) {
							Screenshot[i][j]=Screenshot[i][j]+" T ";  //топохетеи том хгсеа стгм хесг тоу ейтос ам том ежаце о лимотауяос
							b++;
						}
						if((2 * getN() - i - 1) / 2 * getN() +j== MinotaurTile) {
							Screenshot[i][j]=Screenshot[i][j]+" M "; //топохетеи том лиматауяо стгм хесг тоу
							b++;
						}
						if(b==0)
							Screenshot[i][j]=Screenshot[i][j]+"   ";   //диалояжымеи том ваятг йатаккгка
						if(tiles[(2 * getN() - i - 1) / 2 * getN() +j].right) 
							Screenshot[i][j]=Screenshot[i][j]+"|";		//топохетеи теивг опоу вяеиафетаи
						else 
							Screenshot[i][j]=Screenshot[i][j]+" ";
					}
				}
			}
		
		
	
		
			return Screenshot; //епистяжеи том пимайа
	}
}
