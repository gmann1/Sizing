package edu.asu.voctec;

import java.util.Arrays;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

import edu.asu.voctec.language.Dictionary;
import edu.asu.voctec.utilities.AspectRatio.ResolutionNotSupportedException;
import edu.asu.voctec.utilities.Resizable;
import edu.asu.voctec.utilities.ScreenResolution;
import edu.asu.voctec.utilities.Translatable;

/**
 * Initializes game, and sets up the container and all other necessities. Game,
 * along with AppGameContainer, handles all central game logic.
 * 
 * @see #main(String[])
 * @see Game
 * @author Moore, Zachary
 * 
 */
// TODO move all other logic to Game
public class Main
{
	// TODO move defaults to GameDefaults
	public static final int TARGET_FRAME_RATE = 30;
	public static final String GAME_TITLE = "Sizing";
	public static final boolean SHOW_FPS = false;
	public static final String DEFAULT_LANGUAGE = "english";
	
	private static final int DEFAULT_WINDOW_WIDTH = 800;
	private static final int DEFAULT_WINDOW_HEIGHT = 600;
	// TODO move to Game
	private static ScreenResolution currentScreenDimension;
	private static AppGameContainer gameContainer;
	private static Dictionary currentLanguage = Dictionary
			.constructDictionary("default");
	
	/**
	 * Initializes the game, and sets up the current and supported languages and
	 * settings. All central game logic is handled by AppGameContainer and Game.
	 * 
	 * @see Game
	 * @see AppGameContainer
	 * @param args
	 *            Unused.
	 * @throws ResolutionNotSupportedException
	 *             Thrown if the defaults are set to an unsupported resolution
	 *             (e.g. 0x0)
	 */
	public static void main(String[] args) throws SlickException,
			ResolutionNotSupportedException
	{
		currentScreenDimension = new ScreenResolution(DEFAULT_WINDOW_WIDTH,
				DEFAULT_WINDOW_HEIGHT);
		gameContainer = new AppGameContainer(Game.constructGame());
		
		// load languages and translations
		Dictionary.loadDictionaries();
		
		// set language to default setting
		setCurrentLanguage(Dictionary.getDictionary(DEFAULT_LANGUAGE));
		System.out.println("Additional Characters Loaded: "
				+ Arrays.toString(Dictionary.getExtraCharacters()));
		
		// TODO load settings from file
		// adjust settings
		gameContainer.setShowFPS(SHOW_FPS);
		gameContainer.setTargetFrameRate(TARGET_FRAME_RATE);
		gameContainer.setDisplayMode(DEFAULT_WINDOW_WIDTH,
				DEFAULT_WINDOW_HEIGHT, false);
		gameContainer.setForceExit(false);
		
		// launch game
		gameContainer.start();
	}
	
	public static boolean resize(final ScreenResolution screenDimension)
	{
		boolean resizeSuccessfull;
		Game currentGame = Game.getCurrentGame();
		
		// Update dimension information
		Main.currentScreenDimension = screenDimension;
		
		try
		{
			// Resize all gameStates
			for (int id : Game.getGameStates())
			{
				// Iterate through each GameState
				GameState gameState = currentGame.getState(id);
				if (gameState instanceof Resizable)
				{
					// TODO implement resizing
					// ((Resizable) gameState).resize();
				}
			}
			
			// Resize container (user window)
			gameContainer.setDisplayMode(screenDimension.width,
					screenDimension.height, false);
			
			// If no exceptions were thrown while resizing, then resizing was
			// successful
			resizeSuccessfull = true;
		}
		catch (SlickException e)
		{
			e.printStackTrace();
			resizeSuccessfull = false;
		}
		
		return resizeSuccessfull;
	}
	
	/**
	 * @return a copy of the current resolution Dimension object (i.e. current
	 *         game window resolution)
	 */
	public static ScreenResolution getCurrentScreenDimension()
	{
		// TODO replace with copy
		return Main.currentScreenDimension;
	}
	
	public static AppGameContainer getGameContainer()
	{
		return gameContainer;
	}
	
	public static Dictionary getCurrentLanguage()
	{
		return currentLanguage;
	}
	
	public static void setCurrentLanguage(Dictionary currentLanguage)
	{
		Main.currentLanguage = currentLanguage;
		System.out.println("Updating Language...");
		// translate gameStates
		for (int id : Game.getGameStates())
		{
			// Iterate through each GameState
			GameState gameState = Game.getCurrentGame().getState(id);
			// TODO only translate the current state
			// TODO translate each state upon entry
			// label updates are handled in each gamestate
			if (gameState instanceof Translatable)
				((Translatable) gameState).updateTranslation();
		}
		
		System.out.println("Language Updates Complete.");
	}
}
