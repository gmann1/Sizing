package rubbish.deprecate;


public abstract class Variables extends Actor
{
	// variables
	static String	watt1Text		= "10 Watts";
	static String	watt2Text		= "20 Watts";
	static String	watt3Text		= "30 Watts";
	static String	watt4Text		= "40 Watts";
	static String	appliance1Text	= "LightBulb";
	static String	appliance2Text	= "Television";
	static String	appliance3Text	= "Telephone";
	static String	appliance4Text	= "Radio";
	
	// A matrix to hold the diffrent rotation values for the wires
	// rows/columns
	// static int[][] matrix = new int[4][3];
	static int[][]	matrix			= { { 1, 2, 3 }, { 2, 2, 1 }, { 3, 1, 0 },
			{ 2, 0, 2 }			};
	
	// set the Matrix values
	public static void setMatrixValue(int row, int column, int value)
	{
		matrix[row][column] = value;
	}
	
	// get the matrix values
	public static int getMatrixValue(int row, int column)
	{
		return matrix[row][column];
	}
}
