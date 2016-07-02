import java.util.ArrayList;
import java.util.Random;

/**
 * Each instance of this class represents the draw pile in a Quinto game.
 * There are a limited number of each tile available in the pile.
 * Random tiles can be drawn from the pile.
 * 
 * @author Alex McClain
 *
 */
public class DrawPile {
	/*
	 * Class variables
	 */
	// Stores all of the tiles left available
	private ArrayList<Integer> tiles;
	// Number of tiles left to be drawn
	public int numTilesLeft;
	// Generates random numbers for drawing tiles
	private final Random randGen = new Random();
	
	/*
	 * Constructors
	 */
	/**
	 * Fills the draw pile with a uniform distribution of tiles.
	 * @param maxTileValue Upper limit, exclusive, for the value on each tile
	 * @param numTilesPerValue Number of tiles for each tile value
	 */
	public DrawPile(int maxTileValue, int numTilesPerValue)
	{
		// Instantiate the draw pile with the total number of tiles
		numTilesLeft = maxTileValue * numTilesPerValue;
		tiles = new ArrayList<Integer>(numTilesLeft);
		
		// Populate the pile with an equal number of each value
		for (int i = 0; i < maxTileValue; i++)
		{
			for (int j = 1; j <= numTilesPerValue; j++)
			{
				tiles.add(new Integer(i));
			}
		}
	}
	
	/**
	 * Fills the draw pile for the default game; 10 tiles each using 0-9.
	 */
	public DrawPile()
	{
		this(10, 10);
	}
	
	/**
	 * Overrides the current seed for the random draw.
	 * @param seed Seed value for the random draw
	 */
	public void setRandDrawSeed(int seed)
	{
		randGen.setSeed(seed);
	}
	
	/**
	 * Retrieve a random tile from the pile and return it's value.
	 * @return A random tile value from the pile, or -1 if the pile is empty
	 */
	public int drawTile()
	{
		int tilesLeft = tiles.size();
		
		// If there are no tiles left, return an error
		if (tilesLeft == 0)
		{
			return -1;
		}
		
		int drawnTileIndex = randGen.nextInt(tilesLeft);
		int drawnTileValue = tiles.get(drawnTileIndex);
		tiles.remove(drawnTileIndex);
		numTilesLeft--;
		return drawnTileValue;
	}
}



























































