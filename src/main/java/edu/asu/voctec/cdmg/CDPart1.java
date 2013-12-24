package edu.asu.voctec.cdmg;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.game_states.GUI;

public class CDPart1 extends GUI {
	static Image Earth;

	private static int index = 0;

	public static final String APRIL = "resources/default/img/minigames/criticalDesign/april.png";
	public static final String FEBRUARY = "resources/default/img/minigames/criticalDesign/february.png";
	public static final String DECEMBER = "resources/default/img/minigames/criticalDesign/december.png";
	public static final String OCTOBER = "resources/default/img/minigames/criticalDesign/october.png";
	public static final String SEPTEMBER = "resources/default/img/minigames/criticalDesign/september.png";
	public static final String JUNE = "resources/default/img/minigames/criticalDesign/june.png";
	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/space.jpg";

	static ArrayList<String> Earths = new ArrayList<>();
	static ArrayList<TextArea> Desc = new ArrayList<>();
	static CDSelector<SelectorIcon> sel;

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(BACKGROUND);
		Earths.add(APRIL);
		Earths.add(FEBRUARY);
		Earths.add(DECEMBER);
		Earths.add(OCTOBER);
		Earths.add(SEPTEMBER);
		Earths.add(JUNE);

		Earth = new Image(Earths.get(index));
		sel = new CDSelector<SelectorIcon>(120, 380);
		Rectangle textLocation = new Rectangle(0, 0, sel.getMainBounds().width,
				sel.getMainBounds().height / 4);
		TextField April1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.FIT_TEXT);
		TextField April2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.FIT_TEXT);
		TextField April3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.FIT_TEXT);
		// Desc.add(April);
		CDSelectorText April = new CDSelectorText(April1, April2, April3,
				sel.getMainBounds().width, sel.getMainBounds().height);
		

		sel.add(April);
		sel.add(April);
		sel.add(April);
		sel.add(April);
		sel.add(April);
		sel.add(April);
		this.addComponent(sel);
		// this.addComponent(April);

		// this.addComponent(new Selector<SelectorIcon>(100, 100));

		System.out
				.println("Listeners: " + Arrays.toString(this.getListeners()));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics graphics) throws SlickException {
		super.render(container, game, graphics);

		if (Earths.get(index) != JUNE) {
			graphics.drawImage(Earth, 250, 100);
		} else {
			graphics.drawImage(Earth, 250, 70);
		}

	}

	public Dimension getDesignResolution() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void changeImage(boolean right) throws SlickException {
		if (right) {
			index++;
			if (index == Earths.size()) {
				index = 0;

			}
			

		} else {
			index--;
			if (index == -1) {
				index = Earths.size() - 1;
			}
		}
		Earth = new Image(Earths.get(index));

	}

}
