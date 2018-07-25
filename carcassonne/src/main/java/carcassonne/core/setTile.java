package carcassonne.core;
import java.util.ArrayList;

import carcassonne.core.tiles.RoadStraightCity;

public class setTile {
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();

	public setTile() {
		tiles.add(new RoadStraightCity()); 
	}

	public Tile findTile(Position pos) {
		for (Tile t : this.tiles) {
			if (t.pos.x == pos.x && t.pos.y == pos.y)	
				return t;
		}
		return null;
	}

	public Tile computeNeighbour(Tile t, directionId dir) {
		Position pos = t.pos.neighbourPosition(dir);
		return findTile(pos);
	}
	
	public void addSetTile(Tile t) {
		tiles.add(t);
		for (directionId dir : directionId.values()) {
			t.connection(computeNeighbour(t, dir), dir);
			for (int j = Node.CONNEXIONS_PER_SIDE * dir.toInt(); j < Node.CONNEXIONS_PER_SIDE * (dir.toInt() + 1); ++j) {
				if (t.neighbour(dir) != null)
					t.nodes[j].nodeConnection(t.neighbour(dir).nodes[(Place.getOppositePlace(j))]);
			}	
		}
	}

	public void addMeeple(Move m) {
		if (m.place != placeId.NO_MEEPLE) {
			m.tile.nodes[m.place.toInt()].meepleOwner = m.player;
		}
	}

	public void removeSetTile(Tile t) {
		for (directionId dir : directionId.values()) {
			for (int j = Node.CONNEXIONS_PER_SIDE * dir.toInt(); j < Node.CONNEXIONS_PER_SIDE * (dir.toInt() + 1); ++j) {
				if (t.neighbour(dir) != null)
					t.nodes[j].nodeDisconnection(t.neighbour(dir).nodes[(Place.getOppositePlace(j))]);
			}	
			t.disconnection(computeNeighbour(t, dir), dir);
		}
		tiles.remove(t);
	}

	public boolean matchCard(Tile t) {
		int count = 0;
		for (directionId dir : directionId.values()) {
			if (t.matchSide(computeNeighbour(t, dir), dir) == true) {
				++count;
			}
		}
		if (count == t.SIDES)
			return true;
		return false;
	}
	
	public boolean isConnectable(Tile t) {
		directionId rot = directionId.WEST;
		for (directionId dir : directionId.values()) {
			if (matchCard(t) == true) {
				t.dir = dir;
				return true;	
			}
			t.rotation(rot);
		}
		return false;
	}
	
	public boolean isPlayable(Tile t) {   
		for (int i = 0; i < tiles.size(); ++i) {
			for (directionId dir : directionId.values()) {
				t.pos = tiles.get(i).pos.neighbourPosition(dir);
				if (t.isEmptyTile(computeNeighbour(tiles.get(i), dir)) == true && isConnectable(t) == true) {
					t.reInit();
					return true;
				}	
			}
		}
		return false;
	}

	public boolean validCardMove(Move m) {
		if (matchCard(m.tile) == true)
			return true;
		return false;
	}

	public boolean validMeepleMove(Move m) {
		//TODO
		return true;
	}

	public boolean validMove(Move m) { 
		if (validCardMove(m) == true && validMeepleMove(m) == true) 
			return true;
		return false;
	}
	
	public static void main (String[] args) {
		/*
		setTile s = new setTile();	
		Position pos1 = new Position(0, 0);
		Position pos2 = s.neighbourPosition(pos1, directionId.NORTH);
		System.out.printf("%d %d\n%d %d\n", pos1.x, pos1.y, pos2.x, pos2.y);

		Tile t1 = new RoadStraightCity();
		Tile t2 = new MonasteryRoad();
		t1.pos = new Position(0, 1);
		t2.pos = new Position(1, 0);
		s.tiles.add(t1);
		s.tiles.add(t2);
		System.out.println(s.computeNeighbour(s, s.tiles.get(0), directionId.EAST));
		System.out.printf("%d %d\n", t1.pos.x, t1.pos.y);*/

		/*
		setTile s = new setTile();
		MonasteryRoad c = new MonasteryRoad();
		c.pos = new Position(0, 1);
		c.dir = directionId.NORTH;
		
		Move m = new Move(0, c, Place.NO_MEEPLE);
		System.out.println(s.validMove(m));*/
	}
}
