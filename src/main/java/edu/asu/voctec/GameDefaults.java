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
		public static final String FONT_NAME = "Hiragino Kaku Gothic Pro"; //"Meiryo"; //
		public static final int FONT_SIZE = 36;
		public static final boolean ANTI_ALLIAS = true;
	}
}
