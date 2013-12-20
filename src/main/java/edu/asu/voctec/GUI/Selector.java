package edu.asu.voctec.GUI;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import edu.asu.voctec.utilities.CircularList;
import edu.asu.voctec.utilities.PositionedObject;

public class Selector<T extends SelectorIcon> implements Displayable
{
	protected CircularList<T> elements = new CircularList<>();
	protected Rectangle currentChoiceBounds;
	protected Rectangle previousChoiceBounds;
	protected Rectangle nextChoiceBounds;
	protected boolean orientLeft;
	
	protected PositionedObject<Image> rightArrow;
	protected PositionedObject<Image> leftArrow;
	protected PositionedObject<Image> background;
	protected int x;
	protected int y;
	
	@Override
	public void draw(Graphics graphics)
	{
		// Draw background and arrows
		if (background != null)
			graphics.drawImage(background.data, x + background.x, y
					+ background.y);
		if (rightArrow != null)
			graphics.drawImage(rightArrow.data, x + rightArrow.x, y
					+ rightArrow.y);
		if (leftArrow != null)
			graphics.drawImage(leftArrow.data, x + leftArrow.x, y + leftArrow.y);
		
		// Draw choice icons
		if (elements.size() >= 1)
			drawCurrentSelection(graphics);
		if (elements.size() >= 2)
		{
			if (orientLeft || elements.size() <= 3)
				drawPreviousSelection(graphics);
			if (!orientLeft || elements.size() <= 3)
				drawNextSelection(graphics);
		}
		
		// Draw choice borders
		drawRectangle(graphics, previousChoiceBounds);
		drawRectangle(graphics, currentChoiceBounds);
		drawRectangle(graphics, nextChoiceBounds);
	}
	
	public void drawRectangle(Graphics graphics, Rectangle rectangle, boolean filled)
	{
		if (filled)
			graphics.fillRect(rectangle.x, rectangle.y, rectangle.width,
					rectangle.height);
		
		graphics.drawRect(rectangle.x, rectangle.y, rectangle.width,
				rectangle.height);
	}
	
	public void drawRectangle(Graphics graphics, Rectangle rectangle)
	{
		drawRectangle(graphics, rectangle, false);
	}
	
	public void drawCurrentSelection(Graphics graphics)
	{
		graphics.drawImage(elements.getCurrentElement().getCurrentImage(),
				currentChoiceBounds.x, currentChoiceBounds.y);
	}
	
	public void drawPreviousSelection(Graphics graphics)
	{
		graphics.drawImage(elements.getPreviousElement().getCurrentImage(),
				previousChoiceBounds.x, previousChoiceBounds.y);
	}
	
	public void drawNextSelection(Graphics graphics)
	{
		graphics.drawImage(elements.getNextElement().getCurrentImage(),
				nextChoiceBounds.x, nextChoiceBounds.y);
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
