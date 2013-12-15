package edu.asu.voctec;

public interface GameDefaults
{
	public interface ImagePaths
	{
		// Backgrounds
		public static final String MainMenuBackground = "resources/default/img/dualAspectBackground.jpg";
		public static final String OptionsMenuBackground = "resources/default/img/blackBackground.jpg";
		public static final String ScenarioHubBackground = "resources/default/img/dualAspectBackground.jpg";
		
		// Buttons
		public static final String NEW_GAME_BUTTON = "resources/default/img/testButton.png";
		public static final String OPTIONS_BUTTON = "resources/default/img/testButton.png";
		public static final String INSTRUCTOR_CONTROL_PANEL_BUTTON = "resources/default/img/Horo - Square.png";
		public static final String BACK_BUTTON = "resources/default/img/arrow-left.png";
		public static final String LANGUAGE_BUTTON = "resources/default/img/testButton.png";
		
		// Labels
		public static final String BASE_LABEL = "resources/default/img/testButton.png";
		
		// Minigame Resources
		public static final String APPLIANCE_GOOD = "resources/default/img/minigames/appliance1Good.jpg";
		public static final String APPLIANCE_BAD = "resources/default/img/minigames/appliance1.jpg";
		public static final String WATTAGE_GOOD = "resources/default/img/minigames/good.jpg";
		public static final String WATTAGE_BAD = "resources/default/img/minigames/notGood.jpg";
		public static final String TILE_0x0 = "resources/default/img/minigames/tile0x0.jpg";
		public static final String TILE_0x1 = "resources/default/img/minigames/tile0x1.jpg";
		public static final String TILE_0x2 = "resources/default/img/minigames/tile0x2.jpg";
		public static final String TILE_1x0 = "resources/default/img/minigames/tile1x0.jpg";
		public static final String TILE_1x1 = "resources/default/img/minigames/tile1x1.jpg";
		public static final String TILE_1x2 = "resources/default/img/minigames/tile1x2.jpg";
		public static final String TILE_2x0 = "resources/default/img/minigames/tile2x0.jpg";
		public static final String TILE_2x1 = "resources/default/img/minigames/tile2x1.jpg";
		public static final String TILE_2x2 = "resources/default/img/minigames/tile2x2.jpg";
		public static final String TILE_3x0 = "resources/default/img/minigames/tile3x0.jpg";
		public static final String TILE_3x1 = "resources/default/img/minigames/tile3x1.jpg";
		public static final String TILE_3x2 = "resources/default/img/minigames/tile3x2.jpg";
		
		// Critical Design Month Resources
		public static final String BATTERY = "resources/default/img/minigames/criticalDesign/battery.png";
		public static final String BATTERY_CHARGE = "resources/default/img/minigames/criticalDesign/battery.png";
		public static final String INTRO_BACKGROUND = "resources/default/img/minigames/criticalDesign/introSplash.png";
		public static final String LIGHT_BACKGROUND = "resources/default/img/minigames/criticalDesign/lightback.png";
		public static final String CRITICAL_BACKGROUND = "resources/default/img/minigames/criticalDesign/criticalBck.png";
		public static final String DARK_BACKGROUND = "resources/default/img/minigames/criticalDesign/darkBack.png";
		public static final String PANEL = "resources/default/img/minigames/criticalDesign/panel.png";
		public static final String SUN = "resources/default/img/minigames/criticalDesign/sun.png";
		public static final String SUN_BEAM = "resources/default/img/minigames/criticalDesign/sunBeam.png";
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
	
	public interface Fonts
	{
		public static final String FONT_NAME = "Meiryo"; //"Hiragino Kaku Gothic Pro"; //
		public static final int FONT_SIZE = 36;
		public static final boolean ANTI_ALLIAS = true;
	}
}
