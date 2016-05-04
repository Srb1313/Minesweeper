import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class GameManager
{
	private List<Mine> mines=new ArrayList<Mine>();
	private List<Identifier> identifiers=new ArrayList<Identifier>();
	private Grid grid=new Grid(0,0);
	private List<GridLocation> hitSquares=new ArrayList<GridLocation>();
	
	GameManager()
	{
		
	}
	
	public void InitializeGame(Scanner scanner)
	{
		System.out.println("Please enter your desired amount of rows: ");
		int desiredRows = scanner.nextInt();	
		System.out.println("Please enter your desired amount of columns: ");
		int desiredColumns = scanner.nextInt();
		System.out.println("Please enter your desired amount of mines: ");
		int minesAmount = scanner.nextInt();

		grid=new Grid(desiredRows, desiredColumns);
		grid.CreateStartGrid();
		
		mines=Mine.getRandomMineLocations(minesAmount, grid);
		identifiers=Identifier.getIdentifiers(mines, grid);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          		
	}
	
	public void playerTurn(Scanner scanner)
	{
		System.out.println("\nPlease enter a location int the following format. [X,Y] e.g. e,7");
		String location=scanner.next();
		showMidGameGrid(grid.getLocationFromString(location));
	}
	
	private void showMidGameGrid(GridLocation gridLocation)
	{
		hitSquares.add(gridLocation);
		boolean isIdentifier=false;
		List<Identifier> surroundingIdentifiers=new ArrayList<Identifier>();
		
		for (Identifier identifier: identifiers)
		{
			if (identifier.inLocation(gridLocation, grid))
			{
				surroundingIdentifiers=identifier.GetSurroundingIdentifiers(grid,identifier,identifiers);
				isIdentifier=true;
				break;
			}
		}
		if (isIdentifier)
		{
			List<GridLocation> identifiersLocationsToAdd=new ArrayList<GridLocation>();
			boolean inLocation=false;
			for (Identifier identifier : surroundingIdentifiers)
			{
				inLocation=false;
				HitLocations:
				for (GridLocation loc : hitSquares)
				{
					if (identifier.inLocation(loc, grid))
					{
						inLocation=true;
						break HitLocations;
					}
				}
				if (!inLocation)
				{
					identifiersLocationsToAdd.add(identifier.getLocation());
				}
				hitSquares.addAll(identifiersLocationsToAdd);
			}
		}
		
		grid.CreateMidGameGrid(hitSquares, mines, identifiers);
	}
	
	public void showCompleteGrid()
	{
		grid.CreateFinalGrid(identifiers, mines);
	}
}
