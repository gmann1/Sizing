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

public class SelectorTest extends GUI
{
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		Selector<SelectorIcon> selector = new Selector<>(0, 0, true);
		selector.rescale(0.75f);
		Rectangle centered = new Rectangle(selector.getBounds());
		UtilFunctions.centerRectangleHorizontally(new Rectangle(Main.getCurrentScreenDimension()), centered);
		selector.translate(centered.x, 600 - centered.height);
		selector.add(new SelectorIcon(ImagePaths.BATTERY_SIZING, "Step 1", 0));
		selector.add(new SelectorIcon(ImagePaths.PV_SIZING, "Step 2", 1));
		selector.add(new SelectorIcon(ImagePaths.CRITICAL_DESIGN_MONTH,
				"Step 3", 2));
		this.addComponent(selector);

		SelectorDisplay<SelectorIcon> selectorDisplay = new SelectorDisplay<>(
				100, 0, true);
		selectorDisplay.rescale(0.75f);
		selectorDisplay.link(selector);
		this.addComponent(selectorDisplay);
		
		System.out
		.println("Listeners: " + Arrays.toString(this.getListeners()));
	}
}
