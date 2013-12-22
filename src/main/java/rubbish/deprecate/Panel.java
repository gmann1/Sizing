package rubbish.deprecate;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Panel
{
	public static int	MAX_ROTATION	= 45;
	public static int	MAX_X			= 800;
	public static int	MIN_X			= 0;
	
	private Image		baseImage;
	private Image		currentImage;
	private int			currentRotation;
	private double		x;
	private double		y;
	
	public Panel(Image image, int x, int y)
	{
		this.baseImage = image;
		this.currentImage = image;
		this.currentRotation = 0;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics graphics)
	{
		if (this.currentImage != null)
			graphics.drawImage(currentImage, (int) x, (int) y);
		
	}
	
	public boolean moveHorizontal(int amount)
	{
		boolean success = false;
		double newPosition = this.x + amount;
		
		if (!(newPosition < MIN_X || (newPosition + this.currentImage
				.getWidth()) > MAX_X))
		{
			this.x = newPosition;
			success = true;
		}
		
		return success;
	}
	
	public boolean rotate(int degrees)
	{
		boolean successful = false;
		int rotation = normalize(this.currentRotation + degrees);
		
		if (rotation >= (360 - Panel.MAX_ROTATION)
				|| rotation <= Panel.MAX_ROTATION)
		{
			this.currentRotation = rotation;
			this.currentImage = this.baseImage.copy();
			this.currentImage.rotate(this.currentRotation);
			successful = true;
		}
		
		return successful;
	}
	
	// TODO move to utilities
	public static int normalize(int angle)
	{
		int normalizedAngle = 0;
		
		if (angle < 360 && angle >= 0)
			normalizedAngle = angle;
		else if (angle >= 360)
			normalizedAngle = angle % 360;
		else if (angle < 0)
			normalizedAngle = 360 - ((-1 * angle) % 360);
		
		return normalizedAngle;
	}
	
	public Image getBaseImage()
	{
		return baseImage;
	}
	
	public Image getCurrentImage()
	{
		return currentImage;
	}
	
	public int getCurrentRotation()
	{
		return currentRotation;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getAngle()
	{
		return Panel.normalize(this.currentRotation);
	}
}
