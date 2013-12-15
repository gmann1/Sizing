package edu.asu.voctec.minigames.critical_design_month;

import edu.asu.voctec.minigames.Actor;
import edu.asu.voctec.minigames.PortedGameState;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Sun here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sun extends Actor {
	/**
	 * Act - do whatever the Sun wants to do. This method is called whenever the
	 * 'Act' or 'Run' button gets pressed in the environment.
	 */
	private boolean forward = true;
	private int frequency = 200;
	private int inc = 0;
	private int currentRot;
	private int currentx;
	private int randomx;
	private int xc = 0;
	private int yc = 0;
	private int beam_x;
	private int beam_y;
	private int init_x;
	private int init_y;
	private int season;
	private int counter = 0;
	private int rotation;
	List<BatteryCharge> BC = new ArrayList<BatteryCharge>();
	private PortedGameState associatedWorld;

	public Sun(PortedGameState associatedWorld, int season)
	{
		this.associatedWorld = associatedWorld;
		setImage(ImagePaths.SUN);
		this.image = image.getScaledCopy(0.25f);
		this.season = season;
		this.rotation = 0;

		// turnTowards(

	}

	public void act() {
		if (counter == 0) {
			if (season == 1) {
				setLocation(this.getxLocation(), 200);
			}
			currentRot = 330;
			currentx = this.getxLocation();

		}

		if (this.getxLocation() >= 380) {

			if (currentRot > 300) {
				currentRot = 13;
			}
		}

		if (this.getxLocation() - currentx >= 30) {
			currentx = this.getxLocation();
			currentRot += 1;
			if (currentRot == 360) {
				currentRot = 0;
			}

		}
		if (counter % 10 == 0)
		{
			setRotation(currentRot);
			move(2);
		}

		// else{
		// if (this.getxLocation() < (800-currentx)){
		// if(counter%10 == 0){
		// setLocation(this.getxLocation()+2, 42);
		// }
		//
		// }
		// else{
		// if(counter%10 == 0){
		// setLocation(this.getxLocation()+2, 43);
		// currentRot = 360-currentRot;
		// }
		// }
		// }

		// turn(Greenfoot.getRandomNumber(3) - 1);
		// turn(50);
		if (inc % 9 == 0) {
			this.image = getImage().getFlippedCopy(false, true);
		}

		if (inc > frequency) {
			if (inc == frequency + 1) {
				randomx = new Random().nextInt(800);
				beam_x = this.getxLocation();
				beam_y = this.getyLocation();
			}
			fireBeam();
			if (inc == frequency + 10) {
				inc = 0;
			}
		}
		++inc;
		++counter;
		if (this.getxLocation() > 850)
		{
			BatteryCharge batterycharge = BatteryCharge.currentBatteryCharge;
			batterycharge.end();
		}

	}
	
	public void move(int amount)
	{
		double x = this.xLocation + amount*Math.cos(this.rotation);
		double y = this.yLocation + amount*Math.sin(this.rotation);
		
		super.setLocation(x, y);
	}

	public void fireBeam() {
		this.associatedWorld.addObject(
				new SunBeam(this.associatedWorld, beam_x, beam_y, randomx, 600),
				beam_x, beam_y);
	}

	public boolean atEdge() {
		if (this.getxLocation() + (getImage().getWidth() / 2) >= 800
				|| this.getxLocation() - (getImage().getWidth() / 2) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void setRotation(int rotation)
	{
		this.rotation = rotation;
	}

	@Override
	public void actOnMouseClick() {
		// TODO Auto-generated method stub
		
	}
}
