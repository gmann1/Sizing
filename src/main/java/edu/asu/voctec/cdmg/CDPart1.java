package edu.asu.voctec.cdmg;
import java.awt.Dimension;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.game_states.GUI;
public class CDPart1 extends GUI {
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image("resources/default/img/minigames/criticalDesign/space.jpg");
		
		Selector<SelectorIcon> sel = new Selector<>(120,380);
	
		
		
		//SelectorIcon janEarth = new SelectorIcon(new Image("resources/default/img/minigames/criticalDesign/space.jpg"), 0,0, "janearth", "janearth", 5401);
		//janEarth.getBaseImage().getScaledCopy(width, height)
		this.addComponent(sel);
		
		
		//this.addComponent(new Selector<SelectorIcon>(100, 100));
		
		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		super.render(container, game, graphics);
		Image janEarth = new Image("resources/default/img/minigames/criticalDesign/January.png");
	
		graphics.drawImage(janEarth, 250, 100);
	}
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return null;
	}
	

}
