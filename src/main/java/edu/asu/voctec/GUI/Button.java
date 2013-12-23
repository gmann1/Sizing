package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.utilities.UtilFunctions;

public class Button extends BasicComponent
{
	protected TextField textField;
	
	public Button(Image image, int x, int y, Rectangle relativeTextBounds,
			String text)
	{
		super(image, x, y);
		
		if (relativeTextBounds != null)
		{
			Rectangle absoluteTextBounds = UtilFunctions
					.getTranslatedRectangle(relativeTextBounds, new Point(x, y));
			this.textField = new TextField(absoluteTextBounds, 1.0f, text,
					TextDisplay.FormattingOption.FIT_TEXT);
			this.textField.center();
		}
	}
	
	public Button(String imagePath, int x, int y, Rectangle relativeTextBounds,
			String text) throws SlickException
	{
		this(new Image(imagePath), x, y, relativeTextBounds, text);
	}
	
	public Button(String imagePath, int x, int y, int width, int height,
			Rectangle relativeTextBounds, String text) throws SlickException
	{
		this((new Image(imagePath)).getScaledCopy(width, height), x, y,
				relativeTextBounds, text);
	}
	
	public Button(String imagePath, Rectangle bounds,
			Rectangle relativeTextBounds, String text) throws SlickException
	{
		this((new Image(imagePath)).getScaledCopy(bounds.width, bounds.height),
				bounds.x, bounds.y, relativeTextBounds, text);
	}
	
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		if (textField != null)
			textField.draw(graphics);
	}
	
	public void setX(int x)
	{
		super.setX(x);
		if (textField != null)
			textField.setX(x);
	}
	
	public void setY(int y)
	{
		super.setY(y);
		if (textField != null)
			textField.setY(y);
	}
	
	@Override
	public boolean rescale(float horizontalScale, float verticalScale)
	{
		boolean success = super.rescale(horizontalScale, verticalScale);
		if (textField != null)
			success = success
					&& textField.rescale(horizontalScale, verticalScale);
		
		return success;
	}
	
	@Override
	public boolean resize(int width, int height)
	{
		boolean success;
		
		if (textField != null)
		{
			// Give textField a relative position
			textField.setX(textField.getX() - (int) this.x);
			textField.setY(textField.getY() - (int) this.y);
			
			// Resize this button, and rescale the relative text field
			success = super.resize(width, height)
					&& textField.rescale(width, height);
			
			// Give textField an absolute location
			textField.setX(textField.getX() + (int) this.x);
			textField.setY(textField.getY() + (int) this.y);
		}
		else
			success = super.resize(width, height);
		
		return success;
	}
	
}
