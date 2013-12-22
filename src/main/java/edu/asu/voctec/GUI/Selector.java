package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.SelectorDefaults;
import edu.asu.voctec.utilities.CircularList;
import edu.asu.voctec.utilities.PositionedObject;
import edu.asu.voctec.utilities.UtilFunctions;

public class Selector<T extends SelectorIcon> extends Component implements
		Displayable
{
	protected SelectorDisplay<T> associatedDisplay;
	protected CircularList<T> elements = new CircularList<>();
	protected boolean orientLeft;
	
	protected PositionedObject<Image> currentChoiceBackground;
	protected PositionedObject<Image> previousChoiceBackground;
	protected PositionedObject<Image> nextChoiceBackground;
	protected PositionedObject<Image> rightArrow;
	protected PositionedObject<Image> leftArrow;
	protected PositionedObject<Image> background;
	protected int x;
	protected int y;
	
	public class CurrentChoiceListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			System.out.println("Icon Selected.");
			
			// Remove the current selection from this list,
			// and add it to the display
			sendToDisplay(elements.pop());
		}
		
		@Override
		protected boolean verify(Input input)
		{
			Rectangle absoluteBounds = getAbsoluteBounds(currentChoiceBackground);
			return verify(input, absoluteBounds);
		}
	}
	
	public class RightArrowListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			System.out.println("Cycle Right.");
			cycleRight();
		}
		
		@Override
		protected boolean verify(Input input)
		{
			Rectangle absoluteBounds = getAbsoluteBounds(rightArrow);
			return verify(input, absoluteBounds);
		}
	}
	
	public class LeftArrowListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			System.out.println("Cycle Left.");
			cycleLeft();
		}
		
		@Override
		protected boolean verify(Input input)
		{
			Rectangle absoluteBounds = getAbsoluteBounds(leftArrow);
			return verify(input, absoluteBounds);
		}
	}
	
	/**
	 * Constructs the default selector object. The size, images, and position of
	 * the new object will be determined by values in GameDefaults
	 * 
	 * @throws SlickException
	 *             Indicates that the images were unable to load.
	 * @see GameDefaults.SelectorDefaults
	 */
	public Selector() throws SlickException
	{
		currentChoiceBackground = new PositionedObject<>(new Image(
				ImagePaths.SELECTOR_LARGE),
				SelectorDefaults.PRIMARY_SELECTION_LOCATION);
		previousChoiceBackground = new PositionedObject<>(new Image(
				ImagePaths.SELECTOR_SMALL),
				SelectorDefaults.SECONDARY_SELECTION_LOCATION_LEFT);
		nextChoiceBackground = new PositionedObject<>(new Image(
				ImagePaths.SELECTOR_SMALL),
				SelectorDefaults.SECONDARY_SELECTION_LOCATION_RIGHT);
		rightArrow = new PositionedObject<>(new Image(ImagePaths.ARROW_RIGHT),
				SelectorDefaults.ARROW_LOCATION_RIGHT);
		leftArrow = new PositionedObject<>(new Image(ImagePaths.ARROW_LEFT),
				SelectorDefaults.ARROW_LOCATION_LEFT);
		background = new PositionedObject<>(new Image(
				ImagePaths.SELECTOR_SHADOW), SelectorDefaults.SHADOW_LOCATION);
		
		// Listen for clicks to the left and right arrows
		this.addActionListener(new RightArrowListener());
		this.addActionListener(new LeftArrowListener());
		
		// Listen for clicks to the primary selection
		this.addActionListener(new CurrentChoiceListener());
		System.out.println("Listener count: " + this.listeners.size());
	}
	
	public Selector(int x, int y) throws SlickException
	{
		this();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		// Draw background and arrows
		if (background != null)
			drawRelatively(graphics, background);
		if (rightArrow != null)
			drawRelatively(graphics, rightArrow);
		if (leftArrow != null)
			drawRelatively(graphics, leftArrow);
		
		// Draw choice backgrounds
		if (currentChoiceBackground != null)
			drawRelatively(graphics, currentChoiceBackground);
		if (previousChoiceBackground != null)
			drawRelatively(graphics, previousChoiceBackground);
		if (nextChoiceBackground != null)
			drawRelatively(graphics, nextChoiceBackground);
		
		// Draw choice icons
		if (elements.size() >= 1)
			drawRelatively(graphics, elements.getCurrentElement()
					.getCurrentImage(), currentChoiceBackground.x,
					currentChoiceBackground.y);
		if (elements.size() >= 2)
		{
			if (orientLeft || elements.size() <= 3)
				drawRelatively(graphics, elements.getPreviousElement()
						.getCurrentImage(), previousChoiceBackground.x,
						previousChoiceBackground.y);
			if (!orientLeft || elements.size() <= 3)
				drawRelatively(graphics, elements.getNextElement()
						.getCurrentImage(), nextChoiceBackground.x,
						nextChoiceBackground.y);
		}
	}
	
	public void cycleLeft()
	{
		elements.previous();
		this.orientLeft = false;
	}
	
	public void cycleRight()
	{
		elements.next();
		this.orientLeft = true;
	}
	
	public Rectangle getAbsoluteBounds(PositionedObject<Image> object)
	{
		return new Rectangle(object.x + x, object.y + y,
				object.data.getWidth(), object.data.getHeight());
	}
	
	public Rectangle getAbsoluteBounds(Rectangle relativeRectangle)
	{
		return UtilFunctions.getTranslatedRectangle(relativeRectangle,
				new Point(x, y));
	}
	
	public void drawRectangleRelatively(Graphics graphics, Rectangle rectangle,
			Point relativePosition, boolean filled)
	{
		if (filled)
			graphics.fillRect(rectangle.x + relativePosition.x, rectangle.y
					+ relativePosition.y, rectangle.width, rectangle.height);
		
		graphics.drawRect(rectangle.x + relativePosition.x, rectangle.y
				+ relativePosition.y, rectangle.width, rectangle.height);
	}
	
	public void drawRectangleRelatively(Graphics graphics, Rectangle rectangle,
			Point relativePosition)
	{
		drawRectangleRelatively(graphics, rectangle, relativePosition, false);
	}
	
	public void drawRelatively(Graphics graphics, PositionedObject<Image> image)
	{
		graphics.drawImage(image.data, x + image.x, y + image.y);
	}
	
	public void drawRelatively(Graphics graphics, Image image, int relativeX,
			int relativeY)
	{
		graphics.drawImage(image, x + relativeX, y + relativeY);
	}
	
	public boolean add(T selection)
	{
		return elements.add(selection);
	}
	
	public boolean accept(T selection)
	{
		// Accept should always return true
		// TODO handle cases where addCurrent returns false
		return elements.addCurrent(selection);
	}
	
	public void associate(SelectorDisplay<T> associatedDisplay)
	{
		this.associatedDisplay = associatedDisplay;
	}
	
	public boolean sendToDisplay(T data)
	{
		if (data != null)
			return associatedDisplay.accept(data);
		else
			return false;
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public Rectangle getBounds()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setBounds(Rectangle bounds)
	{
		// TODO Auto-generated method stub
		
	}
	
}
