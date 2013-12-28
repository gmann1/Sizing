package edu.asu.voctec.utilities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Image;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.Component;

/**
 * Provides additional transformation functions for java.awt.Rectangle
 * 
 * @author Moore, Zachary
 * 
 */
public abstract class UtilFunctions
{
	public static void centerRectangleVertically(final Rectangle container,
			Rectangle moveableRectangle)
	{
		int x = moveableRectangle.x;
		int y = container.y
				+ ((container.height - moveableRectangle.height) / 2);
		
		moveableRectangle.setLocation(x, y);
	}
	
	public static void centerRectangleHorizontally(final Rectangle container,
			Rectangle moveableRectangle)
	{
		int x = container.x + ((container.width - moveableRectangle.width) / 2);
		int y = moveableRectangle.y;
		
		moveableRectangle.setLocation(x, y);
	}
	
	public static void centerRectangle(final Rectangle container,
			Rectangle moveableRectangle)
	{
		centerRectangleHorizontally(container, moveableRectangle);
		centerRectangleVertically(container, moveableRectangle);
	}
	
	public static Rectangle dialateRectangle(Rectangle bounds, float scale)
	{
		Rectangle scaledBounds = UtilFunctions
				.getScaledRectangle(bounds, scale);
		
		// TODO test
		// Rectangle relativeBounds = new Rectangle(0, 0, bounds.width,
		// bounds.height);
		UtilFunctions.centerRectangle(bounds, scaledBounds);
		
		return scaledBounds;
	}
	
	public static Rectangle dialateRelativeRectangle(Rectangle bounds,
			float scale)
	{
		Rectangle relativeBounds = new Rectangle(0, 0, bounds.width,
				bounds.height);
		
		return dialateRectangle(relativeBounds, scale);
	}
	
	public static Rectangle getTranslatedRectangle(Rectangle baseRectangle,
			Point translationAmount)
	{
		int x = baseRectangle.x + translationAmount.x;
		int y = baseRectangle.y + translationAmount.y;
		int width = baseRectangle.width;
		int height = baseRectangle.height;
		
		return new Rectangle(x, y, width, height);
	}
	
	public static Rectangle getScaledRectangle(Rectangle baseRectangle,
			float scale)
	{
		return getScaledRectangle(baseRectangle, scale, scale);
	}
	
	public static Rectangle getScaledRectangle(Rectangle baseRectangle,
			float horizontalScale, float verticalScale)
	{
		// Scale horizontal components
		int x = (int) (baseRectangle.x * horizontalScale);
		int width = (int) (baseRectangle.width * horizontalScale);
		
		// Scale vertical components
		int y = (int) (baseRectangle.y * verticalScale);
		int height = (int) (baseRectangle.height * verticalScale);
		
		// Return a rectangle representing a scaled version of the object
		return new Rectangle(x, y, width, height);
	}
	
	public static Rectangle getImageBounds(Image image)
	{
		return new Rectangle(0, 0, image.getWidth(), image.getHeight());
	}
	
	public static void translateAll(Point translationAmount,
			Component... components)
	{
		translateAll(translationAmount.x, translationAmount.y);
	}
	
	public static void translateAll(int horizontalAmount, int verticalAmount,
			Component... components)
	{
		for (Component component : components)
		{
			component.translate(horizontalAmount, verticalAmount);
		}
	}
	
	public static void translateAll(int horizontalAmount, int verticalAmount,
			ArrayList<Component> components)
	{
		UtilFunctions.translateAll(horizontalAmount, verticalAmount,
				components.toArray(new Component[components.size()]));
	}
	
	public static void centerComponentsStacked(final Rectangle container,
			int spaceBetweenComponents, Component... components)
	{
		// Variables for the bounds of a rectangle that encompasses all give
		// components
		int width = 0;
		int height = 0;
		
		// Determine the bounds for a rectangle that encompasses all give
		// components
		for (Component component : components)
		{
			int componentMaxX = component.getX() + component.getBounds().width;
			width = (componentMaxX > width) ? componentMaxX : width;
			height += component.getBounds().height;
		}
		
		// Account for the space between components
		height += spaceBetweenComponents * (components.length - 1);
		
		// Create rectangle that encompasses all give components
		Rectangle groupBounds = new Rectangle(0, 0, width, height);
		System.out.println("groupBounds: " + groupBounds.toString());
		
		// Center the rectangle (groupBounds) relative to the given container
		UtilFunctions.centerRectangle(container, groupBounds);
		System.out.println("groupBounds Centered: " + groupBounds.toString());
		
		// Set the location of each component
		int currentY = groupBounds.y;
		for (int componentIndex = 0; componentIndex < components.length; componentIndex++)
		{
			// Define the current component
			Component currentComponent = components[componentIndex];
			
			// Define the bounds of the current component
			Rectangle componentBounds = new Rectangle(0, currentY,
					currentComponent.getBounds().width,
					currentComponent.getBounds().height);
			
			// Center the current component horizontally
			UtilFunctions.centerRectangleHorizontally(groupBounds,
					componentBounds);
			
			// Update the component
			currentComponent.setBounds(componentBounds);
			
			// Account for space between components
			currentY += componentBounds.height + spaceBetweenComponents;
		}
	}
	
	public static float[] getScales(Rectangle bounds, int width, int height)
	{
		float horizontalScale = ((float) width) / ((float) bounds.width);
		float verticalScale = ((float) height) / ((float) bounds.height);
		
		return new float[] { horizontalScale, verticalScale };
	}
	
	public static String getOrdinalRepresentation(int number)
	{
		String ordinalRepresentation = Integer.toString(number);
		
		switch (number)
		{
			case 1:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.FIRST.getTranslation();
				break;
			case 2:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.SECOND.getTranslation();
				break;
			case 3:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.THIRD.getTranslation();
				break;
			case 4:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.FOURTH.getTranslation();
				break;
			case 5:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.FIFTH.getTranslation();
				break;
			case 6:
				ordinalRepresentation = GameDefaults.Labels.OrdinalNumbers.SIXTH.getTranslation();
				break;
		}
		
		return ordinalRepresentation;
	}
}
