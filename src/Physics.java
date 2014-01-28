
public class Physics {
	int nballs=16;
	private int hR=16;
	private int nBallsOn;
	
	public Physics(Ball[] balls, double[] holesX, double[] holesY,int numberOfPocket){
		collisions(balls,holesX,holesY,numberOfPocket);
	}
	
    public void collisions(Ball[] balls, double[] holesX, double[] holesY, int numberOfPocket) {
        borderCollision(balls,holesX,holesY);
        holesCollision(balls,holesX,holesY,numberOfPocket);
        ballsCollision(balls);
    }
    
    public void borderCollision(Ball[] balls, double[] holesX, double[] holesY) {
        
        for (int i=0; i<nballs; ++i) if (balls[i].isOnTable()) {
           
            if (balls[i].getNextX()-balls[i].getR()<holesX[0]) {
                balls[i].setNextX(holesX[0] + balls[i].getR());
                balls[i].setVx(balls[i].getVx()*(-1));
            }
            else if (balls[i].getNextX()+balls[i].getR()>holesX[2]){
                balls[i].setNextX(holesX[2]-balls[i].getR());
                balls[i].setVx(balls[i].getVx()*(-1));
            }
           
            if (balls[i].getNextY()-balls[i].getR()<holesY[0]) {
                balls[i].setNextY(holesY[0] + balls[i].getR());
                balls[i].setVy(balls[i].getVy()*(-1));
            } 
            else if (balls[i].getNextY()+balls[i].getR()>holesY[1]) {
                balls[i].setNextY(holesY[1] - balls[i].getR());
                balls[i].setVy(balls[i].getVy()*(-1));
            }
        }
    }
    
    public void holesCollision(Ball[] balls, double[] holesX, double[] holesY, int numberOfPocket) {
 
     //toplarýn deliðe girme durumlarý ayarlanýyor...
        for (int ball = 0; ball < nballs; ++ball) if (balls[ball].isOnTable()) {
        if(numberOfPocket == 6){
            for (int iHX=0; iHX<3; ++iHX) {
                for (int iHY=0; iHY<2; ++iHY) {
                    if (Math.hypot(holesX[iHX]-balls[ball].getX(), holesY[iHY]-balls[ball].getY())<this.hR) {
                        balls[ball].setOnTable(false);
                        if (ball!=0)setnBallsOn(getnBallsOn()-1);
                        balls[ball].setVx(0);
                        balls[ball].setVy(0);
                    }
                }
            }
        }else{
        	 for (int iHX=0; iHX<3;) {
                for (int iHY=0; iHY<2; ++iHY) {
                    if (Math.hypot(holesX[iHX]-balls[ball].getX(), holesY[iHY]-balls[ball].getY())<this.hR) {
                        balls[ball].setOnTable(false);
                        if (ball!=0)setnBallsOn(getnBallsOn()-1);
                        balls[ball].setVx(0);
                        balls[ball].setVy(0);
                    }
                }
                iHX=iHX+2;
            }
        	
        }
      }        
    }
    
