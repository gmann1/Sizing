package edu.asu.voctec.menu.buttons;

import java.awt.Point;

import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;

public class LanguageButton extends Button implements GameDefaults
{
	Dictionary language;
	
	public LanguageButton(int relativeX, int relativeY, 
			Dictionary language, LayoutOption... layoutOptions) 
					throws SlickException
	{
		super(ImagePaths.LANGUAGE_BUTTON, new Point(relativeX, relativeY), layoutOptions);
		//TODO set label text
		this.language = language;
	}

	@Override
	public void actOnMouseClick()
	{
		Main.setCurrentLanguage(language);
	}
	
}
