/**
 * Testing class for Quinto: DrawPile
 * @author Alex McClain
 */
public class DrawPileTester {
	public static void main(String[] args)
	{
		// Test pile creation with correct distribution
		testDefaultPile();
		testCustomPile(8, 8);
		testCustomPile(10, 1);
		testCustomPile(2, 50);
		
		// Test draw sequence for a specific seed
		testDrawSequence();
	}
	
	/**
	 * Test the default constructor which should use 10 and 10.
	 */
	private static void testDefaultPile()
	{
		int maxTileValue = 10;
		int numTilesPerValue = 10;
		DrawPile tiles = new DrawPile();
		System.out.print("Testing " + maxTileValue + " x "
				+ numTilesPerValue + "...");
		testAllDraws(tiles, maxTileValue, numTilesPerValue);
		System.out.println("SUCCESS");
	}
	
	/**
	 * Test a single custom value constructor.
	 * @param maxTileValue Max value, exclusive, for each tile
	 * @param numTilesPerValue Number of tiles for each value
	 */
	private static void testCustomPile(int maxTileValue, int numTilesPerValue)
	{
		DrawPile tiles = new DrawPile(maxTileValue, numTilesPerValue);
		System.out.print("Testing " + maxTileValue + " x "
				+ numTilesPerValue + "...");
		testAllDraws(tiles, maxTileValue, numTilesPerValue);
		System.out.println("SUCCESS");
	}
	
	/**
	 * Given a draw pile, verifies the the correct number of tiles are drawn.
	 * @param tiles Draw pile to be tested
	 * @param maxTileValue Max, exclusive, value for each tile
	 * @param numTilesPerValue Number of tiles for each value
	 */
	private static void testAllDraws(DrawPile tiles, int maxTileValue,
			int numTilesPerValue)
	{
		// The count of each tile value drawn
		int[] tilesDrawn = new int[maxTileValue];
		// The current tile
		int currentTile = -1;
		// Total number of tiles drawn
		int numTilesDrawn = 0;
		// Total number of tiles that should be in the pile
		int numTilesAvailable = maxTileValue * numTilesPerValue;
		
		// Draw until we run out of tiles
		do
		{
			// Draw a tile
			currentTile = tiles.drawTile();
			
			// Only process the tile if it's an actual value
			if (currentTile > -1)
			{
				tilesDrawn[currentTile]++;
				numTilesDrawn++;
				assert (numTilesDrawn <= numTilesAvailable) :
					"Too many tiles in the draw pile.";
			}
		} while (currentTile > -1);
		
		// Verify that each value has the right number of tiles
		for (int i = 0; i < maxTileValue; i++)
		{
			assert (tilesDrawn[i] == numTilesPerValue) :
				"Tiles not distributed evenly.";
		}
	}
	
	/**
	 * Tests the draw sequence for a specific random seed.
	 */
	private static void testDrawSequence()
	{
		DrawPile tiles = new DrawPile();
		final int RAND_SEED = 10;
		tiles.setRandDrawSeed(RAND_SEED);
		// The expected sequence of draws
		int[] expectedValues = new int[] { 1, 7, 0, 2, 4, 8, 8, 1, 0, 1, 8,
				5, 5, 3, 4, 8, 4, 4, 3, 4 };
		
		// Check first 20 draws for the given seed
		System.out.print("Testing draw sequence...");
		for (int i = 0; i < expectedValues.length; i++)
		{
			assert (expectedValues[i] == tiles.drawTile()) :
				"Draw sequence is incorrect.";
		}
		System.out.println("SUCCESS");
	}
}