package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.UtilFunctions;

public class SelectorDisplay<T extends SelectorIcon> extends Component
{
	protected static Rectangle borderBounds;
	protected static Image defaultBorder;
	protected static Image highlightedBorder;
	protected static Image correctBorder;
	protected static Image incorrectBorder;
	
	protected BasicComponent[] choiceBorders;
	protected ArrayList<T> elements;
	protected Selector<T> associatedSelector;
	protected int capacity;
	protected int x;
	protected int y;
	
	public class ChoiceListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			System.out.println("Choice Border Clicked.");
			
			// Determine which choiceBorder was clicked
			int index = Arrays.asList(choiceBorders).indexOf(
					(BasicComponent) this.associatedComponent);
			
			// Get the icon that is being held by the current choiceBorder
			T icon = elements.get(index);
			
			// Ignore the click if the selected choiceBorder is empty.
			if (icon != null)
			{
				// Return icon to selector
				sendToSelector(icon);
				
				// Free the space in this display
				elements.set(index, null);
			}
		}
	}
	
	static
	{
		try
		{
			defaultBorder = new Image(ImagePaths.SelectorDisplayBorders.DEFAULT);
			borderBounds = UtilFunctions.getImageBounds(defaultBorder);
			
			highlightedBorder = new Image(
					ImagePaths.SelectorDisplayBorders.HIGHLIGHTED)
					.getScaledCopy(borderBounds.width, borderBounds.height);
			correctBorder = new Image(ImagePaths.SelectorDisplayBorders.CORRECT)
					.getScaledCopy(borderBounds.width, borderBounds.height);
			incorrectBorder = new Image(
					ImagePaths.SelectorDisplayBorders.INCORRECT).getScaledCopy(
					borderBounds.width, borderBounds.height);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	protected SelectorDisplay(int x, int y, int capacity)
	{
		super();
		this.x = x;
		this.y = y;
		this.capacity = capacity;
		elements = new ArrayList<>(capacity);
		
		for (int index = 0; index < capacity; index++)
			elements.add(null);
	}
	
	public SelectorDisplay(int x, int y, boolean useDefaults)
	{
		this(x, y, 5);
		if (useDefaults)
		{
			int spacing = 15; // Minimum space between choiceBorders
			
			/*
			 * Create the choice borders for this component, using the default
			 * image and format; relative to this component
			 */
			ArrayList<BasicComponent> borders = generateDefaultFormation(spacing);
			
			// Instantiate choice borders with the values defined above.
			choiceBorders = borders.toArray(new BasicComponent[borders.size()]);
			
			// Setup each choiceBorder
			setupChoiceBorders(true); // Set screen-relative positions, associate with
								// GUI, and listen for mouse clicks
		}
	}
	
	protected void setupChoiceBorders(boolean positionComponents)
	{
		if (choiceBorders != null)
		{
			for (Component component : choiceBorders)
			{
				/*
				 * Associate this component's GUI with each component Note:
				 * because the component has not been associated with the GUI,
				 * the GUI will not render or scale the component, it will only
				 * track the listeners assigned to these components
				 */
				component.associate(associatedGUI);
				
				// Listen for clicks on all components
				component.addActionListener(new ChoiceListener());

				// Replace relative positioning with absolute positioning
				if(positionComponents)
					component.translate(x, y);
			}
		}
	}
	
	protected ArrayList<BasicComponent> generateDefaultFormation(int spacing)
	{
		ArrayList<BasicComponent> borders = new ArrayList<>(5);
		
		// Populate default borders - Make a '5-domino' formation
		// Top left border
		Point relativeLocation = new Point(0, 0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Top right border
		relativeLocation.translate(2 * spacing + 2 * defaultBorder.getWidth(),
				0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Middle Border
		relativeLocation.setLocation(spacing + defaultBorder.getWidth(),
				spacing + defaultBorder.getHeight());
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Bottom right border
		relativeLocation.setLocation(0,
				2 * spacing + 2 * defaultBorder.getHeight());
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Bottom left border
		relativeLocation.translate(2 * spacing + 2 * defaultBorder.getWidth(),
				0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		return borders;
	}
	
	public boolean accept(T element)
	{
		boolean accepted;
		
		// Get the first empty "slot" in this display
		int currentIndex = elements.indexOf(null);

		System.out.println("SelectorDisplay: capacity=" + capacity);
		System.out.println("SelectorDisplay: index=" + currentIndex);
		System.out.println("SelectorDisplay: element=" + element);
		
		// Reject the element if this display is full, or if the element is null
		if (element != null && !(currentIndex > capacity || currentIndex < 0))
		{
			BasicComponent container = choiceBorders[currentIndex];
			
			// Determine new bounds for selection
			Rectangle newBounds = new Rectangle(borderBounds);
			newBounds.setLocation(container.getX(), container.getY());
			
			// Resize and replace element
			element.setBounds(newBounds);
			
			elements.set(currentIndex, element);
			accepted = true;
			System.out.println("SelectorDisplay: Element Accepted.");
		}
		else
		{
			// Reject element
			accepted = false;
			System.out.println("SelectorDisplay: Element Rejected.");
		}
		
		return accepted;
	}
	
	public void associate(Selector<T> associatedSelector)
	{
		this.associatedSelector = associatedSelector;
	}
	
	@Override
	public void associate(GUI associatedGUI)
	{
		super.associate(associatedGUI);
		for (Component component : choiceBorders)
			component.associate(associatedGUI);
	}
	
	public void link(Selector<T> associatedSelector)
	{
		associate(associatedSelector);
		associatedSelector.associate(this);
	}
	
	public boolean sendToSelector(T data)
	{
		if (data != null)
			return associatedSelector.accept(data);
		else
			return false;
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		for (Component border : this.choiceBorders)
		{
			if (border != null)
				border.draw(graphics);
		}
		
		for (Component element : this.elements)
		{
			if (element != null)
				element.draw(graphics);
		}
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
		// TODO calculate width and height based on components
		return new Rectangle(x, y, choiceBorders[1].getX()
				+ choiceBorders[1].getBounds().width - x,
				choiceBorders[1].getY() + choiceBorders[1].getBounds().height
						- y);
	}
	
	@Override
	public void setX(int x)
	{
		// Make choiceBorder positions relative to this component
		UtilFunctions.translateAll(-this.x, -y, choiceBorders);
		
		this.x = x;
		
		// Reset screen-relative positions for all choiceBorders
		UtilFunctions.translateAll(this.x, y, choiceBorders);
	}
	
	@Override
	public void setY(int y)
	{
		// Make choiceBorder positions relative to this component
		UtilFunctions.translateAll(-x, -this.y, choiceBorders);
		
		this.y = y;
		
		// Reset screen-relative positions for all choiceBorders
		UtilFunctions.translateAll(x, this.y, choiceBorders);
	}
	
	@Override
	public boolean resize(int width, int height)
	{
		boolean success = true;
		
		for (Component component : choiceBorders)
		{
			// Make position relative to this component
			component.translate(-x, -y);
			
			// Rescale the component
			success = success && component.rescale(width, height);
			
			// Reset screen-relative position
			component.translate(x, y);
		}
		
		return success;
	}
	
}
