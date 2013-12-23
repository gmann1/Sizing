package edu.asu.voctec.game_states;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.Selector;
import edu.asu.voctec.GUI.SelectorDisplay;
import edu.asu.voctec.GUI.SelectorIcon;

public class SelectorTest extends GUI
{
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		Selector<SelectorIcon> selector = new Selector<>(100, 290, true);
		selector.add(new SelectorIcon(ImagePaths.BATTERY_SIZING, "Step 1", 0));
		selector.add(new SelectorIcon(ImagePaths.PV_SIZING, "Step 2", 1));
		selector.add(new SelectorIcon(ImagePaths.CRITICAL_DESIGN_MONTH,
				"Step 3", 2));
		this.addComponent(selector);

		SelectorDisplay<SelectorIcon> selectorDisplay = new SelectorDisplay<>(
				100, 0, true);
		selectorDisplay.link(selector);
		this.addComponent(selectorDisplay);
		
		System.out
		.println("Listeners: " + Arrays.toString(this.getListeners()));
	}
}
