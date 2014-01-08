package edu.asu.voctec.information;

import java.io.Serializable;

import edu.asu.voctec.utilities.Finalizable;

/**
 * Contains information related to a single attempt made by a user at a
 * mini-game.
 * 
 * @author Moore, Zachary
 * 
 */
public class AttemptData implements Comparable<AttemptData>, Finalizable,
		Serializable
{
	private static final long serialVersionUID = 6482578605941748609L;

	protected boolean finalized;
	
	protected int numberOfUniqueHints;
	protected long timeSpent; // in milliseconds
	protected int score;
	protected int maximumPossibleScore;
	protected int percentCompletion;
	
	public AttemptData()
	{
		this.finalized = false;
		
		this.numberOfUniqueHints = 0;
		this.timeSpent = 0;
		percentCompletion = 0;
		this.score = 0;
		this.maximumPossibleScore = 100;
	}
	
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
		this.finalized = true;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int score)
	{
		if (!finalized)
			this.score = score;
	}
	
	public int getMaximumPossibleScore()
	{
		return maximumPossibleScore;
	}
	
	public void setMaximumPossibleScore(int maximumPossibleScore)
	{
		if (!finalized)
			this.maximumPossibleScore = maximumPossibleScore;
	}
	
	public int getNumberOfUniqueHints()
	{
		return numberOfUniqueHints;
	}
	
	public long getTimeSpent()
	{
		return timeSpent;
	}
	
	public long addTime(long time)
	{
		if (!finalized)
			this.timeSpent += time;
		
		return timeSpent;
	}
	
	public int addHints(int hints)
	{
		if (!finalized)
			this.numberOfUniqueHints += hints;
		
		return numberOfUniqueHints;
	}
	
	public boolean isComplete()
	{
		return (percentCompletion >= 100);
	}
	
	public int getPercentCompletion()
	{
		return percentCompletion;
	}
	
	public void setPercentCompletion(int percentCompletion)
	{
		this.percentCompletion = percentCompletion;
	}
	
}
