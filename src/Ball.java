import java.awt.Color;


public class Ball{
	private double r;
	private double mass;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double nextX;
	private double nextY;
	private double nextVx;
	private double nextVy;
	private boolean onTable;
	Color color = Color.BLACK;
	
    public void update(Ball ball) {       
    	if(ball.isOnTable()){
            ball.setX(ball.getNextX());
            ball.setY(ball.getNextY());        
        }
    }
	
	public Ball(){
		super();
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getVx() {
		return vx;
	}
	
	public void setVx(double vx) {
		this.vx = vx;
	}
	
	public double getVy() {
		return vy;
	}
	
	public void setVy(double vy) {
		this.vy = vy;
	}
	
	public double getNextX() {
		return nextX;
	}
	
	public void setNextX(double nextX) {
		this.nextX = nextX;
	}
	
	public double getNextY() {
		return nextY;
	}
	
	public void setNextY(double nextY) {
		this.nextY = nextY;
	}
	
	public double getNextVx() {
		return nextVx;
	}
	
	public void setNextVx(double nextVx) {
		this.nextVx = nextVx;
	}
	
	public double getNextVy() {
		return nextVy;
	}
	
	public void setNextVy(double nextVy) {
		this.nextVy = nextVy;
	}
	
	public boolean isOnTable() {
		return onTable;
	}
	
	public void setOnTable(boolean onTable) {
		this.onTable = onTable;
	}
	
	public double getR() {
		return r;
	}
	
	public void setR(double r) {
		this.r = r;
	}
	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
}
