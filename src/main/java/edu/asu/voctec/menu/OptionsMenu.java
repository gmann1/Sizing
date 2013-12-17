package edu.asu.voctec.menu;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.menu.buttons.Button;
import edu.asu.voctec.menu.buttons.Button.LayoutOption;
import edu.asu.voctec.menu.buttons.Dictionary;
import edu.asu.voctec.menu.buttons.LabelName;
import edu.asu.voctec.menu.buttons.LanguageButton;
import edu.asu.voctec.menu.buttons.TransitionButton;
import edu.asu.voctec.utilities.ScreenResolution;
import edu.asu.voctec.utilities.AspectRatio.ResolutionNotSupportedException;

public class OptionsMenu extends Menu implements GameDefaults
{
	public static final int ID = 2;
	public static final Dimension DesignResolution = new Dimension(1280, 720);
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{

		//TODO remove sizing test
		//resizing TEST
		addButton(new Button(ImagePaths.NEW_GAME_BUTTON,
				  new Point(-10, -10),
				  LayoutOption.BOTTOM_RIGHT_ALIGN){

			@Override
			public void actOnMouseClick()
			{
				ScreenResolution d1280 = null;
				ScreenResolution d800 = null;
				
				try
				{
					d1280 = new ScreenResolution(1280, 720);
					d800 = new ScreenResolution(800, 600);
				}
				catch (ResolutionNotSupportedException e)
				{
					e.printStackTrace();
				}
				
				//TODO improve algorithm to work with user-definaed resolutions
				if (Main.getCurrentScreenDimension().equals(d1280))
					Main.resize(d800);
				else if (Main.getCurrentScreenDimension().equals(d800))
					Main.resize(d1280);
			}
			
		}); //end resizing TEST

		//TODO replace absolute positioning with ButtonBlock class
		//TODO declare & instantiate all buttons
		//TODO move setLabel() call to button constructor
		Button languageOptionButtonEnglish = 
				new LanguageButton(0, 222, Dictionary.getDictionary("English"),
				LayoutOption.CENTER_HORIZONTALLY);
		languageOptionButtonEnglish.setLabel(LabelName.getLabelNameByXMLListing("English"));
		
		Button languageOptionButtonSpanish = 
				new LanguageButton(0, 322, Dictionary.getDictionary("Espanol"),
				LayoutOption.CENTER_HORIZONTALLY);
		languageOptionButtonSpanish.setLabel(LabelName.getLabelNameByXMLListing("Espanol"));
		
		Button languageOptionButtonJapanese = 
				new LanguageButton(0, 422, Dictionary.getDictionary("“ú–{Œê"),
				LayoutOption.CENTER_HORIZONTALLY);
		languageOptionButtonJapanese.setLabel(LabelName.getLabelNameByXMLListing("“ú–{Œê"));
		
		Button backButton = new TransitionButton(ImagePaths.BACK_BUTTON,
				 10, 10, MainMenu.ID,
				 LayoutOption.TOP_LEFT_ALIGN);
		
		//TODO add all buttons
		//TODO move adding code to Button constructor
		addButton(languageOptionButtonEnglish);
		addButton(languageOptionButtonSpanish);
		addButton(languageOptionButtonJapanese);
		addButton(backButton);
		
		//TODO initialize all resources
		
		//initialize background image
		Image background = new Image(ImagePaths.MainMenuBackground);
		super.initializeBackgroundImage(background);
		
		//ensure the appropriate scale is being used
		resize();
		
		//ensure the appropriate language is being used
		updateTranslation();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		
	}

	@Override
	public int getID()
	{
		return ID;
	}

	@Override
	public Dimension getDesignResolution()
	{
		return DesignResolution;
	}
}
