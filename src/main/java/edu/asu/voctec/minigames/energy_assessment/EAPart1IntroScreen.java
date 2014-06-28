package edu.asu.voctec.minigames.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.SupportFunctions;
import edu.asu.voctec.GUI.*;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.Task;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.utilities.Position;

public class EAPart1IntroScreen extends GUI implements Task
{
	public static final String ARROW_RIGHT = "resources/default/img/arrow-right.png";
	public static final String INTRODUCTION = "In this game you will need to find a correct combinations of appliances that add up to to the target power rating.";
	private int lc;
	private boolean nextState;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		this.backgroundImage = new Image(ImagePaths.MainMenuBackground);
		
		
		
		// Introduction Body
		TextArea introductionText = SupportFunctions
				.generateIntroductionDisplay(INTRODUCTION);
		
		// Start Button
		Button startButton = new Button(new Image(ARROW_RIGHT), 750, 550,
				new Rectangle(0, 0, 50, 25), "Begin!");
		startButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		startButton.addActionListener(new TransitionButtonListener(
				EAPart2.class));
		startButton.positionText(Position.LEFT);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(
				TaskScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		
		// Add all components to this menu
		this.addComponent(startButton);
		//this.addComponent(welcomeLabel);
		this.addComponent(introductionText);
		this.addComponent(backButton);
		
	}
	
	@Override
	public void load()
	{
		
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		//Game.getCurrentGame();
			super.update(container, game, delta);
			if (!nextState){
				++lc;
				if (lc == 5){
				try {
			
					Game.getCurrentGame().addState(new EAPart2(), Game.getCurrentGame().getContainer());
			
				
				} catch (SlickException e) {
		
					e.printStackTrace();
				}
			nextState = true;
				}
		}
			
	}
	
	@Override
	public void onEnter()
	{
		SupportFunctions.ensureAttemptData();
		Game.getExitScreen().updateExitScreen(this.getClass());
	}
}
