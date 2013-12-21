package edu.asu.voctec.GUI;

import java.util.ArrayList;

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
