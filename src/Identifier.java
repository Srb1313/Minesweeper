import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class Identifier extends GridSymbolBase
{
	private Integer amountOfSurroundingMines = 0;

	public Identifier(GridLocation location)
	{
		super(location);
	}

	public static List<Identifier> getIdentifiers(List<Mine> mines, Grid grid)
	{
		List<Identifier> identifiers = new ArrayList<Identifier>();		
		boolean isMine=false;
		
		// The following for loops retrieve the surrounding mines to the current
		// square. They also identify a square region to narrow down logic
		for (int x = 0; x < grid.getRowsCount(); x++)
		{
			for (int y = 0; y < grid.getColumnsCount(); y++)
			{			
				isMine=false;
				for (Mine mine : mines)
				{
					if (mine.inLocation(new GridLocation(x, y), grid))
					{
						isMine=true;
					}
				}
				if (!isMine)
				{
					Identifier identifier=new Identifier(new GridLocation(x, y));
					identifier.setAmountOfMines(identifier.GetSurroundingMines(grid, mines).size());
					if (identifier.getAmountOfSurroundingMines()>0)
					{
						identifiers.add(identifier);
					}
				}
			}
		}

		return identifiers;
	}

	public Integer getAmountOfSurroundingMines()
	{
		return amountOfSurroundingMines;
	}
	
	public void setAmountOfMines(int minesAmount)
	{
		this.amountOfSurroundingMines=minesAmount;
	}

	//TODO could this be in a base class
	public static boolean containsIdentifier(List<Identifier> identifiers,Identifier identifierToFind,Grid grid)
	{
		for (Identifier identifier : identifiers)
		{
			if (identifierToFind.inLocation(identifier.getLocation(), grid))
			{
				return true;
			}
		}
		return false;
	}
}
