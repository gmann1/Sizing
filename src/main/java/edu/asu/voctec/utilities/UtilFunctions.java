package edu.asu.voctec.utilities;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Image;

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
		int x = container.x
				+ ((container.width - moveableRectangle.width) / 2);
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
		Rectangle scaledBounds = UtilFunctions.getScaledRectangle(bounds, scale);
		
		//TODO test
		//Rectangle relativeBounds = new Rectangle(0, 0, bounds.width, bounds.height);
		UtilFunctions.centerRectangle(bounds, scaledBounds);
		
		return scaledBounds;
	}

	public static Rectangle dialateRelativeRectangle(Rectangle bounds, float scale)
	{
		Rectangle relativeBounds = new Rectangle(0, 0, bounds.width, bounds.height);
		
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
}
