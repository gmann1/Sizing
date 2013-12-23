package edu.asu.voctec.GUI;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class SelectorDisplay<T extends SelectorIcon> extends Component
{
	protected Selector<T> associatedSelector;
	protected ArrayList<T> elements = new ArrayList<>();
	
	public boolean accept(T selection)
	{
		//TODO handle cases in which the item should not be accepted
		return elements.add(selection);
	}
	
	public void associate(Selector<T> associatedSelector)
	{
		this.associatedSelector = associatedSelector;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Rectangle getBounds()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setX(int x)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean resize(int width, int height)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
