package edu.asu.voctec.minigames;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults;

public abstract class Actor implements GameDefaults
{
    public static Color textColor = Color.magenta;
	
	protected double xLocation;
	protected double yLocation;
	protected Image baseImage;
	protected Image image;
	
	public abstract void act();
	public abstract void actOnMouseClick();
	
	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
		this.baseImage = image;
	}

	public void setImage(String imagePath)
	{
		try
		{
			setImage(new Image(imagePath));
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics graphics)
	{
		if (this.image != null)
			graphics.drawImage(image, (int) xLocation, (int) yLocation);
	}
	
	public void setLocation(int xLocation, int yLocation)
	{
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
	public void setLocation(double xLocation, double yLocation)
	{
		this.xLocation = xLocation;
		this.yLocation = yLocation;
	}
	
	public void setRotation(int rotationAmount)
	{
		if (this.baseImage != null)
		{
			this.image = this.baseImage.copy();
			this.image.rotate(rotationAmount);
		}
	}

	public int getxLocation() {
		return (int) xLocation;
	}

	public void setxLocation(double xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return (int) yLocation;
	}

	public void setyLocation(double yLocation) {
		this.yLocation = yLocation;
	}
	
	public boolean checkClicked(Point relativeMouseLocation)
	{
		int minimumX = (int) this.xLocation;
		int maximumX = minimumX + image.getWidth();
		int minimumY = (int) this.yLocation;
		int maximumY = minimumY + image.getHeight();
		
		// Determine if mouse location is outside of this button object
		boolean outOfBounds =  (relativeMouseLocation.x <= minimumX ||
								relativeMouseLocation.x >= maximumX ||
								relativeMouseLocation.y <= minimumY ||
								relativeMouseLocation.y >= maximumY);
		
		return !outOfBounds;
	}
	
	public boolean intersects(Actor otherActor)
	{
		Rectangle boundingBoxA = new Rectangle 
				(this.getxLocation(), this.getyLocation(), 
				 this.image.getWidth(), this.image.getHeight());
		Rectangle boundingBoxB = new Rectangle 
				(otherActor.getxLocation(), otherActor.getyLocation(), 
				 otherActor.image.getWidth(), otherActor.image.getHeight());
		
		return boundingBoxA.intersects(boundingBoxB);
	}
}
