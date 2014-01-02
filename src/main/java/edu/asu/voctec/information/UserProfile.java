package edu.asu.voctec.information;

public class UserProfile
{
	protected String name;
	protected ScenarioData[] scenarioData;
	
	public UserProfile(String name)
	{
		this.name = name;
		this.scenarioData = generateDefaultScenarios();
	}
	
	public ScenarioData[] generateDefaultScenarios()
	{
		ScenarioData[] scenarios = new ScenarioData[1];
		// TODO Declare Default Scenarios
		scenarios[0] = scenario1();
		
		return scenarios;
	}
	
	public ScenarioData scenario1()
	{
		return null;
	}
}
