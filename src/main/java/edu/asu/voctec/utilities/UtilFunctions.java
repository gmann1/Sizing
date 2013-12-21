package edu.asu.voctec.utilities;

import java.awt.Point;
import java.awt.Rectangle;

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
		int x = moveableRectangle.x
				+ ((container.width - moveableRectangle.width) / 2);
		int y = container.y;
		
		moveableRectangle.setLocation(x, y);
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
		int x = (int) (baseRectangle.x * scale);
		int y = (int) (baseRectangle.y * scale);
		int width = (int) (baseRectangle.width * scale);
		int height = (int) (baseRectangle.height * scale);
		
		return new Rectangle(x, y, width, height);
	}
}
