package edu.asu.voctec.game_states;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults.ImagePaths;
import edu.asu.voctec.GameDefaults.ImagePaths.SelectorIcons;
import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.utilities.UtilFunctions;

public class SelectorTest extends GUI
{
	private SelectorDisplay<SelectorIcon> selectorDisplay;
	private Selector<SelectorIcon> selector;
	
	public class ReadyButtonListener extends ButtonListener
	{

		@Override
		protected void actionPerformed()
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		// Create and size a new selector object
		selector = new Selector<>(0, 0, true);
		selector.rescale(0.75f);
		
		// Center the selector, along the bottom of the screen
		Rectangle centered = new Rectangle(selector.getBounds());
		UtilFunctions.centerRectangleHorizontally(
				new Rectangle(Main.getCurrentScreenDimension()), centered);
		selector.translate(centered.x, 600 - centered.height);
		
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
		
		// Setup a new selector display, and link it to the selector
		selectorDisplay = new SelectorDisplay<>(
				10, 10, true);
		selectorDisplay.rescale(0.90f);
		selectorDisplay.link(selector);
		
		// Add the display to this screen
		this.addComponent(selectorDisplay);
		
		// Create and add a new ReadyButton
		//Button readyButton = new Button();
		
		// Set background
		Image background = new Image(ImagePaths.MainMenuBackground);
		setBackgroundImage(background.getScaledCopy(800, 600));
	}
}
