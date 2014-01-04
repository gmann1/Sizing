package edu.asu.voctec.information;

import edu.asu.voctec.GUI.Component;

public class ScenarioData
{
	protected TaskData entryStep;
	protected TaskData[] tasks;
	protected Component scenarioIcon;
	
	public ScenarioData(TaskData entryStep, TaskData[] tasks,
			Component scenarioIcon)
	{
		super();
		this.entryStep = entryStep;
		this.tasks = tasks;
		this.scenarioIcon = scenarioIcon;
	}

	public TaskData getEntryStep()
	{
		return entryStep;
	}

	public void setEntryStep(TaskData entryStep)
	{
		this.entryStep = entryStep;
	}

	public TaskData[] getTasks()
	{
		return tasks;
	}

	public void setTasks(TaskData[] tasks)
	{
		this.tasks = tasks;
	}
	
	public boolean isEntryComplete()
	{
		return entryStep.isComplete();
	}

	public Component getScenarioIcon()
	{
		return scenarioIcon;
	}

	public void setScenarioIcon(Component scenarioIcon)
	{
		this.scenarioIcon = scenarioIcon;
	}
	
	
}
