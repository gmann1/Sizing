package edu.asu.voctec.minigames.critical_design_month;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import edu.asu.voctec.minigames.Actor;
import edu.asu.voctec.minigames.PortedGameState;

/**
 * Write a description of class Panel here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Panel extends Actor
{
	/**
	 * Act - do whatever the Panel wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */
	Sound charge;
	private int count = 0;
	private int total = 0;

	private int angle = 0;
	private int timer = 0;
	private boolean startTimer = false;
	private int panelAngle = 0;
	private boolean isRight = false;
	private PortedGameState associatedWorld;
	private int rotation;

	public Panel(PortedGameState associatedWorld) throws SlickException
	{
		this.associatedWorld = associatedWorld;
		this.charge = new Sound(SoundPaths.CHARGE);
		this.rotation = 0;
	}

	public void act()
	{
		SunBeam beam = getIntersectingSunBeam();
		if (beam != null)
		{
			charge.play();
			associatedWorld.removeObject(beam);
			count = count - 3;
			++total;
			startTimer = true;

			angle = beam.getAngle();
			isRight = beam.isRight();
		}
		
		if (timer > 15)
		{
			startTimer = false;
			timer = 0;

			if (getRotation() > 280)
			{
				panelAngle = 360 - getRotation();
			}
			else
			{
				panelAngle = getRotation();
				isRight = !isRight;
			}

			associatedWorld.addObject(new Score
					(this.associatedWorld, this.getxLocation(), this.getyLocation(), 
					 isRight, angle, panelAngle, total), 800, 700);
			total = 0;

		}
		if (startTimer) {
			timer++;
		}
		if (charge.playing()) {
			count++;
		}
		if (count == 15) {
			count = 0;
			charge.stop();
		}

		// System.out.println(getRotation());

	}
	
	public void keyPressed(int key, char c)
	{
		if (!atLeftEdge() && (key == Input.KEY_LEFT)) {
				setLocation(this.getxLocation() - 10, this.getyLocation());
			}
		if (!atRightEdge() && (key == Input.KEY_RIGHT))
		{
				setLocation(this.getxLocation() + 10, this.getyLocation());
			}
		if (!atMaxLeftRotation() && (c == 'q'))
		{
				turn(-2);
		}
		if (!atMaxRightRotation() && (c == 'r'))
		{
				turn(2);
		}
	}
	
	public SunBeam getIntersectingSunBeam()
	{
		SunBeam intersectingBeam = null;
		
		for (SunBeam beam : SunBeam.liveObjects)
		{
			if (beam.intersects(this))
			{
				intersectingBeam = beam;
				break;
			}
		}
		
		return intersectingBeam;
	}

	public boolean atRightEdge() {
		if (this.getxLocation() + (getImage().getWidth() / 2) >= 800) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atLeftEdge() {
		if (this.getxLocation() - (getImage().getWidth() / 2) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atMaxLeftRotation() {
		if (getRotation() >= 280 && getRotation() <= 290) {
			return true;
		} else {
			return false;
		}
	}

	public boolean atMaxRightRotation() {
		if (getRotation() >= 70 && getRotation() <= 80) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void actOnMouseClick() {
		// TODO Auto-generated method stub

	}

	public int getRotation() {
		return rotation;
	}
	
	public int turn(int rotationAmount) {
		this.rotation += rotationAmount;
		normalizeRotation();
		super.setRotation(this.rotation);
		
		return this.rotation;
	}
	
	private void normalizeRotation()
	{
		if (this.rotation > 360)
		{
			this.rotation = this.rotation % 360;
		}
		else if (this.rotation < 0)
		{
			//TODO test
			this.rotation = 360 - this.rotation % 360;
		}
	}
}
