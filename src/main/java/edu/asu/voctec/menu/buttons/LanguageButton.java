package edu.asu.voctec.menu.buttons;

import org.newdawn.slick.SlickException;

import edu.asu.voctec.GameDefaults;
import edu.asu.voctec.Main;
import edu.asu.voctec.menu.MainMenu;

public class LanguageButton extends TransitionButton implements GameDefaults
{
	public static final int	TRANSITION_SCREEN	= MainMenu.ID;
	Dictionary				language;
	
	public LanguageButton(int relativeX, int relativeY, Dictionary language,
			LayoutOption... layoutOptions) throws SlickException
	{
		super(ImagePaths.LANGUAGE_BUTTON, relativeX, relativeY,
				TRANSITION_SCREEN, layoutOptions);
		// TODO set label text
		this.language = language;
	}
	
	@Override
	public void actOnMouseClick()
	{
		super.actOnMouseClick();
		Main.setCurrentLanguage(language);
	}
	
}
