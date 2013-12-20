package rubbish.deprecate;


public class Wire extends Actor
{
	// Column1
	public static Wire	wire0x0			= new Wire(0, 0, ImagePaths.TILE_0x0);
	public static Wire	wire1x0			= new Wire(1, 0, ImagePaths.TILE_1x0);
	public static Wire	wire2x0			= new Wire(2, 0, ImagePaths.TILE_2x0);
	public static Wire	wire3x0			= new Wire(3, 0, ImagePaths.TILE_3x0);
	// Column2
	public static Wire	wire0x1			= new Wire(0, 1, ImagePaths.TILE_0x1);
	public static Wire	wire1x1			= new Wire(1, 1, ImagePaths.TILE_1x1);
	public static Wire	wire2x1			= new Wire(2, 1, ImagePaths.TILE_2x1);
	public static Wire	wire3x1			= new Wire(3, 1, ImagePaths.TILE_3x1);
	// Column3
	public static Wire	wire0x2			= new Wire(0, 2, ImagePaths.TILE_0x2);
	public static Wire	wire1x2			= new Wire(1, 2, ImagePaths.TILE_1x2);
	public static Wire	wire2x2			= new Wire(2, 2, ImagePaths.TILE_2x2);
	public static Wire	wire3x2			= new Wire(3, 2, ImagePaths.TILE_3x2);
	
	// variables
	int					row;
	int					column;
	int					rotationValue	= Variables.getMatrixValue(row, column);
	
	// setup
	public Wire(int newRow, int newColumn, String image)
	{
		row = newRow;
		column = newColumn;
		this.setImage(image);
		setRotation(rotationValue * 90);
	}
	
	// run state
	public void act()
	{
	}
	
	@Override
	public void actOnMouseClick()
	{
		setRotation(rotationValue * 90);
		System.out.println("Wire Clicked\n\tRotaion: " + rotationValue);
		
		// rotationValue = Variables.getMatrixValue(row,column);
		if (rotationValue == 3)
			rotationValue = 0;
		else
			rotationValue += 1;
		
		Variables.setMatrixValue(row, column, rotationValue);
		setRotation(rotationValue * 90);
	}
}
