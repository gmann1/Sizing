package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicComponent extends Component implements Displayable
{
	protected Image baseImage;
	protected Image currentImage;
	protected double x;
	protected double y;
	
	public BasicComponent(Image image, int x, int y)
	{
		this.baseImage = image;
		this.currentImage = image;
		this.x = x;
		this.y = y;
	}
	
	public BasicComponent(String imagePath, int x, int y) throws SlickException
	{
		this(new Image(imagePath), x, y);
	}
	
	public BasicComponent(String imagePath, int x, int y, int width, int height)
			throws SlickException
	{
		this((new Image(imagePath)).getScaledCopy(width, height), x, y);
	}
	
	public BasicComponent(String imagePath, Rectangle bounds)
			throws SlickException
	{
		this(imagePath, bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public BasicComponent(String imagePath, Point primarySelectionLocation)
			throws SlickException
	{
		this(new Image(imagePath), primarySelectionLocation.x,
				primarySelectionLocation.y);
	}
	
	public BasicComponent(Image image, Point primarySelectionLocation)
	{
		this(image, primarySelectionLocation.x, primarySelectionLocation.y);
	}
	
	public void draw(Graphics graphics)
	{
		if (this.currentImage != null)
			graphics.drawImage(currentImage, (int) x, (int) y);
	}
	
	public Image getCurrentImage()
	{
		return currentImage;
	}
	
	public void setCurrentImage(Image currentImage)
	{
		this.baseImage = currentImage;
		this.currentImage = currentImage;
	}
	
	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public Image getBaseImage()
	{
		return baseImage;
	}
	
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(getX(), getY(), this.currentImage.getWidth(),
				this.currentImage.getHeight());
	}
	
	@Override
	public boolean resize(int width, int height)
	{
		this.currentImage = baseImage.getScaledCopy(width, height);
		
		// Operation was successful if no errors were thrown
		return true;
	}
	
	@Override
	public void setX(int x)
	{
		this.x = x;
	}
	
	@Override
	public void setY(int y)
	{
		this.y = y;
	}
	
}