    public void ballsCollision(Ball[] balls) {
        for (int ball1=0; ball1<nballs; ++ball1) if (balls[ball1].isOnTable()){
            for (int ball2=ball1+1; ball2<nballs; ++ball2) if (balls[ball2].isOnTable()){
                boolean collision;
                if(collision = isBallsCollision(balls[ball1], balls[ball2])){ 
            
                    // Adjust position
                    int cont = 0;
                    while (cont <10 && collision){
                        balls[ball1].setNextX((balls[ball1].getNextX() + balls[ball1].getX()) / 2);
                        balls[ball1].setNextY((balls[ball1].getNextY() + balls[ball1].getY()) / 2);
                        
                        balls[ball2].setNextX((balls[ball2].getNextX() + balls[ball2].getX()) / 2);
                        balls[ball2].setNextY((balls[ball2].getNextY() + balls[ball2].getY()) / 2);
                        
                        collision = isBallsCollision(balls[ball1], balls[ball2]);
                        
                        ++cont;
                    }
                    
                    if (collision) {
                        balls[ball1].setNextX(balls[ball1].getX());
                        balls[ball1].setNextY(balls[ball1].getY());
                      
                        balls[ball2].setNextX(balls[ball2].getX());
                        balls[ball2].setNextY(balls[ball2].getY());
                    }

                    // Adjust velocities
                    double dx = balls[ball2].getNextX() - balls[ball1].getNextX();
                    double dy = balls[ball2].getNextY() - balls[ball1].getNextY();
                    double dist = Math.hypot(balls[ball1].getNextX()-balls[ball2].getNextX(), balls[ball1].getNextY()-balls[ball2].getNextY());
                    double cos = dx/dist;
                    double sin = dy/dist;
                    
                    double mass1 = balls[ball1].getMass();
                    double mass2 = balls[ball2].getMass();
                    
                    double v_1 = balls[ball1].getVx() * cos + balls[ball1].getY() * sin ;
                    double v_2 = balls[ball2].getVx() * cos + balls[ball2].getY() * sin ;
                    
                    double w_1 = ((mass1 - mass2) * v_1 + 2 * mass2 * v_2) / (mass1 + mass2);
            		double w_2 = ((mass2 - mass1) * v_2 + 2 * mass1 * v_1) / (mass1 + mass2);
                    
                    balls[ball2].setNextVx(balls[ball2].getVx() +(- balls[ball2].getVx() * cos + w_2 )* cos);
                    balls[ball2].setNextVx(balls[ball2].getNextVx() - balls[ball2].getVy() * cos * sin);
                    balls[ball2].setNextVx(balls[ball2].getNextVx() + balls[ball1].getVx() * cos * cos);
                    balls[ball2].setNextVx(balls[ball2].getNextVx() +balls[ball1].getVy() * cos * sin);       
                    
                    balls[ball2].setNextVy(balls[ball2].getVy() +(- balls[ball2].getVy() * sin + w_2)* sin);
                    balls[ball2].setNextVy(balls[ball2].getNextVy() - balls[ball2].getVx() * cos * sin);
                    balls[ball2].setNextVy(balls[ball2].getNextVy() +balls[ball1].getVx() * cos * sin);
                    balls[ball2].setNextVy(balls[ball2].getNextVy() + balls[ball1].getVy() * sin * sin);
                    
                    balls[ball1].setNextVx(balls[ball1].getVx() +(- balls[ball1].getVx() * cos + w_1) * cos);
                    balls[ball1].setNextVx(balls[ball1].getNextVx() - balls[ball1].getVy() * cos * sin);
                    balls[ball1].setNextVx(balls[ball1].getNextVx() + balls[ball2].getVx() * cos * cos);
                    balls[ball1].setNextVx(balls[ball1].getNextVx() + balls[ball2].getVy() * cos * sin);        
                    
                    balls[ball1].setNextVy(balls[ball1].getVy() +(- balls[ball1].getVy() * sin + w_1)* sin);
                    balls[ball1].setNextVy(balls[ball1].getNextVy()- balls[ball1].getVx() * cos * sin);
                    balls[ball1].setNextVy(balls[ball1].getNextVy() + balls[ball2].getVx() * cos * sin);
                    balls[ball1].setNextVy(balls[ball1].getNextVy() + balls[ball2].getVy() * sin * sin);
                    
                    balls[ball1].setVx(balls[ball1].getNextVx());
                    balls[ball1].setVy(balls[ball1].getNextVy());
                    
                    balls[ball2].setVx(balls[ball2].getNextVx());
                    balls[ball2].setVy(balls[ball2].getNextVy());
                }
            }
        }
    }
    
    public boolean isBallsCollision(Ball b1, Ball b2) {
        return Math.hypot(b1.getNextX()-b2.getNextX(), b1.getNextY()-b2.getNextY()) < b1.getR()+b2.getR();
    }
    
	public int getnBallsOn() {
		return nBallsOn;
	}

	public void setnBallsOn(int nBallsOn) {
		this.nBallsOn = nBallsOn;
	}
    
 
}
