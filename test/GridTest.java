import static org.junit.Assert.*;

import org.junit.Test;


public class GridTest
{
	@Test
	public void testGetColumnIdentifierWithNumbersPlusTwentySix()
	{
		Grid grid= new Grid(10,10);
		assertEquals("BA",grid.convertColumnIdentifierToString(53));
	}
	
	@Test
	public void testGetColumnIdentifierWithNumbersPlusSevevOhTwo()
	{
		Grid grid= new Grid(10,10);
		assertEquals("AAA",grid.convertColumnIdentifierToString(703));
	}
	
	@Test
	public void testGetColumnIdentifierWithNumbersPlusSevevOhTwoLarge()
	{
		Grid grid= new Grid(10,10);
		assertEquals("LHA",grid.convertColumnIdentifierToString(8321));
	}
	
	@Test
	public void testGetColumnIdentifierWithMultipleOfTwentySix()
	{
		Grid grid= new Grid(10,10);
		assertEquals("IZ",grid.convertColumnIdentifierToString(260));
	}		
	
	@Test
	public void testGetColumnIdentifierWithNUmbersLowerThanTwentySix()
	{
		Grid grid= new Grid(10,10);
		assertEquals("E",grid.convertColumnIdentifierToString(5));
	}
	
	@Test
	public void testGetColumnIntFromCharacters()
	{
		Grid grid= new Grid(10,10);
		assertEquals(2056,grid.convertColumnIdentifierToInt("CAB"));
	}
	
	@Test
	public void testGetColumnIntFromCharactersSmall()
	{
		Grid grid= new Grid(10,10);
		assertEquals(78,grid.convertColumnIdentifierToInt("BZ"));
	}
	
	@Test
	public void testGetColumnIntFromCharactersSmallest()
	{
		Grid grid= new Grid(10,10);
		assertEquals(5,grid.convertColumnIdentifierToInt("E"));
	}
}
