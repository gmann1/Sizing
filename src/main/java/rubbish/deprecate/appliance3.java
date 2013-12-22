package rubbish.deprecate;

import org.newdawn.slick.Graphics;

public class appliance3 extends Appliance
{
	// TODO cache appliance images
	// run state
	public void act()
	{
		// logic to test if it is connected
		if (Variables.getMatrixValue(2, 0) == 0)
		{
			if (Variables.getMatrixValue(2, 1) == 0)
			{
				if (Variables.getMatrixValue(3, 1) == 0)
				{
					if (Variables.getMatrixValue(3, 2) == 0
							|| Variables.getMatrixValue(3, 2) == 2)
					{
						// set to connected else not connected
						Watts.watt3.setConnected(true);
						setImage(ImagePaths.APPLIANCE_GOOD);
					}
					else
					{
						Watts.watt3.setConnected(false);
						setImage(ImagePaths.APPLIANCE_BAD);
					}
				}
				else
				{
					Watts.watt3.setConnected(false);
					setImage(ImagePaths.APPLIANCE_BAD);
				}
			}
			else
			{
				Watts.watt3.setConnected(false);
				setImage(ImagePaths.APPLIANCE_BAD);
			}
		}
		else
		{
			Watts.watt3.setConnected(false);
			setImage(ImagePaths.APPLIANCE_BAD);
		}
	}
	
	@Override
	public void draw(Graphics graphics)
	{
		super.draw(graphics);
		graphics.setColor(textColor);
		graphics.drawString(Variables.appliance3Text,
				(float) (this.xLocation + relativeTextLocation.x),
				(float) (this.yLocation + relativeTextLocation.y));
	}
	
	@Override
	public void actOnMouseClick()
	{
	}
}
