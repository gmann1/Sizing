package edu.asu.voctec.energy_assessment;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.cdmg.CDPart1;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.game_states.SelectorTest.ReadyButtonListener;

public class EAPart1 extends GUI
{
	private static final String DEVICES = "resources/default/img/minigames/energyAssessment/devices.png";
	private static final String POWER_RATING = "resources/default/img/minigames/energyAssessment/powerRating.png";
	private static final String ENERGY_CONSUMED = "resources/default/img/minigames/energyAssessment/energyConsumed.png";
	private static final String ENERGY_LOSS = "resources/default/img/minigames/energyAssessment/energyLoss.png";
	private static final String BUFFER = "resources/default/img/minigames/energyAssessment/buffer.png";
	private static final String Extra1 = "resources/default/img/minigames/energyAssessment/extra1.png";
	private static final String Extra2 = "resources/default/img/minigames/energyAssessment/extra2.png";
	
	private static final String READY = "resources/default/img/minigames/energyAssessment/readyButton.png";
	private static final String BACK = "resources/default/img/minigames/energyAssessment/backButton.png";
	private static final String NOTREADY = "resources/default/img/minigames/energyAssessment/readyButtonGray.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	private EASelectorDisplay<SelectorIcon> eaSelectorDisplay;
	private Selector<SelectorIcon> eaSelector;
	Button ready;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		//basic load things
		this.backgroundImage = new Image(BACKGROUND);
		
		//Text
		Rectangle topTextLocation = new Rectangle(500, 20, 250, 50);
		Rectangle instructionTextLocation = new Rectangle(500, 70, 250, 150);
		
		TextField topText = new TextField(topTextLocation, 0.95f, "Put the steps in the right order", TextDisplay.FormattingOption.FIT_TEXT);
		TextField instructionText = new TextField(instructionTextLocation, 0.95f, "Hint Box", null);
		
		topText.setFontColor(Color.white);
		instructionText.setFontColor(Color.lightGray);
		
		topText.center();
		instructionText.setFillColor(Color.darkGray);
		
		this.addComponent(topText);
		this.addComponent(instructionText);

		////Selector////
		eaSelector = new Selector<>(250, 425, true);
		eaSelector.rescale(0.75f);
		//Selector<SelectorIcon> selectorSteps = new Selector<>(50, 340, true);
		
		//needed
		eaSelector.add(new SelectorIcon(DEVICES, "Inventory Devices", 0));
		eaSelector.add(new SelectorIcon(POWER_RATING, "Determine Power Rating", 1));
		eaSelector.add(new SelectorIcon(ENERGY_CONSUMED, "Calculate Energy consumed", 2));
		eaSelector.add(new SelectorIcon(ENERGY_LOSS, "Account For Energy Loss", 3));
		eaSelector.add(new SelectorIcon(BUFFER, "Include Buffer for Growth of the Load", 4));
		//extra
		eaSelector.add(new SelectorIcon(Extra1, "Extra1", 5));
		eaSelector.add(new SelectorIcon(Extra2, "Extra2", 6));
		
		////display the selector//
		eaSelector.shuffle();
		eaSelector.displayLabel(); //Causes Problems
		this.addComponent(eaSelector);
		
		////Selector display////
		eaSelectorDisplay = new EASelectorDisplay<>(50, 50, true);
		eaSelectorDisplay.rescale(0.80f);
		eaSelectorDisplay.link(eaSelector);
		this.addComponent(eaSelectorDisplay);
		
		////Ready Button////
		ready = new Button(new Image(READY), 575, 500, new Rectangle(50,50,300,50), "");
		ready.addActionListener(new ReadyButtonListener());
		//ready.addActionListener(new TransitionButtonListener(EAPart1ScoreScreen.class));
		this.addComponent(ready);
		
		//Back Button
		Button back = new Button(new Image(BACK), 50, 500, new Rectangle(50,50,300,50), "");
		back.addActionListener(new TransitionButtonListener(EAPart1IntroScreen.class));
		this.addComponent(back);
		
		
		////Testing Stuff can be deleted later////
		System.out.println("EAPart1IntroScreen");
				
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart1ScoreScreen.class));
		//this.addComponent(Start);
		
		
	}
	
	public class ReadyButtonListener extends ButtonListener
	{

		@Override
		protected void actionPerformed()
		{
			System.out.println("Ready!");
			if(eaSelectorDisplay.verifyChoices(true))
			{
				System.out.println("Good!");
				Game.getCurrentGame().enterState(EAPart1ScoreScreen.class);
			}
		}
		
	}
}
