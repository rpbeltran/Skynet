package skynet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class Camera {
	
	public static String captureTo(String fileNameNoExtension) {
		BufferedImage image = capture();
		try {
			ImageIO.write(image, "PNG", new File(fileNameNoExtension+".png"));
			return fileNameNoExtension+".png";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage capture() {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		BufferedImage image =  webcam.getImage();
		webcam.close();
		return image;
	}
	
}
