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
import edu.asu.voctec.utilities.UtilFunctions;

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

	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	static ArrayList<String> Earths = new ArrayList<>();
	static ArrayList<String> April = new ArrayList<>();
	static ArrayList<String> February = new ArrayList<>();
	static ArrayList<String> December = new ArrayList<>();
	static ArrayList<String> October = new ArrayList<>();
	static ArrayList<String> September = new ArrayList<>();
	static ArrayList<String> June = new ArrayList<>();
	static ArrayList<ArrayList<String>> months = new ArrayList<>();

	static CDSelector<SelectorIcon> sel;
	static TextField main1;
	static TextField main2;
	static TextField main3;
	static TextField right1;
	static TextField right2;
	static TextField right3;
	static TextField left1;
	static TextField left2;
	static TextField left3;

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(BACKGROUND);
		Earths.add(APRIL);
		Earths.add(FEBRUARY);
		Earths.add(DECEMBER);
		Earths.add(OCTOBER);
		Earths.add(SEPTEMBER);
		Earths.add(JUNE);

		months.add(April);
		months.add(February);
		months.add(December);
		months.add(October);
		months.add(September);
		months.add(June);

		April.add("April");
		April.add("\tSpring");
		April.add("\t7.02 PSH/Day");

		February.add("February");
		February.add("\tWinter");
		February.add("\t6.38 PSH/Day");

		December.add("December");
		December.add("Winter");
		December.add("5.24 PSH/Day");

		October.add("October");
		October.add("\tAutumn");
		October.add("\t6.05 PSH/Day");

		September.add("September");
		September.add("\tAutumn");
		September.add("\t5.96 PSH/Day");

		June.add("June");
		June.add("\tSummer");
		June.add("\t6.64 PSH/Day");

		Earth = new Image(Earths.get(index));
		sel = new CDSelector<SelectorIcon>(50, 520);
		sel.rescale(.75f, .75f);

		// Main
		Rectangle textLocation = new Rectangle(0, 0, sel.getMainBounds().width,
				sel.getMainBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getMainBounds(), textLocation);
		textLocation.x = textLocation.x + 10;
		main2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		main2.setFontSize(LARGE_FONT_SIZE);
		textLocation.y = textLocation.y - textLocation.height;
		main1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		main1.setFontSize(LARGE_FONT_SIZE);
		main3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		main3.setFontSize(MEDIUM_FONT_SIZE);
		main1.setText(months.get(index).get(0));
		main2.setText(months.get(index).get(1));
		main3.setText(months.get(index).get(2));

		// Right
		textLocation = new Rectangle(0, 0, sel.getRightBounds().width,
				sel.getRightBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getRightBounds(), textLocation);
		textLocation.x = textLocation.x + 5;
		right2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		right2.setFontSize(MEDIUM_FONT_SIZE);
		textLocation.y = textLocation.y - textLocation.height;
		right1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		right1.setFontSize(MEDIUM_FONT_SIZE);
		right3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		right3.setFontSize(SMALL_FONT_SIZE);
		right1.setText(months.get(index + 1).get(0));
		right2.setText(months.get(index + 1).get(1));
		right3.setText(months.get(index + 1).get(2));

		// Left
		textLocation = new Rectangle(0, 0, sel.getLeftBounds().width,
				sel.getLeftBounds().height / 4);

		UtilFunctions.centerRectangle(sel.getLeftBounds(), textLocation);
		textLocation.x = textLocation.x + 5;
		left2 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		left2.setFontSize(MEDIUM_FONT_SIZE);
		textLocation.y = textLocation.y - textLocation.height;
		left1 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		textLocation.y = textLocation.y + textLocation.height;
		textLocation.y = textLocation.y + textLocation.height;
		left1.setFontSize(MEDIUM_FONT_SIZE);
		left3 = new TextField(textLocation, 0.95f,
				"Fit Text Field ... CLIP CLIP CLIP",
				TextDisplay.FormattingOption.CLIP_TEXT);
		left3.setFontSize(SMALL_FONT_SIZE);
		left1.setText(months.get(5).get(0));
		left2.setText(months.get(5).get(1));
		left3.setText(months.get(5).get(2));
		// Desc.add(April);
		// CDSelectorText April = new CDSelectorText(April1, April2, April3,
		// sel.getMainBounds().width, sel.getMainBounds().height);

		/*
		 * sel.add(April); sel.add(April); sel.add(April); sel.add(April);
		 * sel.add(April); sel.add(April);
		 */
		// Initialize Header
		Rectangle headerLocation = new Rectangle(50, 25, 600, 500);
		TextArea header = new TextArea(headerLocation, .95f,
				"Location: Niger, Niamey - Latitude: 13°31 N, Longitude: 2°6 E");
		header.setFontSize(MEDIUM_FONT_SIZE);
		// HintBox
		TextArea HintBox = new TextArea(new Rectangle(525, 25, 250, 350), .95f,
				"this is the HintBox");
		HintBox.enableBorder();
		this.addComponent(sel);
		this.addComponent(main1);
		this.addComponent(main2);
		this.addComponent(main3);
		this.addComponent(right1);
		this.addComponent(right2);
		this.addComponent(right3);
		this.addComponent(left1);
		this.addComponent(left2);
		this.addComponent(left3);
		this.addComponent(HintBox);
		this.addComponent(header);

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
			if (Earths.get(index) == FEBRUARY) {

				graphics.drawImage(
						Earth,
						(sel.getBounds().width / 2 - Earth.getWidth() / 2 + 73),
						90);
			} else {

				graphics.drawImage(Earth,
						sel.getBounds().width / 2 - Earth.getWidth() / 2 + 70,
						90);
			}
		}

		else {
			graphics.drawImage(Earth,
					sel.getBounds().width / 2 - Earth.getWidth() / 2 + 70, 60);
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
		if (index == 5) {
			changeRight(0);
		} else {
			changeRight(index + 1);
		}
		if (index == 0) {
			changeLeft(5);
		} else {
			changeLeft(index - 1);
		}
		changeMain(index);
		Earth = new Image(Earths.get(index));

	}

	public static void changeMain(int ind) {
		main1.setText(months.get(ind).get(0));
		main2.setText(months.get(ind).get(1));
		main3.setText(months.get(ind).get(2));

	}

	public static void changeRight(int ind) {
		right1.setText(months.get(ind).get(0));
		right2.setText(months.get(ind).get(1));
		right3.setText(months.get(ind).get(2));

	}

	public static void changeLeft(int ind) {
		left1.setText(months.get(ind).get(0));
		left2.setText(months.get(ind).get(1));
		left3.setText(months.get(ind).get(2));

	}

}
