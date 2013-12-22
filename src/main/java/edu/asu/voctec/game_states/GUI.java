package edu.asu.voctec.game_states;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.GUI.ActionListener;
import edu.asu.voctec.GUI.Component;

public abstract class GUI extends ModifiedGameState implements GameDefaults
{
	protected Image backgroundImage;
	protected final ArrayList<Component> components = new ArrayList<>();
	protected final ArrayList<ActionListener> listeners = new ArrayList<>();
	
	public abstract Dimension getDesignResolution();
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		// Draw background
		if (this.backgroundImage != null)
			graphics.drawImage(this.backgroundImage, 0, 0);
		
		// TODO implement change tracking
		// TODO draw only components that have been changed
		// Draw all components
		for (Component component : components)
		{
			component.draw(graphics);
		}
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		Input input = container.getInput();
		
		// Listen for events
		listen(input);
	}
	
	public final void listen(Input input)
	{
		ActionListener.update(input);
		
		for (ActionListener listener : listeners)
		{
			listener.check(input);
		}
	}
	
	public void addComponent(Component component)
	{
		// Add component
		this.components.add(component);
		
		// Listen for all listeners associated with the given component.
		this.listeners.addAll(Arrays.asList(component.getListeners()));
	}
	
	public void removeComponent(Component component)
	{
		// Add component
		component.associate(this);
		this.components.remove(component);
		
		// Stop listening for all listeners associated with the given component.
		this.listeners.removeAll(Arrays.asList(component.getListeners()));
	}
	
	public Component[] getComponents()
	{
		return components.toArray(new Component[components.size()]);
	}
	
	public ActionListener[] getListeners()
	{
		return listeners.toArray(new ActionListener[listeners.size()]);
	}
	
	public ArrayList<ActionListener> getListenerList()
	{
		return listeners;
	}
	
	public Image getBackgroundImage()
	{
		return backgroundImage;
	}
	
}
