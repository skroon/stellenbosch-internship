package carcassonne.core.tiles;

import carcassonne.core.Tile;
import carcassonne.core.Node;
import carcassonne.core.Position;
import carcassonne.core.Direction;

//20. road connected N to S (8)

public class RoadStraight extends Tile {
	private final String CARDS_CONTENT[] = {"PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","PLAIN","ROAD","PLAIN","PLAIN","PLAIN","PLAIN","ROAD"};

	public RoadStraight () {
		super(Direction.NORTH, new Position(0, 0));
		this.id = 20;
		this.name = "ROAD_STRAIGHT";

		for (int i = 0; i < Direction.NUMBER_OF_DIRECTIONS; ++i) 
			this.nodes[i] = new Node(CARDS_CONTENT[i], this);

		this.nodes[2].connection(this.nodes[3]);
		this.nodes[3].connection(this.nodes[4]);
		this.nodes[4].connection(this.nodes[5]);
		this.nodes[5].connection(this.nodes[6]);
		this.nodes[8].connection(this.nodes[9]);
		this.nodes[9].connection(this.nodes[10]);
		this.nodes[10].connection(this.nodes[11]);
		this.nodes[11].connection(this.nodes[0]);
		this.nodes[1].connection(this.nodes[12]);
		this.nodes[12].connection(this.nodes[7]);
	}
}
