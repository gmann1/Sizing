package edu.asu.voctec.information;

import java.util.ArrayList;

import edu.asu.voctec.GUI.SelectorIcon;
import edu.asu.voctec.utilities.CircularList;

public class SizingStepsData extends AttemptData
{
	private ArrayList<SelectorIcon> selectorDisplayContents;
	private CircularList<SelectorIcon> selectorContents;
	private ArrayList<String> currentHints;
	
	public SizingStepsData(ArrayList<SelectorIcon> selectorDisplayContents,
			CircularList<SelectorIcon> selectorContents,
			ArrayList<String> currentHints)
	{
		super();
		this.selectorDisplayContents = selectorDisplayContents;
		this.selectorContents = selectorContents;
		this.currentHints = currentHints;
	}
	
	public ArrayList<SelectorIcon> getSelectorDisplayContents()
	{
		return selectorDisplayContents;
	}
	
	public void setSelectorDisplayContents(
			ArrayList<SelectorIcon> selectorDisplayContents)
	{
		this.selectorDisplayContents = selectorDisplayContents;
	}
	
	public CircularList<SelectorIcon> getSelectorContents()
	{
		return selectorContents;
	}
	
	public void setSelectorContents(CircularList<SelectorIcon> selectorContents)
	{
		this.selectorContents = selectorContents;
	}
	
	public ArrayList<String> getCurrentHints()
	{
		return currentHints;
	}
	
	public void setCurrentHints(ArrayList<String> currentHints)
	{
		this.currentHints = currentHints;
	}
	
}
