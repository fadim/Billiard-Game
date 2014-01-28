import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class Stick{
	// MOUSE
    private int mX;
    private int mY;
    private int mXPost;
    private int mYPost;
    private boolean clicked;
    
    // STICK
    public final int MAX_STRENGTH = 1000;
    int sL = 300;
    int actualStep = 0;
    
    public Stick(){
    	
    }
    
    public void drawStick(Graphics gBackBuffer, Ball[] balls, double[] holesX, double[] holesY) {
        
        double dist = Math.hypot(balls[0].getX()-this.mX, balls[0].getY()-this.mY);
        double dXNormalized = (this.mX-balls[0].getX())/dist;
        double dYNormalized = (this.mY-balls[0].getY())/dist;
        double strength = (this.clicked) ? strength(balls)/10 : 1;
        double x1 = balls[0].getX() + dXNormalized * (balls[0].getR()+strength);
        double x2 = balls[0].getX() + dXNormalized * (balls[0].getR()+sL+strength);
        double y1 = balls[0].getY() + dYNormalized * (balls[0].getR()+strength);
        double y2 = balls[0].getY() + dYNormalized * (balls[0].getR()+sL+strength);
        
        
        // Draw stick
        Graphics2D g2 = (Graphics2D) gBackBuffer;
        gBackBuffer.setColor(Color.BLACK);
        gBackBuffer.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        //Image img1 = Toolkit.getDefaultToolkit().getImage("stick.png");
        //g2.drawImage(img1,(int)x1, (int)y1, (int)x2, (int)y2,null);
        
   
    
        // Draw path line
        int dot = 0;
        int nDots = (this.clicked) 
            ? (int)(150.0 * (strength / MAX_STRENGTH))
            : 15;
        double step = 30;
        double xStep = step * dXNormalized;
        double yStep = step * dYNormalized;         
        
        double nextX = balls[0].getX() + this.actualStep * dXNormalized;
        double nextY = balls[0].getY() + this.actualStep * dYNormalized;
        --this.actualStep;
        this.actualStep %= step;
        
        gBackBuffer.setColor(Color.WHITE);
        for (; dot<nDots; ++dot) {
            if (nextX < holesX[0]) {
                nextX = holesX[0] - nextX;
                nextX = holesX[0] + nextX;
                xStep *= -1;
            } 
            else if (nextX > holesX[2]) {
                nextX = nextX - holesX[2];
                nextX = holesX[2]-nextX;
                xStep *= -1;
            }
            
            if (nextY < holesY[0]) {
                nextY = holesY[0]-nextY;
                nextY = holesY[0]+nextY;
                yStep *= -1;
            }
            else if(nextY > holesY[1]) {
                nextY = nextY - holesY[1];
                nextY = holesY[1] - nextY;
                yStep *=-1;
            }
            
            gBackBuffer.fillOval((int)nextX-2, (int)nextY-2, 4, 4);
            nextX -= xStep;
            nextY -= yStep;
        }
    }
    
    public double strength(Ball[] balls) {

    	if (this.clicked) {
            return Math.min(MAX_STRENGTH, 10 * Math.hypot(balls[0].getX()-this.mXPost,balls[0].getY()-this.mYPost));
        }
        else {
            return Math.min(MAX_STRENGTH, 10 * Math.hypot(this.mX-this.mXPost, this.mY-this.mYPost));
        }
    }

	public int getmX() {
		return mX;
	}

	public void setmX(int mX) {
		this.mX = mX;
	}

	public int getmY() {
		return mY;
	}

	public void setmY(int mY) {
		this.mY = mY;
	}

	public int getmXPost() {
		return mXPost;
	}

	public void setmXPost(int mXPost) {
		this.mXPost = mXPost;
	}

	public int getmYPost() {
		return mYPost;
	}

	public void setmYPost(int mYPost) {
		this.mYPost = mYPost;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
    

}
