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
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.GUI.SelectorDisplay.DisplayIsFullException;
import edu.asu.voctec.GameDefaults.Fonts;
import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.Labels;
import edu.asu.voctec.cdmg.CDPart1;
import edu.asu.voctec.game_states.GUI;
import edu.asu.voctec.game_states.TaskScreen;
import edu.asu.voctec.game_states.SelectorTest.ReadyButtonListener;
import edu.asu.voctec.step_selection.StepSelectionExitScreen;
import edu.asu.voctec.utilities.UtilFunctions;

public class EAPart1 extends GUI
{
	private final String DEVICES = "resources/default/img/minigames/energyAssessment/part1/devices.png";
	private static final String POWER_RATING = "resources/default/img/minigames/energyAssessment/part1/powerRating.png";
	private static final String ENERGY_CONSUMED = "resources/default/img/minigames/energyAssessment/part1/energyConsumed.png";
	private static final String ENERGY_LOSS = "resources/default/img/minigames/energyAssessment/part1/energyLoss.png";
	private static final String BUFFER = "resources/default/img/minigames/energyAssessment/part1/buffer.png";
	private static final String Extra1 = "resources/default/img/minigames/energyAssessment/part1/extra1.png";
	private static final String Extra2 = "resources/default/img/minigames/energyAssessment/part1/extra2.png";
	 
	private static final String READY = "resources/default/img/minigames/energyAssessment/readyButton.png";
	private static final String BACK = "resources/default/img/minigames/energyAssessment/backButton.png";
	private static final String NOTREADY = "resources/default/img/minigames/energyAssessment/readyButtonGray.png";
	private static final String BACKGROUND = "resources/default/img/minigames/energyAssessment/background.png";
	
	private static EASelectorDisplay<SelectorIcon> eaSelectorDisplay;
	private Selector<SelectorIcon> eaSelector;
	Button ready;
	
	private static TextField instructionsLabel;
	private TextArea hintText;
	private int hintNumber;
	private static boolean complete;
	
	private String instructions1 = "Select which step goes first.";
	private String instructions2 = "Select which step goes second.";
	private String instructions3 = "Select which step goes third.";
	private String instructions4 = "Select which steps should go last.";
	private String instructions5 = "Press the ready button to verify your choices.";
	
	private String instructionsNeedMore = "There are still more steps!";
	private String instructionsRed = "Click the red icons to deselect them.";
	
	
	private String[] hintTextArray = 
		{
			"Hint 1",
			"Hint 2",
			"Hint 3"
		};
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		//basic load things
		this.backgroundImage = new Image(BACKGROUND);
		
		initializeHintBox();
		
		////Instructions Text////
		instructionsLabel = new TextField(new Rectangle(398, 0, 370, 62), 0.95f, instructions1 ,TextDisplay.FormattingOption.FIT_TEXT);
		instructionsLabel.center();
		instructionsLabel.setFontColor(Fonts.FONT_COLOR);
		instructionsLabel.setFontSize(Fonts.FONT_SIZE_MEDIUM);
		this.addComponent(instructionsLabel);

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
		Image readyButtonImage = new Image(ImagePaths.Buttons.BASE);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.75f);
		
		ready = new Button(readyButtonImage, 575, 500, textBounds, "Ready");
		ready.addActionListener(new ReadyButtonListener());
		ready.setFontColor(Fonts.BUTTON_FONT_COLOR);
		//ready.addActionListener(new TransitionButtonListener(EAPart1ScoreScreen.class));
		this.addComponent(ready);
		
		//Back Button
		Button back = new Button(new Image(BACK), 50, 500, new Rectangle(50,50,300,50), "");
		back.addActionListener(new TransitionButtonListener(EAPart1IntroScreen.class));
		this.addComponent(back);
		
		
		////Testing Stuff can be deleted later////
		Button Start = new Button(new Image(ImagePaths.ARROW_RIGHT), 750, 0, new Rectangle(50,50,300,50), "Start!");
		Start.addActionListener(new TransitionButtonListener(EAPart1ScoreScreen.class));
		this.addComponent(Start);
	}
	
	public void initializeHintBox()
	{
		hintText = new TextArea(new Rectangle(500, 70, 250, 225), 0.95f, "");
		hintText.setFontSize(16);
		hintText.setFontColor(Color.white);
		hintText.setFillColor(Color.darkGray);
		this.addComponent(hintText);
	}
	
	private void HintNext()
	{
		hintText.setText(hintTextArray[hintNumber]);
		hintNumber++;
		if(hintNumber>2)
			hintNumber = 0;
	}
	
	public static void updateInstructions()
	{
		try
		{
			// Determine which step is to be decided next
			int firstEmpty = eaSelectorDisplay.getCurrentIndex() + 1;
			
			// Convert integer to an ordinal string
			String ordinalNumber = UtilFunctions
					.getOrdinalRepresentation(firstEmpty);
			
			// Set instructions label text
			String instructions = Labels.Step0.INSTRUCTIONS1.getTranslation()
					+ " " + ordinalNumber
					+ Labels.Step0.INSTRUCTIONS2.getTranslation();
			instructionsLabel.setText(instructions);
			System.out.println("Update Instructions: " + instructions);
		}
		catch (DisplayIsFullException e)
		{
			// Determine instruction text
			String instructions;
			if (complete)
				instructions = Labels.Step0.INSTRUCTIONS_CORRECT
						.getTranslation();
			else
				instructions = Labels.Step0.INSTRUCTIONS_COMPLETE
						.getTranslation();
			
			// Set instructions label text
			instructionsLabel.setText(instructions);
			System.out.println("Update Instructions: " + instructions);
		}
	}
	
	public class ReadyButtonListener extends ButtonListener
	{
		private static final long serialVersionUID = -914640823203112459L;

		@Override
		protected void actionPerformed()
		{
			if (complete)
			{
				Game.getCurrentGame().enterState(EAPart1ScoreScreen.class);
			}
			else
			{
				if (!eaSelectorDisplay.isFull())
				{
					instructionsLabel.setText(Labels.Step0.INSTRUCTIONS_INCOMPLETE.getTranslation());
				}
				else
				{
					complete = eaSelectorDisplay.verifyChoices(true);
					
					if (complete)
					{
						updateInstructions();
						ready.getTextField().setText("Continue");
						//hintBox.clear();
					}
					else
					{
						HintNext();
						instructionsLabel.setText(Labels.Step0.INSTRUCTIONS_RED.getTranslation());
					}
				}
			}
		}
		
	}
	
	/*public class ReadyButtonListener extends ButtonListener
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
			else
				HintNext();
		}
	}*/
	
	public static void reset()
	{
		System.out.println("eaPart1 Reset");
	}
}
