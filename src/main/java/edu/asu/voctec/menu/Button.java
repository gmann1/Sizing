package edu.asu.voctec.menu;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.Main;

public abstract class Button// extends Image
{
	public static enum LayoutOption
	{
		CENTER_VERTICALLY,
		CENTER_HORIZONTALLY,
		TOP_LEFT_ALIGN,
		TOP_RIGHT_ALIGN,
		BOTTOM_LEFT_ALIGN,
		BOTTOM_RIGHT_ALIGN;
	}
	
	public static interface DefaultImagePaths
	{
		public static final String NEW_GAME_BUTTON = "resources/default/img/testButton.png";
		public static final String OPTIONS_BUTTON = "resources/default/img/testButton.png";
		public static final String INSTRUCTOR_CONTROL_PANEL_BUTTON = "resources/default/img/Horo - Square.png";
		public static final String BACK_BUTTON = "resources/default/img/testButton.png";
	}
	
	protected final Image baseImage; //initial image to maintain quality throughout modifications
	protected Image image; //image displayed to user
	protected Point relativeLocation; //location relative to the container/menu holding this button.
	protected Point absoluteOffset; //amount by which to offset image //Applies only to ALIGN layouts
	protected LayoutOption[] layoutOptions;
	
	//TODO update javadoc
	/**
	 * @param image				image of this button
	 * @param relativeLocation	location of this button relative to it's container
	 */
	public Button(Image image, Point relativeLocation, LayoutOption... layoutOptions)
	{
		this.baseImage = image;
		this.image = image;
		this.setRelativeLocation(relativeLocation);
		this.absoluteOffset = relativeLocation;
		this.layoutOptions = layoutOptions;
		this.format();
	}
	
	//TODO update javadoc
	/**
	 * @param imagePath			path to the desired image of this button
	 * @param relativeLocation	location of this button relative to it's container
	 * @throws SlickException	Indicates a failure to load the image
	 * @see Image#Image(String)
	 */
	public Button(String imagePath, Point relativeLocation, LayoutOption... layoutOptions) throws SlickException
	{
		this(new Image(imagePath), relativeLocation, layoutOptions);
	}
	
	public abstract void actOnMouseClick();
	
	public void format()
	{
		if (this.layoutOptions == null)
			this.layoutOptions = new LayoutOption[0];
		
		for (LayoutOption option : layoutOptions)
		{
			if (option.equals(LayoutOption.CENTER_HORIZONTALLY))
				this.centerHorizontaly();
			else if (option.equals(LayoutOption.CENTER_VERTICALLY))
				this.centerVertically();
			else
				this.align(option);
		}
	}
	
	private void align(LayoutOption layout)
	{
		if (layout == LayoutOption.TOP_LEFT_ALIGN)
			relativeLocation = new Point (0, 0);
		else if (layout == LayoutOption.TOP_RIGHT_ALIGN)
			relativeLocation = new Point (Main.getScreenDimension().width - image.getWidth(), 0);
		else if (layout == LayoutOption.BOTTOM_LEFT_ALIGN)
			relativeLocation = new Point (0, Main.getScreenDimension().height - image.getHeight());
		else if (layout == LayoutOption.BOTTOM_RIGHT_ALIGN)
			relativeLocation = new Point (Main.getScreenDimension().width - image.getWidth(), Main.getScreenDimension().height - image.getHeight());
		
		//TODO scale translation
		//offset location by the amount specified by this object
		relativeLocation.translate(this.absoluteOffset.x, this.absoluteOffset.y);
	}
	
	public void centerHorizontaly()
	{
		int relativeX = (Main.getScreenDimension().width / 2) - (image.getWidth() / 2);
		
		this.relativeLocation.setLocation(relativeX, this.relativeLocation.y);
	}
	
	public void centerVertically()
	{
		int relativeY = (Main.getScreenDimension().height / 2) - (image.getHeight() / 2);
		
		this.relativeLocation.setLocation(this.relativeLocation.x, relativeY);
	}
	
	public boolean checkClicked(Point relativeMouseLocation)
	{
		//TODO transition to circle and rectangle checking
		int minimumX = this.relativeLocation.x;
		int maximumX = minimumX + image.getWidth();
		int minimumY = this.relativeLocation.y;
		int maximumY = minimumY + image.getHeight();
		
		//TODO replace with NOR check
		return (relativeMouseLocation.x >= minimumX &&
				relativeMouseLocation.x <= maximumX &&
				relativeMouseLocation.y >= minimumY &&
				relativeMouseLocation.y <= maximumY);
	}

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

	public Image getImage()
	{
		return image;
	}
	
	public Image scale(float scale)
	{
		//scale image
		this.image = baseImage.getScaledCopy(scale);
		
		//scale position
		this.relativeLocation = new Point((int) (scale * absoluteOffset.x), (int) (scale * absoluteOffset.y));
		
		//return image
		return this.image;
	}
}
