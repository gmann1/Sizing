package edu.asu.voctec.information;

/**
 * Represents a hint that the user can receive during a mini-game. In the
 * current version, Hint is simply a wrapper for a String. Future versions will
 * provide further functionality such as different levels of the same hint
 * (some more blunt than others).
 * 
 * @author Moore, Zachary
 *
 */
public class Hint
{
	protected String hint;
	
	public Hint(String hint)
	{
		this.hint = hint;
	}
	
	@Override
	public String toString()
	{
		return this.hint;
	}
}
