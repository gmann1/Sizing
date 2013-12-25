package edu.asu.voctec.game_states;

import java.awt.Rectangle;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.Main;
import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.utilities.UtilFunctions;
import edu.asu.voctec.GameDefaults.ImagePaths.SelectorIcons;

public class SelectorTest extends GUI
{
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		Selector<SelectorIcon> selector = new Selector<>(0, 0, true);
		selector.rescale(0.75f);
		Rectangle centered = new Rectangle(selector.getBounds());
		UtilFunctions.centerRectangleHorizontally(
				new Rectangle(Main.getCurrentScreenDimension()), centered);
		selector.translate(centered.x, 600 - centered.height);
		selector.add(new SelectorIcon(SelectorIcons.ENERGY_ASSESSMENT,
				"Energy Assessment", 0));
		selector.add(new SelectorIcon(SelectorIcons.CRITICAL_DESIGN_MONTH,
				"Determine the Critical Design Month", 1));
		selector.add(new SelectorIcon(SelectorIcons.BATTERY_SIZING,
				"Size the Battery", 2));
		selector.add(new SelectorIcon(SelectorIcons.PV_SIZING,
				"Size the PV Array", 3));
		selector.add(new SelectorIcon(SelectorIcons.CONTROLLER_SIZING,
				"Size the Controllers", 4));
		selector.shuffle();
		selector.displayLabel();
		this.addComponent(selector);
		
		SelectorDisplay<SelectorIcon> selectorDisplay = new SelectorDisplay<>(
				10, 10, true);
		selectorDisplay.rescale(0.90f);
		selectorDisplay.link(selector);
		this.addComponent(selectorDisplay);
		
		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}
}
