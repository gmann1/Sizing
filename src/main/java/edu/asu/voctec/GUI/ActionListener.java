package edu.asu.voctec.GUI;

import org.newdawn.slick.Input;

public abstract class ActionListener
{
	protected static int mouseButton = -1;
	protected Component associatedComponent;
	
	public void associate(Component associatedComponent)
	{
		this.associatedComponent = associatedComponent;
	}
	
	public final boolean check(Input input)
	{
		boolean fireEvent = verify(input);
		
		if (fireEvent)
			actionPerformed();
		
		return fireEvent;
	}
	
	public static void update(Input input)
	{
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			mouseButton = Input.MOUSE_LEFT_BUTTON;
		else if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			mouseButton = Input.MOUSE_RIGHT_BUTTON;
		else if (input.isMousePressed(Input.MOUSE_MIDDLE_BUTTON))
			mouseButton = Input.MOUSE_MIDDLE_BUTTON;
		else
			mouseButton = -1;
	}
	
	protected abstract boolean verify(Input input);
	protected abstract void actionPerformed();
}
