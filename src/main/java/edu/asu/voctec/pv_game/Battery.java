package edu.asu.voctec.pv_game;

import org.newdawn.slick.Image;

import edu.asu.voctec.GUI.BasicComponent;

import java.util.List;
import java.util.ArrayList;

public class Battery extends BatteryControl{
	
	public static List<ArrayList<Battery>> batteryArray = new ArrayList<ArrayList<Battery>>();
	public static List<BasicComponent> parallelLinesArray = new ArrayList<BasicComponent>();
    private int voltage, capacity, seriesIndex, parallelIndex;
    private static final int batteryImageWidth = 60, batteryImageHeight = 60;
    private static PVGame gameWorld;
	
	public Battery(Image image, int x, int y, PVGame gameWorld) {
		super(image, x, y);
		this.gameWorld = gameWorld;
	}

	@Override
	public void update() {
		
		if(mouseDragEnded())
        {
			removeFromArray();
            if(withinArrayCreationArea())
                this.addToArray();
            else
            	gameWorld.removeObject(this);
            
            organizeBatteries();
            calculateSystemVoltage();
            calculateTotalCapacity();
        }
        if(mouseIsBeingDragged())
        {
            setLocation(mouseX()-30, mouseY()-30);
        }
	}   
    
    public int getVoltage()
    {
        return voltage;
    }
    
    public int getCapacity()
    {
        return capacity;
    }
    
    public void setSeriesIndex(int seriesIndex)
    {
        this.seriesIndex = seriesIndex;
    }
    
    public void setParallelIndex(int parallelIndex)
    {
        this.parallelIndex = parallelIndex;
    }
    
    public void initiate(int voltage, int capacity)
    {
        this.voltage = voltage;
        this.capacity = capacity;
        addToArray();
    }
    
    public void addToArray()
    {
       
    	if(!connectedInParallel())
       {
           seriesIndex = batteryArray.size();
           batteryArray.add(new ArrayList<Battery>());
           parallelIndex = batteryArray.get(seriesIndex).size();
           batteryArray.get(seriesIndex).add(this);
       }
       organizeBatteries();
       calculateSystemVoltage();
       calculateTotalCapacity();
    }
    
