package rubbish.deprecate;

import org.newdawn.slick.Graphics;

public class appliance2 extends Appliance
{
	// TODO cache appliance images
	public void act()
	{
		// logic to test if it is connected
		if (Variables.getMatrixValue(1, 0) == 0)
		{
			if (Variables.getMatrixValue(2, 0) == 0)
			{
				if (Variables.getMatrixValue(2, 1) == 0
						|| Variables.getMatrixValue(2, 1) == 3)
				{
					if (Variables.getMatrixValue(2, 2) == 0
							|| Variables.getMatrixValue(2, 2) == 1)
					{
						if (Variables.getMatrixValue(1, 2) == 0)
						{
							// set to connected else not connected
							Watts.watt2.setConnected(true);
							setImage(ImagePaths.APPLIANCE_GOOD);
						}
						else
						{
							Watts.watt2.setConnected(false);
							setImage(ImagePaths.APPLIANCE_BAD);
						}
					}
					else
					{
						Watts.watt2.setConnected(false);
						setImage(ImagePaths.APPLIANCE_BAD);
					}
				}
				else
				{
					Watts.watt2.setConnected(false);
					setImage(ImagePaths.APPLIANCE_BAD);
				}
			}
			else
			{
				Watts.watt2.setConnected(false);
				setImage(ImagePaths.APPLIANCE_BAD);
			}
		}
		else
		{
			Watts.watt2.setConnected(false);
			setImage(ImagePaths.APPLIANCE_BAD);
		}
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.setColor(textColor);
		graphics.drawString(Variables.appliance2Text,
				(float) (this.xLocation + relativeTextLocation.x),
				(float) (this.yLocation + relativeTextLocation.y));
	}
	
	@Override
	public void actOnMouseClick()
	{
	}
}
