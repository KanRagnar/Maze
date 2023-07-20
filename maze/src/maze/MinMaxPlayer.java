package maze;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {
	
		ArrayList<Integer[]> path=new ArrayList<Integer[]> ();   //киста ле пимайес тупоу INTEGER[]
		int minAwareness=0;										//калбамеи то тиLEID тоу амтипакоу
		int NearSupplies;										//апостасг апо йомтимо ежодио
		int OpponentDist;										//апостасг апо евхяо
		
		
		public MinMaxPlayer() {
			super();
			path=null;
			minAwareness=0;
			NearSupplies=0;
			OpponentDist=0;
		}
		public MinMaxPlayer(ArrayList<Integer[]> path,int minAwareness,int NearSupplies, int OpponentDist){
			super();
			this.path=path;
			this.minAwareness=minAwareness;
			this.NearSupplies=NearSupplies;
			this.OpponentDist=OpponentDist;
			
		}
		public MinMaxPlayer(int PlayerId, String name, int Score,Board board, int x, int y,ArrayList<Integer[]> path,int minAwareness, int NearSupplies, int OpponentDist) {
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
		
		double f(int NearOpponent, int NearSupplies) {			//сумаятгсг циа бахлокоцгсг йимгсеым ле басг апостасеис апо та ежодиа йаи том евхяо
			double goal=0;		//циа та ежодиа
			double enemy=0;		//циа том амтипако
			if(NearSupplies==0)			//осо пио йомта то ежодио тосо пио йакг г йимгсг, 0 ейтос ояасгс йаи -10 богхгтийо
				goal=0;
			if(NearSupplies==1)
				goal=1;
			if(NearSupplies==2)
				goal=0.5;
			if(NearSupplies==3)
				goal=0.3;
			if(NearSupplies==-10)
				goal=-0.1;
			if(NearOpponent==1)					//осо пио йомта о евхяос тосо лецакутеяос о аямгтийос сумтекестгс, 4 ейтос ояасгс
				enemy=1;
			if(NearOpponent==2)
				enemy=0.5;
			if(NearOpponent==3)
				enemy=0.3;
			if(NearOpponent==4)
				enemy=0;
			return goal*0.46 -enemy*0.54;
		}

		public double evaluate(int currentPos, int dice, Board board) { 		//бахлокоцеи лиа йимгсг
			int i=0;
			setNearSupplies(0);				//аявийопоиеи тис сумхгйес
			int nearOpponent=4;
			if(dice==1) {						//енетафетаи поиа йимгсг еимаи йаи стгм сумевеиа тгм амапаяастаметаи 3 жояес ежосом дем упаявеи теивос
												//йяатымтас емдеволемыс тгм апостасг апо йапоио ежодио ╧ том идио том амтипако
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
					else if(i==0)						//се пеяиптысг поу г йимгсг одгцеи йатеухеиам се теиво вягсилопоиеи то богхгла стгм паяапамы сумаятгсг
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
			if((getDice()==dice+2 || getDice()==dice-2) && getNearSupplies()==0) //дем ажгмеи том паийтг ма йамеи писы бгла ежосом дем упаявеи ежодио пяос ейеимг тгм йатеухумсг
				setNearSupplies(-10);
			return f(nearOpponent,NearSupplies);   			//епистяежеи тгм бахлокоциа
	}
		
		
		int chooseMinMaxMove(Node root) {					//епикецеи тгм бектистг йимгсг ле басг том акцояихло MINMAX
			double theChosen=-10;
			int k=0, a=0;
			for(Node child : root.children) {					//циа йахе пихамг йимгсг тоу хгсеа
				double difference;
				difference=child.nodeEvaluation-child.children.get(0).nodeEvaluation;		//аявийопоигсг летабкгтгс ле басг то пяыто семаяио
				for(int j=1; j<child.children.size() ; j++) {								//г апамтгсг тоу лимотауяоу
					if(child.nodeEvaluation-child.children.get(j).nodeEvaluation  >  difference)		//бяисйеи то текийо апотекесла циа суцйейяилемг йимгсг тоу хгсеа
						difference=child.nodeEvaluation-child.children.get(j).nodeEvaluation;
				}
				if(theChosen<difference) {				//бяисйеи тгм йакутеяг йимгсг тоу хгсеа
					theChosen=difference;
					a=k;
				}
				k++;
			}
			return root.children.get(a).nodeMove[2];
		}
		
		public int getNextMove(int currentPos, int OpponentCurrentPos) {
			setOpponentDist(4);
			int k=1;
			int bestMove=(int)Math.floor((Math.random()*4)+1);				//йамеи аявийа туваиа тгм йимгсг
			int ScoreCheck=getScore();
			
			Node root= new Node(getBoard());								//дглиоуяцеи тг яифа тоу демтяоу
			createMySubtree(currentPos, OpponentCurrentPos, root, 1);		//дглиоуяцеи око то демтяо
			
			bestMove=chooseMinMaxMove(root);								//диакецеи тг йакутеяг йимгсг ле басг то демтяо
			setDice(bestMove);
			
			evaluate(currentPos, getDice(), getBoard());					//епамакгьг циа тгм епикоцг тым стоивеиым тгс йимгсгс
			
			int[] c=move(currentPos);																//йамеи тгм йимгсг
			System.out.println(getName()+" moved to: ID:" + c[0] + " X:" + c[1]+ " Y:"+c[2]); 			//тупымеи тгм йимгсг
			if(ScoreCheck==getScore())																//екецвеи еам аунгхгйе то сйоя
				k=0;
			Integer[] a={bestMove, k, getNearSupplies(), getOpponentDist()};				//еисацеи стг киста PATH
			path.add(a);																	//стоивеиа циа тгм йимгсг 
			return getX()*board.getN()+getY();
			
		}
		
		void createMySubtree(int currentPos, int OpponentPos, Node Root, int depth) { 					//дглиоуяцеи демтяо циа том акцояихло MINMAX
			if(Root.getNodeBoard().getTiles()[currentPos].isUp()==false) {			//еам дем упаявеи теивос циа том хгсеа
				int[] a=new int[] {(currentPos/ Root.getNodeBoard().getN()), (currentPos%Root.getNodeBoard().getN()), 1};   //дглияоуцеи то NODEMOVE
				Node child=new Node(Root, 1,a, Root.getNodeBoard() );														//дглиоуяцеи амтийеилемо тупоу NODE-йимгсг хгсеа
				child.setNodeEvaluation(evaluate(currentPos,1,child.getNodeBoard()));										//упокоцифеи тг бахлокоциа тгс йимгсгс
				Root.children.add(child);																					//топохетеи то NODE сто демтяо йаты апо тг яифа
				createOpponentTree(currentPos,OpponentPos,child,  depth+1, child.getNodeEvaluation());						//KAкеи тгм паяайаты сумаятгсг
			}
			if(Root.getNodeBoard().getTiles()[currentPos].isRight()==false) {
				int[] a=new int[] {(currentPos/ Root.getNodeBoard().getN()), (currentPos%Root.getNodeBoard().getN()), 2}; 
				Node child=new Node(Root, 1,a, Root.getNodeBoard() );
				child.setNodeEvaluation(evaluate(currentPos,2,child.getNodeBoard()));
				Root.children.add(child);
				createOpponentTree(currentPos,OpponentPos,child,  depth+1, child.getNodeEvaluation());
			}
			if(Root.getNodeBoard().getTiles()[currentPos].isDown()==false) {
				int[] a=new int[] {(currentPos/ Root.getNodeBoard().getN()), (currentPos%Root.getNodeBoard().getN()), 3}; 
				Node child=new Node(Root, 1,a, Root.getNodeBoard() );
				child.setNodeEvaluation(evaluate(currentPos,3,child.getNodeBoard()));
				Root.children.add(child);
				createOpponentTree(currentPos,OpponentPos,child,  depth+1, child.getNodeEvaluation());
			}
			if(Root.getNodeBoard().getTiles()[currentPos].isLeft()==false) {
				int[] a=new int[] {(currentPos/ Root.getNodeBoard().getN()), (currentPos%Root.getNodeBoard().getN()), 4}; 
				Node child=new Node(Root, 1,a, Root.getNodeBoard() );
				child.setNodeEvaluation(evaluate(currentPos,4,child.getNodeBoard()));
				Root.children.add(child);
				createOpponentTree(currentPos,OpponentPos,child,  depth+1, child.getNodeEvaluation());
			}
		}
		
		void createOpponentTree(int currentPos,int OpponentPos, Node parent ,int depth, double parentEval) {		//дглиоуяцеи то 2О епипедо тоу демтяоу
			int NextOpponentPos;
			if(parent.getNodeBoard().getTiles()[OpponentPos].isUp()==false) {										//еам дем упаявеи теивос циа том лимотауяо
				NextOpponentPos=OpponentPos+parent.getNodeBoard().getN();															//пяосолоиафеи тгм йимгсг 
				setMinAwareness(NextOpponentPos);
				int[] b=new int[] {(OpponentPos/ parent.getNodeBoard().getN()), (OpponentPos%parent.getNodeBoard().getN()), 1};		//дглиоуяцеи TO NODEMOVE
				Node grandchild=new Node(parent, 2, b, parent.getNodeBoard());										//дглиоуяцеи амтийеилемо NODE-йимгсг лимотауяоу
				grandchild.setNodeEvaluation(parent.nodeEvaluation- evaluate(currentPos, parent.getNodeMove()[2], parent.getNodeBoard()));	//упокоцифеи тг бахлокоциа тгс йимгсгс тоу хгсеа
				parent.children.add(grandchild);					//еисацеи то NODE сто демтяо
				setMinAwareness(OpponentPos);						//TEкос пяосолоиосгс
			}
			
			if(parent.getNodeBoard().getTiles()[OpponentPos].isRight()==false) {
				NextOpponentPos=OpponentPos+1;
				int[] b=new int[] {(OpponentPos/ parent.getNodeBoard().getN()), (OpponentPos%parent.getNodeBoard().getN()), 2};
				Node grandchild=new Node(parent, 2, b, parent.getNodeBoard());
				grandchild.setNodeEvaluation(parent.nodeEvaluation- evaluate(currentPos, parent.getNodeMove()[2], parent.getNodeBoard()));
				parent.children.add(grandchild);
				setMinAwareness(OpponentPos);
			}
			if(parent.getNodeBoard().getTiles()[OpponentPos].isDown()==false) {
				NextOpponentPos=OpponentPos-parent.getNodeBoard().getN();
				int[] b=new int[] {(OpponentPos/ parent.getNodeBoard().getN()), (OpponentPos%parent.getNodeBoard().getN()), 3};
				Node grandchild=new Node(parent, 2, b, parent.getNodeBoard());
				grandchild.setNodeEvaluation(parent.nodeEvaluation- evaluate(currentPos, parent.getNodeMove()[2], parent.getNodeBoard()));
				parent.children.add(grandchild);
				setMinAwareness(OpponentPos);
			}
			if(parent.getNodeBoard().getTiles()[OpponentPos].isLeft()==false) {
				NextOpponentPos=OpponentPos-1;
				int[] b=new int[] {(OpponentPos/ parent.getNodeBoard().getN()), (OpponentPos%parent.getNodeBoard().getN()), 4};
				Node grandchild=new Node(parent, 2, b, parent.getNodeBoard());
				grandchild.setNodeEvaluation(parent.nodeEvaluation- evaluate(currentPos, parent.getNodeMove()[2], parent.getNodeBoard()));
				parent.children.add(grandchild);
				setMinAwareness(OpponentPos);
			}
		}
		
		
		public void statistics() {								//ейтупымеи та стоивеиа поу суцйяатгхгйам циа окес тис йимгсеис опыс йаи леяийа сумокийа статистийа
			
			int up=0,down=0,left=0,right=0,i=1, k=0;
			for(Integer[] stats : path) {		//паиямеи йахе йимгсг
				
				if(stats[0]==1)					//йяатаеи статистийа циа то ти еяине сто фаяи о паийтгс
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
					System.out.println(" supply.");			//пяосаялофеи емийо-пкгхумтийо
				else 
					System.out.println(" supplies.");
				
																									//апостасг апо ежодио
				if(stats[2]==0)
					System.out.println("He couldn't see any supplies.");							
				else if(stats[2]==1)
					System.out.println("He got a Supply");
				else
					System.out.println("He was "+ (stats[2]-1)+ " tiles away from a supply.");
				
				
				if(stats[3]==4)																		//апостасг апо евхяо
					System.out.println("He didn't sense the enemy near.");
				else
					System.out.println("He was "+ stats[3]+ " tiles away from the enemy.");
				
				
		}
			System.out.println(getName()+ " rolled 1 on the dice: "+ up+ " times");					//сумокийес жояес поу паивтгйе йахе йимгсг
			System.out.println(getName()+ " rolled 2 on the dice: "+ right+ " times");
			System.out.println(getName()+ " rolled 3 on the dice: "+ down+ " times");
			System.out.println(getName()+ " rolled 4 on the dice: "+ left+ " times");
			
		}
	}

