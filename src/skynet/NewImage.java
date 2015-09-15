package skynet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NewImage extends BufferedImage {
	
	/**For saving images out of arrays**/
	
	public NewImage(int[][][] array) {
		//Some Bitwise math to quench Java's thirst 
		super(array.length,array[0].length,BufferedImage.TYPE_INT_RGB);
		
		for (int x = 0; x < array.length; x++) {
		    for (int y = 0; y < array[x].length; y++) {
		    	try {
					this.setRGB(x, y,(array[x][y][0]<<16)|(array[x][y][1]<<8)|array[x][y][2]);
				} catch (Exception e) {
					//Gotta have that error handling
					System.out.println("WHAT HATH AWT WHROUGHT?!?");
				}
		    }
		}
	}
	
	public void saveTo(String filePathNoExtension){
		//For consistency art the meaning of life
		String extension = "PNG";
		File f = new File(filePathNoExtension+"."+extension.toLowerCase());
		try {
			ImageIO.write(this, extension, f);
		} catch (IOException e) {
			//The User's always gotta know what's up 
			System.out.println("You've been 404'ed!");
		}
	}
	

}
