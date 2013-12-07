package edu.asu.voctec.menu;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Button extends Image
{
	private Point relativeLocation; //location relative to the container/menu holding this button.
	
	/**
	 * @param image				image of this button
	 * @param relativeLocation	location of this button relative to it's container
	 * @see Image#Image(org.newdawn.slick.opengl.ImageData)
	 */
	public Button(Image image, Point relativeLocation)
	{
		super(image);
		this.setRelativeLocation(relativeLocation);
	}
	
	/**
	 * @param imagePath			image of this button
	 * @param relativeLocation	location of this button relative to it's container
	 * @throws SlickException	Indicates a failure to load the image
	 * @see Image#Image(String)
	 */
	public Button(String imagePath, Point relativeLocation) throws SlickException
	{
		super(imagePath);
		this.setRelativeLocation(relativeLocation);
	}
	
	public abstract void actOnMouseClick();

	/**
	 * @return	a copy of this button's relativeLocation
	 */
	public Point getRelativeLocation()
	{
		return new Point(relativeLocation.x, relativeLocation.y);
	}

	/**
	 * Set new location for this button.
	 * 
	 * @param relativeLocation	new location
	 */
	public void setRelativeLocation(Point relativeLocation)
	{
		this.relativeLocation = relativeLocation;
		//TODO verify new location
		//TODO ensure new location is on screen
	}
	
	/**
	 * @return	x location of this button relative to it's container
	 */
	public int getX()
	{
		return this.relativeLocation.x;
	}
	
	/**
	 * @return	y location of this button relative to it's container
	 */
	public int getY()
	{
		return this.relativeLocation.y;
	}
}
