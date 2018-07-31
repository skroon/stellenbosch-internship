package carcassonne;

import carcassonne.core.*;

public class Main {

	public static void display(Move playerMove, Player playerTurn) {
		System.out.println();
		System.out.printf("player: %d\n\ncard: %s \t dir: %s \t x: %d \t y: %d \t place: %s \n\n", playerTurn.id, playerMove.tile.name, playerMove.tile.dir, playerMove.tile.pos.x, playerMove.tile.pos.y, playerMove.place);
	}

	public static void main(String[] args) {

		/* Initialisation of the game */
	
		Deck deck = new Deck();
		Players players = new Players(3);
		setTile board = new setTile();
		Client client = new Client();
		Score score = new Score();
		Move playerMove;
		Player playerTurn = players.first();
		Tile tile;
		int round = 1;

		/* End of initialisation */
		
		/* Beginning of the game Loop */
		
		while (deck.isEmpty() == false && players.remaining() > 1) {
			System.out.printf("================= ROUND : %d =================%n", round);
			++round;
			tile = deck.drawCard();
			
			if (board.isPlayable(tile) == true) {
				playerMove = client.clientMove(board, tile, playerTurn);
				
				if (board.validMove(playerMove) == true) {
					board.update(playerMove, score);
					display(playerMove, playerTurn);
				} else
					playerTurn.eject();
				playerTurn = players.computeNext(playerTurn); 	
			} else 
				System.out.printf("%s is unplayable\n", tile.name);
		}
		
		score.end(players);
		players.displayScore();
	
		

		for (Player p : players.players) {
				System.out.println(p.meeples.size());
		}
		

		/* End of the game loop */
	}
}
