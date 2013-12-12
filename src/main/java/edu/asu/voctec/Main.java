package edu.asu.voctec;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

import edu.asu.voctec.AspectRatio.ResolutionNotSupportedException;

public class Main
{
	public static final int TARGET_FRAME_RATE = 30;
	public static final String GAME_TITLE = "Sizing";
	public static final boolean SHOW_FPS = false;
	
	private static final int DEFAULT_WINDOW_WIDTH = 1280;
	private static final int DEFAULT_WINDOW_HEIGHT = 720;
	private static ScreenResolution previousScreenDimension;
	private static ScreenResolution currentScreenDimension;
	private static AppGameContainer gameContainer;
	
	public static void main(String[] args) throws SlickException, ResolutionNotSupportedException
	{
		currentScreenDimension = new ScreenResolution(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		gameContainer = new AppGameContainer(Game.constructGame());
		
		//adjust settings
		gameContainer.setShowFPS(SHOW_FPS);
		gameContainer.setTargetFrameRate(TARGET_FRAME_RATE);
		gameContainer.setDisplayMode(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, false);
		gameContainer.setForceExit(false);
		//TODO load settings from file
		
		//launch game
		gameContainer.start();
	}
	
	public static boolean resize(ScreenResolution screenDimension)
	{
		boolean resizeSuccessfull = false;
		Game currentGame = Game.getCurrentGame();
		
		// Update dimension information
		Main.previousScreenDimension = Main.currentScreenDimension;
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
	
	/**
	 * @return	a copy of the previous resolution Dimension object (i.e. previous
	 * game window resolution)
	 */
	public static ScreenResolution getPreviousScreenDimension()
	{
		//TODO replace with copy
		return Main.previousScreenDimension;
	}

	public static AppGameContainer getGameContainer()
	{
		return gameContainer;
	}
	
	
}
