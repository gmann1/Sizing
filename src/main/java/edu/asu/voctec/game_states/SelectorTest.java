package edu.asu.voctec.game_states;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Game;
import edu.asu.voctec.GameDefaults.ImagePaths.SelectorIcons;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorDisplay.DisplayIsFullException;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.information.SizingStepsData;
import edu.asu.voctec.information.TaskData;
import edu.asu.voctec.step_selection.ScenarioIntroductionScreen;
import edu.asu.voctec.step_selection.StepSelectionExitScreen;
import edu.asu.voctec.utilities.CircularList;
import edu.asu.voctec.utilities.Position;
import edu.asu.voctec.utilities.UtilFunctions;
import edu.asu.voctec.utilities.gameTemplate;

public class SelectorTest extends gameTemplate
{
	private static final String endingAnimationPath = "resources/default/img/minigames/animations/DiscoSprites.png";
	
	private SelectorDisplay<SelectorIcon> selectorDisplay;
	private Selector<SelectorIcon> selector;
	private Animation endingAnimation;
	private Rectangle endingAnimationBounds;
	/*
	 * private TextAreaX hintBox; private TextField instructionBox; private
	 * Button readyButton;
	 */
	private boolean complete;
	
	/**
	 * Listener for the ready button. If the button is pressed before all
	 * choices have been made, a message will be displayed asking the user to
	 * complete his/her selections. Otherwise, the user's choices will be
	 * verified and the instructions and hints will be updated accordingly.
	 * 
	 * If this listener is activated after the choices have been verified and
	 * this state is marked as complete, the user will be transfered to the
	 * StepSelectionExitScreen.
	 * 
	 * @author Moore, Zachary
	 * 
	 */
	public class ReadyButtonListener extends ButtonListener
	{
		private static final long serialVersionUID = -914640823203112459L;
		
		@Override
		protected void actionPerformed()
		{
			if (!complete)
			{
				if (!selectorDisplay.isFull())
				{
					instructionBox.setText(Labels.Step0.INSTRUCTIONS_INCOMPLETE
							.getTranslation());
				}
				else
				{
					complete = selectorDisplay.verifyChoices(true);
					
					if (complete)
					{
						try
						{
							updateInstructions();
							hintBox.clear();
							continueButtonOn();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						updateHints();
						instructionBox.setText(Labels.Step0.INSTRUCTIONS_RED
								.getTranslation());
					}
				}
			}
		}
		
	}

	public class HintButtonListener extends ButtonListener
	{
		private static final long serialVersionUID = -914640823203112459L;
		
		@Override
		protected void actionPerformed()
		{
			if (!complete)
			{
				displayGenericHint();
			}
		}
		
	}
	
	public class ContinueButtonListener extends ButtonListener
	{
		private static final long serialVersionUID = -914640823203112459L;
		
		@Override
		protected void actionPerformed()
		{
			if (complete)
				Game.getCurrentGame().enterState(StepSelectionExitScreen.class);
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("\nSelectorTest: Initializing...");
		
		// Keep track of how much time is spent playing this minigame
		trackTime = true;
		
		// Standard Components (Hint Box, Instructions, and Buttons)
		super.init(container, game);
		
		// Selector
		instantiateSelector();
		
		// Selector Display
		instantiateSelectorDisplay();
		
		// Ending Animation
		instantiateEndingAnimation();
		
		// Add Listeners to all Buttons
		setupButtonListeners();
		
		// TODO REMOVE
		this.removeComponent(hintButton);
		
		// Hint Box
		// instantiateHintBox();
		
		// Buttons
		// instantiateButtons();
		
		// Background Image
		// Image background = new Image(ImagePaths.MainMenuBackground);
		// setBackgroundImage(background.getScaledCopy(800, 600));
		
		System.out.println("SelectorTest: Initialization Finished.\n");
	}
	
	public void setupButtonListeners()
	{
		continueButton.addActionListener(new ContinueButtonListener());
		readyButton.addActionListener(new ReadyButtonListener());
		hintButton.addActionListener(new HintButtonListener());
		backButton.addActionListener(new TransitionButtonListener(
				ScenarioIntroductionScreen.class));
	}
	
	public void instantiateSelector() throws SlickException
	{
		// Create and size a new selector object
		selector = new Selector<>(0, 0, true);
		selector.rescale(0.75f);
		
		// Center the selector, along the bottom of the screen
		Rectangle centered = new Rectangle(selector.getBounds());
		UtilFunctions.centerRectangle(control.getBounds(), centered);
		selector.translate(centered.x, centered.y);
		
		// Format selector, and add it to this screen
		selector.displayLabel();
		this.addComponent(selector);
	}
	
