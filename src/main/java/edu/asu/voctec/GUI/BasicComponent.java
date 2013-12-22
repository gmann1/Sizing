package edu.asu.voctec.GUI;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class BasicComponent implements Displayable
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
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public void setY(double y)
	{
		this.y = y;
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
	public void setBounds(Rectangle bounds)
	{
		this.x = bounds.x;
		this.y = bounds.y;
		this.currentImage = baseImage.getScaledCopy(bounds.width, bounds.height);
	}
	
}
