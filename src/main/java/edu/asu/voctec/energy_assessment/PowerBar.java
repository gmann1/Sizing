package edu.asu.voctec.energy_assessment;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.asu.voctec.GUI.BasicComponent;

public class PowerBar extends BasicComponent{
	
	private static final String POWER_BAR_BACKGROUND = "resources/default/img/minigames/energyAssessment/PowerBar/PowerBarBKGD.png";
	private static final String POWER_BAR_BACKGROUND_CORRECT = "resources/default/img/minigames/energyAssessment/PowerBar/PowerBarBKGDrCorrect.png";
	private static final String POWER_BAR_INDICATOR = "resources/default/img/minigames/energyAssessment/PowerBar/PowerBarIndicatorCorrect.png";
	
	public BasicComponent powerBarIndicator;
	private Image powerBarBackgroundImage, powerBarBackgroundCorrectImage;
	private int powerBarRelativeX = 16, powerBarRelativeY = 382, powerBarMaxHeight = 366, powerBarWidth = 75, powerBarHeight = 0;
	private static int powerBarBackgroundWidth = 107, powerBarBackgroundHeight = 426;
	private int minLimit = 0, maxLimit = 0 , maxPower = 0;
	
	public PowerBar(int x, int y, double scale, int minWinningLimit, int maxWinningLimit)
			throws SlickException {
		
		super(POWER_BAR_BACKGROUND, x, y, (int) (powerBarBackgroundWidth*scale), (int) (powerBarBackgroundHeight*scale));
		
		minLimit = minWinningLimit;
		maxLimit = maxWinningLimit;
		maxPower = (int) (minLimit+(0.28*minLimit));
		powerBarWidth = (int) (powerBarWidth*scale);
		
		powerBarBackgroundCorrectImage = new Image(POWER_BAR_BACKGROUND_CORRECT);
		powerBarBackgroundImage = new Image(POWER_BAR_BACKGROUND);
		
		powerBarIndicator = new BasicComponent(POWER_BAR_INDICATOR, x+powerBarRelativeX, y+powerBarRelativeY-powerBarHeight);
		powerBarIndicator.resize(powerBarWidth,powerBarHeight);
		//gameClass.addComponent(powerBarIndicator);
	}
	
	public void updatePowerBar(int totalPower)
	{
		powerBarHeight = translateIntoHeight(totalPower);
		powerBarIndicator.resize(powerBarWidth,powerBarHeight);
		powerBarIndicator.setY(getY()+powerBarRelativeY-powerBarHeight);
		if(targetAchieved(totalPower))
			this.setCurrentImage(powerBarBackgroundCorrectImage, true);
		else
			this.setCurrentImage(powerBarBackgroundImage, true);
	}
	
	private int translateIntoHeight(int totalPower)
	{
		double newHeight;
		newHeight = ((double) (totalPower)/(double) (maxPower));
		newHeight *= powerBarMaxHeight;
		
		return (int) newHeight;
	}
	
	private boolean targetAchieved(int totalPower)
	{
		if(totalPower >= minLimit && totalPower <= maxLimit)
			return true;
		else
			return false;
	}
}
