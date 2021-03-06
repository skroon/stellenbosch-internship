package carcassonne.ui;

import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.net.URL;

public class spriteStore {
	public static final int NUMBER_OF_TILE_SPRITES = 24;
	public static final int NUMBER_OF_MEEPLE_SPRITES = 5;
	public static final String TILE_LOCATIONS = "../../../resources/main/carcassonne/tiles/tile";
	public static final String MEEPLE_LOCATIONS = "../../../resources/main/carcassonne/meeples/meeple";
	
	public ArrayList<Sprite> sprites;

	public spriteStore() {
        	sprites = new ArrayList<Sprite>();
    	}

	public void loadTiles() {
		for (int i = 0; i < NUMBER_OF_TILE_SPRITES; ++i)
			loadSprite(TILE_LOCATIONS + i + ".jpg");
	}
	
	public void loadMeeples() {
		for (int i = 0; i < NUMBER_OF_MEEPLE_SPRITES; ++i)
			loadSprite(MEEPLE_LOCATIONS + i + ".png");
	}

    	public boolean loadSprite(String ref) {
       		BufferedImage sourceImage = null;
        	try {
           		sourceImage = ImageIO.read(new File(ref));
        	} catch (IOException e) {
            		System.out.println("Failed to load image " + ref);
            		e.printStackTrace();
            		return false;
        	}
		Sprite sprite = new Sprite(sourceImage);
       		sprites.add(sprite);
        	return true;
	}
}
