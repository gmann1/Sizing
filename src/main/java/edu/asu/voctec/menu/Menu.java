package edu.asu.voctec.menu;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Menu extends BasicGameState
{
	protected Image backgroundImage;
	protected final ArrayList<Button> buttons = new ArrayList<>();
	
	/**
	 * Adds a button to this menu, and ensures that no duplicate buttons are
	 * added. More specifically, If this menu already contains a button e
	 * such that e.equals(button), then the provided button will not be added
	 * and this method will return false.
	 * 
	 * @param button	the button to add to this menu
	 * @return			whether or not the provided button was added successfully.
	 * @see 			ArrayList#add(Object)
	 */
	public boolean addButton(Button button)
	{
		if (buttons.contains(button))
			return false;
		else
			return buttons.add(button);
	}
	
	/** 
	 * This method contains actions to take during a mousePressed event. Should
	 * only be called to notify this object of a mousePressed event.
	 * 
	 * @param button 	The index of the button (starting at 0)
	 * @param x 		The x position of the mouse when the button was pressed
	 * @param y 		The y position of the mouse when the button was pressed
	 */
	@Override
	public void mousePressed(int button, int x, int y)
	{
		boolean leftButtonPressed = (button == Input.MOUSE_LEFT_BUTTON);
		
		if (leftButtonPressed)
		{
			//TODO check buttons
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException
	{
		//TODO account for different aspect ratios
		//draw background
		g.drawImage(this.backgroundImage, 0, 0);
		
		//TODO implement change tracking
		//TODO draw only buttons that have been changed
		//draw all buttons
		for (Button button : buttons)
		{
			g.drawImage(button, button.getX(), button.getY());
		}
	}
}
