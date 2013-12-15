package edu.asu.voctec;

import java.util.Arrays;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

import edu.asu.voctec.AspectRatio.ResolutionNotSupportedException;
import edu.asu.voctec.menu.buttons.Dictionary;
import edu.asu.voctec.utilities.Resizable;
import edu.asu.voctec.utilities.Translatable;

public class Main
{
	public static final int TARGET_FRAME_RATE = 30;
	public static final String GAME_TITLE = "Sizing";
	public static final boolean SHOW_FPS = false;
	public static final String DEFAULT_LANGUAGE = "english";
	
	private static final int DEFAULT_WINDOW_WIDTH = 800;
	private static final int DEFAULT_WINDOW_HEIGHT = 600;
	private static ScreenResolution currentScreenDimension;
	private static AppGameContainer gameContainer;
	private static Dictionary currentLanguage = Dictionary.constructDictionary("default");
	
	public static void main(String[] args) throws SlickException, ResolutionNotSupportedException
	{
		currentScreenDimension = new ScreenResolution(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		gameContainer = new AppGameContainer(Game.constructGame());
		
		//load languages and translations
		Dictionary.loadDictionaries();
		
		//set language to default setting
		setCurrentLanguage(Dictionary.getDictionary(DEFAULT_LANGUAGE));
		System.out.println("Additional Characters Loaded: " + Arrays.toString(Dictionary.getExtraCharacters()));

		//TODO load settings from file
		//adjust settings
		gameContainer.setShowFPS(SHOW_FPS);
		gameContainer.setTargetFrameRate(TARGET_FRAME_RATE);
		gameContainer.setDisplayMode(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, false);
		gameContainer.setForceExit(false);
		
		//launch game
		gameContainer.start();
	}
	
	public static boolean resize(ScreenResolution screenDimension)
	{
		boolean resizeSuccessfull = false;
		Game currentGame = Game.getCurrentGame();
		
		// Update dimension information
		Main.currentScreenDimension = screenDimension;
		
		try
		{
			//resize gameStates
			for (int id : Game.GAME_STATES)
			{
				// Iterate through each GameState
				GameState gameState = currentGame.getState(id);
				//TODO only resize the current state
				//TODO resize each state upon entry
				// Image cropping and resizing is handled by each Resizable gameState
				if (gameState instanceof Resizable)
					((Resizable) gameState).resize();
			}

			//resize container (user window) //false indicates windowed mode (not full-screen)
			gameContainer.setDisplayMode(Main.currentScreenDimension.width, Main.currentScreenDimension.height, false);
			
			// If no exceptions were thrown while resizing, then resizing was successful
			resizeSuccessfull = true;
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		return resizeSuccessfull;
	}
	
	/**
	 * @return	a copy of the current resolution Dimension object (i.e. current
	 * game window resolution)
	 */
	public static ScreenResolution getCurrentScreenDimension()
	{
		//TODO replace with copy
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
		//translate gameStates
		for (int id : Game.GAME_STATES)
		{
			// Iterate through each GameState
			GameState gameState = Game.getCurrentGame().getState(id);
			//TODO only translate the current state
			//TODO translate each state upon entry
			// label updates are handled in each gamestate
			if (gameState instanceof Translatable)
				((Translatable) gameState).updateTranslation();
		}
		
		System.out.println("Language Updates Complete.");
	}
	
	
}
