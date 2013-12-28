package edu.asu.voctec.information;

import edu.asu.voctec.utilities.Finalizable;

/**
 * Contains information related to a single attempt made by a user at a 
 * mini-game.
 * 
 * @author Moore, Zachary
 *
 */
public class AttemptData implements Comparable<AttemptData>, Finalizable
{
	protected int numberOfUniqueHints;
	protected long timeSpent; //in milliseconds
	protected int score;
	protected int maximumPossibleScore;
	
	
	
	/**
	 * Performs a comparison between two AttemptData objects based on the
	 * normalized score of each object. Returns a value <0 if this object's
	 * score is less than the other object; returns a value >0 if this object's
	 * score is greater than the other object; returns 0 if the normalized
	 * scores are equivalent. 
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AttemptData otherAttemptData)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void makeFinal()
	{
		// TODO Auto-generated method stub
		
	}
	
}
