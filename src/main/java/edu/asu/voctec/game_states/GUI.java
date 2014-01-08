package edu.asu.voctec.game_states;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.ActionListener;
import edu.asu.voctec.GUI.Component;
import edu.asu.voctec.utilities.UtilFunctions;

public abstract class GUI extends ModifiedGameState implements GameDefaults
{
	private final ArrayList<Component> additionQueue = new ArrayList<>();
	private final ArrayList<Component> removalQueue = new ArrayList<>();
	
	protected Image backgroundImage;
	protected final ArrayList<Component> components = new ArrayList<>();
	protected final ArrayList<ActionListener> listeners = new ArrayList<>();
	protected boolean trackTime;
	
	@Override
	public void onEnter()
	{
		// TODO Remove?
	}

	@Override
	public void onExit()
	{
		// TODO Remove?
	}
	
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
		// Current user input
		Input input = container.getInput();
		
		// Listen for events
		listen(input);
		
		// Update current task (time spent)
		if (trackTime)
			Game.getCurrentTask().getCurrentAttempt().addTime(delta);
	}
	
	public void centerComponentsStacked(int spaceBetweenComponents)
	{
		// Define the bounds of this GUI
		Rectangle guiBounds = new Rectangle(new Point(0, 0),
				Main.getCurrentScreenDimension());
		UtilFunctions.centerComponentsStacked(guiBounds,
				spaceBetweenComponents,
				components.toArray(new Component[components.size()]));
	}
	
	private final void listen(Input input)
	{
		ActionListener.update(input);
		
		for (ActionListener listener : listeners)
		{
			listener.check(input);
		}
		
		processQueue();
	}
	
	private final void processQueue()
	{
		this.removeComponents(removalQueue);
		this.addComponents(additionQueue);
		
		this.additionQueue.clear();
		this.removalQueue.clear();
	}

	public final void queueRemoveComponents(Component... informationComponents)
	{
		queueRemoveComponents(Arrays.asList(informationComponents));
	}

	public final void queueRemoveComponents(List<Component> informationComponents)
	{
		removalQueue.addAll(informationComponents);
	}

	public final void queueAddComponents(Component... informationComponents)
	{
		queueAddComponents(Arrays.asList(informationComponents));
	}

	public final void queueAddComponents(List<Component> informationComponents)
	{
		additionQueue.addAll(informationComponents);
	}
	
	public void addComponent(Component component)
	{
		// Add component
		component.associate(this);
		this.components.add(component);
	}
	
	public void addComponents(Component... components)
	{
		for (Component component : components)
			addComponent(component);
	}
	
	public void addComponents(ArrayList<Component> components)
	{
		for (Component component : components)
			addComponent(component);
	}
	
	public void removeComponent(Component component)
	{
		// Add component
		this.components.remove(component);
		
		// Stop listening for all listeners associated with the given component.
		this.listeners.removeAll(Arrays.asList(component.getListeners()));
	}
	
	public void removeComponents(Component... components)
	{
		for (Component component : components)
			removeComponent(component);
	}
	
	public void removeComponents(ArrayList<Component> components)
	{
		for (Component component : components)
			removeComponent(component);
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
	
	public void setBackgroundImage(Image backgroundImage)
	{
		this.backgroundImage = backgroundImage;
	}
	
	public Image getBackgroundImage()
	{
		return backgroundImage;
	}
	
}
