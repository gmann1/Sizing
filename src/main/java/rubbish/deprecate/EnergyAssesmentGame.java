package rubbish.deprecate;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.ScenarioHub;
import edu.asu.voctec.menu.Menu;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.TransitionButton;

public class EnergyAssesmentGame extends Menu
{
	public static final int		ID			= 80;
	public static final int		xOffset		= -72;
	public static final int		yOffset		= -70;
	private ArrayList<Actor>	actors		= new ArrayList<>();
	
	private int					endDelay	= 3 * 1000;
	
	public EnergyAssesmentGame()
	{
		// //Position of Objects
		// appliances
		addObject(new appliance1(), 121 + xOffset, 110 + yOffset);
		addObject(new appliance2(), 121 + xOffset, 250 + yOffset);
		addObject(new appliance3(), 121 + xOffset, 390 + yOffset);
		addObject(new appliance4(), 121 + xOffset, 530 + yOffset);
		
		// Watts
		addObject(Watts.watt4, 680 + xOffset, 110 + yOffset);
		addObject(Watts.watt2, 680 + xOffset, 250 + yOffset);
		addObject(Watts.watt1, 680 + xOffset, 390 + yOffset);
		addObject(Watts.watt3, 680 + xOffset, 530 + yOffset);
		
		// Column 1
		addObject(Wire.wire0x0, 260 + xOffset, 90 + yOffset);
		addObject(Wire.wire1x0, 260 + xOffset, 230 + yOffset);
		addObject(Wire.wire2x0, 260 + xOffset, 370 + yOffset);
		addObject(Wire.wire3x0, 260 + xOffset, 510 + yOffset);
		
		// Column 2
		addObject(Wire.wire0x1, 400 + xOffset, 90 + yOffset);
		addObject(Wire.wire1x1, 400 + xOffset, 230 + yOffset);
		addObject(Wire.wire2x1, 400 + xOffset, 370 + yOffset);
		addObject(Wire.wire3x1, 400 + xOffset, 510 + yOffset);
		
		// Column 3
		addObject(Wire.wire0x2, 540 + xOffset, 90 + yOffset);
		addObject(Wire.wire1x2, 540 + xOffset, 230 + yOffset);
		addObject(Wire.wire2x2, 540 + xOffset, 370 + yOffset);
		addObject(Wire.wire3x2, 540 + xOffset, 510 + yOffset);
	}
	
	public void end()
	{
		// TODO act on vicotry
		System.out.println("Completed EnergyAssesment");
		Game.getCurrentGame().enterState(ExitScreen.ID);
	}
	
	public void addObject(Actor actor, int xLocation, int yLocation)
	{
		actor.setLocation(xLocation, yLocation);
		this.actors.add(actor);
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
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		initializeBackgroundImage(new Image(ImagePaths.BLACK_BACKGROUND));
		// TODO Auto-generated method stub
		Button backButton = new TransitionButton(ImagePaths.BACK_BUTTON, 10,
				-10, ScenarioHub.ID, LayoutOption.BOTTOM_LEFT_ALIGN);
		
		addButton(backButton);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		super.render(container, game, graphics);
		
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
		
		// TODO replace with listener
		// Check victory conditions
		boolean complete = true;
		for (Watts watt : Watts.watts)
		{
			if (!watt.isConnected())
			{
				complete = false;
				break;
			}
		}
		
		if (complete)
		{
			endDelay -= delta;
		}
		
		if (endDelay <= 0)
			this.end();
	}
	
	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return ID;
	}
	
	@Override
	public void mousePressed(int buttonType, int x, int y)
	{
		super.mousePressed(buttonType, x, y);
		
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
	
	@Override
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return new Dimension(800, 600);
	}
}
