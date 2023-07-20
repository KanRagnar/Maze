package maze;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {
	
		ArrayList<Integer[]> path=new ArrayList<Integer[]> ();   //����� �� ������� ����� INTEGER[]
		int minAwareness=0;										//�������� �� ��LEID ��� ���������
		int NearSupplies;										//�������� ��� ������� ������
		int OpponentDist;										//�������� ��� �����
		
		
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

		public double evaluate(int currentPos, int dice, Board board) { 		//���������� ��� ������
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
		
		
		int chooseMinMaxMove(Node root) {					//�������� ��� �������� ������ �� ���� ��� ��������� MINMAX
			double theChosen=-10;
			int k=0, a=0;
			for(Node child : root.children) {					//��� ���� ������ ������ ��� �����
				double difference;
				difference=child.nodeEvaluation-child.children.get(0).nodeEvaluation;		//������������ ���������� �� ���� �� ����� �������
				for(int j=1; j<child.children.size() ; j++) {								//� �������� ��� ����������
					if(child.nodeEvaluation-child.children.get(j).nodeEvaluation  >  difference)		//������� �� ������ ���������� ��� ������������ ������ ��� �����
						difference=child.nodeEvaluation-child.children.get(j).nodeEvaluation;
				}
				if(theChosen<difference) {				//������� ��� �������� ������ ��� �����
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
			int bestMove=(int)Math.floor((Math.random()*4)+1);				//����� ������ ������ ��� ������
			int ScoreCheck=getScore();
			
			Node root= new Node(getBoard());								//���������� �� ���� ��� �������
			createMySubtree(currentPos, OpponentCurrentPos, root, 1);		//���������� ��� �� ������
			
			bestMove=chooseMinMaxMove(root);								//�������� �� �������� ������ �� ���� �� ������
			setDice(bestMove);
			
			evaluate(currentPos, getDice(), getBoard());					//��������� ��� ��� ������� ��� ��������� ��� �������
			
			int[] c=move(currentPos);																//����� ��� ������
			System.out.println(getName()+" moved to: ID:" + c[0] + " X:" + c[1]+ " Y:"+c[2]); 			//������� ��� ������
			if(ScoreCheck==getScore())																//������� ��� �������� �� ����
				k=0;
			Integer[] a={bestMove, k, getNearSupplies(), getOpponentDist()};				//������� ��� ����� PATH
			path.add(a);																	//�������� ��� ��� ������ 
			return getX()*board.getN()+getY();
			
		}
		
		void createMySubtree(int currentPos, int OpponentPos, Node Root, int depth) { 					//���������� ������ ��� ��� ��������� MINMAX
			if(Root.getNodeBoard().getTiles()[currentPos].isUp()==false) {			//��� ��� ������� ������ ��� ��� �����
				int[] a=new int[] {(currentPos/ Root.getNodeBoard().getN()), (currentPos%Root.getNodeBoard().getN()), 1};   //���������� �� NODEMOVE
				Node child=new Node(Root, 1,a, Root.getNodeBoard() );														//���������� ����������� ����� NODE-������ �����
				child.setNodeEvaluation(evaluate(currentPos,1,child.getNodeBoard()));										//���������� �� ���������� ��� �������
				Root.children.add(child);																					//��������� �� NODE ��� ������ ���� ��� �� ����
				createOpponentTree(currentPos,OpponentPos,child,  depth+1, child.getNodeEvaluation());						//KA��� ��� �������� ���������
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
		
		void createOpponentTree(int currentPos,int OpponentPos, Node parent ,int depth, double parentEval) {		//���������� �� 2� ������� ��� �������
			int NextOpponentPos;
			if(parent.getNodeBoard().getTiles()[OpponentPos].isUp()==false) {										//��� ��� ������� ������ ��� ��� ���������
				NextOpponentPos=OpponentPos+parent.getNodeBoard().getN();															//������������ ��� ������ 
				setMinAwareness(NextOpponentPos);
				int[] b=new int[] {(OpponentPos/ parent.getNodeBoard().getN()), (OpponentPos%parent.getNodeBoard().getN()), 1};		//���������� TO NODEMOVE
				Node grandchild=new Node(parent, 2, b, parent.getNodeBoard());										//���������� ����������� NODE-������ ����������
				grandchild.setNodeEvaluation(parent.nodeEvaluation- evaluate(currentPos, parent.getNodeMove()[2], parent.getNodeBoard()));	//���������� �� ���������� ��� ������� ��� �����
				parent.children.add(grandchild);					//������� �� NODE ��� ������
				setMinAwareness(OpponentPos);						//TE��� ������������
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

