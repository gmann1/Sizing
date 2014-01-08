package edu.asu.voctec.energy_assessment;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.ActionListener;
import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.utilities.UtilFunctions;

public class EAPart2 extends GUI
{
	//Images
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/part2/HouseBackground.png";
	
	//Buttons
	Button ready;
	
	//Text Fields
	private static TextField appliance0Watt;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.backgroundImage = new Image(BACKGROUND);
		
		////Ready Button////
		//ready = new Button(new Image(READY), 575, 500, new Rectangle(50,50,300,50), "");
		//ready.addActionListener(new ReadyButtonListener());
		//this.addComponent(ready);
		
		////Testing Stuff can be deleted later////
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart2ScoreScreen.class));
		this.addComponent(Start);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,Graphics graphics) throws SlickException 
	{
		super.render(container, game, graphics);
		//graphics.drawImage(new Image(applianceImage[v][1]),applianceArray[v][1],applianceArray[v][2]);
	}
		
	public class ReadyButtonListener extends ButtonListener
	{
		@Override
		protected void actionPerformed()
		{
			
		}
	}
	
	public void initializeHintBox()
	{

	}
	
	private void initializeText()
	{

	}
	
	public static void reset()
	{
		System.out.println("eaPart2 Reset");
	}
	
}
