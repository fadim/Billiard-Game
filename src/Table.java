
public class Table{
	
    private double[] tableX;
    private double[] tableY;
    private double[] holesX;
    private double[] holesY;
    private int numberOfPocket;
    private int frictionCoefficient;
    
    
    public Table(int width, int height){
    	initTable(width,height);
    }
    
    public void initTable(int width, int height) {
        
        this.tableX = new double[] {
          40,
          width-40
        };
        
        this.tableY = new double[] {
          this.tableX[0],
          height-this.tableX[0]
        };
        
        this.holesX = new double[] {
          this.tableX[0] + 20,
          width/2,
          this.tableX[1]-20
        };
        
        this.holesY = new double[] {
          this.tableY[0] + 20,
          this.tableY[1]-20
        };        
    }

    
	public double getTableX(int index) {
		return tableX[index];
	}
	public void setTableX(int index, double tableX) {
		this.tableX[index] = tableX;
	}
	public double getTableY(int index) {
		return tableY[index];
	}
	public void setTableY(int index, double tableY) {
		this.tableY[index] = tableY;
	}
	public double getHolesX(int index) {
		return holesX[index];
	}
	public void setHolesX(int index, double holesX) {
		this.holesX[index] = holesX;
	}
	public double getHolesY(int index) {
		return holesY[index];
	}
	public void setHolesY(int index, double holesY) {
		this.holesY[index] = holesY;
	}
	
	public int getNumberOfPocket(){
		return numberOfPocket;
	}
	
	public void setNumberOfPocket(int numberOfPocket){
		this.numberOfPocket = numberOfPocket;
	}
    
	
	public int getFrictionCoefficient(){
		return frictionCoefficient;
	}
	
	public void setFrictionCoefficient(int frictionCoefficient){
		this.frictionCoefficient = frictionCoefficient;
	}
    
   
}
