package edu.asu.voctec.cdmg;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.game_states.GUI;

import org.newdawn.slick.Image;

public class CDPart2 extends GUI {
	
	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;
	
	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/CDPart2background.png";
	public static final String CRITICAL_MONTH_IMAGE = "resources/default/img/minigames/criticalDesign/decemberMonth.png";
	public static final String PANEL = "resources/default/img/minigames/criticalDesign/panel.png";
	public static final String POLE = "resources/default/img/minigames/criticalDesign/pole.png";
	public TextArea theHints;
	
	public Image panel1;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(BACKGROUND);
		// HintBox
		TextArea HintBox = new TextArea(
				new Rectangle(525, 25, 250, 350),
				.95f,
				"Now that you know the critical design month, you should determine the tilt and position of the panel.");
		HintBox.setFontSize(MEDIUM_FONT_SIZE);
		// Hints
		theHints = new TextArea(new Rectangle(525, 150, 250, 225), .95f,
				"");
		theHints.setFontSize(MEDIUM_FONT_SIZE);
		
		
		BasicComponent criticalMonth = new BasicComponent(new Image(CRITICAL_MONTH_IMAGE), 50, 50);
		criticalMonth.rescale(.60f, .60f);
		criticalMonth.setX(20);
		criticalMonth.setY(20);
		
		BasicComponent pole = new BasicComponent(new Image(POLE), 300,300);
		pole.rescale(.75f);
		pole.setX(225);
		pole.setY(200);
		
		panel1 = new Image(PANEL);
		
		panel1 = panel1.getScaledCopy(.75f);
		panel1.setCenterOfRotation(11, 30);
		BasicComponent panel = new BasicComponent(panel1, 50,50);
		panel1.rotate(-45f);
		panel.setX(265);
		panel.setY(235);
	
		
		
		//BasicComponent dot = new BasicComponent(new Image("resources/default/img/minigames/criticalDesign/dot.png"), panel.getX() + 11,panel.getY() + 30);
		HintBox.enableBorder();
		this.addComponent(HintBox);
		this.addComponent(theHints);
		this.addComponent(criticalMonth);
		this.addComponent(panel);
		this.addComponent(pole);
		

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		super.update(container, game, delta);
	}

}
