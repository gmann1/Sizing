package edu.asu.voctec.cdmg;
import java.awt.Dimension;
import java.util.ArrayList;
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
	static Image Earth;
	
	private static int index = 0;
	
	public static final String APRIL = "resources/default/img/minigames/criticalDesign/april.png";
	public static final String FEBRUARY = "resources/default/img/minigames/criticalDesign/february.png";
	public static final String DECEMBER = "resources/default/img/minigames/criticalDesign/december.png";
	public static final String OCTOBER = "resources/default/img/minigames/criticalDesign/october.png";
	public static final String SEPTEMBER = "resources/default/img/minigames/criticalDesign/september.png";
	public static final String JUNE = "resources/default/img/minigames/criticalDesign/june.png";
	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/space.jpg";
	
	static ArrayList<String> Earths = new ArrayList<>();
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		Earths.add(APRIL);
		Earths.add(FEBRUARY);
		Earths.add(DECEMBER);
		Earths.add(OCTOBER);
		Earths.add(SEPTEMBER);
		Earths.add(JUNE);
		ChildSelectorTest<SelectorIcon> sel = new ChildSelectorTest<SelectorIcon>(120,380);
		Earth = new Image(Earths.get(index));
		
		
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
		
		
		graphics.drawImage(Earth, 250, 100);
		
	}
	public Dimension getDesignResolution()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public static void changeImage(boolean right) throws SlickException{
		if (right){
			index++;
			if (index == Earths.size()){
				index = 0;
			}
			
		}
		else{
			index--;
			if (index == -1){
				index = Earths.size()-1;
			}
		}
		Earth = new Image(Earths.get(index));
		
		
	}
	
	
	
	

}
