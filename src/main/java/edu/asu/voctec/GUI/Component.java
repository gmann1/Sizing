package edu.asu.voctec.GUI;

import java.util.ArrayList;

import edu.asu.voctec.game_states.GUI;

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
public abstract class Component implements Displayable
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
}