	public void instantiateSelectorDisplay()
	{
		// Setup a new selector display (using the default appearance)
		selectorDisplay = new SelectorDisplay<>(0, 0, true);
		
		// Size Display
		selectorDisplay.rescale(0.80f);
		
		// Link Display to Selector
		selectorDisplay.link(selector);
		
		// Center the display in the middle of the play-area
		Rectangle playArea = new Rectangle(Main.getCurrentScreenDimension());
		System.out.println("\tPlayArea Bounds: " + playArea);
		int width = playArea.width - sidePanel.getBounds().width;
		int height = playArea.height - control.getBounds().height;
		playArea.setSize(width, height);
		System.out.println("\tPlayArea Bounds: " + playArea);
		System.out.println("\tDisplay Bounds: " + selectorDisplay.getBounds());
		Rectangle centered = new Rectangle(selectorDisplay.getBounds());
		UtilFunctions.centerRectangle(playArea, centered);
		System.out.println("\tCentered Bounds: " + centered);
		selectorDisplay.translate(centered.x, centered.y);
		
		// Add the display to this screen
		this.addComponent(selectorDisplay);
	}
	
	private void instantiateEndingAnimation() throws SlickException
	{
		// Ending Animation
		Image spriteSheetImage = new Image(endingAnimationPath);
		int fps = 6;
		int frameWidth = 115;
		int frameHeight = 150;
		endingAnimationBounds = new Rectangle(0, 0, frameWidth, frameHeight);
		UtilFunctions.centerRectangle(hintBox.getBounds(),
				endingAnimationBounds);
		SpriteSheet endingAnimationSprites = new SpriteSheet(spriteSheetImage,
				frameWidth, frameHeight);
		endingAnimation = new Animation(endingAnimationSprites, 1000 / fps);
	}
	
	private void instantiateHintBox() throws SlickException
	{
		// Hint Bounds
		Rectangle hintBounds = new Rectangle(398, 62, 370, 320);
		Rectangle relativeHintTextBounds = UtilFunctions.dialateRectangle(
				new Rectangle(0, 0, 370, 320), 0.92f);
		
		// Hint Box
		hintBox = new TextAreaX(hintBounds, relativeHintTextBounds, null);
		Image hintBoxBackground = new Image(
				ImagePaths.Selector.HINT_BOX_BACKGROUND);
		hintBox.setCurrentImage(hintBoxBackground, true);
		
		// Format hint box
		hintBox.setFontSize(Fonts.FONT_SIZE_MEDIUM);
		hintBox.setFontColor(Fonts.FONT_COLOR);
		// instructionBox.center();
		instructionBox.setFontColor(Fonts.FONT_COLOR);
		instructionBox.setFontSize(Fonts.FONT_SIZE_MEDIUM);
		
		// Add hint box to this screen
		this.addComponent(hintBox);
		this.addComponent(instructionBox);
	}
	
	private void instantiateButtons() throws SlickException
	{
		// Ready Button
		Image readyButtonImage = new Image(ImagePaths.Buttons.BASE);
		Rectangle textBounds = UtilFunctions.getImageBounds(readyButtonImage);
		textBounds = UtilFunctions.dialateRectangle(textBounds, 0.75f);
		readyButton = new Button(readyButtonImage, 600, 500, textBounds,
				"Ready");
		readyButton.addActionListener(new ReadyButtonListener());
		readyButton.setFontColor(Fonts.BUTTON_FONT_COLOR);
		this.addComponent(readyButton);
		
		// Back Button
		Button backButton = new Button(new Image(ImagePaths.BACK_BUTTON), 5, 5,
				new Rectangle(0, 0, 50, 25), "Back");
		backButton.addActionListener(new TransitionButtonListener(
				ScenarioIntroductionScreen.class));
		backButton.setFontColor(Fonts.TRANSITION_FONT_COLOR);
		backButton.positionText(Position.RIGHT);
		this.addComponent(backButton);
	}
	
	@Override
	public void onExit()
	{
		save();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException
	{
		super.render(container, game, graphics);
		
		if (complete)
			endingAnimation.draw(endingAnimationBounds.x,
					endingAnimationBounds.y);
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
			this.instructionBox.setText(instructions);
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
			this.instructionBox.setText(instructions);
			System.out.println("Update Instructions: " + instructions);
		}
	}
	
