package edu.asu.voctec.menu.energy_assessment;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.menu.Menu;
import edu.asu.voctec.menu.buttons.Button;

public class CriticalDesignMonthGameScreen extends Menu
{
	public static final int ID = 1501;
	public static final Dimension DesignResolution = new Dimension(800, 600);
	
	private Panel panel;
	private Sun sun;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.sun = new Sun(new Point(0, 200), new Point(800, 200), 0, 0.8);
		this.panel = new Panel(new Image(ImagePaths.PANEL), 50, 550);
		addButton(new Button(new Image(ImagePaths.BATTERY).getScaledCopy(0.25f),
				  new Point(5,5)) {public void actOnMouseClick() {}}); // Do nothing on mouse click
		
		//initialize background image
		Image background = (new Image(ImagePaths.LIGHT_BACKGROUND))
				.getScaledCopy(DesignResolution.width, DesignResolution.height);
		super.initializeBackgroundImage(background);
		
		//ensure the appropriate scale is being used
		resize();
		
		//ensure the appropriate language is being used
		updateTranslation();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics)
			throws SlickException
	{
		super.render(container, game, graphics);
		this.panel.draw(graphics);
		this.sun.draw(graphics);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		this.sun.move();
		
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_LEFT))
		{
			panel.moveHorizontal(-5);
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			panel.moveHorizontal(5);
		}
		
		if (input.isKeyDown(Input.KEY_Q))
		{
			panel.rotate(-2);
		}
		else if (input.isKeyDown(Input.KEY_R))
		{
			panel.rotate(2);
		}
	}

	@Override
	public Dimension getDesignResolution()
	{
		return DesignResolution;
	}

	@Override
	public int getID()
	{
		return ID;
	}

}
