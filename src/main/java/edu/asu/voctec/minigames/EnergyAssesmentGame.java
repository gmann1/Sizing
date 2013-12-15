package edu.asu.voctec.minigames;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.ModifiedGameState;
import edu.asu.voctec.minigames.energy_assessment.Watts;
import edu.asu.voctec.minigames.energy_assessment.Wire;
import edu.asu.voctec.minigames.energy_assessment.appliance1;
import edu.asu.voctec.minigames.energy_assessment.appliance2;
import edu.asu.voctec.minigames.energy_assessment.appliance3;
import edu.asu.voctec.minigames.energy_assessment.appliance4;
import edu.asu.voctec.utilities.Greenfoot;

public class EnergyAssesmentGame extends ModifiedGameState
{
	public static final int ID = 80;
	public static final int xOffset = -72;
	public static final int yOffset = -70;
	private ArrayList<Actor> actors = new ArrayList<>();
	
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
	
	//TODO replace Object with Actor
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
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics)
			throws SlickException
	{
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
	public int getID()
	{
		// TODO Auto-generated method stub
		return ID;
	}
	
	@Override
	public void mousePressed(int buttonType, int x, int y)
	{
		boolean leftButtonPressed = (buttonType == Input.MOUSE_LEFT_BUTTON);
		
		if (leftButtonPressed)
		{
			System.out.println("Left mouse pressed");
			//check buttons
			for (Actor actor : actors)
			{
				if (actor.checkClicked(new Point(x,y)))
				{
					actor.actOnMouseClick();
					break;
				}
			}
		}
	}
}