	public void updateHints()
	{
		hintBox.clear();
		
		// Derive hints from the user's current choices (shuffle the hints)
		ArrayList<String> hints = selectorDisplay.deriveHints(true);
		
		// Determine number of hints (2 wrong answers = 1 hint; min 1 hint)
		int answerHintRatio = 2;
		int numberOfHints = hints.size();
		numberOfHints = (answerHintRatio > numberOfHints) ? 1
				: (numberOfHints / answerHintRatio);
		
		// Reduce number of hints to numberOfHints
		while (hints.size() > numberOfHints)
			hints.remove(0);
		
		// Update the number of hints used
		Game.getCurrentTask().getCurrentAttempt().addHints(hints.size());
		
		// Add hints to the hint box (double-spaced)
		hintBox.addLines(hints, true);
	}
	
	public void displayGenericHint()
	{
		hintBox.clear();
		String hint = selectorDisplay.deriveHint();
		hintBox.addLine(hint);
	}
	
	public void load()
	{
		TaskData currentTask = Game.getCurrentTask();
		SizingStepsData currentAttempt = (SizingStepsData) currentTask
				.getCurrentAttempt();
		
		// Create a new attempt instance, if necessary
		if (currentAttempt == null)
		{
			currentTask.setCurrentAttempt(generateDefaultData());
			currentAttempt = (SizingStepsData) currentTask.getCurrentAttempt();
			System.out.println("Current Attempt is null... Resetting...");
		}
		System.out.println("\nLoad Start\n\tCurrent Hints: "
				+ Arrays.toString(currentAttempt.getCurrentHints().toArray()));
		
		// Load from data
		selector.setElements(currentAttempt.getSelectorContents());
		selectorDisplay
				.setElements(currentAttempt.getSelectorDisplayContents());
		ArrayList<String> currentHints = currentAttempt.getCurrentHints();
		hintBox.setText(currentHints);
		selector.updateChoiceLabel();
		updateInstructions();
		complete = currentAttempt.isStepsVerified();
		
		if (selectorDisplay.isFull() && complete)
		{
			try
			{
				selectorDisplay.updateChoiceBorders();
				continueButtonOn();
			}
			catch (SlickException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			selectorDisplay.restoreChoiceBorders();
			readyButton.getTextField().setText("Ready");
		}
		System.out.println("Loaded!\nCurrent Hints: "
				+ Arrays.toString(currentAttempt.getCurrentHints().toArray()));
		System.out.println("HintBox: "
				+ Arrays.toString(hintBox.getText().toArray()));
	}
	
	public SizingStepsData generateDefaultData()
	{
		ArrayList<SelectorIcon> selectorDisplayContents = new ArrayList<>();
		CircularList<SelectorIcon> selectorContents = new CircularList<>();
		ArrayList<String> currentHints = this.hintBox.getText();
		
		// Add each of 5 steps to the selectorContent list
		populateSelectorContents(selectorContents);
		
		// Shuffle the selector choices
		selectorContents.shuffle();
		
		return new SizingStepsData(selectorDisplayContents, selectorContents,
				currentHints, false);
	}
	
	public void populateSelectorContents(
			CircularList<SelectorIcon> selectorContents)
	{
		// Add each of 5 steps to the selectorContent list
		try
		{
			selectorContents.add(new SelectorIcon(
					SelectorIcons.ENERGY_ASSESSMENT, "Energy Assessment", 0));
			selectorContents.add(new SelectorIcon(
					SelectorIcons.CRITICAL_DESIGN_MONTH,
					"Critical Design Month", 1));
			selectorContents.add(new SelectorIcon(SelectorIcons.BATTERY_SIZING,
					"Size the Battery", 2));
			selectorContents.add(new SelectorIcon(SelectorIcons.PV_SIZING,
					"Size the PV Array", 3));
			selectorContents
					.add(new SelectorIcon(SelectorIcons.CONTROLLER_SIZING,
							"Size the Controllers", 4));
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public void save()
	{
		SizingStepsData currentAttempt = (SizingStepsData) Game
				.getCurrentTask().getCurrentAttempt();
		
		ArrayList<SelectorIcon> selectorDisplayContents = this.selectorDisplay
				.getElements();
		CircularList<SelectorIcon> selectorContents = this.selector
				.getElements();
		
		ArrayList<String> currentHints = new ArrayList<>();
		for (String string : this.hintBox.getText())
		{
			currentHints.add(string);
		}
		
		currentAttempt.setCurrentHints(currentHints);
		currentAttempt.setSelectorContents(selectorContents);
		currentAttempt.setSelectorDisplayContents(selectorDisplayContents);
		currentAttempt.setStepsVerified(complete);
	}
}
