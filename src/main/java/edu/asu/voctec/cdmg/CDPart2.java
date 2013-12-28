package edu.asu.voctec.cdmg;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GUI.BasicComponent;
import edu.asu.voctec.GUI.Button;
import edu.asu.voctec.GUI.ButtonListener;
import edu.asu.voctec.GUI.TextArea;
import edu.asu.voctec.GUI.TextDisplay;
import edu.asu.voctec.GUI.TextField;
import edu.asu.voctec.GUI.TransitionButtonListener;
import edu.asu.voctec.game_states.GUI;

/**
 * 
 * @author Gabriel Mann
 * 
 */

public class CDPart2 extends GUI {

	private boolean correctAnswer = false;

	public static final float SMALL_FONT_SIZE = 8f;
	public static final float MEDIUM_FONT_SIZE = 12f;
	public static final float LARGE_FONT_SIZE = 18f;

	public static final Color FONT_COLOR = Color.black;
	private static final Color FONT_COLOR1 = Color.darkGray;

	private int hintCount = 0;

	public static final String BACKGROUND = "resources/default/img/minigames/criticalDesign/CDPart2background.png";
	public static final String CRITICAL_MONTH_IMAGE = "resources/default/img/minigames/criticalDesign/decemberMonth.png";
	public static final String PANEL = "resources/default/img/minigames/criticalDesign/panel.png";
	public static final String POLE = "resources/default/img/minigames/criticalDesign/pole.png";

	public TextArea theHints;
	public TextField angle;
	public String s;
	public Image panel1;
	public Button Continue;

	ArrayList<String> genericHints = new ArrayList<>();

	public class CDReadyListener extends ButtonListener {

		@Override
		protected void actionPerformed() {
			winConditional();
		}

	}

	public class CDHintListener extends ButtonListener {

		@Override
		protected void actionPerformed() {
			displayHint(hintCount);

		}

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.backgroundImage = new Image(BACKGROUND);

		// HintBox
		TextArea HintBox = new TextArea(
				new Rectangle(525, 25, 250, 350),
				.95f,
				"Now that you know the critical design month is December, determine the tilt and position of the solar panel. Use up and down arrow to adjust the tilt.");
		HintBox.setFontSize(MEDIUM_FONT_SIZE);
		HintBox.setFontColor(FONT_COLOR);
		// Hints
		theHints = new TextArea(new Rectangle(525, 150, 250, 225), .95f, "");
		theHints.setFontSize(MEDIUM_FONT_SIZE);
		theHints.setFontColor(FONT_COLOR);
		BasicComponent criticalMonth = new BasicComponent(new Image(
				CRITICAL_MONTH_IMAGE), 50, 50);
		criticalMonth.rescale(.60f, .60f);
		criticalMonth.setX(20);
		criticalMonth.setY(20);

		// Initialize Header
		Rectangle headerLocation = criticalMonth.getBounds();
		TextArea header = new TextArea(headerLocation, .95f, "");
		header.setFontSize(10f);
		header.setFontColor(Color.white);
		header.setText("Location: Niger, Niamey - Latitude: 13° 31 N, Longitude: 2° 6 E");

		BasicComponent pole = new BasicComponent(new Image(POLE), 300, 300);
		pole.rescale(.75f);
		pole.setX(225);
		pole.setY(200);

		panel1 = new Image(PANEL);

		panel1 = panel1.getScaledCopy(.75f);
		panel1.setCenterOfRotation(11, 30);
		BasicComponent panel = new BasicComponent(panel1, 50, 50);
		panel1.rotate(-45f);
		panel.setX(265);
		panel.setY(235);

		s = String.format("%d", (int) (panel1.getRotation() + 45));
		angle = new TextField(new Rectangle(300, 175, 500, 500), 0.95f,
				"Angle: " + s + "°", TextDisplay.FormattingOption.CLIP_TEXT);
		angle.setFontSize(LARGE_FONT_SIZE);
		angle.setFontColor(FONT_COLOR);

