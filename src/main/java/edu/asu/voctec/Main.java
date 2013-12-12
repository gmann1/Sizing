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
	
	private static final int DEFAULT_WINDOW_WIDTH = 800;
	private static final int DEFAULT_WINDOW_HEIGHT = 600;
	private static ScreenResolution screenDimension;
	private static AppGameContainer gameContainer;
	
	public static void main(String[] args) throws SlickException, ResolutionNotSupportedException
	{
		screenDimension = new ScreenResolution(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
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
		
		//TODO add image cropping
		Main.screenDimension = screenDimension;
		
		try
		{
			//resize container (user window) //false indicates windowed mode (not full-screen)
			gameContainer.setDisplayMode(Main.screenDimension.width, Main.screenDimension.height, false);
			
			//resize gameStates
			for (int id : Game.GAME_STATES)
			{
				GameState gameState = currentGame.getState(id);
				if (gameState instanceof Resizable)
					((Resizable) gameState).resize();
			}
			
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
	public static ScreenResolution getScreenDimension()
	{
		return Main.screenDimension;
	}
}
