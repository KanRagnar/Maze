package maze;

public class Supply {
	int SupplyId; 				//A������ ���������� �������
	int x,y;					//���������, ���������
	int SupplyTileId;			//������� ���������� ��������� ��� ����� ��������� �� ������
	
	public Supply() { 			//����� CONSTRUCTOR
		SupplyId=0;
		x=0;
		y=0;
		SupplyTileId=0;
}
	public Supply(int SupplyId,int x , int y , int SupplyTitleId) {   			//CONSTRUCTOR �� �������� ��� ������� �����
		this.SupplyId=SupplyId;
		this.x=x;
		this.y=y;
		this.SupplyTileId=SupplyTitleId; 
}
	public int getSupplyId() {					
		return SupplyId;
	}
	public void setSupplyId(int supplyId) {
		SupplyId = supplyId;
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
	public int getSupplyTileId() {
		return SupplyTileId;
	}
	public void setSupplyTileId(int supplyTitleId) {
		SupplyTileId = supplyTitleId;
	}
	
}