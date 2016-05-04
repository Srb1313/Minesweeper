import java.util.ArrayList;
import java.util.List;

import Extensions.StringExtensions;

/**
 * @author Administrator
 *
 */
public class Grid
{
	private Integer rowsCount = 0;
	private Integer columnsCount = 0;
	private char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	public Grid(Integer rowsCount, Integer columnsCount)
	{
		this.rowsCount = rowsCount;
		this.columnsCount = columnsCount;
	}

	public int getRowsCount()
	{
		return rowsCount;
	}

	public int getColumnsCount()
	{
		return columnsCount;
	}

	public GridLocation getLocationFromString(String location)
	{
		String[] splitLocation = location.split(",");
		Integer xLoc = this.rowsCount-Integer.parseInt(splitLocation[1]);
		Integer yLoc = convertColumnIdentifierToInt(splitLocation[0])-1;
		return new GridLocation(xLoc, yLoc);
	}

	public List<GridLocation> getGridSquareLocations()
	{
		List<GridLocation> locations = new ArrayList<GridLocation>();
		for (int x = 0; x < rowsCount; x++)
		{
			for (int y = 0; y < columnsCount; y++)
			{
				locations.add(new GridLocation(x, y));
			}
		}
		return locations;
	}

	/*
	 * Creates the initial grid
	 */
	public void CreateStartGrid()
	{
		System.out.print(getColumnIdentifier());
		System.out.print("\n");
		for (int x = 0; x < rowsCount; x++)
		{
			System.out.print(getRowIdentifier(x, true));
			for (int i = 0; i < columnsCount; i++)
			{
				System.out.print("[-]");
			}
			System.out.print(getRowIdentifier(x, false));
			System.out.print("\n");
		}
		System.out.print(getColumnIdentifier());
	}

	//TODO maybe store locations using Memento patter
	public void CreateMidGameGrid(List<GridLocation> locations,List<Mine> mines, List<Identifier> identifiers)
	{
		System.out.print(getColumnIdentifier());
		System.out.print("\n");
		String printSymbol="[-]";
		for (int x = 0; x < rowsCount; x++)
		{
			System.out.print(getRowIdentifier(x, true));
			
			for (int y = 0; y < columnsCount; y++)
			{		
				printSymbol="[-]";
				CurrentLocation:
				for (GridLocation location : locations)
				{
					if (location.getxLocation()==x && location.getyLocation()==y)
					{
						for (Mine mine : mines)
						{
							if (mine.inLocation(location, this))
							{
								printSymbol="[#]";
								break CurrentLocation;
							}
						}
						for (Identifier identifier : identifiers)
						{
							if (identifier.inLocation(location, this))
							{
								printSymbol="["+identifier.getAmountOfSurroundingMines()+"]";
								break CurrentLocation;
							}
						}
					}
				}
				System.out.print(printSymbol);
			}
			System.out.print(getRowIdentifier(x, false));
			System.out.print("\n");
		}
		System.out.print(getColumnIdentifier());
	}

	/*
	 * Creates the initial grid
	 */
	public void CreateFinalGrid(List<Identifier> identifiers, List<Mine> mines)
	{
		System.out.print("\n");
		System.out.print(getColumnIdentifier());
		System.out.print("\n");
		for (int x = 0; x < rowsCount; x++)
		{
			System.out.print(getRowIdentifier(x, true));
			for (int i = 0; i < columnsCount; i++)
			{
				System.out.print("["+ getSquareFill(mines, identifiers, new GridLocation(x,i)) + "]");
			}
			System.out.print(getRowIdentifier(x, false));
			System.out.print("\n");
		}
		System.out.print(getColumnIdentifier());
	}

	/*
	 * Gets the string to fill a grid square with
	 */
	private String getSquareFill(List<Mine> mines, List<Identifier> identifiers, GridLocation location)
	{
		for (Mine mine : mines)
		{
			if (mine.inLocation(location,this))
			{
				return "#";
			}
		}

		for (Identifier identifier : identifiers)
		{
			if (identifier.inLocation(location,this))
			{
				return identifier.getAmountOfSurroundingMines() == 0 ? " ": identifier.getAmountOfSurroundingMines().toString();
			}
		}
		return " ";
	}

	/*
	 * Gets a formatted version of row numbers to identify the different rows
	 */
	private String getRowIdentifier(int currentRowsCount, boolean rowStart)
	{
		Integer rowsIndicatorCount = (rowsCount - currentRowsCount);

		// Run the if statement if the row identifier needs formatting for the
		// beginning of the row
		if (rowStart)
		{
			String rowCount = "";
			Integer rowsIndicatorCountLength = rowsIndicatorCount.toString()
					.length();
			Integer maxRowsLength = rowsCount.toString().length();

			// Add spaces to beginning of identifier to ensure correct
			// formatting
			for (int i = 0; i < maxRowsLength - rowsIndicatorCountLength; i++)
			{
				rowCount += " ";
			}
			return rowCount += rowsIndicatorCount;
		}

		return rowsIndicatorCount.toString();
	}

	/*
	 * Gets formatted version of the column identifier
	 */
	private String getColumnIdentifier()
	{
		String columnIdentifiers = "";
		for (int x = 0; x < rowsCount.toString().length(); x++)
		{
			columnIdentifiers += " ";
		}

		for (int i = 0; i < columnsCount; i++)
		{
			String identifier = convertColumnIdentifierToString(i + 1);
			columnIdentifiers += identifier;
			// Hardcoded 3 because each grid square is 3 characters long
			for (int x = 0; x < 3 - identifier.length(); x++)
			{
				columnIdentifiers += " ";
			}
		}
		return columnIdentifiers;
	}

	/*
	 * Gets number converted to string representation
	 */
	private String convertColumnIdentifierToString(Integer numberToConvert)
	{
		String columnIdentifier = "";

		if (numberToConvert > 26)
		{
			Integer amount = numberToConvert;
			Integer modAmount = numberToConvert;
			do
			{
				modAmount = amount % 26;
				columnIdentifier += modAmount == 0 ? alphabet[25]
						: alphabet[modAmount - 1];
				amount = modAmount == 0 ? ((amount - modAmount) / 26) - 1
						: ((amount - modAmount) / 26);
			} while (amount > 26);

			columnIdentifier += alphabet[amount - 1];
			columnIdentifier = StringExtensions.reverse(columnIdentifier);
		} else
		{
			columnIdentifier += alphabet[numberToConvert - 1];
		}

		return columnIdentifier;
	}

	/*
	 * Converts column character name to int
	 */
	private int convertColumnIdentifierToInt(String columnToConvert)
	{
		String reversedColumn = StringExtensions.reverse(columnToConvert);
		int value = 0;

		for (int i = 0; i < reversedColumn.length(); i++)
		{
			int powerValue = (int) Math.pow(26, i);
			int multiplyValue=new String(alphabet).indexOf(reversedColumn.toUpperCase().toCharArray()[i]) + 1;
			value += powerValue*multiplyValue;
		}

		return value;
	}
}