		// Back Button
		Button Back = new Button(new Image(ImagePaths.BACK_BUTTON), 0, 0,
				new Rectangle(0, 0, 50, 25), "Back");
		Back.addActionListener(new TransitionButtonListener(CDPart1.class));
		Back.rescale(.6f);
		Back.setFontColor(FONT_COLOR1);
		// Ready Button
		Button Ready = new Button(new Image(ImagePaths.READY_BUTTON), 650, 460,
				new Rectangle(0, 0, 50, 25), "");
		Ready.addActionListener(new CDReadyListener());
		Ready.rescale(.75f, .75f);
		Ready.setX(640);
		Ready.setY(510);
		Ready.setFontColor(FONT_COLOR1);
		// Continue Button
		Continue = new Button(new Image(ImagePaths.READY_BUTTON), 850, 650,
				new Rectangle(0, 0, 50, 25), "");
		Continue.addActionListener(new TransitionButtonListener(CDExtra.class));
		Continue.rescale(.75f, .75f);
		Continue.setX(850);
		Continue.setY(660);
		Continue.setFontColor(FONT_COLOR);
		// Hint Button
		Button Hint = new Button(new Image(ImagePaths.SELECTOR_SMALL), 650,
				320, new Rectangle(0, 0, 90, 75), "HINT");
		Hint.addActionListener(new CDHintListener());
		Hint.rescale(.5f, .25f);
		Hint.setFontColor(FONT_COLOR1);
		Hint.setX(540);
		Hint.setY(510);
		// BasicComponent dot = new BasicComponent(new
		// Image("resources/default/img/minigames/criticalDesign/dot.png"),
		// panel.getX() + 11,panel.getY() + 30);

		genericHints.add("Point the solar panel in the direction that captures the most sun. ");
		genericHints.add("Make adjustments due to the latitude and the tilt of the earth.");

		HintBox.enableBorder();
		this.addComponent(HintBox);
		this.addComponent(theHints);
		this.addComponent(criticalMonth);
		this.addComponent(panel);
		this.addComponent(pole);
		this.addComponent(angle);
		this.addComponent(Back);
		this.addComponent(Hint);
		this.addComponent(Ready);
		this.addComponent(header);
		this.addComponent(Continue);

	}

	private void displayHint(int hCount) {
		if (hCount == 0) {
			theHints.setText(genericHints.get(0));
			++hintCount;
			if (!correctAnswer) {
				++CDPart1.hints;
			}
			System.out.println("Generic Hint1 shown, total hints: "
					+ CDPart1.hints);
		}
		if (hCount == 1) {
			theHints.setText(genericHints.get(1));
			++hintCount;
			if (!correctAnswer) {
				++CDPart1.hints;
			}
			System.out.println("Generic Hint2 shown, total hints: "
					+ CDPart1.hints);
		}
		if (hCount > 1) {
			if (hCount % 2 == 0) {
				theHints.setText(genericHints.get(0));
			} else {
				theHints.setText(genericHints.get(1));
			}
			++hintCount;
			System.out
					.println("User requested more hints, none to give. total hints: "
							+ CDPart1.hints);

		}

	}

	private void winConditional() {
		if ((panel1.getRotation() + 45 > (28.3 - 5))
				&& (panel1.getRotation() + 45 < (28.3 + 5))) {
			if ((int)((panel1.getRotation() +45)*10) == 283){
				theHints.setText("Great Job! You got the correct answer exactly.");
			}
			else{
				theHints.setText("Good job! However, the precise answer is 28.3 degrees (latitude + 15 degrees)");
			}
			Continue.setX(640);
			Continue.setY(320);
			correctAnswer = true;
		} else {
			displayHint(hintCount);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		super.update(container, game, delta);
		// container.getInput();

		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			if (panel1.getRotation() + 45 < 90) {
				if ((int)panel1.getRotation() + 45 == 0) {
					panel1.rotate(.1f);

				} else if (panel1.getRotation() + 45 >= (float)89.9) {
					panel1.rotate(.1f);
				} else {
					panel1.rotate(.2f);
				}
			}
			s = String.format("Angle: %.1f°", (panel1.getRotation() + 45));
			angle.setText(s);
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			if (panel1.getRotation() + 45 > 0) {
				if ((int)panel1.getRotation() + 45 == 90) {
					panel1.rotate(-.1f);
				} else if (panel1.getRotation() + 45 <= (float).1) {
					panel1.rotate(-.1f);
				} else {
					panel1.rotate(-.2f);
				}
				
			}
			s = String.format("Angle: %.1f°", (panel1.getRotation() + 45));
			angle.setText(s);

		}

	}

}
