package edu.asu.voctec.batter_sizing;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.game_states.GUI;

public class BatteryExitScreen extends GUI
{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// TODO Auto-generated method stub
		this.backgroundImage = new Image("resources/default/img/minigames/BatterySizing/WhiteBackground.jpg");
		
	}
	
}
