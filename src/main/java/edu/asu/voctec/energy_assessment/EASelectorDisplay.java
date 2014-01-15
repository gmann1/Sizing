package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.utilities.UtilFunctions;

public class EASelectorDisplay<T extends SelectorIcon> extends SelectorDisplay<T>
{
	private static final long serialVersionUID = 3141827850596081521L;
	
	protected static Image bottomBox;
	protected static Image rightArrow;
	protected static Image downArrow;
	private static final String RIGHT = "resources/default/img/minigames/energyAssessment/part1/rightArrow.png";
	private static final String DOWN = "resources/default/img/minigames/energyAssessment/part1/downArrow.png";
	private static final String BOTTOM = "resources/default/img/minigames/energyAssessment/part1/bottomBox.png";
	protected static Dimension rightArrowDimension;
	protected static Dimension downArrowDimension;
	protected static Dimension bottomBoxDimension;

	static
	{
		try
		{
			defaultBorder = new Image(ImagePaths.SelectorDisplayBorders.DEFAULT);
			defaultBorderBounds = UtilFunctions.getImageBounds(defaultBorder);
			rightArrowDimension = new Dimension(109, 109);
			downArrowDimension = new Dimension(218, 109);
			bottomBoxDimension = new Dimension(337, 119);
			
			highlightedBorder = new Image(ImagePaths.SelectorDisplayBorders.HIGHLIGHTED).getScaledCopy(defaultBorderBounds.width,defaultBorderBounds.height);
			correctBorder = new Image(ImagePaths.SelectorDisplayBorders.CORRECT).getScaledCopy(defaultBorderBounds.width,defaultBorderBounds.height);
			incorrectBorder = new Image(ImagePaths.SelectorDisplayBorders.INCORRECT).getScaledCopy(defaultBorderBounds.width, defaultBorderBounds.height);
			
			bottomBox = new Image(BOTTOM).getScaledCopy(bottomBoxDimension.width,bottomBoxDimension.height);
			rightArrow = new Image(RIGHT).getScaledCopy(rightArrowDimension.width,rightArrowDimension.height);
			downArrow = new Image(DOWN).getScaledCopy(downArrowDimension.width,downArrowDimension.height/2);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public EASelectorDisplay(int x, int y, boolean useDefaults)
			throws SlickException {
		super(x, y, true);//have to decide if true or false
		// TODO Auto-generated constructor stub
		
	}
	
	public void updateChoiceBorders()
	{
		// TODO
		for (int index = 0; index < capacity; index++)
		{
			T element = elements.get(index);
			
			
			if (element == null)
				this.choiceBorders[index].setCurrentImage(defaultBorder, true);
			else if (element.getId() == index || (element.getId() == 3 && element.getId() == (index-1))  || (element.getId() == 4 && element.getId() == (index+1)))
				this.choiceBorders[index].setCurrentImage(correctBorder, true);
			else
				this.choiceBorders[index].setCurrentImage(incorrectBorder, true);
		}
		
	}
	
	public boolean verifyChoices(boolean updateBorders)
	{
		// TODO Test
		boolean correctChoices = true;
		
		for (int index = 0; index < capacity; index++)
		{
			T element = elements.get(index);
			if (element == null)
			{
				correctChoices = false;
				break;
			}
			else if (element.getId() == index || (element.getId() == 3 && element.getId() == (index-1))  || (element.getId() == 4 && element.getId() == (index+1)))
			{
				
			}
			else
			{
				correctChoices = false;
				break;
			}
		}
		
		if (updateBorders)
			updateChoiceBorders();
		
		return correctChoices;
	}
	
	public ArrayList<String> deriveHints()
	{
		// TODO
		throw new UnsupportedOperationException();
	}
	
	protected ArrayList<Button> generateDefaultFormation(int spacing,
			ArrayList<Component> extraComponentContainer)
	{
		ArrayList<Button> borders = new ArrayList<>(5);
		
		// Populate default borders - Make a 'inverted pyramid' formation
		Point relativeLocation = new Point(0, 0);
		
		// TODO generate default formation
		// Top left border
		/*relativeLocation.setLocation(0,0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Top middle border
		relativeLocation.setLocation(2*defaultBorder.getWidth(),0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Top right Border
		relativeLocation.setLocation(4*defaultBorder.getWidth(),0);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		//Bottom Surrounding box////
		relativeLocation.setLocation(defaultBorder.getWidth()-5,1.5*defaultBorder.getHeight());
		extraComponentContainer.add(new BasicComponent(bottomBox,relativeLocation));
		
		// Bottom left border
		relativeLocation.setLocation(defaultBorder.getWidth(),1.5*defaultBorder.getHeight()+5);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));
		
		// Bottom right border
		relativeLocation.setLocation(3*defaultBorder.getWidth(),1.5*defaultBorder.getHeight()+5);
		borders.add(new BasicComponent(defaultBorder, relativeLocation));*/
		
		//bottom back box
		//relativeLocation.setLocation(defaultBorder.getWidth()-5,1.5*defaultBorder.getHeight());
		//extraComponentContainer.add(new BasicComponent(bottomBox,relativeLocation));
		
		//Arrows
		//Step 1-2
		relativeLocation.setLocation(defaultBorder.getWidth(),0);
		extraComponentContainer.add(new BasicComponent(rightArrow,relativeLocation));
		//Step 2-3
		relativeLocation.setLocation(3*defaultBorder.getWidth(),0);
		extraComponentContainer.add(new BasicComponent(rightArrow,relativeLocation));
		//Step 3-(4,5)
		relativeLocation.setLocation(2.5*defaultBorder.getWidth(),defaultBorder.getHeight());
		extraComponentContainer.add(new BasicComponent(downArrow,relativeLocation));
		
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
			EAPart1.updateInstructions();
			System.out.println("SelectorDisplay: Element Accepted.\n");
		}
		else
		{
			// Reject element
			accepted = false;
			System.out.println("SelectorDisplay: Element Rejected.");
		}
		
		return accepted;
	}

	@Override
	public void draw(Graphics graphics)
	{
		// Draw arrows and other aethstetic components
		for (Component component : this.aethsteticComponents)
		{
			if (component != null)
				component.draw(graphics);
		}
		// Draw choice borders
		for (Component border : this.choiceBorders)
		{
			if (border != null)
				border.draw(graphics);
		}
		
		// Draw choice icons
		for (Component element : this.elements)
		{
			if (element != null)
				element.draw(graphics);
		}
	}
}
