package edu.asu.voctec;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import edu.asu.voctec.language.LabelName;

public interface GameDefaults
{
	public interface ImagePaths
	{
		// Backgrounds
		public static final String MainMenuBackground = "resources/default/img/backgrounds/mainMenu.png";
		public static final String OptionsMenuBackground = "resources/default/img/backgrounds/mainMenu.png";
		public static final String ScenarioHubBackground = "resources/default/img/backgrounds/mainMenu.png";
		public static final String TaskHubBackground = "resources/default/img/backgrounds/taskScreen2.png";
		public static final String BLACK_BACKGROUND = "resources/default/img/blackBackground.jpg";
		
		// Buttons
		public static final String BASE_BUTTON = "resources/default/img/buttons/testButton.png";
		public static final String NEW_GAME_BUTTON = "resources/default/img/buttons/newProfileButtonR.png";
		public static final String OPTIONS_BUTTON = "resources/default/img/buttons/optionsButtonR.png";
		public static final String INSTRUCTOR_CONTROL_PANEL_BUTTON = "resources/default/img/buttons/optionsButtonR.png";
		public static final String BACK_BUTTON = "resources/default/img/buttons/backButton.png";
		public static final String LANGUAGE_BUTTON = "resources/default/img/buttons/languagesButtonR.png";
		public static final String READY_BUTTON = "resources/default/img/buttons/readyButton.png";
		
		// Labels
		public static final String BASE_LABEL = "resources/default/img/testButton.png";
		public static final String HINT_BOX = "resources/default/img/misc/hintBox.png";
		
		// Selector Resources
		public static final String ARROW_LEFT = "resources/default/img/selector/ArrowLeft.png";
		public static final String ARROW_RIGHT = "resources/default/img/selector/ArrowRight.png";
		public static final String SELECTOR_LARGE = "resources/default/img/selector/SelectorLarge.png";
		public static final String SELECTOR_SMALL = "resources/default/img/selector/SelectorSmall.png";
		public static final String SELECTOR_SHADOW = "resources/default/img/selector/SelectorShadow.png";
		
		public interface Buttons
		{
			public static final String BASE = "resources/default/img/buttons/testButton.png";
			public static final String NEW_GAME = "resources/default/img/buttons/newProfileButtonR.png";
			public static final String OPTIONS = "resources/default/img/buttons/optionsButtonR.png";
			public static final String INSTRUCTOR_CONTROL_PANEL = "resources/default/img/buttons/optionsButtonR.png";
			public static final String BACK = "resources/default/img/buttons/backButton.png";
			public static final String LANGUAGE = "resources/default/img/buttons/languagesButtonR.png";
			public static final String READY = "resources/default/img/buttons/readyButton.png";
		}
		
		public interface TaskScreen
		{
			public static final String STEP_ONE = "resources/default/img/taskScreen/Task1.png";
			public static final String STEP_TWO = "resources/default/img/taskScreen/Task2.png";
			public static final String STEP_THREE = "resources/default/img/taskScreen/Task3.png";
			public static final String STEP_FOUR = "resources/default/img/taskScreen/Task4.png";
			public static final String STEP_FIVE = "resources/default/img/taskScreen/Task5.png";

			public static final String STEP_ONE_COMPLETE = "resources/default/img/taskScreen/Task1Complete.png";
			public static final String STEP_TWO_COMPLETE = "resources/default/img/taskScreen/Task2Complete.png";
			public static final String STEP_THREE_COMPLETE = "resources/default/img/taskScreen/Task3Complete.png";
			public static final String STEP_FOUR_COMPLETE = "resources/default/img/taskScreen/Task4Complete.png";
			public static final String STEP_FIVE_COMPLETE = "resources/default/img/taskScreen/Task5Complete.png";

			public static final String STEP_ONE_SELECTED = "resources/default/img/taskScreen/Task1Current.png";
			public static final String STEP_TWO_SELECTED = "resources/default/img/taskScreen/Task2Current.png";
			public static final String STEP_THREE_SELECTED = "resources/default/img/taskScreen/Task3Current.png";
			public static final String STEP_FOUR_SELECTED = "resources/default/img/taskScreen/Task4Current.png";
			public static final String STEP_FIVE_SELECTED = "resources/default/img/taskScreen/Task5Current.png";
		}
		
		public interface SelectorIcons
		{
			public static final String ENERGY_ASSESSMENT = "resources/default/img/selector/icons/energyAssessment.png";
			public static final String CRITICAL_DESIGN_MONTH = "resources/default/img/selector/icons/criticalDesignMonth.png";
			public static final String BATTERY_SIZING = "resources/default/img/selector/icons/batterySizing.png";
			public static final String PV_SIZING = "resources/default/img/selector/icons/pvSizing.png";
			public static final String CONTROLLER_SIZING = "resources/default/img/selector/icons/controllerSizing.png";
		}

