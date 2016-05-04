import java.util.ArrayList;
import java.util.List;

public abstract class GridSymbolBase
{
	private GridLocation location= new GridLocation(0,0);
	
	public GridSymbolBase(GridLocation location)
	{
		this.location=location;
	}
	
	public GridLocation getLocation()
	{
		return location;
	}
	
	/*
	 * Is mine in provided location 
	 */
	public boolean inLocation(GridLocation location, Grid grid)
	{
		if (location.getxLocation()>=0 && location.getxLocation()<grid.getRowsCount())
		{
			if (location.getyLocation()>=0&&location.getyLocation()<grid.getColumnsCount())
			{
				if (this.getLocation().getxLocation() == location.getxLocation() && this.getLocation().getyLocation() == location.getyLocation())
				{
					return true;
				}
			}
		}
		return false;
	}	
	
	//TODO refactor all below here
	public List<Mine> GetSurroundingMines(Grid grid, List<Mine> symbols)
	{
		List<Mine> returnSymbols=new ArrayList<Mine>();
		int xLoc=this.getLocation().getxLocation();
		int yLoc=this.getLocation().getyLocation();
		for (Mine symbol : symbols)
		{
			if (symbol.inLocation(new GridLocation(xLoc, yLoc-1), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc-1), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc+1), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc, yLoc+1), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc+1), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc), grid))
			{
				returnSymbols.add(symbol);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc-1), grid))
			{
				returnSymbols.add(symbol);
			}
		}
		return returnSymbols;
	}
	
	//TODO this isnt dry! How to make this method and the one above the same thing
	public List<Identifier> GetSurroundingIdentifiers(Grid grid, Identifier currentIdentifier, List<Identifier> allIdentifiers, List<Identifier> searchedSymbols)
	{
		int xLoc=currentIdentifier.getLocation().getxLocation();
		int yLoc=currentIdentifier.getLocation().getyLocation();
		for (Identifier symbol : allIdentifiers)
		{
			if (symbol.inLocation(new GridLocation(xLoc, yLoc-1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc-1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc-1, yLoc+1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc, yLoc+1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc+1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
			if (symbol.inLocation(new GridLocation(xLoc+1, yLoc-1), grid))
			{
				addToIdentifiers(grid,symbol,allIdentifiers,searchedSymbols);
			}
		}
		return searchedSymbols;
	}

	private void addToIdentifiers(Grid grid,Identifier nextIdentifier, List<Identifier> allIdentifiers,List<Identifier> searchedSymbols)
	{
		if (!Identifier.containsIdentifier(searchedSymbols, nextIdentifier, grid))
		{
			searchedSymbols.add(nextIdentifier);
			GetSurroundingIdentifiers(grid, nextIdentifier, allIdentifiers, searchedSymbols);	
		}
	}
	
	//TODO this isnt dry! How to make this method and the one above the same thing
		public List<Identifier> GetSurroundingIdentifiers(Grid grid, Identifier currentIdentifier, List<Identifier> allIdentifiers)
		{
			return GetSurroundingIdentifiers(grid, currentIdentifier, allIdentifiers,new ArrayList<Identifier>());
		}
}
