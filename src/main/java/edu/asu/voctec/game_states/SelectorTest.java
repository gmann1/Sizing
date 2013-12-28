package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.ImagePaths.SelectorIcons;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.SelectorDisplay.DisplayIsFullException;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.utilities.UtilFunctions;

public class SelectorTest extends GUI
{
	private SelectorDisplay<SelectorIcon> selectorDisplay;
	private Selector<SelectorIcon> selector;
	private TextAreaX hintBox;
	private TextField instructionsLabel;
	private boolean complete;
	
	public class ReadyButtonListener extends ButtonListener
	{
		
		@Override
		protected void actionPerformed()
		{
			System.out.println("Ready!");
			if (complete)
			{
				// TODO transition to score screen
			}
			else
			{
				if (!selectorDisplay.isFull())
				{
					instructionsLabel
							.setText(Labels.Step0.INSTRUCTIONS_INCOMPLETE
									.getTranslation());
				}
				else
				{
					complete = selectorDisplay.verifyChoices(true);
					updateInstructions();
					if (!complete)
						instructionsLabel.setText(Labels.Step0.INSTRUCTIONS_RED
								.getTranslation());
				}
			}
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("\nSelectorTest: Initializing...");
		
		// Selector
		// Create and size a new selector object
		selector = new Selector<>(0, 0, true);
		selector.rescale(0.75f);
		
		// Center the selector, along the bottom of the screen
		Rectangle centered = new Rectangle(selector.getBounds());
		UtilFunctions.centerRectangleHorizontally(
				new Rectangle(Main.getCurrentScreenDimension()), centered);
		selector.translate(centered.x - 75, 600 - centered.height);
		
		// Add each of 5 steps to the selector
		selector.add(new SelectorIcon(SelectorIcons.ENERGY_ASSESSMENT,
				"Energy Assessment", 0));
		selector.add(new SelectorIcon(SelectorIcons.CRITICAL_DESIGN_MONTH,
				"Critical Design Month", 1));
		selector.add(new SelectorIcon(SelectorIcons.BATTERY_SIZING,
				"Size the Battery", 2));
		selector.add(new SelectorIcon(SelectorIcons.PV_SIZING,
				"Size the PV Array", 3));
		selector.add(new SelectorIcon(SelectorIcons.CONTROLLER_SIZING,
				"Size the Controllers", 4));
		
		// Format selector, and add it to this screen
		selector.shuffle();
		selector.displayLabel();
		this.addComponent(selector);
		
		// Selector Display
		// Setup a new selector display, and link it to the selector
		selectorDisplay = new SelectorDisplay<>(38, 28, true);
		selectorDisplay.rescale(0.80f);
		selectorDisplay.link(selector);
		
		// Add the display to this screen
		this.addComponent(selectorDisplay);
		
		// Hint Bounds
		Rectangle hintBounds = new Rectangle(398, 62, 365, 303);
		Rectangle relativeHintTextBounds = new Rectangle(0, 0, 365, 303);
		Rectangle instructionBounds = new Rectangle(398, 0, 365, 62);
		// Rectangle relativeInstructionTextBounds = new Rectangle(0, 0, 365,
		// 61);
		
		// Hint Box Initialization
		hintBox = new TextAreaX(hintBounds, relativeHintTextBounds, null);
		instructionsLabel = new TextField(instructionBounds, 0.95f, null,
				TextDisplay.FormattingOption.FIT_TEXT);
		Image hintBoxBackground = new Image(
				ImagePaths.Selector.HINT_BOX_BACKGROUND);
		hintBox.setCurrentImage(hintBoxBackground, true);
		
		// Format hint box
		hintBox.setFontSize(10f);
		instructionsLabel.center();
		hintBox.setFontColor(Color.white);
		instructionsLabel.setFontColor(Color.white);
		
		// Add hint box to this screen
		this.addComponent(hintBox);
		this.addComponent(instructionsLabel);
		
		// Create and add a new ReadyButton
		Image readyButtonImage = new Image(ImagePaths.READY_BUTTON);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.80f);
		Button readyButton = new Button(readyButtonImage, 600, 500, textBounds,
				null);
		readyButton.addActionListener(new ReadyButtonListener());
		this.addComponent(readyButton);
		
		// Set background
		Image background = new Image(ImagePaths.MainMenuBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
		
		updateInstructions();
		
		System.out.println("SelectorTest: Initialization Finished.\n");
	}
	
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		super.render(container, game, graphics);
	}
	
	public void updateInstructions()
	{
		try
		{
			// Determine which step is to be decided next
			int firstEmpty = this.selectorDisplay.getCurrentIndex() + 1;
			
			// Convert integer to an ordinal string
			String ordinalNumber = UtilFunctions
					.getOrdinalRepresentation(firstEmpty);
			
			// Set instructions label text
			String instructions = Labels.Step0.INSTRUCTIONS1.getTranslation()
					+ " " + ordinalNumber
					+ Labels.Step0.INSTRUCTIONS2.getTranslation();
			this.instructionsLabel.setText(instructions);
			System.out.println("Update Instructions: " + instructions);
		}
		catch (DisplayIsFullException e)
		{
			// Determine instruction text
			String instructions;
			instructions = Labels.Step0.INSTRUCTIONS_COMPLETE.getTranslation();
			
			// Set instructions label text
			this.instructionsLabel.setText(instructions);
			System.out.println("Update Instructions: " + instructions);
		}
	}
}