		public interface SelectorDisplayBorders
		{
			public static final String DEFAULT = "resources/default/img/selector/display/EmptyBox.png";
			public static final String HIGHLIGHTED = "resources/default/img/selector/display/EmptyBoxHighlighted.png";
			public static final String CORRECT = "resources/default/img/selector/display/EmptyBoxCorrect.png";
			public static final String INCORRECT = "resources/default/img/selector/display/EmptyBoxWrong.png";

			public static final String SMALL_ARROW = "resources/default/img/selector/display/smallArrow.png";
			public static final String LARGE_ARROW = "resources/default/img/selector/display/smallArrow.png";
		}
		
	}
	
	public interface SoundPaths
	{
		// Critical Design Month Resources
		public static final String CHARGE = "resources/default/sounds/charge.wav";
	}
	
	public interface XMLPaths
	{
		public static final String Dictionary = "resources/default/globalDictionary.xml";
	}
	
	public interface DictionaryTags
	{
		public static final String LABEL_NAME = "label";
		public static final String SUPPORTED_LANGUAGE = "supported_language";
	}
	
	public interface Labels
	{
		// Critical Design Month Labels
		public interface CriticalDesignMonth
		{
			public static final LabelName start = new LabelName(
					"criticalDesignStart", "Start");
			public static final LabelName exit = new LabelName(
					"criticalDesignExit", "Exit");
			public static final LabelName instructionsLine1 = LabelName.instructionsLine1;
			public static final LabelName instructionsLine2 = LabelName.instructionsLine2;
			public static final LabelName instructionsLine3 = LabelName.instructionsLine3;
			public static final LabelName instructionsLine4 = LabelName.instructionsLine4;
			public static final LabelName instructionsLine5 = LabelName.instructionsLine5;
			public static final LabelName instructionsLine6 = LabelName.instructionsLine6;
		}
		
		// Generic Exit Screen Labels
		public interface ExitScreen
		{
			public static final LabelName instructionsLine1 = new LabelName(
					"criticalDesignInstructionsLn1", "Complete!");
			public static final LabelName instructionsLine2 = new LabelName(
					"criticalDesignInstructionsLn2", "Score:");
			public static final LabelName instructionsLine3 = new LabelName(
					"criticalDesignInstructionsLn3", "Controls:");
			public static final LabelName instructionsLine4 = new LabelName(
					"criticalDesignInstructionsLn4", "Move Left: left arrow");
			public static final LabelName instructionsLine5 = new LabelName(
					"criticalDesignInstructionsLn5", "Move Right: right arrow");
			public static final LabelName instructionsLine6 = new LabelName(
					"criticalDesignInstructionsLn6",
					"Rotate Panel: 'q' and 'r' keys");
		}
		
	}
	
	public interface Fonts
	{
		public static final String FONT_NAME = "Meiryo"; // "Hiragino Kaku Gothic Pro";
		public static final int FONT_SIZE = 36;
		public static final boolean ANTI_ALLIAS = true;
		public static final Color FONT_COLOR = Color.magenta;
		
		public static final Font AWT_FONT = new Font(FONT_NAME,
				Font.BOLD, Fonts.FONT_SIZE);
		public static final TrueTypeFont TRUE_TYPE_FONT = new 
				TrueTypeFont(AWT_FONT, ANTI_ALLIAS);
	}
	
	public interface SelectorDefaults
	{
		public static Dimension ARROW_SIZE = new Dimension(41, 123);
		public static Dimension PRIMARY_SELECTION_SIZE = new Dimension(183, 184);
		public static Dimension SECONDARY_SELECTION_SIZE = new Dimension(124, 123);
		public static Dimension SHADOW_SIZE = new Dimension(494, 122);
		
		public static Point ARROW_LOCATION_LEFT = new Point(0, 91);
		public static Point ARROW_LOCATION_RIGHT = new Point(535, 91);
		public static Point PRIMARY_SELECTION_LOCATION = new Point(196, 0);
		public static Point SECONDARY_SELECTION_LOCATION_LEFT = new Point(52, 91);
		public static Point SECONDARY_SELECTION_LOCATION_RIGHT = new Point(399, 91);
		public static Point SHADOW_LOCATION = new Point(41, 184);
		public static Rectangle ICON_LABEL_BOUNDS = new Rectangle(0, 200, 183, 75);
	}
}
