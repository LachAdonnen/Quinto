import java.util.Arrays;

/**
 * Each instance of this class represents a player in a Quinto game.
 * Each player has a hand and can play/draw tiles on their turn.
 * 
 * @author Alex McClain
 *
 */
public class Player {
	/*
	 * Class variables
	 */
	// Integer array containing the tiles currently in the player's hand
	private int[] tilesInHand;
	// Integer for the player's current score
	public int currentScore;
	// Draw pile for this game
	private DrawPile tiles;

	/*
	 * Constructors
	 */
	/**
	 * Creates a player and populates their hand with random tiles.
	 * @param tiles Draw pile for tiles
	 * @param handSize Max number of tiles in the player's hand
	 */
	public Player(DrawPile tiles, int handSize)
	{
		this.tiles = tiles;
		// Create an empty (-1) hand
		tilesInHand = new int[handSize];
		Arrays.fill(tilesInHand, -1);
		currentScore = 0;
		
		// Draw tiles from the pile
		this.drawNewTiles();
	}
	
	/**
	 * Returns the player's current hand.
	 * @return The players current hand
	 */
	public int[] getPlayerHand()
	{
		return this.tilesInHand;
	}
	
	/**
	 * Empties the player's hand by setting all tiles to -1.
	 */
	public void clearPlayerHand()
	{
		Arrays.fill(tilesInHand, -1);
	}
	
	/**
	 * Draws new tiles for each empty (-1) spot in the player's hand. Quits
	 * early if there are no tiles left in the pile.
	 */
	public void drawNewTiles()
	{
		// New tile value
		int newTile = -1;
		
		// Fill player hand with new tiles
		for (int i = 0; i < tilesInHand.length; i++)
		{
			if (tilesInHand[i] == -1)
			{
				newTile = tiles.drawTile();
				if (newTile == -1)
				{
					break;
				}
				else
				{
					tilesInHand[i] = newTile;
				}
			}
		}
	}
}