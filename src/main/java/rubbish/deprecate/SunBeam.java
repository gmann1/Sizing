package rubbish.deprecate;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults.ImagePaths;

public class SunBeam
{
	public static final int	MAX_Y	= 600;
	
	double					x;
	double					y;
	double					deltaX;
	double					deltaY;
	private Image			baseImage;
	private Image			image;
	
	public SunBeam(int x, int y, double deltaX, double deltaY, double angle)
			throws SlickException
	{
		this.image = new Image(ImagePaths.SUN_BEAM_MEDIUM);
		this.baseImage = this.image.copy();
		this.image.rotate((float) angle + 90);
		this.x = x;
		this.y = y;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public SunBeam(int x, int y, double speed, double angle)
			throws SlickException
	{
		this(x, y, speed * Math.cos(Math.toRadians(angle)), speed
				* Math.sin(Math.toRadians(angle)), angle);
	}
	
	public SunBeam(int x, int y, int targetX, int targetY, double speed)
			throws SlickException
	{
		this(x, y, speed, Math.toDegrees(Math.atan2(targetY - y, targetX - x)));
	}
	
	public boolean move()
	{
		this.x += this.deltaX;
		this.y += this.deltaY;
		
		if (this.y > MAX_Y)
			return true;
		else
			return false;
	}
	
	public Image getBaseImage()
	{
		return baseImage;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getDeltaX()
	{
		return deltaX;
	}
	
	public double getDeltaY()
	{
		return deltaY;
	}
	
	public double getAngle()
	{
		return Panel
				.normalize((int) Math.toDegrees(Math.atan2(deltaY, deltaX)));
	}
	
	public void delete()
	{
		this.image = null;
	}
	
	public void draw(Graphics graphics)
	{
		if (image != null)
			graphics.drawImage(image, (int) x, (int) y);
	}
	
}
