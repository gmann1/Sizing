package rubbish.deprecate;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults.ImagePaths;

public class Sun
{
	public static final int	MAX_X	= 800;
	private double			deltaX;
	private double			deltaY;
	private double			yAcceleration;
	private double			x;
	private double			y;
	private Image			image;
	
	public Sun(Point risingLocation, Point settingLocation, int minHeight,
			double deltaX) throws SlickException
	{
		int range = settingLocation.x - risingLocation.x;
		double numberOfFrames_yTravel = (range / deltaX) / 2;
		double numberOfFrames_yTravel_squared = Math.pow(
				numberOfFrames_yTravel, 2);
		int yDistance = risingLocation.y - minHeight;
		
		this.yAcceleration = -1
				* ((2 * yDistance) / numberOfFrames_yTravel_squared);
		this.deltaY = numberOfFrames_yTravel * this.yAcceleration;
		this.deltaX = deltaX;
		this.x = risingLocation.getX();
		this.y = risingLocation.getY();
		this.image = new Image(ImagePaths.SUN).getScaledCopy(0.25f);
	}
	
	public void draw(Graphics graphics)
	{
		if (this.image != null)
			graphics.drawImage(image, (int) x, (int) y);
		
	}
	
	public boolean move()
	{
		this.x += this.deltaX;
		this.y += this.deltaY;
		this.deltaY = this.deltaY - this.yAcceleration;
		
		if (this.x > MAX_X)
			return true;
		else
			return false;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return image;
	}
	
}
