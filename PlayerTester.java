import java.util.Arrays;

/**
 * Testing class for Quinto: Player
 * @author Alex McClain
 */
public class PlayerTester {
	public static void main(String[] args)
	{
		testPlayerDraw();
	}
	
	/**
	 * Tests player draw sequence, total number of draws from the pile, and how
	 * many tiles are left for the last draw.
	 */
	private static void testPlayerDraw()
	{
		// Instantiate the draw pile and set the seed for random draw
		DrawPile tiles = new DrawPile();
		final int RAND_SEED = 10;
		tiles.setRandDrawSeed(RAND_SEED);
		
		// Stores the first four expected hands
		int[][] expectedHands = new int[][] {
			{1, 7, 0, 2, 4},
			{8, 8, 1, 0, 1},
			{8, 5, 5, 3, 4},
			{8, 4, 4, 3, 4} };
		int[] playerHand;
		
		// Instantiate player 1 to test the draw sequence
		Player player1 = new Player(tiles, 5);
		
		System.out.print("Testing draw sequence...");
		for (int i = 0; i < expectedHands.length; i++)
		{
			// Check the players current hand
			playerHand = player1.getPlayerHand();
			assert (Arrays.equals(expectedHands[i], playerHand)) :
				"Draw sequence is incorrect.";
			
			// Draw a new hand - this goes after checking the current hand
			// since the constructor draws a fresh hand to start with
			player1.clearPlayerHand();
			player1.drawNewTiles();
		}
		System.out.println("SUCCESS");
		
		// Instantiate player 2 to test emptying the draw pile
		Player player2 = new Player(tiles, 6);
		int expectedP2Draws = 13;
		int expectedP2Empty = 3;
		int cntDraws = 1;
		int cntEmpty = 0;
		
		// Draw tiles until the pile is empty
		while (tiles.numTilesLeft > 0)
		{
			player2.clearPlayerHand();
			player2.drawNewTiles();
			cntDraws++;
		}
		// Test that the number of draws is correct
		System.out.print("Testing full pile draw...");
		assert (cntDraws == expectedP2Draws) : "Wrong number of turns.";
		System.out.println("SUCCESS");
		// Test that the right number of tiles were left for the last draw
		playerHand = player2.getPlayerHand();
		for (int i = 0; i < playerHand.length; i++)
		{
			if (playerHand[i] == -1)
			{
				cntEmpty++;
			}
		}
		System.out.print("Testing number of tiles left for last draw...");
		assert (cntEmpty == expectedP2Empty) : "Wrong number of tiles left.";
		System.out.println("SUCCESS");
	}
}