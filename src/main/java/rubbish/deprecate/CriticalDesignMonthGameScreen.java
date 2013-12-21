package rubbish.deprecate;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import rubbish.deprecate.voctec.menu.Menu;
import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.language.LabelName;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.TranslatableLabel;

public class CriticalDesignMonthGameScreen extends Menu
{
	public static final int			ID					= 1501;
	public static final int			beamFireTimeDelay	= 2 * 1000;
	public static final int			FULL_CAPACITY		= 500;
	public static final int			PERFECT_HIT_SCORE	= 50;
	public static final double		BEAM_SPEED			= 3;
	public static final Dimension	DesignResolution	= new Dimension(800,
																600);
	
	private Panel					panel;
	private Sun						sun;
	private int						timer				= 0;					// in
																				// miliseconds
	private int						currentScore		= 0;
	private ArrayList<SunBeam>		sunBeams			= new ArrayList<>();
	TranslatableLabel				scoreLabel;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.sun = new Sun(new Point(0, 200), new Point(800, 200), 0, 0.5);
		this.panel = new Panel(new Image(ImagePaths.PANEL), 50, 550);
		// TODO replace with generic label
		scoreLabel = new TranslatableLabel(new LabelName("score", "%"),
				new Rectangle(90, 5, 400, 50));
		scoreLabel.noTranslate = true;
		
		// Add all buttons
		addButton(new Button(
				new Image(ImagePaths.BATTERY).getScaledCopy(0.25f), new Point(
						5, 5)) {
			public void actOnMouseClick()
			{
			}
		}); // Do nothing on mouse click
		
		// Add score label
		addLabel(scoreLabel);
		
		// initialize background image
		Image background = (new Image(ImagePaths.LIGHT_BACKGROUND))
				.getScaledCopy(DesignResolution.width, DesignResolution.height);
		super.initializeBackgroundImage(background);
		
		// ensure the appropriate scale is being used
		resize();
		
		// ensure the appropriate language is being used
		updateTranslation();
	}
	
	public void end()
	{
		// TODO
		System.out.println("End");
		Game.getCurrentGame().enterState(ExitScreen.ID);
	}
	
	public void adjustScore(double angleDelta)
	{
		double angleValue = (Math.abs(180 - angleDelta) / 90);
		if (angleValue > 1)
			angleValue = 2 - angleValue;
		int scoreIncrease = (int) (PERFECT_HIT_SCORE * angleValue);
		System.out.println("Hit: \n\tAngle: "
				+ (Math.abs(180 - angleDelta) / 90) + "\n\tScore: "
				+ scoreIncrease);
		this.currentScore += scoreIncrease;
		
		int scorePercent = (int) (100 * (((double) currentScore) / ((double) FULL_CAPACITY)));
		if (scorePercent >= 100)
		{
			scorePercent = 100;
			this.end();
		}
		this.scoreLabel.setText("%" + scorePercent);
	}
	
	public void fireBeam() throws SlickException
	{
		sunBeams.add(new SunBeam((int) sun.getX() + sun.getImage().getWidth(),
				(int) sun.getY() + sun.getImage().getHeight(), new Random()
						.nextInt(801), 600, BEAM_SPEED));
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		super.render(container, game, graphics);
		this.panel.draw(graphics);
		this.sun.draw(graphics);
		this.scoreLabel.draw(graphics);
		
		for (SunBeam beam : sunBeams)
		{
			beam.draw(graphics);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		actOnInput(container.getInput());
		
		timer += delta;
		if (timer > beamFireTimeDelay)
		{
			timer = timer % beamFireTimeDelay;
			fireBeam();
		}
		
		for (SunBeam beam : sunBeams)
		{
			if (beam.getImage() != null)
			{
				Rectangle beamRectangle = new Rectangle((int) beam.getX(),
						(int) beam.getY(), beam.getImage().getWidth(), beam
								.getImage().getHeight());
				Rectangle panelRectangle = new Rectangle((int) panel.getX(),
						(int) panel.getY(), panel.getCurrentImage().getWidth(),
						panel.getCurrentImage().getHeight());
				
				// Check collision with panel
				if (beamRectangle.intersects(panelRectangle))
				{
					this.adjustScore(Math.abs(beam.getAngle()
							- panel.getAngle()));
					beam.delete();
				}
				
				// Move Beam
				beam.move();
			}
		}
		
		if (this.sun.move())
			this.end();
	}
	
	public void actOnInput(Input input)
	{
		// Panel Movement
		if (input.isKeyDown(Input.KEY_LEFT))
		{
			// Move Panel Left
			panel.moveHorizontal(-5);
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			// Move Panel Right
			panel.moveHorizontal(5);
		}
		
		// Panel Rotation
		if (input.isKeyDown(Input.KEY_Q))
		{
			// Rotate Panel counter-clock-wise (left)
			panel.rotate(-2);
		}
		else if (input.isKeyDown(Input.KEY_R))
		{
			// Rotate Panel clock-wise (right)
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
