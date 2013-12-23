package edu.asu.voctec.GUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.Resizable;
import edu.asu.voctec.utilities.UtilFunctions;

/**
 * Represents a displayable object on the screen. Components have the ability to
 * listen for events, including mouse, keyboard, and joystick.
 * 
 * Note: all components should be associated with a GUI instance. The events
 * will be fired from said GUI. A user can easily associate this button with a
 * GUI using the associate() method.
 * 
 * @author Moore, Zachary
 * @see #associate(GUI)
 * 
 */
public abstract class Component implements Displayable, Resizable
{
	protected final ArrayList<ActionListener> listeners = new ArrayList<>();
	protected GUI associatedGUI;
	
	public ActionListener[] getListeners()
	{
		return listeners.toArray(new ActionListener[listeners.size()]);
	}
	
	public GUI getAssociatedGUI()
	{
		return associatedGUI;
	}
	
	public void associate(GUI associatedGUI)
	{
		this.associatedGUI = associatedGUI;
		
		// Make GUI listen for all listeners associated with this component.
		associatedGUI.getListenerList().addAll(listeners);
	}
	
	public void addActionListener(ActionListener listener)
	{
		// Add listener to this component
		listener.associate(this);
		this.listeners.add(listener);
		
		// Alert the associated GUI that the new event should be listened for.
		if (this.associatedGUI != null)
			this.associatedGUI.getListenerList().add(listener);
	}
	
	@Override
	public boolean setBounds(Rectangle bounds)
	{
		boolean success = resize(bounds.width, bounds.height);
		
		// Only reposition the object if the resize was successful.
		if (success)
		{
			setX(bounds.x);
			setY(bounds.y);
		}
		else
			System.out.println("Component: setBounds FAILED");
		
		return success;
	}

	@Override
	public boolean rescale(float scale)
	{
		return rescale(scale, scale);
	}
	
	@Override
	public boolean rescale(int width, int height)
	{
		int currentWidth = getBounds().width;
		int currentHeight = getBounds().height;

		float horizontalScale = ((float) width) / ((float) currentWidth);
		float verticalScale = ((float) height) / ((float) currentHeight);
		
		return rescale(horizontalScale, verticalScale);
	}
	
	@Override
	public boolean rescale(float horizontalScale, float verticalScale)
	{
		Rectangle newBounds = UtilFunctions.getScaledRectangle(getBounds(),
				horizontalScale, verticalScale);
		return setBounds(newBounds);
	}

	@Override
	public boolean resize(Dimension dimension)
	{
		return resize(dimension.width, dimension.height);
	}
}