package rubbish.deprecate.misc;

import org.newdawn.slick.Image;

import edu.asu.voctec.GUI.GUI;
import edu.asu.voctec.utilities.AspectRatio.ResolutionNotSupportedException;
import edu.asu.voctec.utilities.Resizable;
import edu.asu.voctec.utilities.ScreenResolution;
import edu.asu.voctec.utilities.Translatable;

//TODO implement translation and resizing
public abstract class ModifiedGUI extends GUI implements Resizable, Translatable
{
	private ScreenResolution baseResolution;
	private Image baseBackgroundImage;
	
	protected float scale;
	
	protected void initializeBackgroundImage(Image backgroundImage)
	{
		try
		{
			if (backgroundImage != null)
				this.baseResolution = new ScreenResolution(backgroundImage);
		}
		catch (ResolutionNotSupportedException e)
		{
			e.printStackTrace();
			// TODO resize image to design resolution
		}
		
		this.backgroundImage = backgroundImage;
		this.baseBackgroundImage = backgroundImage;
	}
	
	public float getScale()
	{
		return scale;
	}
}