    private boolean connectedInParallel()
    {
        for(int indexInSeries = 0; indexInSeries < batteryArray.size(); indexInSeries++)
        {
            for(int indexInParallel = 0; indexInParallel < batteryArray.get(indexInSeries).size(); indexInParallel++)
            {
                if(!batteryArray.get(indexInSeries).isEmpty())
                {
                    ArrayList<Battery> testedBatteryArray = batteryArray.get(indexInSeries);
                    Battery testedBattery = testedBatteryArray.get(indexInParallel);
                    if(withinX(testedBattery) && withinY(testedBattery))
                    {
                       seriesIndex = indexInSeries;
                       parallelIndex = batteryArray.get(indexInSeries).size();
                       batteryArray.get(indexInSeries).add(this);
                       return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean withinX(Battery testedBattery)
    {
        if(this.getX() < testedBattery.getX()+batteryImageWidth && this.getX()>=testedBattery.getX())
            return true;
        else if(this.getX() > testedBattery.getX()-batteryImageWidth && this.getX()<=testedBattery.getX())
            return true;
        else
            return false;
    }
    
    private boolean withinY(Battery testedBattery)
    {
        if(this.getY() < testedBattery.getY()+batteryImageHeight && this.getY()>=testedBattery.getY())
            return true;
        else if(this.getY() > testedBattery.getY()-batteryImageHeight && this.getY()<=testedBattery.getY())
            return true;
        else
            return false;
    }
    
    private void organizeBatteries()
    {
        removeLines();
    	for(int indexInSeries = 0; indexInSeries < batteryArray.size(); indexInSeries++)
        {
            for(int indexInParallel = 0; indexInParallel < batteryArray.get(indexInSeries).size(); indexInParallel++)
            {
                ArrayList<Battery> testedBatteryArray = batteryArray.get(indexInSeries);
                Battery testedBattery = testedBatteryArray.get(indexInParallel);
                testedBattery.setSeriesIndex(indexInSeries);
                testedBattery.setParallelIndex(indexInParallel);
                testedBattery.setBatteryLocation();
                if(indexInParallel>0)
                	addParallelLines(indexInSeries,indexInParallel);
            }
        }
    }
    
    private void addParallelLines(int x, int y)
    {
    	BasicComponent parallelLines = new BasicComponent(PVGame.getVerticalLinesImage(),60+(x*90),12+(y*90));
    	gameWorld.addComponent(parallelLines);
		parallelLinesArray.add(parallelLines);
    }
    
    private static void removeLines()
    {
    	if(!parallelLinesArray.isEmpty())
    	{
    		for(BasicComponent parallelLines:parallelLinesArray)
    			gameWorld.removeComponent(parallelLines);
    	}
    }
    
    private void setBatteryLocation()
    {
        setLocation(60+(seriesIndex*90), 90+(parallelIndex*90));
    }
    
    private void removeFromArray()
    {
        batteryArray.get(seriesIndex).remove(parallelIndex);
        if(batteryArray.get(seriesIndex).isEmpty())
            batteryArray.remove(seriesIndex);
        if(batteryArray.isEmpty())
        	InitialBattery.removeHorizontalLine();
    }   
    
    public static void reset()
    {
    	for(int indexRows = 0; indexRows<batteryArray.size(); indexRows++)
    	{
    		for(int indexColumn = 0; indexColumn<batteryArray.get(indexRows).size(); indexColumn++)
        	{
    			Battery removedBattery = batteryArray.get(indexRows).get(indexColumn);
    			gameWorld.removeObject(removedBattery);
        	}
    	}
    	
    	batteryArray.clear();
    	InitialBattery.removeHorizontalLine();
    	calculateSystemVoltage();
    	calculateTotalCapacity();
    	removeLines();
    }
    
    private static void calculateSystemVoltage()
    {
        if(batteryArray.isEmpty())
        	gameWorld.changeCurrentVoltage(0);
        else
        {
            if(allParallelsHaveSameVoltage())
                gameWorld.changeCurrentVoltage(getTotalVoltage());
            else
            	gameWorld.changeCurrentVoltage("N/A");
        }
    }
    
    private static boolean allParallelsHaveSameVoltage()
    {
        for(int indexInSeries = 0; indexInSeries < batteryArray.size(); indexInSeries++)
        {
            for(int indexInParallel = 0; indexInParallel < batteryArray.get(indexInSeries).size()-1; indexInParallel++)
            {
                ArrayList<Battery> testedBatteryArray = batteryArray.get(indexInSeries);
                Battery testedBattery1 = testedBatteryArray.get(indexInParallel);
                Battery testedBattery2 = testedBatteryArray.get(indexInParallel+1);
                if(testedBattery1.getVoltage() != testedBattery2.getVoltage())
                    return false;
            }
        }
        return true;
    }
    
    private static int getTotalVoltage()
    {
        int totalVoltage = 0;
        for(int indexInSeries = 0; indexInSeries < batteryArray.size(); indexInSeries++)
        {
            ArrayList<Battery> testedBatteryArray = batteryArray.get(indexInSeries);
            Battery testedBattery = testedBatteryArray.get(0);
            totalVoltage += testedBattery.getVoltage();
        }
        return totalVoltage;
    }
    
    private static void calculateTotalCapacity()
    {
        if(batteryArray.isEmpty())
        	gameWorld.changeCurrentCapacity(0);
        else
        {
            if(allSeriesHaveSameCapacity())
            	gameWorld.changeCurrentCapacity(getTotalCapacity(batteryArray.get(0)));
            else
            	gameWorld.changeCurrentCapacity("N/A");
        }
    }
    
    private static boolean allSeriesHaveSameCapacity()
    {
        for(int indexInSeries = 0; indexInSeries < batteryArray.size()-1; indexInSeries++)
        {
            ArrayList<Battery> testedBatteryArray1 = batteryArray.get(indexInSeries);
            ArrayList<Battery> testedBatteryArray2 = batteryArray.get(indexInSeries+1);
            if(getTotalCapacity(testedBatteryArray1) != getTotalCapacity(testedBatteryArray2))
                return false;
        }
        return true;
    }
    
    private static int getTotalCapacity(ArrayList<Battery> testedBatteryArray)
    {
        int totalCapacity = 0;
        for(int indexInParallel = 0; indexInParallel < testedBatteryArray.size(); indexInParallel++)
        {
            Battery testedBattery = testedBatteryArray.get(indexInParallel);
            totalCapacity += testedBattery.getCapacity();
        }
        return totalCapacity;
    }
    
    public static boolean isGameOver()
    {
    	if(!batteryArray.isEmpty())
    	{
    		if(allParallelsHaveSameVoltage() && allSeriesHaveSameCapacity())
    		{
    			if(getTotalCapacity(batteryArray.get(0))== PVGame.getRequiredCapacity() && getTotalVoltage()== PVGame.getRequiredVoltage())
    				return true;
    		}
    	} 
    	return false;
    }
    
    public static int getNumberOfBatteries()
    {
    	int totalNumber = 0;
    	for(int indexInSeries = 0; indexInSeries < batteryArray.size(); indexInSeries++)
        {
            for(int indexInParallel = 0; indexInParallel < batteryArray.get(indexInSeries).size(); indexInParallel++)
            {
            	totalNumber++;
            }
        }
    	return totalNumber;
    }

}
