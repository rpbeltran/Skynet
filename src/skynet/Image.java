package skynet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	public static BufferedImage getImage(String path){
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("IO Error on image read. Call to Image.getImage(path) failed, probably due to invalid path.");
		}
		return null;
		
	}
}
