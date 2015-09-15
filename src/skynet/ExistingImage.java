package skynet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ExistingImage {
	/** For manipulating saved images**/
	private BufferedImage buffered;
	private int width, height;
	
	public ExistingImage(String path){
		//Does nothing, in hindsight
		buffered = getImage(path);
		width  = buffered.getWidth();
		height = buffered.getHeight();
	}
	
	protected int[] getRGBat(int x, int y){
		//Some bitwise math to appease Java's hunger
		int dec = buffered.getRGB(x, y);
		return new int[]{ (dec>>16)&0x000000FF, (dec>>8)&0x000000FF, dec&0x000000FF};	
	}
	
	public int[][][] getRGBArray() {
		
		int rgbArray[][][] = new int[width][height][3];
		
		for (int x = 0; x < width; x++) {
			
		    for (int y = 0; y < height; y++) {
		    	
		    	int[] rgb = getRGBat(x, y);
		    	
		    	rgbArray[x][y][0] = rgb[0];
		    	rgbArray[x][y][1] = rgb[1];
		    	rgbArray[x][y][2] = rgb[2];
		    	
		    }
		    
		}
		
		return rgbArray;
		
	}
	
	protected BufferedImage getImage(String path){
		//protect this method at all costs
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("IO Error on image read. Call to Image.getImage(path) failed, probably due to invalid path.");
		}
		return null;
	}
	
	public int getWidth(){
		//The client's wish iseth my command 
		return width;
	}
	
	public int getHeight(){
		//This one's really a game-changer.
		return height;
	}
	
	public int getActualHeight(){
		/**Deprecated, but never depreciated**/
		//Deprecated by design. Gives the class a nice timeless feel.
		return height;
	}
}
