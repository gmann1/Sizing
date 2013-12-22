package edu.asu.voctec.GUI;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Input;

public abstract class ButtonListener extends ActionListener
{
	@Override
	protected boolean verify(Input input)
	{
		return verify(input, associatedComponent.getBounds());
	}
	
	protected static boolean verify(Input input, Rectangle bounds)
	{
		boolean actionVerified;
		
		if (mouseButton == Input.MOUSE_LEFT_BUTTON)
		{
			// Get mouse location
			Point mouseLocation = new Point(input.getMouseX(),
					input.getMouseY());
			
			// Verify action if mouse is pressed over the associated component
			// (i.e. the component was clicked with the left button).
			actionVerified = bounds.contains(mouseLocation);
		}
		else
		{
			actionVerified = false;
		}
		
		return actionVerified;
	}
	
}
