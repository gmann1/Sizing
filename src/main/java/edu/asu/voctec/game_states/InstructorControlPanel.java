package edu.asu.voctec.game_states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.BasicComponent;

public class InstructorControlPanel extends GUI
{

	private int counter = 0;
	private int frame = 2;
	
	BasicComponent logo;
	private static final String LOGO1 = "resources/default/img/voctecLogo/Intro0001.png";
	private static final String LOGO2 = "resources/default/img/voctecLogo/Intro0002.png";
	private static final String LOGO3 = "resources/default/img/voctecLogo/Intro0003.png";
	private static final String LOGO4 = "resources/default/img/voctecLogo/Intro0004.png";
	private static final String LOGO5 = "resources/default/img/voctecLogo/Intro0005.png";
	private static final String BACKGROUND = "resources/default/img/voctecLogo/WhiteBackground.png";
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		logo = new BasicComponent(new Image(LOGO1), 125, 100);
		this.addComponent(logo);
		this.backgroundImage = new Image(BACKGROUND);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		super.update(container, game, delta);
		
		if(counter == frame)
		{
			logo.setCurrentImage(new Image(LOGO1), true);
		}
		else if(counter== frame*4)
		{
			logo.setCurrentImage(new Image(LOGO2), true);
		}
		else if(counter == frame*6)
		{
			logo.setCurrentImage(new Image(LOGO3), true);
		}
		else if(counter == frame*8)
		{
			logo.setCurrentImage(new Image(LOGO4), true);
		}
		else if(counter == frame*10)
		{
			logo.setCurrentImage(new Image(LOGO5), true);
		}
		else if(counter>=20)
		{
			counter = 0;
			Game.getCurrentGame().secondaryStatesList(container);
		}
		
		counter ++;
	}
	
}
