import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mine extends GridSymbolBase
{
	public Mine(GridLocation location)
	{
		super(location);
	}

	/*
	 * Returns a list of mines each with a random location
	 */
	public static List<Mine> getRandomMineLocations(int minesAmount, Grid grid)
	{
		List<Mine> mineLocations = new ArrayList<Mine>();
		List<GridLocation> locations = grid.getGridSquareLocations();

		// For every mine wanted shuffle locations and retrieve the first in the
		// list
		for (int i = 0; i < minesAmount; i++)
		{
			Collections.shuffle(locations);
			if (!locations.isEmpty())
			{
				mineLocations.add(new Mine(locations.get(0)));

				// Remove the first in the list so it cant be chosen again
				locations.remove(locations.get(0));
			}
		}

		return mineLocations;
	}
}
