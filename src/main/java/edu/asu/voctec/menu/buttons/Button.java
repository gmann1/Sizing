package edu.asu.voctec.menu.buttons;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.Main;

public abstract class Button
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
	
	/** Default location of textLabel, relative to the button object */
	public static final Rectangle DEFAULT_LABEL_POSITION = new Rectangle (5, 5, 265, 65);
	
	protected final Image baseImage; //initial image to maintain quality throughout modifications
	protected Image image; //image displayed to user
	protected LabelName labelName = null; //text to display next to the button
	protected TranslatableLabel buttonLabel; //object to display labelName text
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
	
	private void format()
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
			relativeLocation = new Point (Main.getCurrentScreenDimension().width - image.getWidth(), 0);
		else if (layout == LayoutOption.BOTTOM_LEFT_ALIGN)
			relativeLocation = new Point (0, Main.getCurrentScreenDimension().height - image.getHeight());
		else if (layout == LayoutOption.BOTTOM_RIGHT_ALIGN)
			relativeLocation = new Point (Main.getCurrentScreenDimension().width - image.getWidth(), Main.getCurrentScreenDimension().height - image.getHeight());
		
		//TODO scale translation
		//offset location by the amount specified by this object
		relativeLocation.translate(this.absoluteOffset.x, this.absoluteOffset.y);
	}
	
	public void centerHorizontaly()
	{
		int relativeX = (Main.getCurrentScreenDimension().width / 2) - (image.getWidth() / 2);
		
		this.relativeLocation.setLocation(relativeX, this.relativeLocation.y);
	}
	
	public void centerVertically()
	{
		int relativeY = (Main.getCurrentScreenDimension().height / 2) - (image.getHeight() / 2);
		
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
		
		//reposition button according to format settings
		this.format();
		
		//scale label
		scaleLabel(scale);
		
		//return image
		return this.image;
	}

	public LabelName getLabel() {
		return labelName;
	}
	
	public void scaleLabel(float scale)
	{
		//TODO add support for custom positioning
		//scale label rectangle
		Rectangle scaledLabelRectangle = getScaledRectangle(Button.DEFAULT_LABEL_POSITION, scale);
		
		//get label position relative to the screen
		Rectangle labelPosition = getTranslatedRectangle(scaledLabelRectangle, this.relativeLocation);
		
		//create the scaled label, and set it as this button's label
		if (this.labelName != null)
			this.buttonLabel = new TranslatableLabel(labelName, labelPosition);
		else
			this.buttonLabel = null;
	}

	public void setLabel(LabelName labelName)
	{
		this.labelName = labelName;
		
		//TODO account for instances where the current scale is not 1.0
		//create full sized label and set it as this button's label
		scaleLabel(1);
	}
	
	public void draw(Graphics graphics)
	{
		graphics.drawImage(this.getImage(), this.getX(), this.getY());
		if (this.buttonLabel != null)
			this.buttonLabel.render(Main.getGameContainer(), graphics);
	}
	
	//TODO move to utilities class
	public static Rectangle getTranslatedRectangle(Rectangle baseRectangle, Point translationAmount)
	{
		int x = baseRectangle.x + translationAmount.x;
		int y = baseRectangle.y + translationAmount.y;
		int width = baseRectangle.width;
		int height = baseRectangle.height;
		
		return new Rectangle(x, y, width, height);
	}
	
	//TODO move to utilities class
	public static Rectangle getScaledRectangle(Rectangle baseRectangle, float scale)
	{
		int x = (int) (baseRectangle.x * scale);
		int y = (int) (baseRectangle.y * scale);
		int width = (int) (baseRectangle.width * scale);
		int height = (int) (baseRectangle.height * scale);
		
		return new Rectangle(x, y, width, height);
	}
}
