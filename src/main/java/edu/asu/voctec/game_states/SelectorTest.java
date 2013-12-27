package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.ImagePaths.SelectorIcons;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextAreaX;
import edu.asu.voctec.utilities.UtilFunctions;

public class SelectorTest extends GUI
{
	private SelectorDisplay<SelectorIcon> selectorDisplay;
	private Selector<SelectorIcon> selector;
	private TextAreaX hintBox;
	private TextAreaX instructionsLabel;
	
	public class ReadyButtonListener extends ButtonListener
	{
		
		@Override
		protected void actionPerformed()
		{
			// TODO Auto-generated method stub
			System.out.println("Ready!");
			selectorDisplay.verifyChoices(true);
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
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
		
		// Hint box
		Rectangle hintBounds = new Rectangle(398, 42, 365, 303);
		Rectangle hintTextBounds = new Rectangle(398, 103, 365, 242);
		Rectangle instructionTextBounds = new Rectangle(398, 42, 365, 61);
		hintBox = new TextAreaX(hintBounds, hintTextBounds, null);
		instructionsLabel = new TextAreaX(instructionTextBounds,
				instructionTextBounds, null);
		Image hintBoxBackground = new Image(ImagePaths.Selector.HINT_BOX_BACKGROUND);
		hintBox.setCurrentImage(hintBoxBackground, true);
		
		// Format hint box
		hintBox.setFontSize(16f);
		instructionsLabel.setFontSize(16f);
		hintBox.setFontColor(Color.darkGray);
		instructionsLabel.setFontColor(Color.darkGray);
		
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
	}
}
