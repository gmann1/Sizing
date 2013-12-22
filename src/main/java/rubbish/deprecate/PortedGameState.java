package rubbish.deprecate;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.game_states.ModifiedGameState;

public abstract class PortedGameState extends ModifiedGameState
{
	protected ArrayList<Actor>	actors	= new ArrayList<>();
	protected Image				backgroundImage;
	
	public void addObject(Actor actor, int xLocation, int yLocation)
	{
		actor.setLocation(xLocation, yLocation);
		this.actors.add(actor);
	}
	
	public void removeObject(Actor actor)
	{
		this.actors.remove(actor);
	}
	
	@Override
	public boolean resize()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void rescale()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateTranslation()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		if (this.backgroundImage != null)
			graphics.drawImage(backgroundImage, 0, 0);
		
		// TODO Auto-generated method stub
		for (Actor actor : actors)
		{
			actor.draw(graphics);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		// TODO update; remove actor references
		for (Actor actor : actors)
		{
			actor.act();
		}
		
		Greenfoot.currentMouseEvent = null;
	}
	
	@Override
	public void mousePressed(int buttonType, int x, int y)
	{
		boolean leftButtonPressed = (buttonType == Input.MOUSE_LEFT_BUTTON);
		
		if (leftButtonPressed)
		{
			System.out.println("Left mouse pressed");
			// check buttons
			for (Actor actor : actors)
			{
				if (actor.checkClicked(new Point(x, y)))
				{
					actor.actOnMouseClick();
					break;
				}
			}
		}
	}
	
}
