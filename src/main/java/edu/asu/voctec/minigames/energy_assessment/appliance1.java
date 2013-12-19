package edu.asu.voctec.minigames.energy_assessment;

import org.newdawn.slick.Graphics;

public class appliance1 extends Appliance
{
	// TODO cache appliance images
	public void act()
	{
		// logic to test if it is connected
		if (Variables.getMatrixValue(0, 0) == 0)
		{
			if (Variables.getMatrixValue(1, 0) == 0)
			{
				if (Variables.getMatrixValue(1, 1) == 0)
				{
					if (Variables.getMatrixValue(0, 1) == 0)
					{
						if (Variables.getMatrixValue(1, 2) == 0)
						{
							if (Variables.getMatrixValue(2, 2) == 0)
							{
								// set to connected else not connected
								Watts.watt1.setConnected(true);
								setImage(ImagePaths.APPLIANCE_GOOD);
							}
							else
							{
								Watts.watt1.setConnected(false);
								setImage(ImagePaths.APPLIANCE_BAD);
							}
						}
						else
						{
							Watts.watt1.setConnected(false);
							setImage(ImagePaths.APPLIANCE_BAD);
						}
					}
					else
					{
						Watts.watt1.setConnected(false);
						setImage(ImagePaths.APPLIANCE_BAD);
					}
				}
				else
				{
					Watts.watt1.setConnected(false);
					setImage(ImagePaths.APPLIANCE_BAD);
				}
			}
			else
			{
				Watts.watt1.setConnected(false);
				setImage(ImagePaths.APPLIANCE_BAD);
			}
		}
		else
		{
			Watts.watt1.setConnected(false);
			setImage(ImagePaths.APPLIANCE_BAD);
		}
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.setColor(textColor);
		graphics.drawString(Variables.appliance1Text,
				(float) (this.xLocation + relativeTextLocation.x),
				(float) (this.yLocation + relativeTextLocation.y));
	}
	
	@Override
	public void actOnMouseClick()
	{
	}
}
