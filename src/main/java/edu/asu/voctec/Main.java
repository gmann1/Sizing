package edu.asu.voctec;

import java.awt.Dimension;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

public class Main
{
	public static final int TARGET_FRAME_RATE = 30;
	public static final String GAME_TITLE = "Sizing";
	public static final boolean SHOW_FPS = false;
	
	private static int windowWidth = 800;
	private static int windowHeight = 600;
	private static AppGameContainer gameContainer;
	
	public static void main(String[] args) throws SlickException
	{
		gameContainer = new AppGameContainer(Game.constructGame());
		
		//adjust settings
		gameContainer.setShowFPS(SHOW_FPS);
		gameContainer.setTargetFrameRate(TARGET_FRAME_RATE);
		gameContainer.setDisplayMode(windowWidth, windowHeight, false);
		gameContainer.setForceExit(false);
		//TODO load settings from file
		
		//launch game
		gameContainer.start();
	}
	
	public static boolean resize(Dimension screenDimension)
	{
		boolean resizeSuccessfull = false;
		Game currentGame = Game.getCurrentGame();
		
		windowWidth = screenDimension.width;
		windowHeight = screenDimension.height;
		
		try
		{
			//resize container (user window)
			gameContainer.setDisplayMode(windowWidth, windowHeight, false);
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resizeSuccessfull;
	}
	
	/**
	 * @return	a copy of the current resolution Dimension object
	 */
	public static Dimension getScreenDimension()
	{
		return new Dimension(Main.windowWidth, Main.windowHeight);
	}
}
