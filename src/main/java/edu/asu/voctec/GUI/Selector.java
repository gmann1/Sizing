package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	
	protected BasicComponent currentChoiceBackground;
	protected BasicComponent previousChoiceBackground;
	protected BasicComponent nextChoiceBackground;
	protected BasicComponent rightArrow;
	protected BasicComponent leftArrow;
	protected BasicComponent background;
	protected ArrayList<BasicComponent> components = new ArrayList<>();
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
			T element = elements.pop();
			sendToDisplay(element);
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
	public Selector(boolean useDeafultActions) throws SlickException
	{
		currentChoiceBackground = new BasicComponent(ImagePaths.SELECTOR_LARGE,
				SelectorDefaults.PRIMARY_SELECTION_LOCATION);
		
		previousChoiceBackground = new BasicComponent(
				ImagePaths.SELECTOR_SMALL,
				SelectorDefaults.SECONDARY_SELECTION_LOCATION_LEFT);
		
		nextChoiceBackground = new BasicComponent(ImagePaths.SELECTOR_SMALL,
				SelectorDefaults.SECONDARY_SELECTION_LOCATION_RIGHT);
		
		rightArrow = new BasicComponent(new Image(ImagePaths.ARROW_RIGHT),
				SelectorDefaults.ARROW_LOCATION_RIGHT);
		
		leftArrow = new BasicComponent(new Image(ImagePaths.ARROW_LEFT),
				SelectorDefaults.ARROW_LOCATION_LEFT);
		
		background = new BasicComponent(new Image(ImagePaths.SELECTOR_SHADOW),
				SelectorDefaults.SHADOW_LOCATION);
		
		// Add all components to array
		components.add(currentChoiceBackground);
		components.add(previousChoiceBackground);
		components.add(nextChoiceBackground);
		components.add(rightArrow);
		components.add(leftArrow);
		components.add(background);
		
		// Listen for clicks to the left and right arrows
		this.addActionListener(new RightArrowListener());
		this.addActionListener(new LeftArrowListener());
		
		if (useDeafultActions)
		{
			// Listen for clicks to the primary selection
			this.addActionListener(new CurrentChoiceListener());
		}
		
		System.out.println("Listener count: " + this.listeners.size());
	}
	
	public Selector(int x, int y, boolean useDeafultActions)
			throws SlickException
	{
		this(useDeafultActions);
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
			drawElement(graphics, elements.getCurrentElement(),
					currentChoiceBackground);
		if (elements.size() >= 2)
		{
			if (orientLeft || elements.size() >= 3)
				drawElement(graphics, elements.getPreviousElement(),
						previousChoiceBackground);
			if (!orientLeft || elements.size() >= 3)
				drawElement(graphics, elements.getNextElement(),
						nextChoiceBackground);
		}
	}
	
	protected void drawElement(Graphics graphics, SelectorIcon icon,
			BasicComponent container)
	{
		Image scaledImage = icon.getCurrentImage().getScaledCopy(
				container.getBounds().width, container.getBounds().height);
		drawRelatively(graphics, scaledImage, container.getX(),
				container.getY());
	}
	
	public void cycleLeft()
	{
		if (orientLeft || elements.size() >= 3)
			elements.previous();
		this.orientLeft = false;
	}
	
	public void cycleRight()
	{
		if (!orientLeft || elements.size() >= 3)
			elements.next();
		this.orientLeft = true;
	}
	
	public Rectangle getAbsoluteBounds(PositionedObject<Image> object)
	{
		return new Rectangle(object.x + x, object.y + y,
				object.data.getWidth(), object.data.getHeight());
	}
	
	public Rectangle getAbsoluteBounds(Component component)
	{
		return UtilFunctions.getTranslatedRectangle(component.getBounds(),
				new Point(x, y));
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
	
	public void drawRelatively(Graphics graphics, BasicComponent component)
	{
		graphics.drawImage(component.getCurrentImage(), x + component.getX(), y
				+ component.getY());
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
		if (associatedDisplay != null && data != null)
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
		// TODO calculate max and min X/Y components
		int x = this.x;
		int y = this.y;
		int width = this.rightArrow.getX() + this.rightArrow.getBounds().width;
		int height = this.background.getY() + this.background.getBounds().height;
		
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public boolean rescale(float horizontalScale, float verticalScale)
	{
		this.x = (int) (x * horizontalScale);
		this.y = (int) (y * verticalScale);
		
		boolean success = true;
		
		for (Component component : components)
		{
			success = success && component.rescale(horizontalScale, verticalScale);
		}
		
		return success;
	}
	
	@Override
	public boolean resize(int width, int height)
	{
		int originalX = this.x;
		int originalY = this.y;
		
		boolean success = this.rescale(width, height);
		
		this.x = originalX;
		this.y = originalY;
		
		return success;
	}
	
	@Override
	public void setX(int x)
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setY(int y)
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
}
