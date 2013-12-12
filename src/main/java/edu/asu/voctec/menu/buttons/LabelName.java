package edu.asu.voctec.menu.buttons;

public enum LabelName
{
	startButton 	("startButton"),
	optionsButton 	("optionsButton");
	
	public final String xmlListing;
	
	private LabelName(String xmlListing)
	{
		this.xmlListing = xmlListing;
	}
	
	public String getTranslation()
	{
		//TODO add loading from dictionary
		return this.xmlListing;
	}
}
