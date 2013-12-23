package edu.asu.voctec.game_states;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.ActionListener;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.utilities.UtilFunctions;

public abstract class GUI extends ModifiedGameState implements GameDefaults
{
	protected Image backgroundImage;
	protected final ArrayList<Component> components = new ArrayList<>();
	protected final ArrayList<ActionListener> listeners = new ArrayList<>();
	
	@Override
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return new Dimension(800, 600);
	}
	
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
	
	public void centerComponentsStacked(int spaceBetweenComponents,
			Component... components)
	{
		// Variables for the bounds of a rectangle that encompasses all give
		// components
		int width = 0;
		int height = 0;
		
		// Determine the bounds for a rectangle that encompasses all give
		// components
		for (Component component : components)
		{
			int componentMaxX = component.getX() + component.getBounds().width;
			width = (componentMaxX > width) ? componentMaxX : width;
			height += component.getBounds().height;
		}
		
		// Account for the space between components
		height += spaceBetweenComponents * (components.length - 1);
		
		// Create rectangle that encompasses all give components
		Rectangle groupBounds = new Rectangle(0, 0, width, height);
		System.out.println("groupBounds: " + groupBounds.toString());
		
		// Define the bounds of this GUI
		Rectangle guiBounds = new Rectangle(new Point(0, 0),
				Main.getCurrentScreenDimension());
		System.out.println("guiBounds: " + guiBounds.toString());
		
		// Center the rectangle (groupBounds) relative to this GUI
		UtilFunctions.centerRectangle(guiBounds, groupBounds);
		System.out.println("groupBounds Centered: " + groupBounds.toString());
		
		// Set the location of each component
		int currentY = groupBounds.y;
		for (int componentIndex = 0; componentIndex < components.length; componentIndex++)
		{
			// Define the current component
			Component currentComponent = components[componentIndex];
			
			// Define the bounds of the current component
			Rectangle componentBounds = new Rectangle(0, currentY,
					currentComponent.getBounds().width,
					currentComponent.getBounds().height);

			// Center the current component horizontally
			UtilFunctions.centerRectangleHorizontally(groupBounds, componentBounds);
			
			// Update the component
			currentComponent.setBounds(componentBounds);
			
			// Account for space between components
			currentY += componentBounds.height + spaceBetweenComponents;
		}
	}
	
	private final void listen(Input input)
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
		component.associate(this);
		this.components.add(component);
	}
	
	public void removeComponent(Component component)
	{
		// Add component
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
