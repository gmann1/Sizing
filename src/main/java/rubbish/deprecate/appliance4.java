package rubbish.deprecate;

import org.newdawn.slick.Graphics;

public class appliance4 extends Appliance
{
	// TODO cache appliance images
	public void act()
	{
		// TODO remove nestings
		// logic to test if it is connected
		if (Variables.getMatrixValue(3, 0) == 0)
		{
			if (Variables.getMatrixValue(2, 0) == 0)
			{
				if (Variables.getMatrixValue(3, 1) == 0)
				{
					if (Variables.getMatrixValue(2, 1) == 0)
					{
						if (Variables.getMatrixValue(1, 1) == 0)
						{
							if (Variables.getMatrixValue(1, 2) == 0)
							{
								if (Variables.getMatrixValue(0, 2) == 0)
								{
									// set to connected else not connected
									Watts.watt4.setConnected(true);
									setImage(ImagePaths.APPLIANCE_GOOD);
								}
								else
								{
									Watts.watt4.setConnected(false);
									setImage(ImagePaths.APPLIANCE_BAD);
								}
							}
							else
							{
								Watts.watt4.setConnected(false);
								setImage(ImagePaths.APPLIANCE_BAD);
							}
						}
						else
						{
							Watts.watt4.setConnected(false);
							setImage(ImagePaths.APPLIANCE_BAD);
						}
					}
					else
					{
						Watts.watt4.setConnected(false);
						setImage(ImagePaths.APPLIANCE_BAD);
					}
				}
				else
				{
					Watts.watt4.setConnected(false);
					setImage(ImagePaths.APPLIANCE_BAD);
				}
			}
			else
			{
				Watts.watt4.setConnected(false);
				setImage(ImagePaths.APPLIANCE_BAD);
			}
		}
		else
		{
			Watts.watt4.setConnected(false);
			setImage(ImagePaths.APPLIANCE_BAD);
		}
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.setColor(textColor);
		graphics.drawString(Variables.appliance4Text,
				(float) (this.xLocation + relativeTextLocation.x),
				(float) (this.yLocation + relativeTextLocation.y));
	}
	
	@Override
	public void actOnMouseClick()
	{
	}
}
