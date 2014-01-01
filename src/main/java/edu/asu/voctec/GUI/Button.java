package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
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
			Rectangle buttonBounds = new Rectangle(x, y, image.getWidth(),
					image.getHeight());
			
			// Determine textBounds relative to the screen
			Rectangle absoluteTextBounds = UtilFunctions
					.getTranslatedRectangle(relativeTextBounds, new Point(x, y));
			
			// Center text bounds vertically, with respect to this button
			UtilFunctions.centerRectangleVertically(buttonBounds,
					absoluteTextBounds);
			
			// Create and format text field
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
	
	public static void addTransitionListener(Button button,
			Class<?> transitionScreen)
	{
		
		button.addActionListener(new TransitionButtonListener(transitionScreen));
	}
	
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		if (textField != null)
			textField.draw(graphics);
	}
	
	public void setX(int x)
	{
		if (textField != null)
		{
			int deltaX = x - (int)this.x;
			textField.translate(deltaX, 0);
		}
		super.setX(x);
	}
	
	public void setY(int y)
	{
		if (textField != null)
		{
			int deltaY = y - (int)this.y;
			textField.translate(0, deltaY);
		}
		super.setY(y);
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
			
			// Deterime horizontal and vertical scales, for resizing the text
			float[] scales = getScales(width, height);
			
			// Resize this button, and rescale the relative text field
			success = super.resize(width, height)
					&& textField.rescale(scales[0], scales[1]);
			
			// Give textField an absolute location
			textField.setX(textField.getX() + (int) this.x);
			textField.setY(textField.getY() + (int) this.y);
		}
		else
			success = super.resize(width, height);
		
		return success;
	}
	
	public TextField getTextField()
	{
		return textField;
	}
	
	public void setFontColor(Color color)
	{

		this.textField.setFontColor(color);
	}
	
}
